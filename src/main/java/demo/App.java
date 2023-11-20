package demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
            Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:3333/northwind" ,
             "postgres", "ihatethis19");
             if(conexao != null){
                System.out.println("banco de dados conectado");
             }else{
                System.out.println("conexão falhou :( ");
             }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
