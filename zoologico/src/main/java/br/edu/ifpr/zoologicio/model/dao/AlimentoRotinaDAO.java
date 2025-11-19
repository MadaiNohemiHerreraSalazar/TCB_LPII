package br.edu.ifpr.zoologicio.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.edu.ifpr.zoologicio.model.Alimento_Rotina;

public class AlimentoRotinaDAO {

    public static void cadastrar(Alimento_Rotina alimentoRotina) {
        Connection con = ConnectionFactory.getConnection();

        try {
            String sql = "INSERT INTO alimento_rotina (alimento_id, fornecedor_id, rotinaAlimentar_id, ) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, alimentoRotina.getFornecedor_id());
            ps.setInt(2, alimentoRotina.getRotinaAlimentar_id());
            ps.setInt(3, alimentoRotina.getAlimento_id());

            ps.executeUpdate();
            System.out.println("AlimentoRotina cadastrada com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void editar(Alimento_Rotina alimentoRotina) {
        Connection con = ConnectionFactory.getConnection();

        try {
            String sql = "UPDATE alimento_rotina SET alimento_id=?, fornecedor_id=?, rotinaAlimentar_id=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, alimentoRotina.getFornecedor_id());
            ps.setInt(2, alimentoRotina.getRotinaAlimentar_id());
            ps.setInt(3, alimentoRotina.getAlimento_id());
            ps.setInt(4, alimentoRotina.getId());
            ps.executeUpdate();

            System.out.println("AlimentoRotina atualizada com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        Connection con = ConnectionFactory.getConnection();

        try {
            String deleteSql = "DELETE FROM alimento_rotina WHERE id=?";
            PreparedStatement pst = con.prepareStatement(deleteSql);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("AlimentoRotina excluída com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Alimento_Rotina> select(int id) {
        Connection con = ConnectionFactory.getConnection();
        ArrayList<Alimento_Rotina> rotinas = new ArrayList<>();

        try {
            String sql = "SELECT * FROM alimento_rotina WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Alimento_Rotina rotina = new Alimento_Rotina();
                rotina.setId(rs.getInt("id"));
                rotina.setFornecedor_id(rs.getInt("fornecedor_id"));
                rotina.setRotinaAlimentar_id(rs.getInt("rotinaAlimentar_id"));
                rotina.setAlimento_id(rs.getInt("alimento_id"));

                rotinas.add(rotina);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return rotinas;
    }

    public ArrayList<Alimento_Rotina> listar() {
        Connection con = ConnectionFactory.getConnection();
        ArrayList<Alimento_Rotina> rotinas = new ArrayList<>();

        try {
            String sql = "SELECT * FROM alimento_rotina";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Alimento_Rotina rotina = new Alimento_Rotina();
                rotina.setId(rs.getInt("id"));
                rotina.setFornecedor_id(rs.getInt("fornecedor_id"));
                rotina.setRotinaAlimentar_id(rs.getInt("rotinaAlimentar_id"));
                rotina.setAlimento_id(rs.getInt("alimento_id"));

                rotinas.add(rotina);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return rotinas;
    }

    // // Buscar nome do alimento (opcional — caso queira mostrar no relatório)
    // public Alimento buscarAlimento(int alimentoId) {
    //     Connection con = ConnectionFactory.getConnection();
    //     Alimento alimento = null;

    //     try {
    //         String sql = "SELECT * FROM alimentos WHERE id=?";
    //         PreparedStatement pst = con.prepareStatement(sql);
    //         pst.setInt(1, alimentoId);
    //         ResultSet rs = pst.executeQuery();

    //         if (rs.next()) {
    //             alimento = new Alimento();
    //             alimento.setId(rs.getInt("id"));
    //             alimento.setNome(rs.getString("nome"));
    //         }

    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }

    //     return alimento;
    // }

}
