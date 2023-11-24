package demo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import demo.ententidades.Professor;

public class ProfessorDAO {
  private String jdbcURL;
  private String jdbcUsername;
  private String jdbcpassword;
  private Connection jdbcConnection;
  private PreparedStatement preparedStatement;

  public ProfessorDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
    this.jdbcURL = "jdbc:postresql://localhost:333/northwind";
    this.jdbcUsername = "postgres";
    this.jdbcpassword = "ihatethis19";
  }

  public void connect() throws SQLException {
    if (jdbcConnection == null || jdbcConnection.isClosed()) {
      try {
        Class.forName("org.postgresql.Driver");
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
        throw new SQLException(e);
      }
    }
  }

  public void disconnect() throws SQLException {
    if (jdbcConnection != null && !jdbcConnection.isClosed()) {
      jdbcConnection.close();
    }
  }

    public void cadastrarProfessor(Professor professores) throws SQLException {
      connect();  
      try{
        String query = "INSERT INTO professor (Nome, Email, cursoMinistrado) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = jdbcConnection.prepareStatement(query)){
          preparedStatement.setString(1, Professor.getNome());
          preparedStatement.setString(2, Professor.getEmail());
          preparedStatement.setString(3, Professor.getcursoMinistrado());

        } catch(SQLException e){
          e.printStackTrace();
        }
    } finally {
        disconnect();             
    }
  }

    public void atualizarProfessores(Professor professor) throws SQLException{
      connect();
      try{
      String query ="UPDATE professor SET (nome, email, cursosMinistrados VALUE (?, ?, ?)";
     try (PreparedStatement preparedStatement = jdbcConnection.prepareStatement(query)){
      preparedStatement.setString(1, Professor.getNome());
      preparedStatement.setString(2, Professor.getEmail());
      preparedStatement.setString(3, Professor.getcursoMinistrado());
      preparedStatement.executeUpdate();
      } catch(SQLException e){
        e.printStackTrace();
      }
    } finally {
      disconnect();
    }
  }
    public List<Professor> listarProfessor() throws SQLException {
      connect();
      List<Professor> professorList = new ArrayList<>();

      try{
        String query = "SELECT * FROM professor";
        try (PreparedStatement preparedStatement = jdbcConnection.prepareStatement(query)){
          try (ResultSet resultSet = preparedStatement.executeQuery()){
            while (resultSet.next()){
              Professor professor = new Professor (
                resultSet.getString("nomeProfessor"),
                resultSet.getString("emailProfessor"),
                resultSet.getString("cursosMinistrados")
              );
              professorList.add(professor);
              }
            }
          }
        } finally {
          disconnect();
        }
        return professorList;
      }
    }






