package br.edu.ifpr.zoologicio.model;

import java.util.ArrayList;

public class CadastroAlimento {

            private ArrayList<Alimento> alimentos = new ArrayList<Alimento>();

    public CadastroAlimento(){

    }

    public ArrayList<Alimento> getAlimentos() {
        return alimentos;
    }

    public void setAlimentos(ArrayList<Alimento> alimentos) {
        this.alimentos = alimentos;
    }
    
}
