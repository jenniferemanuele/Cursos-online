package demo.ententidades;

public class Aluno {
    private String nome;
    private String email;
    private String cursosMatriculados;
    private double notas;
    private boolean status;

    public Aluno(String nome, String email, String cursosMatriculados) {
        this.nome = nome;
        this.email = email;
        this.cursosMatriculados = cursosMatriculados;
        this.notas = notas;
        this.status = status;
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
    public String getCursosMatriculados() {
        return cursosMatriculados;
    }
    public void setCursosMatriculados(String cursosMatriculados) {
        this.cursosMatriculados = cursosMatriculados;
    }
    public double getNotas() {
        return notas;
    }
    public void setNotas(double notas) {
        this.notas = notas;
    }
    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCurso() {
        return null;
    }
}
