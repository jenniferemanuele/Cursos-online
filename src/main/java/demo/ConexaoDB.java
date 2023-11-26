package demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {
    private Connection conexao;

    public ConexaoDB() {
        try {
            conexao = DriverManager.getConnection("jdbc:postgresql://localhost:3333/northwind",
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

    public Connection retonarConexao() {
        return conexao;
    }
}




    
