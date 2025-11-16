package br.edu.ifpr.zoologicio.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.edu.ifpr.zoologicio.model.CargoPermissao;

public class CargoPermissaoDAO {

    public static void cadastrar(CargoPermissao cargoPermissao) {

        Connection con = ConnectionFactory.getConnection();

        String sql = "INSERT INTO cargoPermissoes(nome, descricao) VALUES (?,?)";

        try {

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, cargoPermissao.getNome());
            pst.setString(2, cargoPermissao.getDescricao());
            pst.executeUpdate();
            System.out.println("CargoPermissao inserido com sucesso");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void editar(CargoPermissao cargoPermissao) {

        Connection con = ConnectionFactory.getConnection();

        try {

            String sql = "UPDATE cargoPermissoes SET nome=?, descricao=? WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, cargoPermissao.getNome());
            pst.setString(2, cargoPermissao.getDescricao());
            pst.setInt(3, cargoPermissao.getCargo().getId());
            pst.executeUpdate();
            System.out.println("CargoPermissao atualizado com sucesso");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id) {

        Connection con = ConnectionFactory.getConnection();

        try {

            String sql = "DELETE FROM cargoPermissoes WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();

            System.out.println("CargoPermissao excluído com sucesso");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<CargoPermissao> select(int id) {

        Connection con = ConnectionFactory.getConnection();
        ArrayList<CargoPermissao> cargoPermissoes = new ArrayList<>();

        try {

            String sql = "SELECT * FROM cargoPermissoes WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                CargoPermissao cargoPermissao = new CargoPermissao();

                cargoPermissao.setNome(rs.getString("nome"));
                cargoPermissao.setDescricao(rs.getString("descricao"));

                cargoPermissoes.add(cargoPermissao);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return cargoPermissoes;
    }

    public ArrayList<CargoPermissao> listar() {

        Connection con = ConnectionFactory.getConnection();
        ArrayList<CargoPermissao> cargoPermissoes = new ArrayList<>();

        try {

            String sql = "SELECT * FROM cargoPermissoes";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                CargoPermissao cargoPermissao = new CargoPermissao();
                cargoPermissao.setNome(rs.getString("nome"));
                cargoPermissao.setDescricao(rs.getString("descricao"));

                cargoPermissoes.add(cargoPermissao);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return cargoPermissoes;
    }
}

