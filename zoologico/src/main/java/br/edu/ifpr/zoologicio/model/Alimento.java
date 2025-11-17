package br.edu.ifpr.zoologicio.model;

import java.util.ArrayList;

public class Alimento{

    private int id;
    private String nome;
    private String validade;
    private String estoque;
    private Fornecedor fornecedor;
    private RotinaAlimentar rotinaAlimentar;

    private ArrayList<RotinaAlimentar> rotinaAlimentares = new ArrayList<RotinaAlimentar>();


public Alimento (){

}

 public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


public ArrayList<RotinaAlimentar> getRotinaAlimentares() {
    return rotinaAlimentares;
}


public void setRotinaAlimentares(ArrayList<RotinaAlimentar> rotinaAlimentares) {
    this.rotinaAlimentares = rotinaAlimentares;
}

public String getNome() {
    return nome;
}


public void setNome(String nome) {
    this.nome = nome;
}


public String getValidade() {
    return validade;
}


public void setValidade(String validade) {
    this.validade = validade;
}


public String getEstoque() {
    return estoque;
}


public void setEstoque(String estoque) {
    this.estoque = estoque;
}

public Fornecedor getFornecedor() {
    return fornecedor;
}

public void setFornecedor(Fornecedor fornecedor) {
    this.fornecedor = fornecedor;
}


public RotinaAlimentar getRotinaAlimentar() {
    return rotinaAlimentar;
}

public void setRotinaAlimentar(RotinaAlimentar rotinaAlimentar) {
    this.rotinaAlimentar = rotinaAlimentar;
}


}