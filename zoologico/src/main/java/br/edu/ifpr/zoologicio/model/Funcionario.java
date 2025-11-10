package br.edu.ifpr.zoologicio.model;

public class Funcionario {

    String nome;
    String cpf;
    String email;
    String telefone;
    AgendaFuncionario agendaFuncionario;
    Cargo cargo;

    public Funcionario() {

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

    public AgendaFuncionario getAgendaFuncionario() {
        return agendaFuncionario;
    }

    public void setAgendaFuncionario(AgendaFuncionario agendaFuncionario) {
        this.agendaFuncionario = agendaFuncionario;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
}
