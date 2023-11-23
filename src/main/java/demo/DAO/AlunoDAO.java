package demo.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import demo.Cursos;

public class AlunoDAO {
   private String jdbcURL;
   private String jdbcUsername;
   private String jdbcPassword;
   private Connection jdbcConnection;

   public AlunoDAO(String jdbcURL, String jdbcUsername, String jdbcConnection){
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
         jdbcConnection = DriveManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
     }
}

   public void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }

   public void cadastrarAluno(Aluno aluno) throws SQLException {
        connect();
        try {
            String query = "ÏNSERT INTO aluno (nome, ID, email, curso) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = jdbcConnection.prepareStatement(query)) {
                preparedStatement.setString(1, aluno.getNome());
                preparedStatement.setString(2, aluno.getID());
                preparedStatement.setString(3, aluno.getEmail());
                preparedStatement.setString(4, aluno.getCurso());
            } 

        }
   }
   }
}