//gerado automaticamente ao fazer compra

package br.edu.ifpr.zoologicio.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.edu.ifpr.zoologicio.model.Ticket;

public class TicketDAO {

    public ArrayList<Ticket> select(int id) {

        Connection con = ConnectionFactory.getConnection();
        ArrayList<Ticket> tickets = new ArrayList<>();

        try {

            String sql = "SELECT * FROM tickets WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Ticket ticket = new Ticket();
                ticket.setId(rs.getInt("id"));
                ticket.setData("data");
                ticket.setHora("hora");
                ticket.setPreco("preco");
                tickets.add(ticket);

            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }

        return tickets;
    }

    public ArrayList<Ticket> listar() {

        Connection con = ConnectionFactory.getConnection();

        ArrayList<Ticket> tickets = new ArrayList<>();

        try {

            String sql = "SELECT * FROM tickets";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Ticket ticket = new Ticket();
                ticket.setId(rs.getInt("id"));
                ticket.setData("data");
                ticket.setHora("hora");
                ticket.setPreco("preco");
                tickets.add(ticket);

            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }

        return tickets;
    }

}
