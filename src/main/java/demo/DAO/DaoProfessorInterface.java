package demo.DAO;

import java.sql.SQLException;
import java.util.List;
import demo.entidades.Curso;
import demo.entidades.Professor;

public interface  DaoProfessorInterface<t>{
    public void cadastrarProfessor(t entidade);
    public void atualizarProfessores(t entidade);
    public List<t> listarProfessor() throws SQLException;
    public List<Curso> listarCursosMinistrado(Professor professor);
    public Professor autenticar(String email, String senha);
}