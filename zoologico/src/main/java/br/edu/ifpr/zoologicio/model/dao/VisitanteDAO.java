// Falta "Compra" como atributo
// O visitante não pode ser cadastrado sem sua compra 
// - Arrumar cadastro para que se cadastre a compra também

package br.edu.ifpr.zoologicio.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.edu.ifpr.zoologicio.model.Visitante;

public class VisitanteDAO {

    public static void cadastrar(Visitante visitante) {

        Connection con = ConnectionFactory.getConnection();

        String sqlEndereco = "INSERT INTO visitantes(nome, cpf, telefone, email) VALUES (?,?,?,?)";

        try {

            PreparedStatement psVisitante = con.prepareStatement(sqlEndereco);

            psVisitante.setString(1, visitante.getNome());
            psVisitante.setString(2, visitante.getCpf());
            psVisitante.setString(3, visitante.getEmail());
            psVisitante.setString(4, visitante.getTelefone());
            psVisitante.executeUpdate();
            System.out.println("Visitante inserido com sucesso");

        } catch (Exception e) {

            // TODO: handle exception
            e.printStackTrace();

        }

    }

    public static void editar(Visitante visitante) {

        Connection con = ConnectionFactory.getConnection();

        try {

            String sql = "UPDATE visitantes SET nome=?, cpf=?, telefone=? email=?, WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, visitante.getNome());
            pst.setString(2, visitante.getCpf());
            pst.setString(3, visitante.getEmail());
            pst.setString(4, visitante.getTelefone());
            pst.setInt(5, visitante.getId());
            pst.executeUpdate();
            System.out.println("Visitante atualizado com sucesso");

        } catch (Exception e) {

            // TODO: handle exception
            System.out.println(e.getMessage());

        }

    }

    public void delete(int id) {
        Connection con = ConnectionFactory.getConnection();

        try {

            String sql = "DELETE FROM visitantes WHERE id= ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Visitante excluido com sucesso");

        } catch (Exception e) {

            // TODO: handle exception
            e.printStackTrace();

        }
    }

    public ArrayList<Visitante> select(int id) {

        Connection con = ConnectionFactory.getConnection();
        ArrayList<Visitante> visitantes = new ArrayList<>();

        try {

            String sql = "SELECT * FROM visitantes WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Visitante visitante = new Visitante();
                visitante.setId(rs.getInt("id"));
                visitante.setNome("nome");
                visitante.setCpf("cpf");
                visitante.setTelefone("telefone");
                visitante.setEmail("email");
                visitantes.add(visitante);

            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }

        return visitantes;
    }

    public ArrayList<Visitante> listar() {

        Connection con = ConnectionFactory.getConnection();

        ArrayList<Visitante> visitantes = new ArrayList<>();

        try {

            String sql = "SELECT * FROM visitantes";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Visitante visitante = new Visitante();
                visitante.setId(rs.getInt("id"));
                visitante.setNome("nome");
                visitante.setCpf("cpf");
                visitante.setTelefone("telefone");
                visitante.setEmail("email");
                visitantes.add(visitante);

            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }

        return visitantes;
    }

}
