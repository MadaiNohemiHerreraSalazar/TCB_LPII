package br.edu.ifpr.zoologicio.model;

import java.util.ArrayList;

public class CargoPermissao {

    private Integer  id;
    private Cargo cargo;
    private ArrayList<Permissao> permissoes = new ArrayList<Permissao>();
    private String nome;
    private String descricao;

    public CargoPermissao() {

    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public ArrayList<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(ArrayList<Permissao> permissoes) {
        this.permissoes = permissoes;
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
