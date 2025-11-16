package br.edu.ifpr.zoologicio.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.edu.ifpr.zoologicio.model.Permissao;

public class PermissaoDAO {

    public static void cadastrar(Permissao permissao) {

        Connection con = ConnectionFactory.getConnection();

        String sql = "INSERT INTO permissoes (nome, descricao) VALUES (?,?)";

        try {

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, permissao.getNome());
            ps.setString(2, permissao.getDescricao());

            ps.executeUpdate();
            System.out.println("Permissão inserida com sucesso");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void editar(Permissao permissao) {

        Connection con = ConnectionFactory.getConnection();

        try {

            String sql = "UPDATE permissoes SET nome=?, descricao=? WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, permissao.getNome());
            pst.setString(2, permissao.getDescricao());
            pst.setInt(3, permissao.getId());

            pst.executeUpdate();
            System.out.println("Permissão atualizada com sucesso");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void delete(int id) {

        Connection con = ConnectionFactory.getConnection();

        try {

            String sql = "DELETE FROM permissoes WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);

            pst.executeUpdate();
            System.out.println("Permissão excluída com sucesso");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Permissao> select(int id) {

        Connection con = ConnectionFactory.getConnection();
        ArrayList<Permissao> permissoes = new ArrayList<>();

        try {

            String sql = "SELECT * FROM permissoes WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Permissao permissao = new Permissao();
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

    public ArrayList<Permissao> listar() {

        Connection con = ConnectionFactory.getConnection();
        ArrayList<Permissao> permissoes = new ArrayList<>();

        try {

            String sql = "SELECT * FROM permissoes";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Permissao permissao = new Permissao();
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

}

