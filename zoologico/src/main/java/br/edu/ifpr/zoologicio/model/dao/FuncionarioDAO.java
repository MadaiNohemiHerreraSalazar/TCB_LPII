// O funcionario não precisa de ter uma agenda para ser cadastrado.
// Ele precisa de ter um cargo previamente cadastrado
// Arrumar cadastro para poder cadastrar cargo.

package br.edu.ifpr.zoologicio.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.edu.ifpr.zoologicio.model.Funcionario;

public class FuncionarioDAO {

    public static void cadastrar(Funcionario funcionario){

        Connection con = ConnectionFactory.getConnection();

        String sqlEndereco = "INSERT INTO funcionarios(nome, cpf, email) VALUES (?,?,?)";

        try {

            PreparedStatement psFuncionario = con.prepareStatement(sqlEndereco);

            psFuncionario.setString(1, funcionario.getNome());
            psFuncionario.setString(2, funcionario.getCpf());
            psFuncionario.setString(3, funcionario.getEmail());
            psFuncionario.executeUpdate();
            System.out.println("Funcionario inserido com sucesso");
            
        } catch (Exception e) {

            // TODO: handle exception
            e.printStackTrace();

        }

    }

    public static void editar(Funcionario funcionario){

        Connection con = ConnectionFactory.getConnection();

        try {

            String sql = "UPDATE funcionarios SET nome=?, cpf=?, email=?, WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, funcionario.getNome());
            pst.setString(2, funcionario.getCpf());
            pst.setString(3, funcionario.getEmail());
            pst.setInt(4, funcionario.getId());
            pst.executeUpdate();
            System.out.println("Funcionario atualizado com sucesso");

            
        } catch (Exception e) {

            // TODO: handle exception
            System.out.println(e.getMessage());

        }

    }

    public void delete(int id){
        Connection con = ConnectionFactory.getConnection();

        try {

            String sql = "DELETE FROM funcionarios WHERE id= ?";
            PreparedStatement pst =  con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Funcionario excluido com sucesso");

        } catch (Exception e) {

            // TODO: handle exception
            e.printStackTrace();

        }
    }

     public ArrayList<Funcionario> select(int id){

        Connection con = ConnectionFactory.getConnection();
        ArrayList<Funcionario> funcionarios = new ArrayList<>();

        try {

            String sql = "SELECT * FROM veterinarios WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while(rs.next()){

                Funcionario funcionario = new Funcionario();
                funcionario.setId(rs.getInt("id"));
                funcionario.setNome("nome");
                funcionario.setCpf("cpf");
                funcionario.setEmail("email");
                funcionarios.add(funcionario);

            }
            
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }

        return funcionarios;
    }
    
     public ArrayList<Funcionario> listar(){

        Connection con = ConnectionFactory.getConnection();

        ArrayList<Funcionario> funcionarios = new ArrayList<>();

        try {

            String sql = "SELECT * FROM funcionarios";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while(rs.next()){

                Funcionario funcionario = new Funcionario();
                funcionario.setId(rs.getInt("id"));
                funcionario.setNome("nome");
                funcionario.setCpf("cpf");
                funcionario.setEmail("email");
                funcionarios.add(funcionario);

            }
            
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }

        return funcionarios;
    }

}
