//Um fornecedor não pode ter nenhum alimento em sua lista
// arrumar cadastro para cadastrar alimentos também

package br.edu.ifpr.zoologicio.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.edu.ifpr.zoologicio.model.Fornecedor;

public class FornecedorDAO {
    
    public static void cadastrar(Fornecedor fornecedor){

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

    public static void editar(Fornecedor fornecedor){

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

    public void delete(int id){
        Connection con = ConnectionFactory.getConnection();

        try {

            String sql = "DELETE FROM fornecedores WHERE id= ?";
            PreparedStatement pst =  con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Fornecedor excluido com sucesso");

        } catch (Exception e) {

            // TODO: handle exception
            e.printStackTrace();

        }
    }

     public ArrayList<Fornecedor> select(int id){

        Connection con = ConnectionFactory.getConnection();
        ArrayList<Fornecedor> fornecedores = new ArrayList<>();

        try {

            String sql = "SELECT * FROM fornecedores WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while(rs.next()){

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
    
     public ArrayList<Fornecedor> listar(){

        Connection con = ConnectionFactory.getConnection();

        ArrayList<Fornecedor> fornecedores = new ArrayList<>();

        try {

            String sql = "SELECT * FROM fornecedores";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while(rs.next()){

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
