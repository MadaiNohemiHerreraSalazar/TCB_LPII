package br.edu.ifpr.zoologicio.model;

import java.util.ArrayList;

public class RotinaAlimentar {
    
    private int id;
    private String data;
    private String hora;
    private String quantidadeAlimento;
    private int agendaAnimal_ID;
    private AgendaAnimal agendaAnimal;
    private ArrayList<Alimento> alimentos = new ArrayList<Alimento>();

     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RotinaAlimentar() {

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

    public int getAgendaAnimal_ID() {
        return agendaAnimal_ID;
    }

    public void setAgendaAnimal_ID(int agendaAnimal_ID) {
        this.agendaAnimal_ID = agendaAnimal_ID;
    }

    public AgendaAnimal getAgendaAnimal() {
        return agendaAnimal;
    }

    public void setAgendaAnimal(AgendaAnimal agendaAnimal) {
        this.agendaAnimal= agendaAnimal;
    }

    public ArrayList<Alimento> getAlimentos() {
        return alimentos;
    }

    public void setAlimentos(ArrayList<Alimento> alimentos) {
        this.alimentos = alimentos;
    }

}