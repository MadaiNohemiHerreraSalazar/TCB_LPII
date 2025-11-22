package br.edu.ifpr.zoologicio.model;

public class Permissao {

    private Integer id;
    private String nome;
    private String descricao;
    private Cargo_Permissao cargoPermissao;

    public Permissao() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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


    public Cargo_Permissao getCargoPermissao() {
        return cargoPermissao;
    }

    public void setCargoPermissao(Cargo_Permissao cargoPermissao) {
        this.cargoPermissao = cargoPermissao;
    }

}
