package br.edu.ifpr.zoologicio.model.dao;

import br.edu.ifpr.zoologicio.model.Animal;
import br.edu.ifpr.zoologicio.model.Area;
import br.edu.ifpr.zoologicio.model.Habitat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HabitatDAO {

    
    // BUSCAR HABITATS POR ÁREA
    // ______________________________________________________
    public static ArrayList<Habitat> buscarHabitatsPorArea(int areaId) {
        ArrayList<Habitat> habitats = new ArrayList<>();

        String sql = "SELECT id, nome, descricao, capacidade FROM habitats WHERE area_id=?";

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, areaId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Habitat habitat = new Habitat();
                habitat.setId(rs.getInt("id"));
                habitat.setNome(rs.getString("nome"));
                habitat.setDescricao(rs.getString("descricao"));
                habitat.setCapacidade(rs.getString("capacidade"));
                habitats.add(habitat);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return habitats;
    }

    // BUSCA HABITATs POR ID
    // ______________________________________________________________

    public static int buscaHabitat_ID(int habitat_id) {

        String sqlHabitat = "SELECT from habitats WHERE id= ?";
        int id = -1;

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(sqlHabitat)) {
            ps.setInt(1, habitat_id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;

    }

    // CADASTRAR
    // ______________________________________________________

    public static void cadastrar(Habitat habitat) {
        String sqlHabitat = "INSERT INTO habitats(nome, descricao, capacidade, area_id) VALUES (?,?,?,?)";

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sqlHabitat, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, habitat.getNome());
            pst.setString(2, habitat.getDescricao());
            pst.setString(3, habitat.getCapacidade());
            pst.setInt(4, habitat.getArea().getId()); // <<< ALTERADO
            pst.executeUpdate();

            try (ResultSet rs = pst.getGeneratedKeys()) {
                if (rs.next()) {
                    habitat.setId(rs.getInt(1));
                } else {
                    throw new SQLException("Falha ao obter ID gerado para Habitat");
                }
            }

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
                pst.setInt(4, habitat.getArea().getId());
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

                // Criar objeto Area e setar dentro de Habitat
                Area area = new Area();
                area.setId(rs.getInt("area_id")); // somente ID
                area = AreaDAO.buscarAreaPorId(rs.getInt("area_id"));
                habitat.setArea(area);

            }

            if (habitat != null) {
                habitat.setAnimais(AnimalDAO.buscarAnimaisPorHabitat(habitat.getId()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return habitat;
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
                Area area = new Area();
                area.setId(rs.getInt("area_id"));
                habitat.setArea(area);

                habitats.add(habitat);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return habitats;
    }

    // LISTAR COM ANIMAIS
    // ____________________________________________________________________

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
                Area area = new Area();
                area.setId(rs.getInt("area_id"));
                habitat.setArea(area);

                // Busca e adiciona os animais vinculados
                habitat.setAnimais(AnimalDAO.buscarAnimaisPorHabitat(habitat.getId()));

                habitats.add(habitat);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return habitats;
    }

    // _________________________________________________________________

}
