package br.edu.ifpr.zoologicio.model;

import java.util.ArrayList;

public class CargoPermissao {
Cargo cargo;
    private ArrayList<Permissao> permissoes = new ArrayList<Permissao>();
    String nome;
    String descricao;


    public CargoPermissao(){

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
