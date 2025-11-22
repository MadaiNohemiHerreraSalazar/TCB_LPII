package br.edu.ifpr.zoologicio.model.dao;

import br.edu.ifpr.zoologicio.model.*;
import java.sql.*;
import java.util.ArrayList;

public class CompraDAO {

    // CADASTRAR COMPRA
    // ______________________________________________________
    public static void cadastrar(Compra compra, int visitante_id, int funcionario_id) {

        String sqlCompra = "INSERT INTO compras(data, hora, quantidade, meioPagamento, precoTotal, visitante_id, funcionario_id) "
                + "VALUES (?,?,?,?,?,?,?)";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sqlCompra, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, compra.getData());
            pst.setString(2, compra.getHora());
            pst.setString(3, compra.getQuantidade());
            pst.setString(4, compra.getMeioPagamento());
            pst.setString(5, compra.getPrecoTotal());
            pst.setInt(6, visitante_id);
            pst.setInt(7, funcionario_id);

            pst.executeUpdate();

            try (ResultSet rs = pst.getGeneratedKeys()) {
                if (rs.next()) {
                    compra.setId(rs.getInt(1));
                } else {
                    throw new SQLException("Falha ao obter ID gerado para Compra");
                }
            }

            if (!cadastroTickets(con, compra.getTickets(), compra.getId())) {
                throw new SQLException("Compra NÃO cadastrada. Erro ao cadastrar tickets.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // CADASTRAR TICKETS
    // ______________________________________________________
    public static boolean cadastroTickets(Connection con, ArrayList<Ticket> tickets, int compra_id) {

        String sql = "INSERT INTO tickets(data, hora, preco, compra_id) VALUES (?,?,?,?)";

        try {
            for (Ticket ticket : tickets) {
                try (PreparedStatement pst = con.prepareStatement(sql)) {
                    pst.setString(1, ticket.getData());
                    pst.setString(2, ticket.getHora());
                    pst.setString(3, ticket.getPreco());
                    pst.setInt(4, compra_id);
                    pst.executeUpdate();
                }
            }

            System.out.println("Tickets cadastrados com sucesso!");
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    // EDITAR COMPRA
    // ______________________________________________________
    public static void editar(Compra compra, int visitante_id, int funcionario_id) {

        String sqlUpdate = "UPDATE compras SET data=?, hora=?, quantidade=?, meioPagamento=?, precoTotal=?, "
                + "visitante_id=?, funcionario_id=? WHERE id=?";
        String sqlDeleteTickets = "DELETE FROM tickets WHERE compra_id=?";
        String sqlInsertTicket = "INSERT INTO tickets(data, hora, preco, compra_id) VALUES (?,?,?,?)";

        try (Connection con = ConnectionFactory.getConnection()) {

            // Atualiza compra
            try (PreparedStatement pst = con.prepareStatement(sqlUpdate)) {
                pst.setString(1, compra.getData());
                pst.setString(2, compra.getHora());
                pst.setString(3, compra.getQuantidade());
                pst.setString(4, compra.getMeioPagamento());
                pst.setString(5, compra.getPrecoTotal());
                pst.setInt(6, visitante_id);
                pst.setInt(7, funcionario_id);
                pst.setInt(8, compra.getId());
                pst.executeUpdate();
            }

            // Remove tickets antigos
            try (PreparedStatement pst = con.prepareStatement(sqlDeleteTickets)) {
                pst.setInt(1, compra.getId());
                pst.executeUpdate();
            }

            // Insere novos tickets
            try (PreparedStatement pst = con.prepareStatement(sqlInsertTicket)) {
                for (Ticket ticket : compra.getTickets()) {
                    pst.setString(1, ticket.getData());
                    pst.setString(2, ticket.getHora());
                    pst.setString(3, ticket.getPreco());
                    pst.setInt(4, compra.getId());
                    pst.executeUpdate();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // DELETE COMPRA
    // ______________________________________________________
    public static void delete(int id) {

        String sqlDeleteTickets = "DELETE FROM tickets WHERE compra_id=?";
        String sqlDeleteCompra = "DELETE FROM compras WHERE id=?";

        try (Connection con = ConnectionFactory.getConnection()) {

            try (PreparedStatement pst = con.prepareStatement(sqlDeleteTickets)) {
                pst.setInt(1, id);
                pst.executeUpdate();
            }

            try (PreparedStatement pst = con.prepareStatement(sqlDeleteCompra)) {
                pst.setInt(1, id);
                pst.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // SELECT SIMPLES
    // ______________________________________________________
    public Compra select(int id) {

        String sql = "SELECT c.id, c.data, c.hora, c.quantidade, c.meioPagamento, c.precoTotal, "
                + "c.visitante_id, c.funcionario_id "
                + "FROM compras c WHERE c.id = ?";

        Compra compra = null;

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                compra = new Compra();
                compra.setId(rs.getInt("id"));
                compra.setData(rs.getString("data"));
                compra.setHora(rs.getString("hora"));
                compra.setQuantidade(rs.getString("quantidade"));
                compra.setMeioPagamento(rs.getString("meioPagamento"));
                compra.setPrecoTotal(rs.getString("precoTotal"));

                compra.setVisitante(VisitanteDAO.buscarVisitantePor_ID(rs.getInt("visitante_id")));
                compra.setFuncionario(FuncionarioDAO.buscarFuncionarioPor_ID(rs.getInt("funcionario_id")));
                compra.setTickets(TicketDAO.buscarTicketsPorCompra(id));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return compra;
    }


    // LISTAR COMPLETO COM TICKETS
    // ______________________________________________________
    public ArrayList<Compra> listarCompleto() {
        ArrayList<Compra> compras = new ArrayList<>();
        String sql = "SELECT id, data, hora, quantidade, meioPagamento, precoTotal FROM compras";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Compra compra = new Compra();
                compra.setId(rs.getInt("id"));
                compra.setData(rs.getString("data"));
                compra.setHora(rs.getString("hora"));
                compra.setQuantidade(rs.getString("quantidade"));
                compra.setMeioPagamento(rs.getString("meioPagamento"));
                compra.setPrecoTotal(rs.getString("precoTotal"));

                compra.setTickets(TicketDAO.buscarTicketsPorCompra(compra.getId()));
                compras.add(compra);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar Compras: " + e.getMessage());
        }

        return compras;
    }

}
