package br.edu.ifpr.zoologicio.model;

import java.util.ArrayList;

public class Cargo_Permissao {

    private Integer  id;
    private Cargo cargo;
    private ArrayList<CargoPermissao> permissoes = new ArrayList<CargoPermissao>();
    private String nome;
    private String descricao;

    public Cargo_Permissao() {

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

    public ArrayList<CargoPermissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(ArrayList<CargoPermissao> permissoes) {
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
