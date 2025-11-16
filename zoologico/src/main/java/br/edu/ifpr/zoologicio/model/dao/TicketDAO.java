// O ticket tem que ter uma "compra" que já foi cadastrada anteriormente ou na hora (arrumar)
// Não se pode cadastrar um ticket sem sua compra.

package br.edu.ifpr.zoologicio.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.edu.ifpr.zoologicio.model.Ticket;

public class TicketDAO {

    public static void cadastrar(Ticket ticket) {

        Connection con = ConnectionFactory.getConnection();

        String sqlEndereco = "INSERT INTO tickets(data, hora, preco) VALUES (?,?,?)";

        try {

            PreparedStatement psTicket = con.prepareStatement(sqlEndereco);

            psTicket.setString(1, ticket.getData());
            psTicket.setString(2, ticket.getHora());
            psTicket.setString(3, ticket.getPreco());

            psTicket.executeUpdate();
            System.out.println("Ticket inserido com sucesso");

        } catch (Exception e) {

            // TODO: handle exception
            e.printStackTrace();

        }

    }

    public static void editar(Ticket ticket) {

        Connection con = ConnectionFactory.getConnection();

        try {

            String sql = "UPDATE tickets SET data=?, hora?, preco=?, WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, ticket.getData());
            pst.setString(2, ticket.getHora());
            pst.setString(3, ticket.getPreco());
            pst.setInt(4, ticket.getId());

            pst.executeUpdate();
            System.out.println("Ticket atualizado com sucesso");

        } catch (Exception e) {

            // TODO: handle exception
            System.out.println(e.getMessage());

        }

    }

    public void delete(int id) {
        Connection con = ConnectionFactory.getConnection();

        try {

            String sql = "DELETE FROM tickets WHERE id= ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Ticket excluido com sucesso");

        } catch (Exception e) {

            // TODO: handle exception
            e.printStackTrace();

        }
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
