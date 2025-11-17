//Os animais serão cadastrados na lista depois.✅

package br.edu.ifpr.zoologicio.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.edu.ifpr.zoologicio.model.Animal;
import br.edu.ifpr.zoologicio.model.Veterinario;

public class VeterinarioDAO {

    public static void cadastroAnimais(ArrayList<Animal> animais, Veterinario veterinario) {

        Connection con = ConnectionFactory.getConnection();

        String sqlAnimais = "INSERT INTO alimentos(nome, validade, estoque, rotina_id) VALUES (?,?,?)";

        try {

            for (Animal a : animais) {
                PreparedStatement pst = con.prepareStatement(sqlAnimais);
                pst.setString(1, a.getNome());
                pst.setString(2, a.getDescricao());
                pst.setString(3, a.getHistoria());
                pst.setString(3, a.getEspecie());
                pst.setString(3, a.getIdade());
                pst.setString(3, a.getGenero());
                pst.setString(3, a.getPeso());
                pst.setString(3, a.getAltura());
                pst.setString(3, a.getSaude());
                pst.setInt(4, veterinario.getId()); // FK para a rotina

                pst.executeUpdate();
            }

            System.out.println("Alimentos cadastrados com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }

        // _____________________________________________________________________________________________________

    }

    public static void cadastrar(Veterinario veterinario) {

        Connection con = ConnectionFactory.getConnection();

        String sqlVeterinario = "INSERT INTO veterinarios(nome, cpf, email, especializacao) VALUES (?,?,?,?)";

        try {

            PreparedStatement psVeterinario = con.prepareStatement(sqlVeterinario);

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

    public static void editar(Veterinario veterinario) {

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

    public void delete(int id) {
        Connection con = ConnectionFactory.getConnection();

        try {

            String sql = "DELETE FROM veterinarios WHERE id= ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Veterinario excluido com sucesso");

        } catch (Exception e) {

            // TODO: handle exception
            e.printStackTrace();

        }
    }

    public ArrayList<Veterinario> select(int id) {

        Connection con = ConnectionFactory.getConnection();
        ArrayList<Veterinario> veterinarios = new ArrayList<>();

        try {

            String sql = "SELECT * FROM veterinarios WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

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

    public ArrayList<Veterinario> listar() {

        Connection con = ConnectionFactory.getConnection();

        ArrayList<Veterinario> veterinarios = new ArrayList<>();

        try {

            String sql = "SELECT * FROM veterinarios";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

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
