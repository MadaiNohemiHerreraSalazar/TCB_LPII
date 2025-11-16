//O animal precisa estar cadastrado
//O veterinario precisa estar cadastrdo ou ser cadastrado na hora
//A rotinaAlimentar precisa estar cadastrdo ou ser cadastrado na hora


package br.edu.ifpr.zoologicio.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.edu.ifpr.zoologicio.model.AgendaAnimal;

public class AgendaAnimalDAO {

    public static void cadastrar(AgendaAnimal agendaAnimal) {

        Connection con = ConnectionFactory.getConnection();

        String sqlEndereco = "INSERT INTO agendaAnimais(consulta, banho, medicacao, atividade) VALUES (?,?,?,?)";

        try {

            PreparedStatement psAnimalAgenda = con.prepareStatement(sqlEndereco);

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

    public ArrayList<AgendaAnimal> listar() {

        Connection con = ConnectionFactory.getConnection();

        ArrayList<AgendaAnimal> agendaAnimais = new ArrayList<>();

        try {

            String sql = "SELECT * FROM agendaAnimais";
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

}
