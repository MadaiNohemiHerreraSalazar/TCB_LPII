package br.edu.ifpr.zoologicio.model;

public class Funcionario {

    private int id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    // private AgendaFuncionario agendaFuncionario;
    private int agendaFuncionario_ID;
    private Cargo cargo;
    private int cargo_ID;

    public Funcionario() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getAgendaFuncionario_ID() {
        return agendaFuncionario_ID;
    }

    public void setAgendaFuncionario_ID(int agendaFuncionario_ID) {
        this.agendaFuncionario_ID = agendaFuncionario_ID;
    }

    public int getCargo_ID() {
        return cargo_ID;
    }

    public void setCargo_ID(int cargo_ID) {
        this.cargo_ID = cargo_ID;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }


}
