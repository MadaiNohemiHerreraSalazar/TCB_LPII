package br.edu.ifpr.zoologicio.controller;

import br.edu.ifpr.zoologicio.model.Ticket;
import br.edu.ifpr.zoologicio.model.dao.TicketDAO;

public class TicketController {

    private TicketDAO dao;

    public TicketController(){
        this.dao = new TicketDAO();
    }


    public void cadastrarTicket(Ticket ticket){
        if(ticket.getData() == null){
            System.out.println("Data não pode ser vazio!");
            return;
        }

        dao.cadastrar(ticket);
    }

    public void editarTicket(Ticket ticket){
        if(ticket.getData() == null || ticket.getData().isEmpty()){
            System.out.println("Data não pode ser vazio!");
            return;
        }

        if(ticket.getId() <= 0){
            System.out.println("id invalido");
            return;
        }

        dao.editar(ticket);
    }

    public void deleteTicket(int id){
       
        if(id <= 0){

            System.out.println("id invalido");
            return;
        }

        dao.delete(id);
    }

    public void selecionarTicket(int id){

        if(id <= 0){

            System.out.println("id invalido");
            return;
        }

        dao.select(id);
    }

    //public void listarTicket()
    
}
