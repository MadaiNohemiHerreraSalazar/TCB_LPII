//A area não precisa dos funcionarios ou habitats para ser cadastrado

package br.edu.ifpr.zoologicio.model.dao;

import br.edu.ifpr.zoologicio.model.Area;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AreaDAO {

    // BUSCAR AREA
    // ___________________________________________________________________

    public static Area buscarAreaPorId(int id) {
        String sql = "SELECT id, nome, descricao FROM areas WHERE id = ?";
        Area area = null;

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                area = new Area();
                area.setId(rs.getInt("id"));
                area.setNome(rs.getString("nome"));
                area.setDescricao(rs.getString("descricao"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return area;
    }

    // CADASTRAR AREA
    // ______________________________________________________
    public static void cadastrar(Area area) {
        String sql = "INSERT INTO areas(nome, descricao) VALUES (?, ?)";

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, area.getNome());
            pst.setString(2, area.getDescricao());
            pst.executeUpdate();

            try (ResultSet rs = pst.getGeneratedKeys()) {
                if (rs.next()) {
                    area.setId(rs.getInt(1));
                } else {
                    throw new SQLException("Falha ao obter ID gerado para Área");
                }
            }

            System.out.println("Área cadastrada com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // EDITAR AREA
    // ______________________________________________________
    public static void editar(Area area) {
        String sql = "UPDATE areas SET nome=?, descricao=? WHERE id=?";

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, area.getNome());
            pst.setString(2, area.getDescricao());
            pst.setInt(3, area.getId());
            pst.executeUpdate();

            System.out.println("Área atualizada com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE AREA
    // ______________________________________________________
    public static void delete(int id) {
        String sqlDeleteFuncionarios = "UPDATE funcionarios SET area_id=NULL WHERE area_id=?";
        String sqlDeleteHabitats = "DELETE FROM habitats WHERE area_id=?";
        String sqlDeleteArea = "DELETE FROM areas WHERE id=?";

        try (Connection con = ConnectionFactory.getConnection()) {

            // Desassociar os funcionários (não apagar)
            try (PreparedStatement pst = con.prepareStatement(sqlDeleteFuncionarios)) {
                pst.setInt(1, id);
                pst.executeUpdate();
            }

            // Excluir habitats da área
            try (PreparedStatement pst = con.prepareStatement(sqlDeleteHabitats)) {
                pst.setInt(1, id);
                pst.executeUpdate();
            }

            // Excluir a área
            try (PreparedStatement pst = con.prepareStatement(sqlDeleteArea)) {
                pst.setInt(1, id);
                pst.executeUpdate();
            }

            System.out.println("Área excluída com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // SELECT COMPLETO (com Funcionários e Habitats)
    // ______________________________________________________
    public Area select(int id) {
        Area area = null;
        String sql = "SELECT id, nome, descricao FROM areas WHERE id=?";

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                area = new Area();
                area.setId(rs.getInt("id"));
                area.setNome(rs.getString("nome"));
                area.setDescricao(rs.getString("descricao"));

                // carregar funcionários e habitats vinculados
                area.setFuncionarios(FuncionarioDAO.buscarFuncionariosPorArea(area.getId()));
                area.setHabitats(HabitatDAO.buscarHabitatsPorArea(area.getId()));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return area;
    }

    // LISTAR SIMPLES
    // ______________________________________________________
    public ArrayList<Area> listar() {
        ArrayList<Area> areas = new ArrayList<>();
        String sql = "SELECT id, nome, descricao FROM areas";

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sql);
                ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Area area = new Area();
                area.setId(rs.getInt("id"));
                area.setNome(rs.getString("nome"));
                area.setDescricao(rs.getString("descricao"));
                areas.add(area);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return areas;
    }

    // LISTAR COMPLETO (COM FUNCIONÁRIOS E HABITATS)
    // ______________________________________________________

    public ArrayList<Area> listarCompleto() {
        ArrayList<Area> areas = new ArrayList<>();

        String sql = "SELECT id, nome, descricao FROM areas";

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sql);
                ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Area area = new Area();
                area.setId(rs.getInt("id"));
                area.setNome(rs.getString("nome"));
                area.setDescricao(rs.getString("descricao"));

                // Busca os FUNCIONÁRIOS desta Área
                area.setFuncionarios(FuncionarioDAO.buscarFuncionariosPorArea(area.getId()));

                // Busca os HABITATS desta Área
                area.setHabitats(HabitatDAO.buscarHabitatsPorArea(area.getId()));

                areas.add(area);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar Áreas: " + e.getMessage());
        }

        return areas;
    }

}
