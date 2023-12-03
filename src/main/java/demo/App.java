package demo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import demo.DAO.implementacao.AlunoDAOimplementacao;
import demo.DAO.implementacao.CursoDAOimplementacao;
import demo.DAO.implementacao.ProfessorDAOimplementacao;
import demo.DAO.DaoALunoIterface;
import demo.DAO.DaoCursosInterface;
import demo.DAO.DaoProfessorInterface;
import demo.entidades.Aluno;
import demo.entidades.Curso;
import demo.entidades.Professor;

public class App {
    private static Scanner scanner = new Scanner(System.in);
    private static int opcao = 0;

    private static DaoALunoIterface alunoDao;
    private static DaoProfessorInterface professorDao;
    private static DaoCursosInterface cursoDao;

    public static void main(String[] args) throws SQLException {
       
        ConexaoDB db = new ConexaoDB();
        Connection conexaoDB = db.retonarConexao();
        
        alunoDao = new DaoALunoIterface(); 
        professorDao = new DaoProfessorInterface(); 
        cursoDao = new DaoCursosInterface(); 

        do {
            System.out.println("--------------------------------------------");
            System.out.println("| ->          Sistema Acadêmico         <- |");
            System.out.println("| 1 - Cadastro (aluno)                      ");
            System.out.println("| 2 - Cadastro (professor)                 |");
            System.out.println("| 3 - Autenticação                         |");
            System.out.println("| 4 - Listagem de cursos                   |");
            System.out.println("| 0 - Sair                                 |");
            System.out.println("--------------------------------------------");

            System.out.print("\nDigite o número da opção desejada: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarAluno();
                    break;
                case 2:
                    cadastrarProfessor();
                    break;
                case 3:
                    autenticarUsuario();
                    break;
                case 4:
                    listarCursos();
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }

        } while (opcao != 0);
    }


    public static void autenticarUsuario() throws SQLException {
        String email, senha;

        System.out.println("--------------------------------------------");
        System.out.println("| ->               AUTENTICAÇÃO             <- |");
        System.out.println("--------------------------------------------");

        System.out.print("\nDigite seu e-mail: ");
        email = scanner.nextLine();

        System.out.print("\nDigite sua senha: ");
        senha = scanner.nextLine();

        // Realizar a autenticação
        Aluno aluno = alunoDao.autenticar(email, senha);
        if (aluno != null) {
            // Usuário autenticado com perfil de aluno
            System.out.println("Usuário autenticado com perfil de aluno");
            // Apresentar o menu de opções para alunos
            apresentarMenuAluno(aluno);
        } else {
            Professor professor = professorDao.autenticar(email, senha);
            if (professor != null) {
                // Usuário autenticado com perfil de professor
                System.out.println("Usuário autenticado com perfil de professor");
                // Apresentar o menu de opções para professores
                apresentarMenuProfessor(professor);
            } else {
                // Usuário não encontrado
                System.out.println("Usuário não encontrado");
            }
        }
    }

    public static void apresentarMenuAluno(Aluno aluno) {
        int opcaoAluno;

        do {
            System.out.println("--------------------------------------------");
            System.out.println("| ->          Menu do Aluno             <- |");
            System.out.println("| 1 - Ver notas                              ");
            System.out.println("| 2 - Inscrever-se em curso                 ");
            System.out.println("| 0 - Voltar ao menu principal              ");
            System.out.println("--------------------------------------------");

            System.out.print("\nDigite o número da opção desejada: ");
            opcaoAluno = scanner.nextInt();
            scanner.nextLine();

            switch (opcaoAluno) {
                case 1:
                    verNotas(aluno);
                    break;
                case 2:
                    inscreverCurso(aluno);
                    break;
                case 0:
                    System.out.println("Retornando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }

        } while (opcaoAluno != 0);
    }

    public static void apresentarMenuProfessor(Professor professor) {
        // Implemente o menu específico para professores, se necessário
    }

    public static void verNotas(Aluno aluno) {
        // Implementar lógica para visualizar notas do aluno
        System.out.println("Funcionalidade ainda não implementada.");
    }

    public static void inscreverCurso(Aluno aluno) {
        // Implementar lógica para permitir que o aluno se inscreva em um curso
        System.out.println("Funcionalidade ainda não implementada.");
    }

    public static void cadastrarProfessor() throws SQLException {
        String nome, email;

        System.out.println("--------------------------------------------");
        System.out.println("| ->               Professor            <- |");
        System.out.println("--------------------------------------------");

        System.out.print("\nDigite o nome do professor: ");
        nome = scanner.nextLine();

        System.out.print("\nDigite o email do professor: ");
        email = scanner.nextLine();

        Professor professor = new Professor(nome, email);

        professorDao.cadastrarProfessor(professor);
        System.out.println("Professor cadastrado com sucesso!");
    }

    public static void cadastrarAluno() throws SQLException {
        String nome, email, senha;

        System.out.println("--------------------------------------------");
        System.out.println("| ->               Aluno                 <- |");
        System.out.println("--------------------------------------------");

        System.out.print("\nDigite o nome do aluno: ");
        nome = scanner.nextLine();

        System.out.print("\nDigite o email do aluno: ");
        email = scanner.nextLine();

        System.out.print("\nDigite a senha do aluno: ");
        senha = scanner.nextLine();

        Aluno aluno = new Aluno(nome, email, senha);

        alunoDao.cadastrarAluno(aluno);
        System.out.println("Aluno cadastrado com sucesso!");
    }

    public static void listarCursos() {
        System.out.println("--------------------------------------------");
        System.out.println("| ->          Listagem de Cursos        <- |");
        System.out.println("--------------------------------------------");

        try {
            List<Curso> cursos = cursoDao.listarCursos();

            if (cursos.isEmpty()) {
                System.out.println("Nenhum curso disponível no momento.");
            } else {
                System.out.println("Cursos disponíveis:");

                for (Curso curso : cursos) {
                    System.out.println("ID: " + curso.getID() + ", Nome: " + curso.getNome() + ", Status: " + curso.getStatus() + ", Carga Horária: " + curso.getCargaHoraria());
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar cursos: " + e.getMessage());
        }
    }
}
