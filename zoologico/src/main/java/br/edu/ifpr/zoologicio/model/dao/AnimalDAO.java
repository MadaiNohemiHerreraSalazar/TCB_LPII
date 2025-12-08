
package br.edu.ifpr.zoologicio.model.dao;

import br.edu.ifpr.zoologicio.model.Animal;
import br.edu.ifpr.zoologicio.model.Habitat;
import java.sql.*;
import java.util.ArrayList;

public class AnimalDAO {

    // CADASTRAR
    // _______________________________________________________________________

    public static void cadastrar(Animal animal, int veterinarioId) {
        String sqlAnimal = "INSERT INTO animal " +
                "(nome, descricao, historia, especie, idade, genero, peso, altura, saude, habitat_id) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?)";

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sqlAnimal, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, animal.getNome());
            pst.setString(2, animal.getDescricao());
            pst.setString(3, animal.getHistoria());
            pst.setString(4, animal.getEspecie());
            pst.setString(5, animal.getIdade());
            pst.setString(6, animal.getGenero());
            pst.setString(7, animal.getPeso());
            pst.setString(8, animal.getAltura());
            pst.setString(9, animal.getSaude());
            pst.setInt(10, animal.getHabitat().getId());

            pst.executeUpdate();

            try (ResultSet rs = pst.getGeneratedKeys()) {
                if (rs.next()) {
                    animal.setId(rs.getInt(1));
                }
            }

            cadastrarAnimalVeterinario(con, animal.getId(), veterinarioId);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // CADASTRAR ANIMAL_VETERINARIO
    // ____________________________________________________________________

    private static void cadastrarAnimalVeterinario(Connection con, int animalId, int veterinarioId) {
        String sql = "INSERT INTO animal_veterinario (animal_id, veterinario_id) VALUES (?,?)";

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, animalId);
            pst.setInt(2, veterinarioId);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // EDITAR
    // ______________________________________________________________________

