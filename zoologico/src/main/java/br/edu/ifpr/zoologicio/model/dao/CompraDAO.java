// A o visitante precisa ser cadastrado na compra
// O funcionario precisa estar cadastrado previamente
// Não sei como se faz com o ticket


package br.edu.ifpr.zoologicio.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.edu.ifpr.zoologicio.model.Compra;

public class CompraDAO {

    public static void cadastrar(Compra compra) {

        Connection con = ConnectionFactory.getConnection();

        String sqlEndereco = "INSERT INTO compras(data, hora, quantidade, meioPago, precioTotal ) VALUES (?,?,?,?,?)";

        try {

            PreparedStatement psCompra = con.prepareStatement(sqlEndereco);

            psCompra.setString(1, compra.getData());
            psCompra.setString(2, compra.getHora());
            psCompra.setString(3, compra.getQuantidade());
            psCompra.setString(3, compra.getMeioPagamento());
            psCompra.setString(3, compra.getMeioPagamento());

            psCompra.executeUpdate();
            System.out.println("Compra inserida com sucesso");

        } catch (Exception e) {

            // TODO: handle exception
            e.printStackTrace();

        }

    }

    public static void editar(Compra compra) {

        Connection con = ConnectionFactory.getConnection();

        try {

            String sql = "UPDATE compras SET data=?, hora?, quantidade=?, meioPago=?, precioTotal=?,  WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, compra.getData());
            pst.setString(2, compra.getHora());
            pst.setString(3, compra.getQuantidade());
            pst.setString(4, compra.getMeioPagamento());
            pst.setInt(5, compra.getId());

            pst.executeUpdate();
            System.out.println("Compra atualizada com sucesso");

        } catch (Exception e) {

            // TODO: handle exception
            System.out.println(e.getMessage());

        }

    }

    public void delete(int id) {
        Connection con = ConnectionFactory.getConnection();

        try {

            String sql = "DELETE FROM compras WHERE id= ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Compra excluida com sucesso");

        } catch (Exception e) {

            // TODO: handle exception
            e.printStackTrace();

        }
    }

    public ArrayList<Compra> select(int id) {

        Connection con = ConnectionFactory.getConnection();
        ArrayList<Compra> compras = new ArrayList<>();

        try {

            String sql = "SELECT * FROM compras WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Compra compra = new Compra();
                compra.setId(rs.getInt("id"));
                compra.setData("data");
                compra.setHora("hora");
                compra.setQuantidade("quantidade");
                compra.setMeioPagamento("meioPagamento");
                compra.setPrecoTotal("precoTotal");
                compras.add(compra);

            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }

        return compras;
    }

    public ArrayList<Compra> listar() {

        Connection con = ConnectionFactory.getConnection();

        ArrayList<Compra> compras = new ArrayList<>();

        try {

            String sql = "SELECT * FROM compras";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Compra compra = new Compra();
                compra.setId(rs.getInt("id"));
                compra.setData("data");
                compra.setHora("hora");
                compra.setQuantidade("quantidade");
                compra.setMeioPagamento("meioPagamento");
                compra.setPrecoTotal("precoTotal");
                compras.add(compra);

            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }

        return compras;
    }

}
