package demo.ententidades;

import java.sql.SQLException;

import demo.DAO.AlunoDAO;
import demo.DAO.CursosDAO;
import demo.DAO.ProfessorDAO;

public class App {
    public static void main(String[] args) {
        try {
            // Instanciar os DAOs
            AlunoDAO alunoDao = new AlunoDAO("jdbc:postgresql://localhost:3333/northwind", "postgres", "ihatethis19");
            ProfessorDAO professorDAO = new ProfessorDAO("jdbc:postgresql://localhost:3333/northwind", "postgres", "ihatethis19");
            CursosDAO cursosDAO = new CursosDAO("jdbc:postgresql://localhost:3333/northwind", "postgres", "ihatethis19");

            // Instanciar um aluno
            Aluno aluno = new Aluno("sales", "sales@gmail.com", "ADS");

            // Cadastrar o aluno usando o objeto alunoDao
            alunoDao.cadastrarAluno(aluno);

            // Gerar relatório de desempenho usando o objeto alunoDao
            alunoDao.gerarRelatorioDesempenho(aluno);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Se houver algum código que você precisa executar sempre, coloque aqui
        }
    }
}
