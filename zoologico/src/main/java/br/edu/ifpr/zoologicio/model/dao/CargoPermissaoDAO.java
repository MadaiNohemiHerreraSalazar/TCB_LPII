package br.edu.ifpr.zoologicio.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.edu.ifpr.zoologicio.model.CargoPermissao;
import br.edu.ifpr.zoologicio.model.Permissao;

public class CargoPermissaoDAO {

    // CADASTRAR CARGO PERMISSAO
    // ______________________________________________________
    public static void cadastrar(CargoPermissao CargoPermissao) {

        String sqlPermissao = "INSERT INTO Permissao (nome, descricao) VALUES (?,?)";

        try (Connection con = ConnectionFactory.getConnection();

                PreparedStatement pst = con.prepareStatement(sqlPermissao, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, CargoPermissao.getNome());
            pst.setString(2, CargoPermissao.getDescricao());

            pst.executeUpdate();
            System.out.println("Permissão inserida com sucesso");

            if (!cadastrarPermissao(con, CargoPermissao.getPermissoes(), CargoPermissao.getId())) {
                throw new SQLException("Fornecedor NÃO cadastrado. Erro ao cadastrar alimentos vinculados.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // EDITAR CARGO PERMISSAO
    // ______________________________________________________
    public static void editar(CargoPermissao CargoPermissao) {

        String sqlCargoPermissao = "UPDATE Permissao SET nome=?, descricao=? WHERE permissao_id=?";

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sqlCargoPermissao)) {

            pst.setString(1, CargoPermissao.getNome());
            pst.setString(2, CargoPermissao.getDescricao());
            pst.setInt(3, CargoPermissao.getId());

            pst.executeUpdate();
            System.out.println("Permissão atualizada com sucesso");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // DELETE CARGO PERMISSAO
    // ______________________________________________________
    public void delete(int id) {

        String sqlCargoPermissao = "DELETE FROM Permissao WHERE permissao_id=?";

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sqlCargoPermissao)) {

            pst.setInt(1, id);

            pst.executeUpdate();
            System.out.println("Permissão excluída com sucesso");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<CargoPermissao> select(int id) {
        String sqlCargoPermissao = "SELECT * FROM Permissao WHERE permissao_id=?";

        ArrayList<CargoPermissao> permissoes = new ArrayList<>();

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sqlCargoPermissao)) {

            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                CargoPermissao CargopPermissao = new CargoPermissao();
                CargopPermissao.setId(rs.getInt("permissao_id"));
                CargopPermissao.setNome(rs.getString("nome"));
                CargopPermissao.setDescricao(rs.getString("descricao"));

                permissoes.add(CargopPermissao);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return permissoes;
    }

    public ArrayList<CargoPermissao> listar() {
        String sqlCargoPermissao = "SELECT * FROM Permissao";

        ArrayList<CargoPermissao> permissoes = new ArrayList<>();

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sqlCargoPermissao)) {

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                CargoPermissao CargoPermissao = new CargoPermissao();
                CargoPermissao.setId(rs.getInt("permissao_id"));
                CargoPermissao.setNome(rs.getString("nome"));
                CargoPermissao.setDescricao(rs.getString("descricao"));

                permissoes.add(CargoPermissao);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return permissoes;
    }

    // METODOS AUXILIARES
    // ----------------------------------------------------------------

    public static void cadastrarPermissao(CargoPermissao permissao) {

        String sqlPermissao = "INSERT INTO Permissao (nome, descricao) VALUES (?,?)";

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sqlPermissao)) {

            pst.setString(1, permissao.getNome());
            pst.setString(2, permissao.getDescricao());

            pst.executeUpdate();
            System.out.println("Permissão inserida com sucesso");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean cadastrarPermissao(Connection con, ArrayList<Permissao> permissao, int cargo_int) {

    String sql = "INSERT INTO Cargo_Permissao(cargo_id, permissao_id) VALUES (?,?)";

    try {
        for (Permissao p : permissao) {
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setInt(1, cargo_int);
                pst.setInt(2, p.getId()); 
                pst.executeUpdate();
            }
        }

        System.out.println("Permissões vinculadas ao cargo com sucesso!");
        return true;

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}

}
