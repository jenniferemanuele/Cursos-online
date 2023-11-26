package demo.DAO;

import java.util.List;

public interface DaoALunoIterface<t>{
    public int cadastrarAluno(t entidade);
    public List<t>  listarAlunos();
}  