//A area não precisa dos funcionarios ou habitats para ser cadastrado

package br.edu.ifpr.zoologicio.model.dao;

import br.edu.ifpr.zoologicio.model.Area;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AreaDAO {

    public static void cadastrar(Area area) {

        Connection con = ConnectionFactory.getConnection();

        String sqlArea = "INSERT INTO areas(nome, descricao) VALUES (?,?)";

        try {

            PreparedStatement psArea = con.prepareStatement(sqlArea);

            psArea.setString(1, area.getNome());
            psArea.setString(2, area.getDescricao());

            psArea.executeUpdate();
            System.out.println("Area inserida com sucesso");

        } catch (Exception e) {

            // TODO: handle exception
            e.printStackTrace();

        }

    }

    public static void editar(Area area) {

        Connection con = ConnectionFactory.getConnection();

        try {

            String sql = "UPDATE areas SET nome=?, descricao=?, WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, area.getNome());
            pst.setString(2, area.getDescricao());
            pst.setInt(3, area.getId());

            pst.executeUpdate();
            System.out.println("Area atualizada com sucesso");

        } catch (Exception e) {

            // TODO: handle exception
            System.out.println(e.getMessage());

        }

    }

    public void delete(int id) {
        Connection con = ConnectionFactory.getConnection();

        try {

            String sql = "DELETE FROM areas WHERE id= ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Area excluida com sucesso");

        } catch (Exception e) {

            // TODO: handle exception
            e.printStackTrace();

        }
    }

    public ArrayList<Area> select(int id) {

        Connection con = ConnectionFactory.getConnection();
        ArrayList<Area> areas = new ArrayList<>();

        try {

            String sql = "SELECT * FROM areas WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Area area = new Area();
                area.setId(rs.getInt("id"));
                area.setNome("nome");
                area.setDescricao("descricao");
                areas.add(area);

            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }

        return areas;
    }

    /*
     * public ArrayList<Area> listar() {
     * 
     * Connection con = ConnectionFactory.getConnection();
     * 
     * ArrayList<Area> areas = new ArrayList<>();
     * 
     * try {
     * 
     * String sql = "SELECT * FROM areas";
     * PreparedStatement pst = con.prepareStatement(sql);
     * ResultSet rs = pst.executeQuery();
     * 
     * while (rs.next()) {
     * 
     * Area area = new Area();
     * area.setId(rs.getInt("id"));
     * area.setNome("nome");
     * area.setDescricao("descricao");
     * areas.add(area);
     * 
     * }
     * 
     * } catch (Exception e) {
     * // TODO: handle exception
     * System.out.println(e.getMessage());
     * }
     * 
     * return areas;
     * }
     */

}
