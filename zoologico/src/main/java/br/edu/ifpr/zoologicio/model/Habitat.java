package br.edu.ifpr.zoologicio.model;

import java.util.ArrayList;

public class Habitat {

    private Integer  id;
    private String nome;
    private String descricao;
    private String capacidade;
    private Area area;
    private ArrayList<Animal> animais = new ArrayList<Animal>();
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    

    public Habitat() {

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

    public String getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(String capacidade) {
        this.capacidade = capacidade;
    }


    public ArrayList<Animal> getAnimais() {
        return animais;
    }

    public void setAnimais(ArrayList<Animal> animais) {
        this.animais = animais;
    }

    public Area getArea() {
        return area;
    }
}
