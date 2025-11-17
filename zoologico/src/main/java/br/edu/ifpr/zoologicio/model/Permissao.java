package br.edu.ifpr.zoologicio.model;

public class Permissao {

    private int id;
    private String nome;
    private String descricao;
   // private CargoPermissao cargoPermissao;
    private int cargoPermissao_ID;

    public Permissao() {

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getCargoPermissao_ID() {
        return cargoPermissao_ID;
    }

    public void setCargoPermissao_ID(int cargoPermissao_ID) {
        this.cargoPermissao_ID = cargoPermissao_ID;
    }


}
