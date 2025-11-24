package br.edu.ifpr.zoologicio.model;

import java.util.ArrayList;

public class RotinaAlimentar {
    
    private Integer  id;
    private String data;
    private String hora;
    private String quantidadeAlimento;
    private int agendaAnimal_ID;
    private AgendaAnimal agendaAnimal;
    private ArrayList<Alimento> alimentos = new ArrayList<Alimento>();

     public Integer  getId() {
        return id;
    }

    public void setId(Integer  id) {
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

    //////
    public void reduzirQuantidade(String quantidadeAlimento) {
        int estoqueAtual = Integer.parseInt(this.quantidadeAlimento);
        int intQuantidadeReduzir =  Integer.parseInt(quantidadeAlimento);
        this.quantidadeAlimento = String.valueOf(estoqueAtual - intQuantidadeReduzir);

    }

    public void adicionarEstoque(String quantidadeAlimento) {
        int estoqueAtual = Integer.parseInt(this.quantidadeAlimento);
        int intQuantidadeAumenta  = Integer.parseInt(quantidadeAlimento);
        this.quantidadeAlimento = String.valueOf(estoqueAtual + intQuantidadeAumenta);
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