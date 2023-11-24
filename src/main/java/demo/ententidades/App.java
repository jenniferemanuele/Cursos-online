package demo.ententidades;

import java.sql.SQLException;

import demo.DAO.AlunoDAO;
import demo.DAO.CursosDAO;
import demo.DAO.ProfessorDAO;

public class App{
    public static void main(String[] args){
        try{
            AlunoDAO alunoDao = new AlunoDAO("jdbc:postgresql://localhost:3333/northwind", "postgres", "ihatethis19");
            ProfessorDAO professorDAO = new ProfessorDAO("jdbc:postgresql://localhost:3333/northwind", "postgres", "ihatethis19");
            CursosDAO cursosDAO = new CursosDAO("jdbc:postgresql://localhost:3333/northwind", "postgres", "ihatethis19");


            Aluno aluno = new Aluno("sales", "sales@gmail.com", "ADS");
            AlunoDAO.cadastrarAluno(aluno);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{}
      
    }
}