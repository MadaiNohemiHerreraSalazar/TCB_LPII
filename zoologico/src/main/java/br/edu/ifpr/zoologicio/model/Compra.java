package br.edu.ifpr.zoologicio.model;

import java.util.ArrayList;

public class Compra {

    private int id;
    private String data;
    private String hora;
    private String quantidade;
    private String meioPagamento;
    private String precoTotal;
    //private Visitante visitante;
    private int visitante_ID;
   // private Funcionario funcionario;
 private int funcionario_ID;
    private ArrayList<Ticket> tickets = new ArrayList<Ticket>();

    public Compra() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getMeioPagamento() {
        return meioPagamento;
    }

    public void setMeioPagamento(String meioPagamento) {
        this.meioPagamento = meioPagamento;
    }

    public String getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(String precoTotal) {
        this.precoTotal = precoTotal;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

    public int getVisitante_ID() {
        return visitante_ID;
    }

    public void setVisitante_ID(int visitante_ID) {
        this.visitante_ID = visitante_ID;
    }

    public int getFuncionario_ID() {
        return funcionario_ID;
    }

    public void setFuncionario_ID(int funcionario_ID) {
        this.funcionario_ID = funcionario_ID;
    }

}
