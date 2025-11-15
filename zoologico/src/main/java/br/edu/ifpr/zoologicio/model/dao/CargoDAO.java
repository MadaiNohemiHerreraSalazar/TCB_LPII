package br.edu.ifpr.zoologicio.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.edu.ifpr.zoologicio.model.Cargo;

public class CargoDAO {

    public static void cadastrar(Cargo cargo) {

        Connection con = ConnectionFactory.getConnection();

        String sqlEndereco = "INSERT INTO cargos(nome, salario, cargaHoraria, senha) VALUES (?,?,?,?)";

        try {

            PreparedStatement psCargo = con.prepareStatement(sqlEndereco);

            psCargo.setString(1, cargo.getNome());
            psCargo.setString(2, cargo.getSalario());
            psCargo.setString(3, cargo.getCargaHoraroia());
            psCargo.setString(4, cargo.getSenha());

            psCargo.executeUpdate();
            System.out.println("Cargo inserido com sucesso");

        } catch (Exception e) {

            // TODO: handle exception
            e.printStackTrace();

        }

    }

    public static void editar(Cargo cargo) {

        Connection con = ConnectionFactory.getConnection();

        try {

            String sql = "UPDATE cargos  SET nome=?, salario=?, cargaHoraria=?, senha=?, WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, cargo.getNome());
            pst.setString(2, cargo.getSalario());
            pst.setString(3, cargo.getCargaHoraroia());
            pst.setString(4, cargo.getSenha());

            pst.executeUpdate();
            System.out.println("Cargo atualizado com sucesso");

        } catch (Exception e) {

            // TODO: handle exception
            System.out.println(e.getMessage());

        }

    }

    public void delete(int id) {
        Connection con = ConnectionFactory.getConnection();

        try {

            String sql = "DELETE FROM cargos WHERE id= ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Cargo excluido com sucesso");

        } catch (Exception e) {

            // TODO: handle exception
            e.printStackTrace();

        }
    }

    public ArrayList<Cargo> select(int id) {

        Connection con = ConnectionFactory.getConnection();
        ArrayList<Cargo> cargos = new ArrayList<>();

        try {

            String sql = "SELECT * FROM cargos WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Cargo cargo = new Cargo();
                cargo.setId(rs.getInt("id"));
                cargo.setNome("nome");
                cargo.setSalario("salario");
                cargo.setCargaHoraroia("cargaHoraria");
                cargo.setSenha("senha");
                cargos.add(cargo);

            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }

        return cargos;
    }

    public ArrayList<Cargo> listar() {

        Connection con = ConnectionFactory.getConnection();

        ArrayList<Cargo> cargos = new ArrayList<>();

        try {

            String sql = "SELECT * FROM cargos";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Cargo cargo = new Cargo();
                cargo.setId(rs.getInt("id"));
                cargo.setNome("nome");
                cargo.setSalario("salario");
                cargo.setCargaHoraroia("cargaHoraria");
                cargo.setSenha("senha");
                cargos.add(cargo);

            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }

        return cargos;
    }

}
