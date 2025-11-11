package br.edu.ifpr.zoologicio.model;

public class Permissao {

    String nome;
    String descricao;
    CargoPermissao cargoPermissao;
    
    public Permissao (){

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

    public CargoPermissao getCargoPermissao() {
        return cargoPermissao;
    }

    public void setCargoPermissao(CargoPermissao cargoPermissao) {
        this.cargoPermissao = cargoPermissao;
    }
    
}
