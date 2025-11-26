//gerado automaticamente ao fazer compra

package br.edu.ifpr.zoologicio.model.dao;  

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.edu.ifpr.zoologicio.model.Ticket;

public class TicketDAO {

    // BUSCAR TICKETS POR COMPRA
    public static ArrayList<Ticket> buscarTicketsPorCompra(int compraId) {
        ArrayList<Ticket> tickets = new ArrayList<>();

        String sql = "SELECT id, data, hora, preco FROM tickets WHERE compra_id=?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, compraId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(rs.getInt("id"));
                ticket.setData(rs.getString("data"));
                ticket.setHora(rs.getString("hora"));
                ticket.setPreco(rs.getString("preco"));
                tickets.add(ticket);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return tickets;
    }

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
            e.printStackTrace();
        }

        return tickets;
    }


    // LISTAR TICKET
    // ______________________________________________________

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
            e.printStackTrace();
        }

        return tickets;
    }

}
