package br.edu.ifpr.zoologicio.model;

import java.util.ArrayList;

public class CadastroAgendaAnimal {

    private ArrayList<AgendaAnimal> agendaAnimal = new ArrayList<AgendaAnimal>();


    public CadastroAgendaAnimal(){

    }


    public ArrayList<AgendaAnimal> getAgendaAnimal() {
        return agendaAnimal;
    }


    public void setAgendaAnimal(ArrayList<AgendaAnimal> agendaAnimal) {
        this.agendaAnimal = agendaAnimal;
    }
    
}