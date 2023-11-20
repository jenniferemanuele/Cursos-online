package demo.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDAO {

    public static void main(String[] args) {
        ConexaoDAO conexaoDAO = new ConexaoDAO();
        conexaoDAO.testarConexao();
    }

    public void testarConexao() {
        try {
            Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:3333/northwind",
                    "postgres", "ihatethis19");

            if (conexao != null) {
                System.out.println("Conexão bem-sucedida!");
            } else {
                System.out.println("Conexão falhou :(");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}




    
