package demo.DAO;
import java.util.List;


public interface DaoCursosInterface<t>{
    public void cadastrarCurso(t entidade);
    public void atualizarCurso(t entidade);
    public List<t> listarCursos();
    public double registrarNota(t entidade);
    public double calcularMediaNotas(t entidade); 
}