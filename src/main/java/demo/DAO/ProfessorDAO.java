package demo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import demo.Professor;

public class ProfessorDAO {
  private String jdbcURL;
  private String jdbcUsername;
  private String jdbcpassword;
  private Connection jdbcConnection;

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
        preparedStatement.setLong(3,Professor.getCargaHoraria());
      } catch(SQLException e){
        e.printStackTrace();
        }
    } finally {
        disconnect();
    }
  }
}
