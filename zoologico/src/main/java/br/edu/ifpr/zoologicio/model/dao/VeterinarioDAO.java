// O veterinario não precisa dos os animais para ser cadastrados. 
// Isso se faz quando se cadastre o a agendaAnimal
package br.edu.ifpr.zoologicio.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.edu.ifpr.zoologicio.model.Veterinario;

public class VeterinarioDAO {

    public static void cadastrar(Veterinario veterinario){

        Connection con = ConnectionFactory.getConnection();

        String sqlEndereco = "INSERT INTO veterinarios(nome, cpf, email, especializacao) VALUES (?,?,?,?)";

        try {

            PreparedStatement psVeterinario = con.prepareStatement(sqlEndereco);

            psVeterinario.setString(1, veterinario.getNome());
            psVeterinario.setString(2, veterinario.getCpf());
            psVeterinario.setString(3, veterinario.getEmail());
            psVeterinario.setString(4, veterinario.getEspecializacao());
            psVeterinario.executeUpdate();
            System.out.println("Veterinario inserido com sucesso");
            
        } catch (Exception e) {

            // TODO: handle exception
            e.printStackTrace();

        }

    }

    public static void editar(Veterinario veterinario){

        Connection con = ConnectionFactory.getConnection();

        try {

            String sql = "UPDATE veterinarios SET nome=?, cpf=?, email=?, especializacao=?, WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, veterinario.getNome());
            pst.setString(2, veterinario.getCpf());
            pst.setString(3, veterinario.getEmail());
            pst.setString(4, veterinario.getEspecializacao());
            pst.setInt(5, veterinario.getId());
            pst.executeUpdate();
            System.out.println("Veterinario atualizado com sucesso");

            
        } catch (Exception e) {

            // TODO: handle exception
            System.out.println(e.getMessage());

        }

    }

    public void delete(int id){
        Connection con = ConnectionFactory.getConnection();

        try {

            String sql = "DELETE FROM veterinarios WHERE id= ?";
            PreparedStatement pst =  con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Veterinario excluido com sucesso");

        } catch (Exception e) {

            // TODO: handle exception
            e.printStackTrace();

        }
    }

    public ArrayList<Veterinario> select(int id){

        Connection con = ConnectionFactory.getConnection();
        ArrayList<Veterinario> veterinarios = new ArrayList<>();

        try {

            String sql = "SELECT * FROM veterinarios WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while(rs.next()){

                Veterinario veterinario = new Veterinario();
                veterinario.setId(rs.getInt("id"));
                veterinario.setNome("nome");
                veterinario.setCpf("cpf");
                veterinario.setEmail("email");
                veterinario.setEspecializacao("especializacao");
                veterinarios.add(veterinario);

            }
            
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }

        return veterinarios;
    }

    public ArrayList<Veterinario> listar(){

        Connection con = ConnectionFactory.getConnection();

        ArrayList<Veterinario> veterinarios = new ArrayList<>();

        try {

            String sql = "SELECT * FROM veterinarios";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while(rs.next()){

                Veterinario veterinario = new Veterinario();
                veterinario.setId(rs.getInt("id"));
                veterinario.setNome("nome");
                veterinario.setCpf("cpf");
                veterinario.setEmail("email");
                veterinario.setEspecializacao("especializacao");
                veterinarios.add(veterinario);

            }
            
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }

        return veterinarios;
    }
    
}
