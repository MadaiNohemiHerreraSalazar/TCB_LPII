package br.edu.ifpr.zoologicio.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.edu.ifpr.zoologicio.model.CargoPermissao;
import br.edu.ifpr.zoologicio.model.Permissao;

public class PermissaoDAO {

    // CADASTRAR PERMISSAO
    // ______________________________________________________

    public static void cadastrar(Permissao permissao) {

        String sqlPermissao = "INSERT INTO permissoes (nome, descricao) VALUES (?,?)";

        try (Connection con = ConnectionFactory.getConnection();

                PreparedStatement pst = con.prepareStatement(sqlPermissao, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, permissao.getNome());
            pst.setString(2, permissao.getDescricao());
            pst.executeUpdate();

            try (ResultSet rs = pst.getGeneratedKeys()) {
                if (rs.next()) {
                    permissao.setId(rs.getInt(1));
                } else {
                    throw new SQLException("Falha ao obter ID gerado para Permissão");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // EDITAR PERMISSAO
    // ______________________________________________________

    public static void editar(Permissao permissao) {

        String sqlUpdatePermissao = "UPDATE permissoes SET nome=?, descricao=? WHERE id=?";

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sqlUpdatePermissao)) {

            pst.setString(1, permissao.getNome());
            pst.setString(2, permissao.getDescricao());
            pst.executeUpdate();
            System.out.println("Permissão atualizada com sucesso");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // DELETE PERMISSAO
    // ______________________________________________________
    public void delete(int id) {
        String sqlDeletePermissao = "DELETE FROM permissao WHERE id=?";

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sqlDeletePermissao)) {

            pst.setInt(1, id);

            pst.executeUpdate();
            System.out.println("Permissão excluída com sucesso");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<CargoPermissao> select(int id) {

        Connection con = ConnectionFactory.getConnection();
        ArrayList<CargoPermissao> permissoes = new ArrayList<>();

        try {

            String sqlPermissao = "SELECT * FROM permissoes WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sqlPermissao);
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                CargoPermissao permissao = new CargoPermissao();
                permissao.setId(rs.getInt("id"));
                permissao.setNome(rs.getString("nome"));
                permissao.setDescricao(rs.getString("descricao"));

                permissoes.add(permissao);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return permissoes;
    }

    public ArrayList<CargoPermissao> listar() {
        String sqlPermissao = "SELECT * FROM permissoes";

        ArrayList<CargoPermissao> permissoes = new ArrayList<>();

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sqlPermissao);
                ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {

                CargoPermissao permissao = new CargoPermissao();
                permissao.setId(rs.getInt("id"));
                permissao.setNome(rs.getString("nome"));
                permissao.setDescricao(rs.getString("descricao"));

                permissoes.add(permissao);
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar " + e.getMessage());
        }

        return permissoes;
    }

    // METODOS AUXILIARES
    // ----------------------------------------------------------------
    // BUSCAR PERMISSOES DE CARGO PERMISSAO POR NOME E ID
    // _______________________________________________________________
    public static CargoPermissao buscarPermissao_ID(int CargoPermissao_id) {
        Connection con = ConnectionFactory.getConnection();
        CargoPermissao cargoPermissao = null;

        try {
            String sql = "SELECT * FROM permissao WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, CargoPermissao_id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                cargoPermissao = new CargoPermissao();
                cargoPermissao.setId(rs.getInt("id"));
                cargoPermissao.setNome(rs.getString("nome"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cargoPermissao;
    }

}
