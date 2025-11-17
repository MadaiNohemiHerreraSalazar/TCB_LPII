//Habitat e Veterinario precisam ser cadastrados con antecedencia✅

package br.edu.ifpr.zoologicio.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.edu.ifpr.zoologicio.model.Animal;

public class AnimalDAO {

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

    public static int buscaVeterinario_ID(String nomeVeterinario) {

        Connection con = ConnectionFactory.getConnection();

        String sqlVeterinario = "SELECT from agendaAnimais WHERE nome= ?";
        int id = -1;

        try {
            PreparedStatement ps = con.prepareStatement(sqlVeterinario);
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

    public static void cadastrar(Animal animal) {

        Connection con = ConnectionFactory.getConnection();

        String sqlAnimal = "INSERT INTO animais(nome, descricao, historia, idade, genero, peso, altura, saude) VALUES (?,?,?,?,?,?,?,?)";

        try {

            PreparedStatement psAnimal = con.prepareStatement(sqlAnimal);

            psAnimal.setString(1, animal.getNome());
            psAnimal.setString(2, animal.getDescricao());
            psAnimal.setString(3, animal.getHistoria());
            psAnimal.setString(4, animal.getIdade());
            psAnimal.setString(5, animal.getGenero());
            psAnimal.setString(6, animal.getPeso());
            psAnimal.setString(7, animal.getAltura());
            psAnimal.setString(8, animal.getSaude());

            psAnimal.executeUpdate();
            System.out.println("Animal inserido com sucesso");

        } catch (Exception e) {

            // TODO: handle exception
            e.printStackTrace();

        }

    }

    public static void editar(Animal animal) {

        Connection con = ConnectionFactory.getConnection();

        try {

            String sql = "UPDATE animais SET nome=?,  descricao=?, historia=?, idade=?, genero=?, peso=?, altura=?, saude=? WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, animal.getNome());
            pst.setString(2, animal.getDescricao());
            pst.setString(3, animal.getHistoria());
            pst.setString(4, animal.getIdade());
            pst.setString(5, animal.getGenero());
            pst.setString(6, animal.getPeso());
            pst.setString(7, animal.getAltura());
            pst.setString(8, animal.getSaude());
            pst.setInt(9, animal.getId());

            pst.executeUpdate();
            System.out.println("Animal atualizado com sucesso");

        } catch (Exception e) {

            // TODO: handle exception
            System.out.println(e.getMessage());

        }

    }

    public void delete(int id) {
        Connection con = ConnectionFactory.getConnection();

        try {

            String sql = "DELETE FROM animais WHERE id= ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Animal excluido com sucesso");

        } catch (Exception e) {

            // TODO: handle exception
            e.printStackTrace();

        }
    }

    public ArrayList<Animal> select(int id) {

        Connection con = ConnectionFactory.getConnection();
        ArrayList<Animal> animais = new ArrayList<>();

        try {

            String sql = "SELECT * FROM animais WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Animal animal = new Animal();
                animal.setId(rs.getInt("id"));
                animal.setNome("nome");
                animal.setDescricao("descricao");
                animal.setHistoria("historia");
                animal.setIdade("idade");
                animal.setGenero("genero");
                animal.setPeso("peso");
                animal.setAltura("altura");
                animal.setSaude("saude");
                animais.add(animal);

            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }

        return animais;
    }

    public ArrayList<Animal> listar() {

        Connection con = ConnectionFactory.getConnection();

        ArrayList<Animal> animais = new ArrayList<>();

        try {

            String sql = "SELECT * FROM animais";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Animal animal = new Animal();
                animal.setId(rs.getInt("id"));
                animal.setNome("nome");
                animal.setDescricao("descricao");
                animal.setHistoria("historia");
                animal.setIdade("idade");
                animal.setGenero("genero");
                animal.setPeso("peso");
                animal.setAltura("altura");
                animal.setSaude("saude");

            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }

        return animais;
    }

}
