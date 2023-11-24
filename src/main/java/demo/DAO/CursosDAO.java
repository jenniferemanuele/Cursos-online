package demo.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import demo.ententidades.Cursos;

public class CursosDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;    
    private Connection jdbcConnection;

    public CursosDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = "jdbc:postgresql://localhost:3333/northwind";
        this.jdbcUsername = "postgres";
        this.jdbcPassword = "ihatethis19";
    }

    public void connect() throws SQLException {
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

    public void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close(); 
        }
}

    public void cadastrarCurso(Cursos curso) throws SQLException {
        connect();
        try {
            String query = "INSERT INTO cursos (nome, status, cargaHoraria) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = jdbcConnection.prepareStatement(query)) {
                preparedStatement.setString(1, curso.getNome());
                preparedStatement.setString(2, curso.getStatus());
                preparedStatement.setInt(3, curso.getCargaHoraria());
                preparedStatement.executeUpdate();
            } catch(SQLException e){
                e.printStackTrace();
                }
        } finally {
            disconnect();
        }
    }
    
    public void atualizarCurso(Cursos curso) throws SQLException{
        connect();
        try{
            String query = "UPDATE cursos SET nome=?, status=?, cargaHoraria=? WHERE id=?";
            try (PreparedStatement preparedStatement = jdbcConnection.prepareStatement(query)){
                preparedStatement.setString(1, curso.getNome());
                preparedStatement.setString(2, curso.getStatus());
                preparedStatement.setInt(3, curso.getCargaHoraria());
                preparedStatement.setInt(4, curso.getID());
                preparedStatement.executeUpdate();
            } catch(SQLException e){
                e.printStackTrace();
            }
        } finally {
            disconnect();
        }
    }

    public List<Cursos> listarCursos() throws SQLException {
    connect();
    List<Cursos> cursosList = new ArrayList<>();

    try {
        String query = "SELECT * FROM cursos";
        try (PreparedStatement preparedStatement = jdbcConnection.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                        Cursos curso = new Cursos(
                        resultSet.getString("nome"),
                        resultSet.getString("status"),
                        resultSet.getInt("cargaHoraria")
                    );
 
                    cursosList.add(curso);
                    }
                }
            }
        } finally {
            disconnect();
        }

        return cursosList;
    }

    public double registrarNota(int alunoId, int cursoId) throws SQLException{
        connect();
        try {
            String query = "SELECT nota FROM notas WHERE aluno_id=? AND curso_id=?";
            try (PreparedStatement preparedStatement = jdbcConnection.prepareStatement(query)){
                preparedStatement.setInt(1, alunoId);
                preparedStatement.setInt(2, cursoId);
                try (ResultSet resultSet = preparedStatement.executeQuery()){
                    if (resultSet.next()){
                        return resultSet.getDouble("nota");
                    } else{
                        return 0;
                    }
                }
            }
        } finally {
            disconnect();
        }
    }

    public double calcularMediaNotas(int cursoId) throws SQLException{
        connect();
        try {
            String query = "SELECT AVG(nota) AS media FROM notas WHERE curso_id=?";
            try (PreparedStatement preparedStatement = jdbcConnection.prepareStatement(query)){
                preparedStatement.setInt(1, cursoId);
                try (ResultSet resultSet = preparedStatement.executeQuery()){
                    if (resultSet.next()){
                        return resultSet.getDouble("media");
                    } else {
                        return 0;
                    }
                }
            }
        } finally {
            disconnect();
        }
    }
}
