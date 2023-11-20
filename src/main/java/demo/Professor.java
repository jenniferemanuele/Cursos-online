package demo;

public class Professor {
    private String nome;
    private String email;
    private Cursos cursos;
    
    public Professor(String nome, String email, Cursos cursos) {
        this.nome = nome;
        this.email = email;
        this.cursos = cursos;
    }
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
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
}
