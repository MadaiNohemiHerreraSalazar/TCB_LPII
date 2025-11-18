//Um fornecedor cadastrado com alimento
//fornecedor pode editar alimento 

package br.edu.ifpr.zoologicio.model.dao;

import br.edu.ifpr.zoologicio.model.Alimento;
import br.edu.ifpr.zoologicio.model.Fornecedor;
import br.edu.ifpr.zoologicio.model.RotinaAlimentar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FornecedorDAO {

    public static int buscaFornecedor_ID(String nomeFornecedor) {

        String sqlFornecedor = "SELECT from fornecedor WHERE id= ?";
        int id = -1;

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(sqlFornecedor);) {
            ps.setString(1, nomeFornecedor);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;

    }

    /*
     * excluir
     * 
     * public static void cadastroAlimentos(ArrayList<Alimento> alimentos,
     * RotinaAlimentar rotinaAlimentar) {
     * 
     * Connection con = ConnectionFactory.getConnection();
     * 
     * String sqlAlimento =
     * "INSERT INTO alimentos(nome, validade, estoque, rotina_id) VALUES (?,?,?)";
     * 
     * try {
     * 
     * for (Alimento a : alimentos) {
     * PreparedStatement pst = con.prepareStatement(sqlAlimento);
     * pst.setString(1, a.getNome());
     * pst.setString(2, a.getValidade());
     * pst.setString(3, a.getEstoque());
     * pst.setInt(4, rotinaAlimentar.getId()); // FK para a rotina
     * 
     * pst.executeUpdate();
     * }
     * 
     * System.out.println("Alimentos cadastrados com sucesso!");
     * 
     * } catch (Exception e) {
     * e.printStackTrace();
     * }
     * 
     * //
     * _____________________________________________________________________________________________________
     * 
     * }
     */

    public static void cadastrar(Fornecedor fornecedor) {

        Connection con = ConnectionFactory.getConnection();

        String sqlFornecedor = "INSERT INTO Fornecedores(nome, cpf, telefone, email) VALUES (?,?,?,?)";

        try {

            PreparedStatement psFornecedor = con.prepareStatement(sqlFornecedor);

            psFornecedor.setString(1, fornecedor.getNome());
            psFornecedor.setString(2, fornecedor.getCpf());
            psFornecedor.setString(3, fornecedor.getEmail());
            psFornecedor.executeUpdate();
            System.out.println("Fornecedor inserido com sucesso");

        } catch (Exception e) {

            // TODO: handle exception
            e.printStackTrace();

        }

    }

    public static void editar(Fornecedor fornecedor) {

        Connection con = ConnectionFactory.getConnection();

        try {

            String sql = "UPDATE fornecedores  SET nome=?, cpf=?, telefone=?, email=?, WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, fornecedor.getNome());
            pst.setString(2, fornecedor.getCpf());
            pst.setString(3, fornecedor.getEmail());
            pst.setInt(4, fornecedor.getId());
            pst.executeUpdate();
            System.out.println("Fornecedor atualizado com sucesso");

        } catch (Exception e) {

            // TODO: handle exception
            System.out.println(e.getMessage());

        }

    }

    public void delete(int id) {
        Connection con = ConnectionFactory.getConnection();

        try {

            String sql = "DELETE FROM fornecedores WHERE id= ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Fornecedor excluido com sucesso");

        } catch (Exception e) {

            // TODO: handle exception
            e.printStackTrace();

        }
    }

    public ArrayList<Fornecedor> select(int id) {

        Connection con = ConnectionFactory.getConnection();
        ArrayList<Fornecedor> fornecedores = new ArrayList<>();

        try {

            String sql = "SELECT * FROM fornecedores WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setId(rs.getInt("id"));
                fornecedor.setNome("nome");
                fornecedor.setCpf("cpf");
                fornecedor.setTelefone("telefone");
                fornecedor.setEmail("email");
                fornecedores.add(fornecedor);

            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }

        return fornecedores;
    }

    public ArrayList<Fornecedor> listar() {

        Connection con = ConnectionFactory.getConnection();

        ArrayList<Fornecedor> fornecedores = new ArrayList<>();

        try {

            String sql = "SELECT * FROM fornecedores";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setId(rs.getInt("id"));
                fornecedor.setNome("nome");
                fornecedor.setCpf("cpf");
                fornecedor.setTelefone("telefone");
                fornecedor.setEmail("email");
                fornecedores.add(fornecedor);

            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }

        return fornecedores;
    }

}
