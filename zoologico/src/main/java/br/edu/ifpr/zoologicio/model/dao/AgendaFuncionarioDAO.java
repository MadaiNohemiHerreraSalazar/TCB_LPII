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
        String sql = "INSERT INTO agendaFuncionarios (criadoPor, ultimaAtualizacao, atividade, cargo_id, funcionario_id) "
                +
                "VALUES (?,?,?,?,?)";

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, agenda.getCriadoPor());
            pst.setString(2, agenda.getUltimaAtualizacao());
            pst.setString(3, agenda.getAtividade());
            pst.setInt(4, agenda.getCargo().getId());
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
        String sql = "UPDATE agendaFuncionarios SET criadoPor=?, ultimaAtualizacao=?, atividade=?, cargo_id=?, funcionario_id=? WHERE id=?";

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, agenda.getCriadoPor());
            pst.setString(2, agenda.getUltimaAtualizacao());
            pst.setString(3, agenda.getAtividade());
            pst.setInt(4, agenda.getCargo().getId());
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
        String sql = "DELETE FROM agendaFuncionarios WHERE id=?";

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // SELECT COMPLETO AgendaFuncionario + Cargo + Funcionario
    // ______________________________________________________
    public AgendaFuncionario selectCompleto(int id) {
        String sql = "SELECT af.id, af.criadoPor, af.ultimaAtualizacao, af.atividade, " +
                "af.cargo_id, af.funcionario_id " +
                "FROM agendaFuncionarios af " +
                "WHERE af.id = ?";

        AgendaFuncionario agenda = null;

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                agenda = new AgendaFuncionario();
                agenda.setId(rs.getInt("id"));
                agenda.setCriadoPor(rs.getString("criadoPor"));
                agenda.setUltimaAtualizacao(rs.getString("ultimaAtualizacao"));
                agenda.setAtividade(rs.getString("atividade"));

                // busca cargo e funcionario por id (carrega objetos)
                int cargoId = rs.getInt("cargo_id");
                int funcId = rs.getInt("funcionario_id");

                if (cargoId > 0) {
                    agenda.setCargo(CargoDAO.buscarCargoPorId(cargoId));
                }

                if (funcId > 0) {
                    agenda.setFuncionario(FuncionarioDAO.buscarFuncionarioPor_ID(funcId));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return agenda;
    }

    // LISTAR SIMPLES
    // ______________________________________________________
    public ArrayList<AgendaFuncionario> listar() {
        ArrayList<AgendaFuncionario> agendas = new ArrayList<>();
        String sql = "SELECT id, criadoPor, ultimaAtualizacao, atividade FROM agendaFuncionarios ORDER BY id DESC";

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sql);
                ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                AgendaFuncionario agenda = new AgendaFuncionario();
                agenda.setId(rs.getInt("id"));
                agenda.setCriadoPor(rs.getString("criadoPor"));
                agenda.setUltimaAtualizacao(rs.getString("ultimaAtualizacao"));
                agenda.setAtividade(rs.getString("atividade"));
                agendas.add(agenda);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return agendas;
    }

    // LISTAR COMPLETO Cargo + Funcionario
    // ___________________________________________________________________

    public ArrayList<AgendaFuncionario> listarCompleto() {
        ArrayList<AgendaFuncionario> agendas = new ArrayList<>();
        String sql = "SELECT af.id, af.criadoPor, af.ultimaAtualizacao, af.atividade, af.cargo_id, af.funcionario_id " +
                "FROM agendaFuncionarios af " +
                "ORDER BY af.id DESC";

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sql);
                ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                AgendaFuncionario agenda = new AgendaFuncionario();
                agenda.setId(rs.getInt("id"));
                agenda.setCriadoPor(rs.getString("criadoPor"));
                agenda.setUltimaAtualizacao(rs.getString("ultimaAtualizacao"));
                agenda.setAtividade(rs.getString("atividade"));

                int cargoId = rs.getInt("cargo_id");
                int funcId = rs.getInt("funcionario_id");

                if (cargoId > 0) {
                    agenda.setCargo(CargoDAO.buscarCargoPorId(cargoId));
                }

                if (funcId > 0) {
                    agenda.setFuncionario(FuncionarioDAO.buscarFuncionarioPor_ID(funcId));
                }

                agendas.add(agenda);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return agendas;
    }
}



   
