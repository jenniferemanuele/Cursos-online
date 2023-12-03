package demo.entidades;

public class Professor {
    private String nome;
    private String email;
    private Curso cursos;
    
    public Professor(String string, String string2) {
        this.nome = nome;
        this.email = email;
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
    public Curso getCursos() {
        return cursos;
    }
    public void setCursos(Curso cursos) {
        this.cursos = cursos;
    }

    public int getCargaHoraria() {
        return 0;
    }

    public int getID(){
        return 0;
    }

    public void setSenha(String senha) {
    }

    public Object getTipo() {
        return null;
    }
}
