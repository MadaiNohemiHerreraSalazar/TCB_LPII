package br.edu.ifpr.zoologicio.model;

import java.util.ArrayList;

public class CadastroRotina {

    private ArrayList<RotinaAlimentar> rotinaAlimentares = new ArrayList<RotinaAlimentar>();


    public CadastroRotina(){

    }


    public ArrayList<RotinaAlimentar> getRotinaAlimentares() {
        return rotinaAlimentares;
    }


    public void setRotinaAlimentares(ArrayList<RotinaAlimentar> rotinaAlimentares) {
        this.rotinaAlimentares = rotinaAlimentares;
    }
    
}
