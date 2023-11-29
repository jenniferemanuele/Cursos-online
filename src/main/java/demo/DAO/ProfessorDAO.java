package demo.DAO;

import java.util.List;
import demo.entidades.Curso;
import demo.entidades.Professor;

public interface  DaoProfessorInterface<t>{
    public void cadastrarProfessor(t entidade);
    public void atualizarProfessores(t entidade);
    public List<t> listarProfessor();
    public List<Curso> listarCursosMinistrado(Professor professor);
}