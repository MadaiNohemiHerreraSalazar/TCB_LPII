package br.edu.ifpr.zoologicio.model.dao;

import br.edu.ifpr.zoologicio.model.Cargo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CargoDAO {

    //BUSCAR CARGO POR ID
    //______________________________________________________________________

    public static Cargo buscarCargoPorId(int id) {
        Cargo cargo = null;
        String sql = "SELECT cargo_id, nome, salario, cargaHoraroia, senha FROM cargo WHERE cargo_id = ?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                cargo = new Cargo();
                cargo.setId(rs.getInt("id"));
                cargo.setNome(rs.getString("nome"));
                cargo.setSalario(rs.getString("salario"));
                cargo.setCargaHoraria(rs.getString("cargaHoraroia"));
                cargo.setSenha(rs.getString("senha"));
                // não carrega lista de funcionarios aqui (evita recursão pesada)
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cargo;
    }

    // CADASTRAR CARGO
    // ______________________________________________________
    public static void cadastrar(Cargo cargo) {
        String sql = "INSERT INTO cargo(nome, salario, cargaHoraroia, senha) VALUES (?,?,?,?)";

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, cargo.getNome());
            pst.setString(2, cargo.getSalario());
            pst.setString(3, cargo.getCargaHoraria());
            pst.setString(4, cargo.getSenha());
            pst.executeUpdate();

            try (ResultSet rs = pst.getGeneratedKeys()) {
                if (rs.next()) {
                    cargo.setId(rs.getInt(1));
                } else {
                    throw new SQLException("Falha ao obter id gerado para Cargo");
                }
            }

            System.out.println("Cargo cadastrado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // EDITAR
    // __________________________________________________________________

    public static void editar(Cargo cargo) {

        String sqlUpdateCargo = "UPDATE cargo SET nome=?, salario=?, cargaHoraroia=?, senha=? WHERE cargo_id=?";
      
        try (Connection con = ConnectionFactory.getConnection()) {

            // Atualizar dados do Cargo
            try (PreparedStatement pst = con.prepareStatement(sqlUpdateCargo)) {
                pst.setString(1, cargo.getNome());
                pst.setString(2, cargo.getSalario());
                pst.setString(3, cargo.getCargaHoraria());
                pst.setString(4, cargo.getSenha());
                pst.setInt(5, cargo.getId());
                pst.executeUpdate();
            }

            System.out.println("Cargo atualizado com funcionários editados com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // DELETE CARGO
    // ______________________________________________________
    public static void delete(int id) {

        String sqlDesvincular = "UPDATE funcionarios SET cargo_id = NULL WHERE cargo_id = ?";
        String sqlDelete = "DELETE FROM cargo WHERE cargo_id = ?";

        try (Connection con = ConnectionFactory.getConnection()) {

            try (PreparedStatement pst = con.prepareStatement(sqlDesvincular)) {
                pst.setInt(1, id);
                pst.executeUpdate();
            }

            try (PreparedStatement pst = con.prepareStatement(sqlDelete)) {
                pst.setInt(1, id);
                pst.executeUpdate();
            }

            System.out.println("Cargo excluído com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // SELECT COMPLETO (COM FUNCIONÁRIOS)
    // ______________________________________________________
    public static Cargo select(int id) {
        String sql = "SELECT id, nome, salario, cargaHoraroia, senha FROM cargo WHERE cargo_id = ?";
        Cargo cargo = null;

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                cargo = new Cargo();
                cargo.setId(rs.getInt("cargo_id"));
                cargo.setNome(rs.getString("nome"));
                cargo.setSalario(rs.getString("salario"));
                cargo.setCargaHoraria(rs.getString("cargaHoraroia"));
                cargo.setSenha(rs.getString("senha"));
            }

            if (cargo != null) {
                cargo.setFuncionarios(FuncionarioDAO.buscarFuncionariosPorCargo(cargo.getId()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cargo;
    }

    // LISTAR SIMPLES
    // ______________________________________________________________________

    public static ArrayList<Cargo> listar() {
        String sql = "SELECT cargo_id, nome, salario, cargaHoraroia, senha FROM cargo";
        ArrayList<Cargo> cargos = new ArrayList<>();

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sql);
                ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Cargo cargo = new Cargo();
                cargo.setId(rs.getInt("cargo_id"));
                cargo.setNome(rs.getString("nome"));
                cargo.setSalario(rs.getString("salario"));
                cargo.setCargaHoraria(rs.getString("cargaHoraroia"));
                cargo.setSenha(rs.getString("senha"));

                cargos.add(cargo);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar cargos: " + e.getMessage());
        }

        return cargos;
    }

    // LISTAR COMPLETO (COM FUNCIONÁRIOS)
    // ______________________________________________________
    public static ArrayList<Cargo> listarCompleto() {
        ArrayList<Cargo> cargos = new ArrayList<>();
        String sql = "SELECT cargo_id, nome, salario, cargaHoraroia, senha FROM cargo";

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sql);
                ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Cargo cargo = new Cargo();
                cargo.setId(rs.getInt("cargo_id"));
                cargo.setNome(rs.getString("nome"));
                cargo.setSalario(rs.getString("salario"));
                cargo.setCargaHoraria(rs.getString("cargaHoraroia"));
                cargo.setSenha(rs.getString("senha"));
                cargo.setFuncionarios(FuncionarioDAO.buscarFuncionariosPorCargo(cargo.getId()));

                cargos.add(cargo);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar cargos: " + e.getMessage());
        }

        return cargos;
    }

}
