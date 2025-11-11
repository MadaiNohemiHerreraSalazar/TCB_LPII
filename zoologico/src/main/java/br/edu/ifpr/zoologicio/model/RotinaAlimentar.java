package br.edu.ifpr.zoologicio.model;

import java.util.ArrayList;

public class RotinaAlimentar {

    String data;
    String hora;
    String quantidadeAlimento;
    AgendaAnimal agendaAnimal;
    
        private ArrayList<Alimento> alimentos = new ArrayList<Alimento>();

    
    public RotinaAlimentar (){

    }


    public String getData() {
        return data;
    }


    public void setData(String data) {
        this.data = data;
    }


    public String getHora() {
        return hora;
    }


    public void setHora(String hora) {
        this.hora = hora;
    }


    public String getQuantidadeAlimento() {
        return quantidadeAlimento;
    }


    public void setQuantidadeAlimento(String quantidadeAlimento) {
        this.quantidadeAlimento = quantidadeAlimento;
    }


    public AgendaAnimal getAgendaAnimal() {
        return agendaAnimal;
    }


    public void setAgendaAnimal(AgendaAnimal agendaAnimal) {
        this.agendaAnimal = agendaAnimal;
    }


    public ArrayList<Alimento> getAlimentos() {
        return alimentos;
    }


    public void setAlimentos(ArrayList<Alimento> alimentos) {
        this.alimentos = alimentos;
    }
    
}