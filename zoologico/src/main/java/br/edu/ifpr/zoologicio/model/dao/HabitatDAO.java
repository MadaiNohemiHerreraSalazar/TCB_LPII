package br.edu.ifpr.zoologicio.model.dao;

import br.edu.ifpr.zoologicio.model.Animal;
import br.edu.ifpr.zoologicio.model.Habitat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HabitatDAO {

    // CADASTRAR 
    // ______________________________________________________

    public static void cadastrar(Habitat habitat) {

        String sqlHabitat = "INSERT INTO habitats(nome, descricao, capacidade, area_id) VALUES (?,?,?,?)";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sqlHabitat, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, habitat.getNome());
            pst.setString(2, habitat.getDescricao());
            pst.setString(3, habitat.getCapacidade());
            pst.setInt(4, habitat.getArea());

            pst.executeUpdate();

            try (ResultSet rs = pst.getGeneratedKeys()) {
                if (rs.next()) {
                    habitat.setId(rs.getInt(1));
                } else {
                    throw new SQLException("Falha ao obter ID gerado para Habitat");
                }
            }

            // Se houver animais vinculados, cadastra
            if (habitat.getAnimais() != null && !habitat.getAnimais().isEmpty()) {
                if (!cadastrarAnimais(con, habitat.getAnimais(), habitat.getId())) {
                    throw new SQLException("Erro ao cadastrar animais do habitat");
                }
            }

            System.out.println("Habitat cadastrado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // CADASTRAR ANIMAIS 
    // ______________________________________________________

    private static boolean cadastrarAnimais(Connection con, ArrayList<Animal> animais, int habitatId) {
        String sql = "INSERT INTO animal_habitat(animal_id, habitat_id) VALUES (?, ?)";

        try {
            for (Animal animal : animais) {
                try (PreparedStatement pst = con.prepareStatement(sql)) {
                    pst.setInt(1, animal.getId());
                    pst.setInt(2, habitatId);
                    pst.executeUpdate();
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    // EDITAR 
    // ______________________________________________________

    public static void editar(Habitat habitat) {
        String sqlHabitat = "UPDATE habitats SET nome=?, descricao=?, capacidade=?, area_id=? WHERE id=?";
        String sqlDeleteAnimais = "DELETE FROM animal_habitat WHERE habitat_id=?";
        String sqlInsertAnimais = "INSERT INTO animal_habitat(animal_id, habitat_id) VALUES (?, ?)";

        try (Connection con = ConnectionFactory.getConnection()) {

            // Atualiza habitat
            try (PreparedStatement pst = con.prepareStatement(sqlHabitat)) {
                pst.setString(1, habitat.getNome());
                pst.setString(2, habitat.getDescricao());
                pst.setString(3, habitat.getCapacidade());
                pst.setInt(4, habitat.getArea());
                pst.setInt(5, habitat.getId());
                pst.executeUpdate();
            }

            // Exclui animais antigos
            try (PreparedStatement pst = con.prepareStatement(sqlDeleteAnimais)) {
                pst.setInt(1, habitat.getId());
                pst.executeUpdate();
            }

            // Insere novos animais
            if (habitat.getAnimais() != null && !habitat.getAnimais().isEmpty()) {
                try (PreparedStatement pst = con.prepareStatement(sqlInsertAnimais)) {
                    for (Animal animal : habitat.getAnimais()) {
                        pst.setInt(1, animal.getId());
                        pst.setInt(2, habitat.getId());
                        pst.executeUpdate();
                    }
                }
            }

            System.out.println("Habitat atualizado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // DELETE 
    // ______________________________________________________

    public static void delete(int id) {
        String sqlDeleteAnimais = "DELETE FROM animal_habitat WHERE habitat_id=?";
        String sqlDeleteHabitat = "DELETE FROM habitats WHERE id=?";

        try (Connection con = ConnectionFactory.getConnection()) {

            try (PreparedStatement pst = con.prepareStatement(sqlDeleteAnimais)) {
                pst.setInt(1, id);
                pst.executeUpdate();
            }

            try (PreparedStatement pst = con.prepareStatement(sqlDeleteHabitat)) {
                pst.setInt(1, id);
                pst.executeUpdate();
            }

            System.out.println("Habitat excluído com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // SELECT COMPLETO
    // ______________________________________________________

    public Habitat select(int id) {
        Habitat habitat = null;

        String sql = "SELECT id, nome, descricao, capacidade, area_id FROM habitats WHERE id=?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                habitat = new Habitat();
                habitat.setId(rs.getInt("id"));
                habitat.setNome(rs.getString("nome"));
                habitat.setDescricao(rs.getString("descricao"));
                habitat.setCapacidade(rs.getString("capacidade"));
                habitat.setArea(rs.getInt("area_id"));
            }

            if (habitat != null) {
                habitat.setAnimais(buscarAnimaisPorHabitat(habitat.getId()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return habitat;
    }


    // BUSCAR ANIMAIS 
    // ______________________________________________________

    private ArrayList<Animal> buscarAnimaisPorHabitat(int habitatId) {
        ArrayList<Animal> animais = new ArrayList<>();

        String sql = "SELECT a.id, a.nome FROM animais a " +
                "JOIN animal_habitat ah ON a.id = ah.animal_id " +
                "WHERE ah.habitat_id = ?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, habitatId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Animal animal = new Animal();
                animal.setId(rs.getInt("id"));
                animal.setNome(rs.getString("nome"));
                animais.add(animal);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return animais;
    }


    // LISTAR 
    // ______________________________________________________

    public ArrayList<Habitat> listar() {
        ArrayList<Habitat> habitats = new ArrayList<>();
        String sql = "SELECT id, nome, descricao, capacidade, area_id FROM habitats";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Habitat habitat = new Habitat();
                habitat.setId(rs.getInt("id"));
                habitat.setNome(rs.getString("nome"));
                habitat.setDescricao(rs.getString("descricao"));
                habitat.setCapacidade(rs.getString("capacidade"));
                habitat.setArea(rs.getInt("area_id"));

                habitats.add(habitat);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return habitats;
    }

    // LISTAR COM ANIMAIS

    public ArrayList<Habitat> listarComAnimais() {
    ArrayList<Habitat> habitats = new ArrayList<>();
    String sql = "SELECT id, nome, descricao, capacidade, area_id FROM habitats";

    try (Connection con = ConnectionFactory.getConnection();
         PreparedStatement pst = con.prepareStatement(sql);
         ResultSet rs = pst.executeQuery()) {

        while (rs.next()) {
            Habitat habitat = new Habitat();
            habitat.setId(rs.getInt("id"));
            habitat.setNome(rs.getString("nome"));
            habitat.setDescricao(rs.getString("descricao"));
            habitat.setCapacidade(rs.getString("capacidade"));
            habitat.setArea(rs.getInt("area_id"));

            // Busca e adiciona os animais vinculados
            habitat.setAnimais(buscarAnimaisPorHabitat(habitat.getId()));

            habitats.add(habitat);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return habitats;
}

//_________________________________________________________________


}
