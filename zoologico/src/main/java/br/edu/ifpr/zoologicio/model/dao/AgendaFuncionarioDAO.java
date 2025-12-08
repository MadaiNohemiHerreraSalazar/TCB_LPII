package br.edu.ifpr.zoologicio.model.dao;

import br.edu.ifpr.zoologicio.model.AgendaFuncionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AgendaFuncionarioDAO {

    // CADASTRAR
    // ____________________________________________________________

    public static void cadastrar(AgendaFuncionario agenda) {
        String sql = "INSERT INTO agendaFuncionario (atividade, funcionario_id) "
                +
                "VALUES (?,?)";

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pst.setString(3, agenda.getAtividade());
            pst.setInt(5, agenda.getFuncionario().getId());

            pst.executeUpdate();

            try (ResultSet rs = pst.getGeneratedKeys()) {
                if (rs.next()) {
                    agenda.setId(rs.getInt(1));
                } else {
                    throw new SQLException("Falha ao obter ID gerado para AgendaFuncionario");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // EDITAR
    // ______________________________________________________
    public static void editar(AgendaFuncionario agenda) {
        String sql = "UPDATE agendaFuncionario SET  atividade=?, funcionario_id=? WHERE agendaFuncionario_id=?";

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(3, agenda.getAtividade());
            pst.setInt(5, agenda.getFuncionario().getId());
            pst.setInt(6, agenda.getId());

            pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // DELETE
    // ______________________________________________________
    public static void delete(int id) {
        String sql = "DELETE FROM agendaFuncionario WHERE  agendaFuncionario_id=?";

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // SELECT 
    // ______________________________________________________
    public static AgendaFuncionario select(int id) {
        String sql = "SELECT af.agendaFuncionario_id, af.atividade, " +
                " af.funcionario_id " +
                "FROM agendaFuncionario af " +
                "WHERE af.agendaFuncionario_id = ?";

        AgendaFuncionario agenda = null;

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                agenda = new AgendaFuncionario();
                agenda.setId(rs.getInt("id"));
                agenda.setAtividade(rs.getString("atividade"));

                // busca cargo e funcionario por id (carrega objetos)
                agenda.setFuncionario(FuncionarioDAO.buscarFuncionarioPor_ID(rs.getInt("funcionario_id")));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return agenda;
    }

    // LISTAR SIMPLES
    // ______________________________________________________
    public static ArrayList<AgendaFuncionario> listar() {
        ArrayList<AgendaFuncionario> agendas = new ArrayList<>();
        String sql = "SELECT agendaFuncionario_id, atividade FROM agendaFuncionario ORDER BY agendaFuncionario_id DESC";

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sql);
                ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                AgendaFuncionario agenda = new AgendaFuncionario();
                agenda.setId(rs.getInt("agendaFuncionario_id"));
                agenda.setAtividade(rs.getString("atividade"));
                agendas.add(agenda);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return agendas;
    } 

   public static ArrayList<AgendaFuncionario> listarCompleto() {
    ArrayList<AgendaFuncionario> agendas = new ArrayList<>();

    String sql = "SELECT af.agendaFuncionario_id, af.atividade, af.funcionario_id " +
                 "FROM agendaFuncionario af ORDER BY af.agendaFuncionario_id DESC";

    try (Connection con = ConnectionFactory.getConnection();
         PreparedStatement pst = con.prepareStatement(sql);
         ResultSet rs = pst.executeQuery()) {

        while (rs.next()) {

            AgendaFuncionario agenda = new AgendaFuncionario();
            agenda.setId(rs.getInt("agendaFuncionario_id"));
            agenda.setAtividade(rs.getString("atividade"));

            // Carrega o FUNCIONÁRIO completo
            agenda.setFuncionario(
                FuncionarioDAO.buscarFuncionarioPor_ID(rs.getInt("funcionario_id"))
            );

            agendas.add(agenda);
        }

    } catch (SQLException e) {
        System.err.println("Erro ao listar AgendaFuncionario COMPLETO: " + e.getMessage());
    }

    return agendas;
}

}
