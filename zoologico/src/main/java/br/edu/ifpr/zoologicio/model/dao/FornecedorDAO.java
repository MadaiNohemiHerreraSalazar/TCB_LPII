package br.edu.ifpr.zoologicio.model.dao;

import br.edu.ifpr.zoologicio.model.Alimento;
import br.edu.ifpr.zoologicio.model.Fornecedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;

public class FornecedorDAO {

    // CADASTRAR FORNECEDOR
    // ______________________________________________________

    public static void cadastrar(Fornecedor fornecedor) {

        String sqlFornecedor = "INSERT INTO Fornecedor(nome, cpf, telefone, email) VALUES (?,?,?,?)";

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sqlFornecedor, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, fornecedor.getNome());
            pst.setString(2, fornecedor.getCpf());
            pst.setString(3, fornecedor.getTelefone());
            pst.setString(4, fornecedor.getEmail());
            pst.executeUpdate();

            try (ResultSet rs = pst.getGeneratedKeys()) {
                if (rs.next()) {
                    fornecedor.setId(rs.getInt(1));
                } else {
                    throw new SQLException("Falha ao obter ID gerado para Fornecedor");
                }
            }

            // Cadastra os alimentos vinculados
            if (!cadastrarAlimentos(con, fornecedor.getAlimentos(), fornecedor.getId())) {
                throw new SQLException("Fornecedor NÃO cadastrado. Erro ao cadastrar alimentos vinculados.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // CADASTRAR ALIMENTOS DO FORNECEDOR
    // ______________________________________________________

    public static boolean cadastrarAlimentos(Connection con, ArrayList<Alimento> alimentos, int fornecedor_id) {

        String sql = "INSERT INTO Fornecedor_Alimento(fornecedor_id, alimento_id) VALUES (?,?)";

        try {
            for (Alimento alimento : alimentos) {
                try (PreparedStatement pst = con.prepareStatement(sql)) {
                    pst.setInt(1, fornecedor_id);
                    pst.setInt(2, alimento.getId());
                    pst.executeUpdate();
                }
            }
            System.out.println("Alimentos vinculados ao fornecedor com sucesso!");
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // EDITAR FORNECEDOR
    // ______________________________________________________

    public static void editar(Fornecedor fornecedor) {

        String sqlUpdateFornecedor = "UPDATE Fornecedor SET nome=?, cpf=?, telefone=?, email=? WHERE fornecedor_id=?";
        String sqlDeleteAlimentos = "DELETE FROM Fornecedor_Alimento WHERE fornecedor_id=?";

        try (Connection con = ConnectionFactory.getConnection()) {

            // Atualiza dados principais
            try (PreparedStatement pst = con.prepareStatement(sqlUpdateFornecedor)) {
                pst.setString(1, fornecedor.getNome());
                pst.setString(2, fornecedor.getCpf());
                pst.setString(3, fornecedor.getTelefone());
                pst.setString(4, fornecedor.getEmail());
                pst.setInt(5, fornecedor.getId());
                pst.executeUpdate();
            }

            // Remove os alimentos antigos
            try (PreparedStatement pst = con.prepareStatement(sqlDeleteAlimentos)) {
                pst.setInt(1, fornecedor.getId());
                pst.executeUpdate();
            }

            // Insere os alimentos atualizados
            if (!cadastrarAlimentos(con, fornecedor.getAlimentos(), fornecedor.getId())) {
                throw new SQLException("Fornecedor NÃO cadastrado. Erro ao cadastrar alimentos vinculados.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // DELETE FORNECEDOR
    // ______________________________________________________

    public static void delete(int id) {

        String sqlDeleteAlimentos = "DELETE FROM Fornecedor_Alimento WHERE fornecedor_id=?";
        String sqlDeleteFornecedor = "DELETE FROM Fornecedor WHERE fornecedor_id=?";

        try (Connection con = ConnectionFactory.getConnection()) {

            // Excluir alimentos vinculados
            try (PreparedStatement pst = con.prepareStatement(sqlDeleteAlimentos)) {
                pst.setInt(1, id);
                pst.executeUpdate();
            }

            // Excluir fornecedor
            try (PreparedStatement pst = con.prepareStatement(sqlDeleteFornecedor)) {
                pst.setInt(1, id);
                pst.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // SELECT
    // ______________________________________________________

    public static Fornecedor select(int id) {

        String sql = "SELECT fornecedor_id, nome, cpf, telefone, email FROM Fornecedor WHERE fornecedor_id = ?";
        Fornecedor fornecedor = null;

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                fornecedor = new Fornecedor();
                fornecedor.setId(rs.getInt("fornecedor_id"));
                fornecedor.setNome(rs.getString("nome"));
                fornecedor.setCpf(rs.getString("cpf"));
                fornecedor.setTelefone(rs.getString("telefone"));
                fornecedor.setEmail(rs.getString("email"));
                // Busca os alimentos desse fornecedor
                fornecedor.setAlimentos(buscarAlimentosPorFornecedor(fornecedor.getId()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return fornecedor;
    }

    // LISTAR COMPLETO COM TODOS OS FORNECEDORES COM SEUS ALIMENTOS
    // ______________________________________________________

    public static ArrayList<Fornecedor> listar() {
        ArrayList<Fornecedor> fornecedores = new ArrayList<>();
        String sql = "SELECT fornecedor_id, nome, cpf, telefone, email FROM Fornecedor ORDER BY nome";

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sql);
                ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setId(rs.getInt("fornecedor_id"));
                fornecedor.setNome(rs.getString("nome"));
                fornecedor.setCpf(rs.getString("cpf"));
                fornecedor.setTelefone(rs.getString("telefone"));
                fornecedor.setEmail(rs.getString("email"));

                // Adiciona os alimentos de cada fornecedor
                fornecedor.setAlimentos(buscarAlimentosPorFornecedor(fornecedor.getId()));

                fornecedores.add(fornecedor);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar Fornecedores: " + e.getMessage());
        }

        return fornecedores;
    }

    // METODOS AUXILIARES
    // -----------------------------------------------------------------

    // BUSCAR FORNECEDOR POR ID
    // ____________________________________________________________

    public static int buscaFornecedor_ID(int fornecedor_id) {

        String sqlFornecedor = "SELECT from Fornecedor WHERE nome= ?";
        int id = -1;

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(sqlFornecedor)) {
            ps.setInt(1, fornecedor_id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                id = rs.getInt("fornecedor_id");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;

    }

    // BUSCAR ALIMENTOS POR FORNECEDOR POR ID - DEVOLVE ALIMENTOS
    // _____________________________________________________________________________-

    public static ArrayList<Alimento> buscarAlimentosPorFornecedor(int fornecedorId) {
        ArrayList<Alimento> alimentos = new ArrayList<>();

        String sql = "SELECT a.alimento_id, a.nome, a.validade, a.estoque " +
                "FROM Alimento a " +
                "JOIN Fornecedor_Alimento fa ON a.alimento_id = fa.alimento_id " +
                "WHERE fa.fornecedor_id = ?";

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, fornecedorId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Alimento alimento = new Alimento();
                alimento.setId(rs.getInt("fornecedor_id"));
                alimento.setNome(rs.getString("nome"));
                alimento.setValidade(rs.getString("validade"));
                alimento.setEstoque(rs.getString("estoque"));

                alimentos.add(alimento);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar alimentos por fornecedor: " + e.getMessage());
        }

        return alimentos;
    }

}
