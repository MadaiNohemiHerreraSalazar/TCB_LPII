// A o visitante precisa ser cadastrado na compra
// O funcionario precisa estar cadastrado previamente
// ticket -> main 

package br.edu.ifpr.zoologicio.model.dao;

import br.edu.ifpr.zoologicio.model.Compra;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CompraDAO {

    public static int buscaFuncionario_ID(String nomeFuncionario) {

        String sqlFuncionario = "SELECT from agendaAnimais WHERE nome= ?";
        int id = -1;

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(sqlFuncionario)) {
            ps.setString(1, nomeFuncionario);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;

    }

   public static int buscaVisitante_ID(String nomeVisitante) {


        String sqlVisitante = "SELECT from agendaAnimais WHERE nome= ?";
        int id = -1;

        try (Connection con = ConnectionFactory.getConnection();
                    PreparedStatement ps = con.prepareStatement(sqlVisitante)0){
            ps.setString(1, nomeVisitante);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;

    }

    public static void cadastrar(Compra compra) {

        Connection con = ConnectionFactory.getConnection();

        String sqlCompra = "INSERT INTO compras(data, hora, quantidade, meioPago, precioTotal ) VALUES (?,?,?,?,?)";

        try {

            PreparedStatement psCompra = con.prepareStatement(sqlCompra);

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
