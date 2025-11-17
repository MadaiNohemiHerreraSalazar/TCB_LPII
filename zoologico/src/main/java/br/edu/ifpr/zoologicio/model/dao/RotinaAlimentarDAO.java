// A rotina alimentar precisa de sua agendaAnimal cadastrada Previamente
// E Alimentos cadastrados previamente

package br.edu.ifpr.zoologicio.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.edu.ifpr.zoologicio.model.Alimento;
import br.edu.ifpr.zoologicio.model.RotinaAlimentar;

public class RotinaAlimentarDAO {

    // Cadastro da RotinaAlimentar
    // ______________________________________________________

    public static int buscarAgendaAnimal_ID(String nomeAnimal) {

        Connection con = ConnectionFactory.getConnection();

        String sqlAgendaAnimal = "SELECT from agendaAnimais WHERE nome= ?";
        int id = -1;

        try {
            PreparedStatement ps = con.prepareStatement(sqlAgendaAnimal);
            ps.setString(1, nomeAnimal);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;

    }

    public static void cadastrar(RotinaAlimentar rotinaAlimentar) {

        Connection con = ConnectionFactory.getConnection();

        // 1. Buscar o id do animal já cadastrado
        int idAgendaAnimal = buscarAgendaAnimal_ID(rotinaAlimentar.getAgendaAnimal().getAnimal().getNome());

        if (idAgendaAnimal == -1) {
            System.out.println("AgendaAnimal não encontrada! Cadastre o animal primeiro.");
            return;
        }

        String sqlRotinaAlimentar = "INSERT INTO rotinasAlimentares(data, hora, quantidadeAlimento) VALUES (?,?,?)";

        try {

            PreparedStatement psRotinaAlimentar = con.prepareStatement(sqlRotinaAlimentar);

            psRotinaAlimentar.setString(1, rotinaAlimentar.getData());
            psRotinaAlimentar.setString(2, rotinaAlimentar.getHora());
            psRotinaAlimentar.setString(3, rotinaAlimentar.getQuantidadeAlimento());

            psRotinaAlimentar.executeUpdate();
            System.out.println("rotinaAlimentar inserida com sucesso");

        } catch (Exception e) {

            // TODO: handle exception
            e.printStackTrace();

        }

    }

    public static void cadastroAlimentos(ArrayList<Alimento> alimentos, RotinaAlimentar rotinaAlimentar) {

        Connection con = ConnectionFactory.getConnection();

        String sqlAlimento = "INSERT INTO alimentos(nome, validade, estoque, rotina_id) VALUES (?,?,?)";

        try {

            for (Alimento a : alimentos) {
                PreparedStatement pst = con.prepareStatement(sqlAlimento);
                pst.setString(1, a.getNome());
                pst.setString(2, a.getValidade());
                pst.setString(3, a.getEstoque());
                pst.setInt(4, rotinaAlimentar.getId()); // FK para a rotina

                pst.executeUpdate();
            }

            System.out.println("Alimentos cadastrados com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int buscarFornecedor_ID(String nomeFornecedor) {

        Connection con = ConnectionFactory.getConnection();

        String sqlAgendaAnimal = "SELECT from fornecedores WHERE nome= ?";
        int id = -1;

        try {
            PreparedStatement ps = con.prepareStatement(sqlAgendaAnimal);
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

    }

    // _____________________________________________________________________________________________________

    public static void editar(RotinaAlimentar rotinaAlimentar) {

        Connection con = ConnectionFactory.getConnection();

        try {

            String sql = "UPDATE rotinasAlimentares SET data=?, Hora=?, quantidadeAlimento=?, WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, rotinaAlimentar.getData());
            pst.setString(2, rotinaAlimentar.getHora());
            pst.setString(3, rotinaAlimentar.getQuantidadeAlimento());
            pst.setInt(4, rotinaAlimentar.getId());

            pst.executeUpdate();
            System.out.println("rotinaAlimentar atualizado com sucesso");

        } catch (Exception e) {

            // TODO: handle exception
            System.out.println(e.getMessage());

        }

    }

    public void delete(int id) {
        Connection con = ConnectionFactory.getConnection();

        try {

            String sql = "DELETE FROM rotinasAlimentares WHERE id= ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("RotinaAlimentar excluida com sucesso");

        } catch (Exception e) {

            // TODO: handle exception
            e.printStackTrace();

        }
    }

    public ArrayList<RotinaAlimentar> select(int id) {

        Connection con = ConnectionFactory.getConnection();
        ArrayList<RotinaAlimentar> rotinasAlimentares = new ArrayList<>();

        try {

            String sql = "SELECT * FROM rotinasAlimentares WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                RotinaAlimentar rotinaAlimentar = new RotinaAlimentar();
                rotinaAlimentar.setId(rs.getInt("id"));
                rotinaAlimentar.setData("data");
                rotinaAlimentar.setHora("hora");
                rotinaAlimentar.setQuantidadeAlimento("quantidadeAlimento");
                rotinasAlimentares.add(rotinaAlimentar);

            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }

        return rotinasAlimentares;
    }

    public ArrayList<RotinaAlimentar> listar() {

        Connection con = ConnectionFactory.getConnection();

        ArrayList<RotinaAlimentar> rotinasAlimentares = new ArrayList<>();

        try {

            String sql = "SELECT * FROM rotinasAlimentares";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                RotinaAlimentar rotinaAlimentar = new RotinaAlimentar();
                rotinaAlimentar.setId(rs.getInt("id"));
                rotinaAlimentar.setData("data");
                rotinaAlimentar.setHora("hora");
                rotinaAlimentar.setQuantidadeAlimento("quantidadeAlimento");
                rotinasAlimentares.add(rotinaAlimentar);

            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }

        return rotinasAlimentares;
    }

}
