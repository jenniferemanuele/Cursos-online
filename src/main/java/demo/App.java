package demo;

import java.sql.SQLException;

import demo.DAO.AlunoDAO;
import demo.DAO.CursosDAO;
import demo.DAO.ProfessorDAO;
import demo.entidades.Aluno;
import demo.ConexaoDB;
import java.sql.Connection;
import java.util.Scanner;

public class App {
    private static String username, password;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            do {
                System.out.println("--------------------------------------------");
                System.out.println("| ->          Sistema acadêmico         <- |");
                System.out.println("--------------------------------------------");

                System.out.print("Informe seu e-mail: ");
                username = scanner.nextLine();

                System.out.print("Informe sua senha: ");
                password = scanner.nextLine();

                // TO DO

            } while (true);

            // Conexão com o banco de dados
            ConexaoDB db = new ConexaoDB();
            Connection conexaoDB = db.retonarConexao();

            // Instanciar os DAOs
            AlunoDAO alunoDao = new AlunoDAO(conexaoDB);
            ProfessorDAO professorDAO = new ProfessorDAO(conexaoDB);
            CursosDAO cursosDAO = new CursosDAO(conexaoDB);

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
