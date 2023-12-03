package demo.DAO.implementacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import demo.ConexaoDB;
import demo.DAO.DaoALunoIterface;
import demo.entidades.Aluno;

public class AlunoDAOimplementacao implements DaoALunoIterface {
    public static Object cadastrarAluno;
    private Connection conexaoDB;

    public AlunoDAOimplementacao(Connection conexaoDB) {
        this.conexaoDB = conexaoDB;
    }

    public void cadastrarAluno(Aluno aluno) throws SQLException {     
        String query = "INSERT INTO aluno (nome_aluno, email_aluno) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = conexaoDB.prepareStatement(query)) {
            preparedStatement.setString(1, aluno.getNome());
            preparedStatement.setString(2, aluno.getEmail());
            // preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarAluno(Aluno aluno) throws SQLException {
        String query = "UPDATE aluno SET nome_aluno=?, email_aluno=?, cursos_matriculados=?";
        try (PreparedStatement preparedStatement = conexaoDB.prepareStatement(query)) {
            preparedStatement.setString(1, aluno.getNome());
            preparedStatement.setString(2, aluno.getEmail());
            preparedStatement.setString(3, aluno.getCursosMatriculados());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Aluno> listarAluno() {
        List<Aluno> alunosList = new ArrayList<>();

        String query = "SELECT * FROM aluno";
        try (PreparedStatement preparedStatement = conexaoDB.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Aluno aluno = new Aluno(
                            resultSet.getInt("id"), // Assuming there's an 'id' column in your database
                            resultSet.getString("nome_aluno"),
                            resultSet.getString("email_aluno"),
                            resultSet.getString("senha") // Assuming there's a 'senha' column in your database
                    );

                    alunosList.add(aluno);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return alunosList;
    }

    public void matricularAlunoEmCurso(Aluno aluno, String curso) throws SQLException {

            String query = "INSERT INTO cursos (id_curso, nome_curso) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = conexaoDB.prepareStatement(query)) {
                preparedStatement.setString(1, aluno.getNome());
                preparedStatement.setString(2, curso);
                preparedStatement.executeUpdate();
            }
    }

    public void cancelarMatriculaAluno(Aluno aluno, String curso) throws SQLException {

            String query = "DELETE FROM cursos WHERE id_curso = ? AND nome_curso = ?";
            try (PreparedStatement preparedStatement = conexaoDB.prepareStatement(query)) {
                preparedStatement.setString(1, aluno.getNome());
                preparedStatement.setString(2, curso);
                preparedStatement.executeUpdate();
            }
    }

public void gerarRelatorioDesempenho(Aluno aluno) throws SQLException {

    String query = "SELECT nome_aluno, matricula.id_curso, notas.nota " +
            "FROM aluno " +
            "JOIN matricula ON aluno.nome_aluno = matricula.id_aluno " +
            "LEFT JOIN notas ON matricula.id_curso = notas.id_curso " +
            "AND aluno.nome_aluno = notas.id_aluno";

    try (PreparedStatement preparedStatement = conexaoDB.prepareStatement(query)) {
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                String nomeAluno = resultSet.getString("nome_aluno");
                String nomeCurso = resultSet.getString("id_curso");
                int nota = resultSet.getInt("nota");

                System.out.print("Aluno: " + nomeAluno + " | Curso: " + nomeCurso + " | Nota: " + nota);
            }
        }
    }
}
public Aluno autenticar(String email, String senha) throws SQLException {
    
    // Consultar o registro do aluno na tabela
    String sql = "SELECT * FROM aluno WHERE email = ? AND senha = ?";
    PreparedStatement stmt = ConexaoDB.prepareStatement(sql);
    stmt.setString(1, email);
    stmt.setString(2, senha);
    ResultSet rs = stmt.executeQuery();

    // Se o registro for encontrado, retornar a entidade do aluno
    if (rs.next()) {
        int id = rs.getInt("id");
        String nome = rs.getString("nome");
        return new Aluno(id, nome, email, senha);
    }

    // Caso contrário, retornar null
    return null;
}

@Override
public int cadastrarAluno(Object entidade) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'cadastrarAluno'");
}

@Override
public List listarAlunos() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'listarAlunos'");
} 
}