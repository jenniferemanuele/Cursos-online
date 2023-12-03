package demo.DAO;
import java.sql.SQLException;
import java.util.List;


public interface DaoCursosInterface<t>{
    public void cadastrarCurso(t entidade);
    public void atualizarCurso(t entidade);
    public List<t> listarCursos() throws SQLException;
    public double registrarNota(t entidade);
    public double calcularMediaNotas(t entidade); 
}