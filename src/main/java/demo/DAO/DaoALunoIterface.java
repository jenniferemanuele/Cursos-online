package demo.DAO;

import java.sql.SQLException;
import java.util.List;

import demo.entidades.Aluno;

public interface DaoALunoIterface<T> {
    int cadastrarAluno(T entidade);
    List<T> listarAlunos();
    Aluno autenticar(String email, String senha) throws SQLException;
}
