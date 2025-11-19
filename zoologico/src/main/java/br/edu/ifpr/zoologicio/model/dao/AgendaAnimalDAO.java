/*
O animal precisa estar cadastrado
O veterinario precisa estar cadastrado
A rotinaAlimentar precisa estar cadastrada
*/

package br.edu.ifpr.zoologicio.model.dao;

import br.edu.ifpr.zoologicio.model.AgendaAnimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AgendaAnimalDAO {

    
    public static int buscaVeterinario_ID(String nomeVeterinario) {

        String sqlVeterinario = "SELECT from agendaAnimais WHERE nome= ?";
        int id = -1;

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(sqlVeterinario)) {

            ps.setString(1, nomeVeterinario);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;

    }

    public static int buscaRotinaAlimentar_ID(String nomeRotinaALimentar) {

        String sqlAgendaAnimal = "SELECT from agendaAnimais WHERE nome= ?";
        int id = -1;

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(sqlAgendaAnimal)) {

            ps.setString(1, nomeRotinaALimentar);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;

    }

    public static void cadastrar(AgendaAnimal agendaAnimal) {

        Connection con = ConnectionFactory.getConnection();

        try {

            String sqlAnimalAgenda = "INSERT INTO agendaAnimais(consulta, banho, medicacao, atividade) VALUES (?,?,?,?)";

            PreparedStatement psAnimalAgenda = con.prepareStatement(sqlAnimalAgenda);

            psAnimalAgenda.setString(1, agendaAnimal.getConsulta());
            psAnimalAgenda.setString(2, agendaAnimal.getBanho());
            psAnimalAgenda.setString(3, agendaAnimal.getMedicacao());
            psAnimalAgenda.setString(4, agendaAnimal.getAtividade());

            psAnimalAgenda.executeUpdate();
            System.out.println("AgendaAnimal inserida com sucesso");

        } catch (Exception e) {

            // TODO: handle exception
            e.printStackTrace();

        }

    }

    public static void editar(AgendaAnimal agendaAnimal) {

        Connection con = ConnectionFactory.getConnection();

        try {

            String sql = "UPDATE agendaAnimais SET consulta=?, banho=?, medicacao=?, atividade=?, WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, agendaAnimal.getConsulta());
            pst.setString(2, agendaAnimal.getBanho());
            pst.setString(3, agendaAnimal.getMedicacao());
            pst.setString(4, agendaAnimal.getAtividade());
            pst.setInt(5, agendaAnimal.getId());

            pst.executeUpdate();
            System.out.println("agendaAnimal atualizada com sucesso");

        } catch (Exception e) {

            // TODO: handle exception
            System.out.println(e.getMessage());

        }

    }

    public void delete(int id) {
        Connection con = ConnectionFactory.getConnection();

        try {

            String sql = "DELETE FROM agendaAnimais WHERE id= ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("agendaAnimal excluida com sucesso");

        } catch (Exception e) {

            // TODO: handle exception
            e.printStackTrace();

        }
    }

    public ArrayList<AgendaAnimal> select(int id) {

        Connection con = ConnectionFactory.getConnection();
        ArrayList<AgendaAnimal> agendaAnimais = new ArrayList<>();

        try {

            String sql = "SELECT * FROM agendaAnimais WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                AgendaAnimal agendaAnimal = new AgendaAnimal();
                agendaAnimal.setId(rs.getInt("id"));
                agendaAnimal.setConsulta("consulta");
                agendaAnimal.setBanho("banho");
                agendaAnimal.setMedicacao("medicacao");
                agendaAnimal.setAtividade("atividade");
                agendaAnimais.add(agendaAnimal);

            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }

        return agendaAnimais;
    }

    /*
     * public ArrayList<AgendaAnimal> listar() {
     * 
     * Connection con = ConnectionFactory.getConnection();
     * 
     * ArrayList<AgendaAnimal> agendaAnimais = new ArrayList<>();
     * 
     * try {
     * 
     * String sql = "SELECT * FROM agendaAnimais";
     * PreparedStatement pst = con.prepareStatement(sql);
     * ResultSet rs = pst.executeQuery();
     * 
     * while (rs.next()) {
     * 
     * AgendaAnimal agendaAnimal = new AgendaAnimal();
     * agendaAnimal.setId(rs.getInt("id"));
     * agendaAnimal.setConsulta("consulta");
     * agendaAnimal.setBanho("banho");
     * agendaAnimal.setMedicacao("medicacao");
     * agendaAnimal.setAtividade("atividade");
     * agendaAnimais.add(agendaAnimal);
     * 
     * }
     * 
     * } catch (Exception e) {
     * // TODO: handle exception
     * System.out.println(e.getMessage());
     * }
     * 
     * return agendaAnimais;
     * }
     */

}
