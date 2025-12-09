package br.edu.ifpr.zoologicio.model.dao;

import br.edu.ifpr.zoologicio.model.Alimento;
import br.edu.ifpr.zoologicio.model.RotinaAlimentar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;

public class RotinaAlimentarDAO {

    // CADASTRAR ROTINA ALIMENTAR
    // ______________________________________________________

    public static void cadastrar(RotinaAlimentar rotinaAlimentar, int agendaAnimal_id, int fornecedor_id) {

        String sqlRotinaAlimentar = "INSERT INTO RotinaAlimentar(data, hora, quantidadeAlimento, agendaAnimal_id) VALUES (?,?,?,?)";

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sqlRotinaAlimentar,
                        PreparedStatement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, rotinaAlimentar.getData());
            pst.setString(2, rotinaAlimentar.getHora());
            pst.setString(3, rotinaAlimentar.getQuantidadeAlimento());
            pst.setInt(4, agendaAnimal_id);
            pst.executeUpdate();

            try (ResultSet rs = pst.getGeneratedKeys()) {
                if (rs.next()) {
                    rotinaAlimentar.setId(rs.getInt(1));
                } else {
                    throw new SQLException("Falha ao obter id gerado para RotinaAlimentar");
                }
            }

            if (!cadastroAlimentos(con, rotinaAlimentar.getAlimentos(), rotinaAlimentar, fornecedor_id)) {
                throw new SQLException("Rotina NÃO cadastrada. Erro ao cadastrar alimentos.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // CADASTRAR ALIMENTOS
    // ______________________________________________________

    public static boolean cadastroAlimentos(Connection con, ArrayList<Alimento> alimentos,
            RotinaAlimentar rotinaAlimentar, int fornecedor_id) {

        String sql = "INSERT INTO Alimento_Rotina(alimento_id, fornecedor_id, rotinaAlimentar_id) VALUES (?,?,?)";

        try {

            for (Alimento alimento : alimentos) {

                try (PreparedStatement pst = con.prepareStatement(sql)) {

                    pst.setInt(1, alimento.getId());
                    pst.setInt(2, fornecedor_id);
                    pst.setInt(3, rotinaAlimentar.getId());

                    pst.executeUpdate();
                }
            }

            System.out.println("Alimentos cadastrados com sucesso!");
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    // EDITAR
    // ______________________________________________________

    public static void editar(RotinaAlimentar rotinaAlimentar, int fornecedor_id) {

        String sqlUpdateRotina = "UPDATE RotinaAlimentar SET data=?, hora=?, quantidadeAlimento=? WHERE rotinaAlimentar_id=?";
        String sqlDeleteAlimentos = "DELETE FROM Alimento_Rotina WHERE rotinaAlimentar_id=?";

        try (Connection con = ConnectionFactory.getConnection()) {

            // Atualiza a tabela principal
            try (PreparedStatement pst = con.prepareStatement(sqlUpdateRotina)) {
                pst.setString(1, rotinaAlimentar.getData());
                pst.setString(2, rotinaAlimentar.getHora());
                pst.setString(3, rotinaAlimentar.getQuantidadeAlimento());
                pst.setInt(4, rotinaAlimentar.getId());
                pst.executeUpdate();
            }

            // Remove os alimentos associados antigos
            try (PreparedStatement pst = con.prepareStatement(sqlDeleteAlimentos)) {
                pst.setInt(1, rotinaAlimentar.getId());
                pst.executeUpdate();
            }

            // Insere os alimentos atualizados
            if (!cadastroAlimentos(con, rotinaAlimentar.getAlimentos(), rotinaAlimentar, fornecedor_id)) {
                throw new SQLException("Rotina NÃO cadastrada. Erro ao cadastrar alimentos.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // DELETE
    // ______________________________________________________

    public static void delete(int id) {

        String sqlDeleteAlimentos = "DELETE FROM Alimento_Rotina WHERE rotinaAlimentar_id=?";
        String sqlDeleteRotina = "DELETE FROM RotinaAlimentar WHERE rotinaAlimentar_id=?";

        try (Connection con = ConnectionFactory.getConnection()) {

            // Excluir alimentos vinculados
            try (PreparedStatement pst = con.prepareStatement(sqlDeleteAlimentos)) {
                pst.setInt(1, id);
                pst.executeUpdate();
            }

            // Excluir rotina
            try (PreparedStatement pst = con.prepareStatement(sqlDeleteRotina)) {
                pst.setInt(1, id);
                pst.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // SELECT
    // ______________________________________________________

    public RotinaAlimentar select(int id) {

        String sql = "SELECT r.rotinaAlimentar_id, r.data, r.hora, r.quantidadeAlimento " +
                "FROM rotinaAlimentar r " +
                "WHERE r.rotinaAlimentar_id = ?";

        RotinaAlimentar rotina = null;

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                rotina = new RotinaAlimentar();
                rotina.setId(rs.getInt("rotinaAlimentar_id"));
                rotina.setData(rs.getString("data"));
                rotina.setHora(rs.getString("hora"));
                rotina.setQuantidadeAlimento(rs.getString("quantidadeAlimento"));
            }

            // Agora buscar os alimentos vinculados à rotina
            if (rotina != null) {
                rotina.setAlimentos(buscarAlimentosPorRotina(rotina.getId()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rotina;
    }

    // LISTAR COMPLETO - COM ALIMENTOS
    // ______________________________________________________

    public ArrayList<RotinaAlimentar> listar() {
        String sql = "SELECT RotinaAlimentar_id, data, hora, quantidadeAlimento FROM RotinaAlimentar ORDER BY data, hora";
        ArrayList<RotinaAlimentar> rotinas = new ArrayList<>();

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sql);
                ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                RotinaAlimentar rotina = new RotinaAlimentar();
                rotina.setId(rs.getInt("rotinaAlimentar_id"));
                rotina.setData(rs.getString("data"));
                rotina.setHora(rs.getString("hora"));
                rotina.setQuantidadeAlimento(rs.getString("quantidadeAlimento"));
                rotina.setAlimentos(buscarAlimentosPorRotina(rotina.getId()));

                rotinas.add(rotina);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar Rotinas Alimentares: " + e.getMessage());
        }

        return rotinas;
    }

    // METODOS AUXILIARES
    // ----------------------------------------------------------------------

    // BUSCAR ROTINA ALIMENTAR POR ID - DEVOLVE ID
    // _____________________________________________________________________

    public static int buscaRotinaAlimentar_ID(int rotinaAlimentar_id) {

        String sqlAgendaAnimal = "SELECT rotinaAlimentar_id from RotinaAlimentar WHERE rotinaAlimentar_id= ?";
        int id = -1;

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(sqlAgendaAnimal)) {

            ps.setInt(1, rotinaAlimentar_id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                id = rs.getInt("rotinaAlimentar_id");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;

    }

    // BUSCAR ROTINA POR ID - DEVOLVE ROTINA ALIMENTAR
    // ______________________________________________________________________

    public static RotinaAlimentar buscarRotinaPorId(int id) {
        RotinaAlimentar rotina = null;
        String sql = "SELECT RotinaAlimentar_id, data, hora, quantidadeAlimento FROM RotinaAlimentar WHERE rotinaAlimentar_id=?";

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                rotina = new RotinaAlimentar();
                rotina.setId(rs.getInt("rotinaAlimentar_id"));
                rotina.setData(rs.getString("data"));
                rotina.setHora(rs.getString("hora"));
                rotina.setQuantidadeAlimento(rs.getString("quantidadeAlimento"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rotina;
    }

    // BUSCAR ALIMENTOS POR ROTINA
    // ________________________________________________________

    public static ArrayList<Alimento> buscarAlimentosPorRotina(int rotinaId) {
        ArrayList<Alimento> alimentos = new ArrayList<>();

        String sql = "SELECT a.alimento_id, a.nome FROM Alimento a" +
                "JOIN Alimento_Rotina ar ON a.alimento_id = ar.alimento_id" +
                "WHERE ar.rotinaAlimentar_id = ?;";

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, rotinaId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Alimento alimento = new Alimento();
                alimento.setId(rs.getInt("alimento_id"));
                alimento.setNome(rs.getString("nome"));
                alimentos.add(alimento);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return alimentos;
    }

    // METODOS AUXILIARES
    // __________________________________________________________

    public static Integer buscaFornecedorDaRotina(int rotinaId) {
        String sql = "SELECT fornecedor_id FROM Alimento_Rotina WHERE rotinaAlimentar_id = ? LIMIT 1";

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, rotinaId);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("fornecedor_id");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
