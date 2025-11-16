package br.edu.ifpr.zoologicio.model;

import java.util.ArrayList;

public class Area {

    private int id;
    private String nome;
    private String descricao;
    private ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
    private ArrayList<Habitat> habitats = new ArrayList<Habitat>();

    public Area() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(ArrayList<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }
    
    public ArrayList<Habitat> getHabitats() {
        return habitats;
    }

    public void setHabitats(ArrayList<Habitat> habitats) {
        this.habitats = habitats;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}