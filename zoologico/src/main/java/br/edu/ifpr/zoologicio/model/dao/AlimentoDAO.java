//Precisa ter seu fornecedor o ser cadastrado na hora (arrumar cadastro)
// não precisa ter a rotinaAlimentar para ser cadastrado

package br.edu.ifpr.zoologicio.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.edu.ifpr.zoologicio.model.Alimento;

public class AlimentoDAO {

    public static void cadastrar(Alimento alimento) {

        Connection con = ConnectionFactory.getConnection();

        String sqlEndereco = "INSERT INTO alimentos(nome, validade, estoque) VALUES (?,?,?)";

        try {

            PreparedStatement psAlimento = con.prepareStatement(sqlEndereco);

            psAlimento.setString(1, alimento.getNome());
            psAlimento.setString(2, alimento.getValidade());
            psAlimento.setString(3, alimento.getEstoque());

            psAlimento.executeUpdate();
            System.out.println("Alimento inserido com sucesso");

        } catch (Exception e) {

            // TODO: handle exception
            e.printStackTrace();

        }

    }

    public static void editar(Alimento alimento) {

        Connection con = ConnectionFactory.getConnection();

        try {

            String sql = "UPDATE alimentos SET nome=?, validade=?, estoque=?, WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, alimento.getNome());
            pst.setString(2, alimento.getValidade());
            pst.setString(3, alimento.getEstoque());
            pst.setInt(4, alimento.getId());

            pst.executeUpdate();
            System.out.println("Alimento atualizado com sucesso");

        } catch (Exception e) {

            // TODO: handle exception
            System.out.println(e.getMessage());

        }

    }

    public void delete(int id) {
        Connection con = ConnectionFactory.getConnection();

        try {

            String sql = "DELETE FROM alimentos WHERE id= ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Alimento excluido com sucesso");

        } catch (Exception e) {

            // TODO: handle exception
            e.printStackTrace();

        }
    }

    public ArrayList<Alimento> select(int id) {

        Connection con = ConnectionFactory.getConnection();
        ArrayList<Alimento> alimentos = new ArrayList<>();

        try {

            String sql = "SELECT * FROM alimentos WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Alimento alimento = new Alimento();
                alimento.setId(rs.getInt("id"));
                alimento.setNome("nome");
                alimento.setValidade("validade");
                alimento.setEstoque("estoque");
                alimentos.add(alimento);

            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }

        return alimentos;
    }

    public ArrayList<Alimento> listar() {

        Connection con = ConnectionFactory.getConnection();

        ArrayList<Alimento> alimentos = new ArrayList<>();

        try {

            String sql = "SELECT * FROM alimentos";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Alimento alimento = new Alimento();
                alimento.setId(rs.getInt("id"));
                alimento.setNome("nome");
                alimento.setValidade("validade");
                alimento.setEstoque("estoque");
                alimentos.add(alimento);

            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }

        return alimentos;
    }

}
