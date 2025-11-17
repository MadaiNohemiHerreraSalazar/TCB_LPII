package br.edu.ifpr.zoologicio.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.edu.ifpr.zoologicio.model.Alimento;
import br.edu.ifpr.zoologicio.model.RotinaAlimentar;

public class RotinaAlimentarDAO {

    // BUSCAR AGENDA ANIMAL
    // ______________________________________________________

    public static int buscarAgendaAnimal_ID(String nomeAnimal) {

        String sqlAgendaAnimal = "SELECT * FROM agendaAnimais WHERE nome = ?";
        int id = -1;

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sqlAgendaAnimal)) {

            pst.setString(1, nomeAnimal);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    id = rs.getInt("id");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;
    }

    // CADASTRAR ROTINA ALIMENTAR
    // ______________________________________________________

    public static void cadastrar(RotinaAlimentar rotinaAlimentar) {

        String sqlRotinaAlimentar = "INSERT INTO rotinasAlimentares(data, hora, quantidadeAlimento, agendaAnimal_id) VALUES (?,?,?,?)";

        try (

            Connection con = ConnectionFactory.getConnection();

            PreparedStatement pst = con.prepareStatement(sqlRotinaAlimentar, PreparedStatement.RETURN_GENERATED_KEYS)) {

            int idAgendaAnimal = buscarAgendaAnimal_ID(
                    rotinaAlimentar.getAgendaAnimal().getAnimal().getNome());

            if (idAgendaAnimal == -1) {
                System.out.println("AgendaAnimal não encontrada! Cadastre o animal primeiro.");
                return;
            }

            pst.setString(1, rotinaAlimentar.getData());
            pst.setString(2, rotinaAlimentar.getHora());
            pst.setString(3, rotinaAlimentar.getQuantidadeAlimento());
            pst.setInt(4, idAgendaAnimal);

            pst.executeUpdate();

            try (ResultSet rs = pst.getGeneratedKeys()) {
                if (rs.next()) {
                    rotinaAlimentar.setId(rs.getInt(1));
                }
            }

            if (!cadastroAlimentos(con, rotinaAlimentar.getAlimentos(), rotinaAlimentar)) {
                System.out.println("Rotina NÃO cadastrada. Erro ao cadastrar alimentos.");
                return;
            }

            System.out.println("RotinaAlimentar cadastrada com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // CADASTRAR ALIMENTOS
    // ______________________________________________________

    public static boolean cadastroAlimentos(Connection con, ArrayList<Alimento> alimentos,
            RotinaAlimentar rotinaAlimentar) {

        String sqlAlimento = "INSERT INTO alimentos(nome, validade, estoque, fornecedor_id, rotina_id) VALUES (?,?,?,?,?)";

        try {

            for (Alimento alimento : alimentos) {

                int fornecedor_id = buscarFornecedor_ID(alimento.getFornecedor().getNome());

                if (fornecedor_id == -1) {
                    System.out.println("Fornecedor '" + alimento.getFornecedor().getNome()
                            + "' não encontrado! Alimento NÃO cadastrado.");
                    return false;
                }

                try (PreparedStatement pst = con.prepareStatement(sqlAlimento)) {
                    pst.setString(1, alimento.getNome());
                    pst.setString(2, alimento.getValidade());
                    pst.setString(3, alimento.getEstoque());
                    pst.setInt(4, fornecedor_id);
                    pst.setInt(5, rotinaAlimentar.getId());
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

    // BUSCAR FORNECEDOR
    // ______________________________________________________

    public static int buscarFornecedor_ID(String nomeFornecedor) {

        String sqlFornecedor = "SELECT * from fornecedores WHERE nome= ?";
        int id = -1;

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sqlFornecedor)) {

            pst.setString(1, nomeFornecedor);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    id = rs.getInt("id");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;
    }

    // EDITAR
    // ______________________________________________________

    public static void editar(RotinaAlimentar rotinaAlimentar) {

        String sql = "UPDATE rotinasAlimentares SET data=?, Hora=?, quantidadeAlimento=? WHERE id=?";

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, rotinaAlimentar.getData());
            pst.setString(2, rotinaAlimentar.getHora());
            pst.setString(3, rotinaAlimentar.getQuantidadeAlimento());
            pst.setInt(4, rotinaAlimentar.getId());

            pst.executeUpdate();
            System.out.println("rotinaAlimentar atualizada com sucesso");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // DELETE
    // ______________________________________________________

    public void delete(int id) {

        String sql = "DELETE FROM rotinasAlimentares WHERE id= ?";

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("RotinaAlimentar excluída com sucesso");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // SELECT
    // ______________________________________________________

    public ArrayList<RotinaAlimentar> select(int id) {

        String sql = "SELECT * FROM rotinasAlimentares WHERE id=?";

        ArrayList<RotinaAlimentar> rotinasAlimentares = new ArrayList<>();

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);

            try (ResultSet rs = pst.executeQuery()) {

                while (rs.next()) {

                    RotinaAlimentar rotinaAlimentar = new RotinaAlimentar();
                    rotinaAlimentar.setId(rs.getInt("id"));
                    rotinaAlimentar.setData(rs.getString("data"));
                    rotinaAlimentar.setHora(rs.getString("hora"));
                    rotinaAlimentar.setQuantidadeAlimento(rs.getString("quantidadeAlimento"));

                    rotinasAlimentares.add(rotinaAlimentar);
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return rotinasAlimentares;
    }

    // LISTAR
    // ______________________________________________________

    public ArrayList<RotinaAlimentar> listar() {

        String sql = "SELECT * FROM rotinasAlimentares";

        ArrayList<RotinaAlimentar> rotinasAlimentares = new ArrayList<>();

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sql);
                ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {

                RotinaAlimentar rotinaAlimentar = new RotinaAlimentar();
                rotinaAlimentar.setId(rs.getInt("id"));
                rotinaAlimentar.setData(rs.getString("data"));
                rotinaAlimentar.setHora(rs.getString("hora"));
                rotinaAlimentar.setQuantidadeAlimento(rs.getString("quantidadeAlimento"));

                rotinasAlimentares.add(rotinaAlimentar);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return rotinasAlimentares;
    }
}
