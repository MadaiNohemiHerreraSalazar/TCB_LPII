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

        String sqlFornecedor = "INSERT INTO fornecedores(nome, cpf, telefone, email) VALUES (?,?,?,?)";

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

        String sql = "INSERT INTO fornecedor_alimento(fornecedor_id, alimento_id) VALUES (?,?)";

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

        String sqlUpdateFornecedor = "UPDATE fornecedores SET nome=?, cpf=?, telefone=?, email=? WHERE id=?";
        String sqlDeleteAlimentos = "DELETE FROM fornecedor_alimento WHERE fornecedor_id=?";
        String sqlInsertAlimento = "INSERT INTO fornecedor_alimento(fornecedor_id, alimento_id) VALUES (?,?)";

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
            try (PreparedStatement pst = con.prepareStatement(sqlInsertAlimento)) {
                for (Alimento alimento : fornecedor.getAlimentos()) {
                    pst.setInt(1, fornecedor.getId());
                    pst.setInt(2, alimento.getId());
                    pst.executeUpdate();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // DELETE FORNECEDOR
    // ______________________________________________________

    public static void delete(int id) {

        String sqlDeleteAlimentos = "DELETE FROM fornecedor_alimento WHERE fornecedor_id=?";
        String sqlDeleteFornecedor = "DELETE FROM fornecedores WHERE id=?";

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

    // SELECT COMPLETO
    // ______________________________________________________

    public Fornecedor selectCompleto(int id) {

        String sql = "SELECT id, nome, cpf, telefone, email FROM fornecedores WHERE id = ?";
        Fornecedor fornecedor = null;

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                fornecedor = new Fornecedor();
                fornecedor.setId(rs.getInt("id"));
                fornecedor.setNome(rs.getString("nome"));
                fornecedor.setCpf(rs.getString("cpf"));
                fornecedor.setTelefone(rs.getString("telefone"));
                fornecedor.setEmail(rs.getString("email"));
            }

            // Busca os alimentos desse fornecedor
            if (fornecedor != null) {
                fornecedor.setAlimentos(buscarAlimentosPorFornecedor(fornecedor.getId()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return fornecedor;
    }

    // BUSCAR ALIMENTOS DE UM FORNECEDOR
    // ______________________________________________________

    private ArrayList<Alimento> buscarAlimentosPorFornecedor(int fornecedorId) {
        ArrayList<Alimento> alimentos = new ArrayList<>();

        String sql = "SELECT a.id, a.nome FROM alimentos a " +
                     "JOIN fornecedor_alimento fa ON a.id = fa.alimento_id " +
                     "WHERE fa.fornecedor_id = ?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, fornecedorId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Alimento alimento = new Alimento();
                alimento.setId(rs.getInt("id"));
                alimento.setNome(rs.getString("nome"));
                alimentos.add(alimento);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return alimentos;
    }

    // LISTAR TODOS OS FORNECEDORES COM SEUS ALIMENTOS
    // ______________________________________________________

    public ArrayList<Fornecedor> listarComAlimentos() {
        ArrayList<Fornecedor> fornecedores = new ArrayList<>();
        String sql = "SELECT id, nome, cpf, telefone, email FROM fornecedores ORDER BY nome";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setId(rs.getInt("id"));
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

}
