package br.edu.ifpr.zoologicio.model.dao;

import br.edu.ifpr.zoologicio.model.Alimento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AlimentoDAO {

    // CADASTRAR
    // ______________________________________________________

    public static void cadastrar(Alimento alimento) {
        String sql = "INSERT INTO alimentos(nome, validade, estoque) VALUES (?,?,?)";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, alimento.getNome());
            pst.setString(2, alimento.getValidade());
            pst.setString(3, alimento.getEstoque());
            pst.executeUpdate();

            try (ResultSet rs = pst.getGeneratedKeys()) {
                if (rs.next()) {
                    alimento.setId(rs.getInt(1));
                } else {
                    throw new SQLException("Falha ao obter ID gerado para Alimento");
                }
            }

            System.out.println("Alimento cadastrado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // EDITAR
    // ______________________________________________________

    public static void editar(Alimento alimento) {
        String sql = "UPDATE alimentos SET nome=?, validade=?, estoque=? WHERE id=?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, alimento.getNome());
            pst.setString(2, alimento.getValidade());
            pst.setString(3, alimento.getEstoque());
            pst.setInt(4, alimento.getId());
            pst.executeUpdate();

            System.out.println("Alimento atualizado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // DELETE
    // ______________________________________________________

    public static void delete(int id) {
        String sql = "DELETE FROM alimentos WHERE id=?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            pst.executeUpdate();

            System.out.println("Alimento excluído com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // SELECT COMPLETO (Buscar por ID)
    // ______________________________________________________

    public Alimento selectCompleto(int id) {
        String sql = "SELECT id, nome, validade, estoque FROM alimentos WHERE id=?";
        Alimento alimento = null;

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                alimento = new Alimento();
                alimento.setId(rs.getInt("id"));
                alimento.setNome(rs.getString("nome"));
                alimento.setValidade(rs.getString("validade"));
                alimento.setEstoque(rs.getString("estoque"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return alimento;
    }

    // LISTAR SIMPLES
    // ______________________________________________________

    public ArrayList<Alimento> listarSimples() {
        String sql = "SELECT id, nome, validade, estoque FROM alimentos ORDER BY nome";

        ArrayList<Alimento> alimentos = new ArrayList<>();

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Alimento alimento = new Alimento();
                alimento.setId(rs.getInt("id"));
                alimento.setNome(rs.getString("nome"));
                alimento.setValidade(rs.getString("validade"));
                alimento.setEstoque(rs.getString("estoque"));
                alimentos.add(alimento);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar alimentos: " + e.getMessage());
        }

        return alimentos;
    }
}
