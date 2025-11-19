package br.edu.ifpr.zoologicio.model.dao;

import br.edu.ifpr.zoologicio.model.Animal;
import br.edu.ifpr.zoologicio.model.Veterinario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;

public class VeterinarioDAO {

    // CADASTRAR VETERINARIO
    // ______________________________________________________

    public static void cadastrar(Veterinario veterinario) {

        String sqlVeterinario = "INSERT INTO veterinarios(nome, cpf, email, especializacao) VALUES (?,?,?,?)";

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sqlVeterinario, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, veterinario.getNome());
            pst.setString(2, veterinario.getCpf());
            pst.setString(3, veterinario.getEmail());
            pst.setString(4, veterinario.getEspecializacao());
            pst.executeUpdate();

            try (ResultSet rs = pst.getGeneratedKeys()) {
                if (rs.next()) {
                    veterinario.setId(rs.getInt(1));
                } else {
                    throw new SQLException("Falha ao obter ID gerado para Veterinario");
                }
            }

            if (!cadastrarAnimais(con, veterinario.getAnimais(), veterinario)) {
                throw new SQLException("Erro ao cadastrar animais do Veterinário");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // CADASTRAR ANIMAIS
    // ______________________________________________________

    private static boolean cadastrarAnimais(Connection con, ArrayList<Animal> animais, Veterinario veterinario) {
        String sql = "INSERT INTO animal_veterinario(animal_id, veterinario_id) VALUES (?,?)";

        try {
            for (Animal animal : animais) {
                try (PreparedStatement pst = con.prepareStatement(sql)) {
                    pst.setInt(1, animal.getId());
                    pst.setInt(2, veterinario.getId());
                    pst.executeUpdate();
                }
            }
            System.out.println("Animais vinculados com sucesso!");
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // EDITAR
    // ______________________________________________________

    public static void editar(Veterinario veterinario) {

        String sqlUpdateVet = "UPDATE veterinarios SET nome=?, cpf=?, email=?, especializacao=? WHERE id=?";
        String sqlDeleteAnimais = "DELETE FROM animal_veterinario WHERE veterinario_id=?";
        String sqlInsertAnimais = "INSERT INTO animal_veterinario(animal_id, veterinario_id) VALUES (?,?)";

        try (Connection con = ConnectionFactory.getConnection()) {

            // Atualiza os dados do veterinário
            try (PreparedStatement pst = con.prepareStatement(sqlUpdateVet)) {
                pst.setString(1, veterinario.getNome());
                pst.setString(2, veterinario.getCpf());
                pst.setString(3, veterinario.getEmail());
                pst.setString(4, veterinario.getEspecializacao());
                pst.setInt(5, veterinario.getId());
                pst.executeUpdate();
            }

            // Remove os animais antigos
            try (PreparedStatement pst = con.prepareStatement(sqlDeleteAnimais)) {
                pst.setInt(1, veterinario.getId());
                pst.executeUpdate();
            }

            // Insere os animais novos
            try (PreparedStatement pst = con.prepareStatement(sqlInsertAnimais)) {
                for (Animal animal : veterinario.getAnimais()) {
                    pst.setInt(1, animal.getId());
                    pst.setInt(2, veterinario.getId());
                    pst.executeUpdate();
                }
            }

            System.out.println("Veterinário e animais atualizados com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // DELETE
    // ______________________________________________________

    public static void delete(int id) {

        String sqlDelete = "DELETE FROM veterinarios WHERE id=?";

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sqlDelete)) {

            pst.setInt(1, id);
            pst.executeUpdate();

            System.out.println("Veterinário excluído com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // SELECT COMPLETO (com animais)
    // ______________________________________________________

    public static Veterinario select(int id) {

        String sql = "SELECT * FROM veterinarios WHERE id=?";
        Veterinario veterinario = null;

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                veterinario = new Veterinario();
                veterinario.setId(rs.getInt("id"));
                veterinario.setNome(rs.getString("nome"));
                veterinario.setCpf(rs.getString("cpf"));
                veterinario.setEmail(rs.getString("email"));
                veterinario.setEspecializacao(rs.getString("especializacao"));
                veterinario.setAnimais(buscarAnimaisPorVeterinario(veterinario.getId()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return veterinario;
    }

    // BUSCAR ANIMAIS
    // ______________________________________________________

    public static ArrayList<Animal> buscarAnimaisPorVeterinario(int veterinarioId) {
        ArrayList<Animal> animais = new ArrayList<>();

        String sql = "SELECT a.id, a.nome, a.especie FROM animais a " +
                "JOIN animal_veterinario av ON a.id = av.animal_id " +
                "WHERE av.veterinario_id=?";

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, veterinarioId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Animal animal = new Animal();
                animal.setId(rs.getInt("id"));
                animal.setNome(rs.getString("nome"));
                animal.setEspecie(rs.getString("especie"));
                animais.add(animal);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return animais;
    }

    // LISTAR
    // ______________________________________________________

    public static ArrayList<Veterinario> listar() {
        ArrayList<Veterinario> veterinarios = new ArrayList<>();

        String sql = "SELECT id, nome, cpf, email, especializacao FROM veterinarios";

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sql);
                ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Veterinario veterinario = new Veterinario();
                veterinario.setId(rs.getInt("id"));
                veterinario.setNome(rs.getString("nome"));
                veterinario.setCpf(rs.getString("cpf"));
                veterinario.setEmail(rs.getString("email"));
                veterinario.setEspecializacao(rs.getString("especializacao"));
                veterinarios.add(veterinario);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar veterinários: " + e.getMessage());
        }

        return veterinarios;
    }

     public static ArrayList<Veterinario> listarComAnimais() {
        ArrayList<Veterinario> veterinarios = new ArrayList<>();
        String sql = "SELECT * FROM veterinarios";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Veterinario veterinario = new Veterinario();
                veterinario.setId(rs.getInt("id"));
                veterinario.setNome(rs.getString("nome"));
                veterinario.setCpf(rs.getString("cpf"));
                veterinario.setEmail(rs.getString("email"));
                veterinario.setEspecializacao(rs.getString("especializacao"));
                veterinario.setAnimais(buscarAnimaisPorVeterinario(veterinario.getId()));
                veterinarios.add(veterinario);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return veterinarios;
    }

}