    public static void editar(Animal animal, int veterinarioId) {
        String sqlUpdateAnimal = "UPDATE animail SET nome=?, descricao=?, historia=?, especie=?, idade=?, " +
                "genero=?, peso=?, altura=?, saude=?, habitat_id=? WHERE id=?";
        String sqlDeleteAV = "DELETE FROM animal_veterinario WHERE animal_id=?";

        try (Connection con = ConnectionFactory.getConnection()) {

            // Atualiza os dados do animal
            try (PreparedStatement pst = con.prepareStatement(sqlUpdateAnimal)) {
                pst.setString(1, animal.getNome());
                pst.setString(2, animal.getDescricao());
                pst.setString(3, animal.getHistoria());
                pst.setString(4, animal.getEspecie());
                pst.setString(5, animal.getIdade());
                pst.setString(6, animal.getGenero());
                pst.setString(7, animal.getPeso());
                pst.setString(8, animal.getAltura());
                pst.setString(9, animal.getSaude());
                pst.setInt(10, animal.getHabitat().getId());
                pst.setInt(11, animal.getId());
                pst.executeUpdate();
            }

            // Remove os vínculos antigos
            try (PreparedStatement pst = con.prepareStatement(sqlDeleteAV)) {
                pst.setInt(1, animal.getId());
                pst.executeUpdate();
            }

            // Cria o novo vínculo com veterinário
            cadastrarAnimalVeterinario(con, animal.getId(), veterinarioId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    // __________________________________________________________________________

    public static void delete(int id) {
        String sqlDeleteAV = "DELETE FROM animal_veterinario WHERE animal_id=?";
        String sqlDeleteAnimal = "DELETE FROM animail WHERE animal_id=?";

        try (Connection con = ConnectionFactory.getConnection()) {

            try (PreparedStatement pst = con.prepareStatement(sqlDeleteAV)) {
                pst.setInt(1, id);
                pst.executeUpdate();
            }

            try (PreparedStatement pst = con.prepareStatement(sqlDeleteAnimal)) {
                pst.setInt(1, id);
                pst.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // SELECT COMPLETO
    // ____________________________________________________________________________

    public static Animal select(int id) {
        String sql = "SELECT a.*, h.id AS habitat_id, h.nome AS habitat_nome, " +
                "v.id AS vet_id, v.nome AS vet_nome " +
                "FROM animail a " +
                "LEFT JOIN habitat h ON a.habitat_id = h.id " +
                "LEFT JOIN animal_veterinario av ON a.animal_id = av.animal_id " +
                "LEFT JOIN veterinario v ON av.veterinario_id = v.id " +
                "WHERE a.animal_id = ?";

        Animal animal = null;

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                animal = new Animal();
                animal.setId(rs.getInt("id"));
                animal.setNome(rs.getString("nome"));
                animal.setDescricao(rs.getString("descricao"));
                animal.setHistoria(rs.getString("historia"));
                animal.setEspecie(rs.getString("especie"));
                animal.setIdade(rs.getString("idade"));
                animal.setGenero(rs.getString("genero"));
                animal.setPeso(rs.getString("peso"));
                animal.setAltura(rs.getString("altura"));
                animal.setSaude(rs.getString("saude"));

                Habitat habitat = new Habitat();
                habitat.setId(rs.getInt("habitat_id"));
                habitat.setNome(rs.getString("habitat_nome"));
                animal.setHabitat(habitat);

                // Apenas exibe dados do veterinário (se existir)
                if (rs.getInt("vet_id") != 0) {
                    System.out.println("Veterinário responsável: " +
                            rs.getInt("vet_id") + " - " + rs.getString("vet_nome"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return animal;
    }

    // LISTAR 
    // ______________________________________________________________________________

    public static ArrayList<Animal> listar() {
        ArrayList<Animal> animais = new ArrayList<>();

        String sql = "SELECT a.animal_id, a.nome, a.descricao, a.historia, a.especie, a.idade, " +
                "a.genero, a.peso, a.altura, a.saude, h.id AS habitat_id, h.nome AS habitat_nome " +
                "FROM animail a " +
                "LEFT JOIN habitat h ON a.habitat_id = h.habitat_id " +
                "ORDER BY a.nome";

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sql);
                ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Animal animal = new Animal();
                animal.setId(rs.getInt("animal_id"));
                animal.setNome(rs.getString("nome"));
                animal.setDescricao(rs.getString("descricao"));
                animal.setHistoria(rs.getString("historia"));
                animal.setEspecie(rs.getString("especie"));
                animal.setIdade(rs.getString("idade"));
                animal.setGenero(rs.getString("genero"));
                animal.setPeso(rs.getString("peso"));
                animal.setAltura(rs.getString("altura"));
                animal.setSaude(rs.getString("saude"));

                // Criando o objeto Habitat com dados básicos (id e nome)
                Habitat habitat = new Habitat();
                habitat.setId(rs.getInt("habitat_id"));
                habitat.setNome(rs.getString("habitat_nome"));
                animal.setHabitat(habitat);

                animais.add(animal);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar animais: " + e.getMessage());
        }

        return animais;
    }

    // MÉTODOS AUXILIARES
    // -------------------------------------------------------------------------------

    // BUSCAR ANIMAIS POR VETERINARIO
    // ______________________________________________________________________________

    public static ArrayList<Animal> buscarAnimaisPorVeterinario(int veterinarioId) {
        ArrayList<Animal> animais = new ArrayList<>();

        String sql = "SELECT a.animal_id, a.nome, a.especie FROM animail a " +
                "JOIN animal_veterinario av ON a.animal_id = av.animal_id " +
                "WHERE av.veterinario_id=?";

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, veterinarioId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Animal animal = new Animal();
                animal.setId(rs.getInt("animal_id"));
                animal.setNome(rs.getString("nome"));
                animal.setEspecie(rs.getString("especie"));
                animais.add(animal);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return animais;
    }

    // BUSCAR ANIMAIS POR HABITAT
    // ______________________________________________________________________

    public static ArrayList<Animal> buscarAnimaisPorHabitat(int habitatId) {
        ArrayList<Animal> animais = new ArrayList<>();

        String sql = "SELECT a.animal_id, a.nome FROM animail a " +
                "JOIN animal_habitat ah ON a.animal_id = ah.animal_id " +
                "WHERE ah.habitat_id = ?";

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, habitatId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Animal animal = new Animal();
                animal.setId(rs.getInt("animal_id"));
                animal.setNome(rs.getString("nome"));
                animais.add(animal);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return animais;
    }

    // BUSCAR ANIMAL POR ID - DEVOLVE ANIMAL
    // ___________________________________________________________________________

    public static Animal buscarAnimalPorId(int id) {
        Animal animal = null;
        String sql = "SELECT animal_id, nome FROM animail WHERE animal_id=?";

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                animal = new Animal();
                animal.setId(rs.getInt("animal_id"));
                animal.setNome(rs.getString("nome"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return animal;
    }

}
