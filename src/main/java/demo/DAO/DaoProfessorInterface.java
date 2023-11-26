package demo.DAO;

import java.util.List;
import demo.entidades.Cursos;
import demo.entidades.Professor;

public interface  DaoProfessorInterface<t>{
    public void cadastrarProfessor(t entidade);
    public void atualizarProfessores(t entidade);
    public List<t> listarProfessor();
    public List<Cursos> listarCursosMinistrado(Professor professor);
}