package br.edu.ifpr.zoologicio.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.edu.ifpr.zoologicio.model.CargoPermissao;

public class PermissaoDAO {
  
    public static void cadastrar(CargoPermissao permissao) {

        Connection con = ConnectionFactory.getConnection();

        String sqlPermissao = "INSERT INTO permissoes (nome, descricao) VALUES (?,?)";

        try {

            PreparedStatement pst = con.prepareStatement(sqlPermissao);

            pst.setString(1, permissao.getNome());
            pst.setString(2, permissao.getDescricao());

            pst.executeUpdate();
            System.out.println("Permissão inserida com sucesso");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void editar(Permissao permissoes) {


            String sqlPermissao = "UPDATE permissoes SET nome=?, descricao=? WHERE id=?";

        Connection con = ConnectionFactory.getConnection();


          try (PreparedStatement pst = con.prepareStatement(sqlPermissao)) {

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

            String sqlPermissao = "DELETE FROM permissoes WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sqlPermissao);
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

        Connection con = ConnectionFactory.getConnection();
        ArrayList<CargoPermissao> permissoes = new ArrayList<>();

        try {

            String sqlPermissao = "SELECT * FROM permissoes";
            PreparedStatement pst = con.prepareStatement(sqlPermissao);
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

}

