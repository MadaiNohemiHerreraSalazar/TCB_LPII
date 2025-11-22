package br.edu.ifpr.zoologicio.model;

public class Funcionario {

    private Integer  id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private AgendaFuncionario agendaFuncionario; 
    private Cargo cargo;
    private Area area;

    public Funcionario() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AgendaFuncionario getAgendaFuncionario() {
        return agendaFuncionario;
    }

    public void setAgendaFuncionario(AgendaFuncionario agendaFuncionario) {
        this.agendaFuncionario = agendaFuncionario;
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

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }


}
