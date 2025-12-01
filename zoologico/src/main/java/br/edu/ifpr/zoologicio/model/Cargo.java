package br.edu.ifpr.zoologicio.model;

import java.util.ArrayList;

public class Cargo {

    private Integer  id;
    private String nome;
    private String salario;
    private String cargaHoraria;
    private String senha;
    private ArrayList<Funcionario> funcionarios = new ArrayList<>();

    public Cargo() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public String getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(String cargaHoraroia) {
        this.cargaHoraria = cargaHoraroia;
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

    public ArrayList<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(ArrayList<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

}
