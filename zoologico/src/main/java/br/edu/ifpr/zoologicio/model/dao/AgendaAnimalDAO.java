package br.edu.ifpr.zoologicio.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;


import br.edu.ifpr.zoologicio.model.AgendaAnimal;


public class AgendaAnimalDAO {

    // CADASTRAR
    // ______________________________________________________
    public static void cadastrar(AgendaAnimal agenda) {
        String sql = "INSERT INTO AgendaAnimal(consulta, banho, medicacao, atividade, animal_id, veterinario_id, rotinaAlimentar_id) "
                + "VALUES (?,?,?,?,?,?,?)";

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, agenda.getConsulta());
            pst.setString(2, agenda.getBanho());
            pst.setString(3, agenda.getMedicacao());
            pst.setString(4, agenda.getAtividade());
            pst.setInt(5, agenda.getFuncionario().getId());
            pst.setInt(6, agenda.getVeterinario().getId());
            pst.setInt(7, agenda.getRotinaAlimentar() != null ? agenda.getRotinaAlimentar().getId() : null);

            pst.executeUpdate();

            try (ResultSet rs = pst.getGeneratedKeys()) {
                if (rs.next()) {
                    agenda.setId(rs.getInt(1));
                }
            }

            System.out.println("AgendaAnimal cadastrada com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // EDITAR
    // ______________________________________________________
    public static void editar(AgendaAnimal agenda) {
        String sql = "UPDATE AgendaAnimal SET consulta=?, banho=?, medicacao=?, atividade=?, "
                + "animal_id=?, veterinario_id=?, rotinaAlimentar_id=? WHERE idAgendaAnimal=?";

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, agenda.getConsulta());
            pst.setString(2, agenda.getBanho());
            pst.setString(3, agenda.getMedicacao());
            pst.setString(4, agenda.getAtividade());
            pst.setInt(5, agenda.getFuncionario().getId());
            pst.setInt(6, agenda.getVeterinario().getId());
            pst.setInt(7, agenda.getRotinaAlimentar() != null ? agenda.getRotinaAlimentar().getId() : null);
            pst.setInt(8, agenda.getId());

            pst.executeUpdate();
            System.out.println("AgendaAnimal atualizada com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    // ______________________________________________________
    public static void delete(int id) {
        String sql = "DELETE FROM AgendaAnimal WHERE idAgendaAnimal=?";

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            pst.executeUpdate();

            System.out.println("AgendaAnimal removida com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // SELECT COMPLETO
    // ______________________________________________________
    public static AgendaAnimal select(int id) {
        String sql = "SELECT idAgendaAnimal, consulta, banho, medicacao, atividade, animal_id, veterinario_id, rotinaAlimentar_id "
                + "FROM AgendaAnimal WHERE idAgendaAnimal=?";

        AgendaAnimal agenda = null;

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                agenda = new AgendaAnimal();
                agenda.setId(rs.getInt("id"));
                agenda.setConsulta(rs.getString("consulta"));
                agenda.setBanho(rs.getString("banho"));
                agenda.setMedicacao(rs.getString("medicacao"));
                agenda.setAtividade(rs.getString("atividade"));

                // Buscar Animal, Veterinário e RotinaAlimentar
                agenda.setAnimal(AnimalDAO.buscarAnimalPorId(rs.getInt("animal_id")));
                agenda.setVeterinario(VeterinarioDAO.buscarVeterinarioPorId(rs.getInt("veterinario_id")));
                agenda.setRotinaAlimentar(RotinaAlimentarDAO.buscarRotinaPorId(rs.getInt("rotinaAlimentar_id")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return agenda;
    }

    // LISTAR COMPLETO
    // ______________________________________________________
    public static ArrayList<AgendaAnimal> listar() {
        ArrayList<AgendaAnimal> agendas = new ArrayList<>();
        String sql = "SELECT idAgendaAnimal, consulta, banho, medicacao, atividade, animal_id, veterinario_id, rotinaAlimentar_id FROM AgendaAnimal";

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sql);
                ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                AgendaAnimal agenda = new AgendaAnimal();
                agenda.setId(rs.getInt("id"));
                agenda.setConsulta(rs.getString("consulta"));
                agenda.setBanho(rs.getString("banho"));
                agenda.setMedicacao(rs.getString("medicacao"));
                agenda.setAtividade(rs.getString("atividade"));

                agenda.setAnimal(AnimalDAO.buscarAnimalPorId(rs.getInt("animal_id")));
                agenda.setVeterinario(VeterinarioDAO.buscarVeterinarioPorId(rs.getInt("veterinario_id")));
                agenda.setRotinaAlimentar(RotinaAlimentarDAO.buscarRotinaPorId(rs.getInt("rotinaAlimentar_id")));

                agendas.add(agenda);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return agendas;
    }

    // METODOS AUXILIARES
    //____________________________________________________________________


    public static AgendaAnimal buscarAgendaAnimalPorID(int id) {
        String sql = "SELECT idAgendaAnimal FROM AgendaAnimais WHERE idAgendaAnimal = ?";
        AgendaAnimal agenda = null;

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    agenda = new AgendaAnimal();
                    agenda.setId(rs.getInt("id"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return agenda;
    }

}
