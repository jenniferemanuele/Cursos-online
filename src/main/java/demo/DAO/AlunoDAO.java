package demo.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import demo.ententidades.Aluno;

public class AlunoDAO {
    public static Object cadastrarAluno;
    private static String jdbcURL;
    private static String jdbcUsername;
    private static String jdbcPassword;
    private static Connection jdbcConnection;

    public AlunoDAO(String jdbcURL, String jdbcUsername, String jdbcConnection) {
        this.jdbcURL = "jdbc:postgresql://localhost:3333/northwind";
        this.jdbcUsername = "postgres";
        this.jdbcPassword = "ihatethis19";
    }

    public static void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        }
    }

    public static void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }

    public static void cadastrarAluno(Aluno aluno) throws SQLException {
        connect();
        try {

            String query = "INSERT INTO aluno (nome_aluno, email_aluno, cursos_matriculados) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = jdbcConnection.prepareStatement(query)) {
                preparedStatement.setString(1, aluno.getNome());
                preparedStatement.setString(2, aluno.getEmail());
                preparedStatement.setString(3, aluno.getCurso());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    public void atualizarAluno(Aluno aluno) throws SQLException {
        connect();
        try {
            String query = "UPDATE nome_aluno SET nome_aluno=?, email_aluno=?, cursos_matriculados=?";
            try (PreparedStatement preparedStatement = jdbcConnection.prepareStatement(query)) {
                preparedStatement.setString(1, aluno.getNome());
                preparedStatement.setString(2, aluno.getEmail());
                preparedStatement.setString(3, aluno.getCursosMatriculados());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            disconnect();
        }
    }

    public List<Aluno> listarAluno() throws SQLException {
        connect();
        List<Aluno> alunosList = new ArrayList<>();

        try {
            String query = "SELECT * FROM nome_aluno";
            try (PreparedStatement preparedStatement = jdbcConnection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Aluno aluno = new Aluno(
                                resultSet.getString("nome_aluno"),
                                resultSet.getString("email_aluno"),
                                resultSet.getString("cursos_matriculados"));

                        alunosList.add(aluno);
                    }
                }
            }
        } finally {
            disconnect();
        }

        return alunosList;
    }

    public void matricularAlunoEmCurso(Aluno aluno, String curso) throws SQLException {
      
        connect();

        try {
            String query = "INSERT INTO cursos (id_curso, nome_curso) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = jdbcConnection.prepareStatement(query)) {
                preparedStatement.setString(1, aluno.getNome());
                preparedStatement.setString(2, curso);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    public void cancelarMatriculaAluno(Aluno aluno, String curso) throws SQLException {
        connect();
        try {
            String query = "DELETE FROM cursos WHERE id_curso = ? AND nome_curso = ?";
            try (PreparedStatement preparedStatement = jdbcConnection.prepareStatement(query)) {
                preparedStatement.setString(1, aluno.getNome());
                preparedStatement.setString(2, curso);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }
 public static void gerarRelatorioDesempenho(Aluno aluno) throws SQLException {
        connect();
        try {
            String query = "SELECT nome_aluno, matricula.id_curso, notas.nota " +
                    "FROM aluno " +
                    "JOIN matricula ON aluno.nome_aluno = matricula.id_aluno " +
                    "LEFT JOIN notas ON matricula.id_curso = notas.id_curso " +
                    "AND aluno.nome_aluno = notas.id_aluno";

            try (PreparedStatement preparedStatement = jdbcConnection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String nomeAluno = resultSet.getString("nome_aluno");
                        String nomeCurso = resultSet.getString("id_curso");
                        int nota = resultSet.getInt("nota");

                        System.out.print("Aluno: " + nomeAluno + " | Curso: " + nomeCurso + " | Nota: " + nota);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }
}