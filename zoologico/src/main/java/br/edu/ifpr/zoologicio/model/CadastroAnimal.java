package br.edu.ifpr.zoologicio.model;

import java.util.ArrayList;

public class CadastroAnimal {

    private ArrayList<Animal> animais = new ArrayList<Animal>();


    public CadastroAnimal(){

    }


    public ArrayList<Animal> getAnimais() {
        return animais;
    }


    public void setAnimais(ArrayList<Animal> animais) {
        this.animais = animais;
    }
    
}