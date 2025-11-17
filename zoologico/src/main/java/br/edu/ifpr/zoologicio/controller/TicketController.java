package br.edu.ifpr.zoologicio.controller;

import java.util.ArrayList;

import br.edu.ifpr.zoologicio.model.Ticket;
import br.edu.ifpr.zoologicio.model.dao.TicketDAO;

public class TicketController {

    private TicketDAO dao;

    public TicketController(){
        this.dao = new TicketDAO();
    }


    public void selecionarTicket(int id){

        if(id <= 0){

            System.out.println("id invalido");
            return;
        }

        dao.select(id);
    }

    public ArrayList<Ticket> listarTickets() {
        return dao.listar();
    }
    
}
