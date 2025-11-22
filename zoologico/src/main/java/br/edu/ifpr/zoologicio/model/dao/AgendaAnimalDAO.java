package br.edu.ifpr.zoologicio.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;

import br.edu.ifpr.zoologicio.model.AgendaAnimal;
import br.edu.ifpr.zoologicio.model.Animal;
import br.edu.ifpr.zoologicio.model.Veterinario;
import br.edu.ifpr.zoologicio.model.RotinaAlimentar;

public class AgendaAnimalDAO {

    // CADASTRAR
    // ______________________________________________________
    public static void cadastrar(AgendaAnimal agenda) {
        String sql = "INSERT INTO agendaAnimal(consulta, banho, medicacao, atividade, animal_id, veterinario_id, rotinaAlimentar_id) "
                   + "VALUES (?,?,?,?,?,?,?)";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, agenda.getConsulta());
            pst.setString(2, agenda.getBanho());
            pst.setString(3, agenda.getMedicacao());
            pst.setString(4, agenda.getAtividade());
            pst.setInt(5, agenda.getAnimal().getId());
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
        String sql = "UPDATE agendaAnimal SET consulta=?, banho=?, medicacao=?, atividade=?, "
                   + "animal_id=?, veterinario_id=?, rotinaAlimentar_id=? WHERE id=?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, agenda.getConsulta());
            pst.setString(2, agenda.getBanho());
            pst.setString(3, agenda.getMedicacao());
            pst.setString(4, agenda.getAtividade());
            pst.setInt(5, agenda.getAnimal().getId());
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
        String sql = "DELETE FROM agendaAnimal WHERE id=?";

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
    public AgendaAnimal selectCompleto(int id) {
        String sql = "SELECT id, consulta, banho, medicacao, atividade, animal_id, veterinario_id, rotinaAlimentar_id "
                   + "FROM agendaAnimal WHERE id=?";

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
                agenda.setAnimal(buscarAnimalPorId(rs.getInt("animal_id")));
                agenda.setVeterinario(buscarVeterinarioPorId(rs.getInt("veterinario_id")));
                agenda.setRotinaAlimentar(buscarRotinaPorId(rs.getInt("rotinaAlimentar_id")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return agenda;
    }

    // LISTAR COMPLETO
    // ______________________________________________________
    public ArrayList<AgendaAnimal> listarCompleto() {
        ArrayList<AgendaAnimal> agendas = new ArrayList<>();
        String sql = "SELECT id, consulta, banho, medicacao, atividade, animal_id, veterinario_id, rotinaAlimentar_id FROM agendaAnimal";

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

                agenda.setAnimal(buscarAnimalPorId(rs.getInt("animal_id")));
                agenda.setVeterinario(buscarVeterinarioPorId(rs.getInt("veterinario_id")));
                agenda.setRotinaAlimentar(buscarRotinaPorId(rs.getInt("rotinaAlimentar_id")));

                agendas.add(agenda);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return agendas;
    }

    // MÉTODOS AUXILIARES
    // ______________________________________________________
    private Animal buscarAnimalPorId(int id) {
        Animal animal = null;
        String sql = "SELECT id, nome FROM animais WHERE id=?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                animal = new Animal();
                animal.setId(rs.getInt("id"));
                animal.setNome(rs.getString("nome"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return animal;
    }

    private Veterinario buscarVeterinarioPorId(int id) {
        Veterinario vet = null;
        String sql = "SELECT id, nome, email FROM veterinarios WHERE id=?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                vet = new Veterinario();
                vet.setId(rs.getInt("id"));
                vet.setNome(rs.getString("nome"));
                vet.setEmail(rs.getString("email"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vet;
    }

    private RotinaAlimentar buscarRotinaPorId(int id) {
        RotinaAlimentar rotina = null;
        String sql = "SELECT id, data, hora, quantidadeAlimento FROM rotinasAlimentares WHERE id=?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                rotina = new RotinaAlimentar();
                rotina.setId(rs.getInt("id"));
                rotina.setData(rs.getString("data"));
                rotina.setHora(rs.getString("hora"));
                rotina.setQuantidadeAlimento(rs.getString("quantidadeAlimento"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rotina;
    }

}
