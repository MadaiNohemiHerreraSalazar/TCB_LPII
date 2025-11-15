package br.edu.ifpr.zoologicio.model;

public class Cargo {

    private int id;
    private String nome;
    private String salario;
    private String cargaHoraroia;
    private String senha;
    private Funcionario funcionario;

    public Cargo() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public String getCargaHoraroia() {
        return cargaHoraroia;
    }

    public void setCargaHoraroia(String cargaHoraroia) {
        this.cargaHoraroia = cargaHoraroia;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
}
