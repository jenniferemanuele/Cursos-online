package demo.DAO;

import java.util.List;

public interface  DaoProfessorInterface<t>{
    public void cadastrarProfessor(t entidade);
    public void atualizarProfessores(t entidade);
    public List<t> listarProfessor();
}