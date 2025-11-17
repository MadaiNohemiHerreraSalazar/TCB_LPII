// A habitat precisa ter a Area cadastrada con antecedencia.✅
// - Arrumar cadastro para cadastrar a area também.
// Não precisa ter animais, pode ser cadastrada sem.

package br.edu.ifpr.zoologicio.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.edu.ifpr.zoologicio.model.Habitat;

public class HabitatDAO {

      public static int buscaHabitat_ID(String nomeHabitat) {

        Connection con = ConnectionFactory.getConnection();

        String sqlHabitat = "SELECT from agendaAnimais WHERE nome= ?";
        int id = -1;

        try {
            PreparedStatement ps = con.prepareStatement(sqlHabitat);
            ps.setString(1, nomeHabitat);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;

    }


    public static void cadastrar(Habitat habitat) {

        Connection con = ConnectionFactory.getConnection();

        String sqlHabitat = "INSERT INTO habitats(nome, descricao, capacidade) VALUES (?,?,?)";

        try {

            PreparedStatement psVeterinario = con.prepareStatement(sqlHabitat);

            psVeterinario.setString(1, habitat.getNome());
            psVeterinario.setString(2, habitat.getDescricao());
            psVeterinario.setString(3, habitat.getCapacidade());

            psVeterinario.executeUpdate();
            System.out.println("Habitat inserido com sucesso");

        } catch (Exception e) {

            // TODO: handle exception
            e.printStackTrace();

        }

    }

    public static void editar(Habitat habitat) {

        Connection con = ConnectionFactory.getConnection();

        try {

            String sql = "UPDATE habitats SET nome=?, cpf=?, descricao=?, capacidade=?, WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, habitat.getNome());
            pst.setString(2, habitat.getDescricao());
            pst.setString(3, habitat.getCapacidade());
            pst.setInt(4, habitat.getId());

            pst.executeUpdate();
            System.out.println("Habitat atualizado com sucesso");

        } catch (Exception e) {

            // TODO: handle exception
            System.out.println(e.getMessage());

        }

    }

    public void delete(int id) {
        Connection con = ConnectionFactory.getConnection();

        try {

            String sql = "DELETE FROM habitats WHERE id= ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Habitat excluido com sucesso");

        } catch (Exception e) {

            // TODO: handle exception
            e.printStackTrace();

        }
    }

    public ArrayList<Habitat> select(int id) {

        Connection con = ConnectionFactory.getConnection();
        ArrayList<Habitat> habitats = new ArrayList<>();

        try {

            String sql = "SELECT * FROM habitats WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Habitat habitat = new Habitat();
                habitat.setId(rs.getInt("id"));
                habitat.setNome("nome");
                habitat.setDescricao("descricao");
                habitat.setCapacidade("capacidade");
                habitats.add(habitat);

            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }

        return habitats;
    }

    public ArrayList<Habitat> listar() {

        Connection con = ConnectionFactory.getConnection();

        ArrayList<Habitat> habitats = new ArrayList<>();

        try {

            String sql = "SELECT * FROM habitats";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Habitat habitat = new Habitat();
                habitat.setId(rs.getInt("id"));
                habitat.setNome("nome");
                habitat.setDescricao("descricao");
                habitat.setCapacidade("capacidade");
                habitats.add(habitat);

            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }

        return habitats;
    }

}
