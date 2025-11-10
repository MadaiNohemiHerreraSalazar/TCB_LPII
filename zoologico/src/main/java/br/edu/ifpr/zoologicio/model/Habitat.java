package br.edu.ifpr.zoologicio.model;

import java.util.ArrayList;

public class Habitat {

String nome;
String descricao;
String capacidade;    
    Area area;

    public Habitat (){

    }
            private ArrayList<Animal> animais = new ArrayList<Animal>();

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
            public Area getArea() {
                return area;
            }
            public void setArea(Area area) {
                this.area = area;
            }
            public ArrayList<Animal> getAnimais() {
                return animais;
            }
            public void setAnimais(ArrayList<Animal> animais) {
                this.animais = animais;
            }

}
