package demo.ententidades;

public class Professor {
    private static String nome;
    private static String email;
    private Cursos cursos;
    private static String cursosMinistrados;
    public Professor(String nome,String email, String cursosMinistrados) {
        this.nome = nome;
        this.email = email;
        this.cursos = cursos;
        this.cursosMinistrados = cursosMinistrados;
    }
    
    public static String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public static String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Cursos getCursos() {
        return cursos;
    }
    public void setCursos(Cursos cursos) {
        this.cursos = cursos;
    }

    public static int getCargaHoraria() {
        return 0;
    }

    public static String getcursoMinistrado() {
        return null;
    }
}