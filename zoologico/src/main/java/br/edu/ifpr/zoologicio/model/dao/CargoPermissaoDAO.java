package br.edu.ifpr.zoologicio.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.edu.ifpr.zoologicio.model.Alimento;
import br.edu.ifpr.zoologicio.model.Cargo;
import br.edu.ifpr.zoologicio.model.CargoPermissao;
import br.edu.ifpr.zoologicio.model.Permissao;

public class CargoPermissaoDAO {

    public static void cadastrar(CargoPermissao CargoPermissao) {

        Connection con = ConnectionFactory.getConnection();

        String sqlPermissao = "INSERT INTO permissoes (nome, descricao) VALUES (?,?)";

        try {

            PreparedStatement pst = con.prepareStatement(sqlPermissao);

            pst.setString(1, CargoPermissao.getNome());
            pst.setString(2, CargoPermissao.getDescricao());

            pst.executeUpdate();
            System.out.println("Permissão inserida com sucesso");

            if (!cadastrarPermissao(con, CargoPermissao.getPermissoes(), CargoPermissao.getId())) {
                throw new SQLException("Fornecedor NÃO cadastrado. Erro ao cadastrar alimentos vinculados.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void cadastrarPermissao(CargoPermissao permissao) {

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

    public static boolean cadastrarPermissao(Connection con, ArrayList<Permissao> permissao, int cargo_int) {

        String sql = "INSERT INTO CargoPermissao_permissao(fornecedor_id, alimento_id) VALUES (?,?)";

        try {
            for (Permissao permisoes : permissao) {
                try (PreparedStatement pst = con.prepareStatement(sql)) {
                    pst.setInt(1, cargo_int);
                    pst.executeUpdate();
                }
            }
            System.out.println("Alimentos vinculados ao fornecedor com sucesso!");
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void editar(CargoPermissao CargoPermissao) {

        Connection con = ConnectionFactory.getConnection();

        try {

            String sqlCargoPermissao = "UPDATE permissoes SET nome=?, descricao=? WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sqlCargoPermissao);

            pst.setString(1, CargoPermissao.getNome());
            pst.setString(2, CargoPermissao.getDescricao());
            pst.setInt(3, CargoPermissao.getId());

            pst.executeUpdate();
            System.out.println("Permissão atualizada com sucesso");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void delete(int id) {

        Connection con = ConnectionFactory.getConnection();

        try {

            String sqlCargoPermissao = "DELETE FROM permissoes WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sqlCargoPermissao);
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

            String sqlCargoPermissao = "SELECT * FROM permissoes WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sqlCargoPermissao);
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                CargoPermissao CargopPermissao = new CargoPermissao();
                CargopPermissao.setId(rs.getInt("id"));
                CargopPermissao.setNome(rs.getString("nome"));
                CargopPermissao.setDescricao(rs.getString("descricao"));

                permissoes.add(CargopPermissao);
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

            String sqlCargoPermissao = "SELECT * FROM permissoes";
            PreparedStatement pst = con.prepareStatement(sqlCargoPermissao);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                CargoPermissao CargoPermissao = new CargoPermissao();
                CargoPermissao.setId(rs.getInt("id"));
                CargoPermissao.setNome(rs.getString("nome"));
                CargoPermissao.setDescricao(rs.getString("descricao"));

                permissoes.add(CargoPermissao);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return permissoes;
    }

}
