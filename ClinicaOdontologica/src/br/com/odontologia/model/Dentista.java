package br.com.odontologia.model;

public class Dentista {

    private Integer id;
    private String numeroMatricula;
    private String nome;
    private String sobreNome;

    public Dentista(Integer id, String numeroMatricula, String nome, String sobreNome) {
        this.id = id;
        this.numeroMatricula = numeroMatricula;
        this.nome = nome;
        this.sobreNome = sobreNome;
    }

    public Dentista(String numeroMatricula, String nome, String sobreNome) {
        this.numeroMatricula = numeroMatricula;
        this.nome = nome;
        this.sobreNome = sobreNome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumeroMatricula() {
        return numeroMatricula;
    }

    public void setNumeroMatricula(String numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobreNome() {
        return sobreNome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }

    @Override
    public String toString() {
        return "Dentista{" +
                "numeroMatricula='" + numeroMatricula + '\'' +
                ", nome='" + nome + '\'' +
                ", sobreNome='" + sobreNome + '\'' +
                '}';
    }
}
