package br.edu.ifpr.zoologicio.model.dao;

import br.edu.ifpr.zoologicio.model.AgendaFuncionario;
import br.edu.ifpr.zoologicio.model.Cargo;
import br.edu.ifpr.zoologicio.model.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;

public class FuncionarioDAO {

    // CADASTRAR FUNCIONÁRIO COM CARGO E AGENDAFUNCIONARIO
    // ______________________________________________________

    public static void cadastrar(Funcionario funcionario) {

        String sqlFuncionario = "INSERT INTO Funcionario(nome, cpf, email, telefone, cargo_id, area_id) VALUES (?,?,?,?,?,?)";
        String sqlAgenda = "INSERT INTO AgendaFuncionario(atividade, cargo_id, funcionario_id) VALUES (?,?,?)";

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sqlFuncionario, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, funcionario.getNome());
            pst.setString(2, funcionario.getCpf());
            pst.setString(3, funcionario.getEmail());
            pst.setString(4, funcionario.getTelefone());
            pst.setInt(5, funcionario.getCargo().getId());
            pst.setInt(6, funcionario.getArea().getId());
            pst.executeUpdate();

            // Pega o ID do funcionário
            try (ResultSet rs = pst.getGeneratedKeys()) {
                if (rs.next()) {
                    funcionario.setId(rs.getInt(1));
                }
            }

            // Cadastra AgendaFuncionario (1:1)
            if (funcionario.getAgendaFuncionario() != null) {
                try (PreparedStatement pstAgenda = con.prepareStatement(sqlAgenda)) {
                    pstAgenda.setString(1, funcionario.getAgendaFuncionario().getAtividade());
                    pstAgenda.setInt(2, funcionario.getCargo().getId());
                    pstAgenda.setInt(3, funcionario.getId());
                    pstAgenda.executeUpdate();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // EDITAR FUNCIONÁRIO, CARGO E AGENDA DO FUNCIONARIO
    // ______________________________________________________

    public static void editar(Funcionario funcionario) {

        String sqlFuncionario = "UPDATE Funcionario SET nome=?, cpf=?, email=?, telefone=?, cargo_id=?, area_id=? WHERE funcionario_id=?";
        String sqlAgenda = "UPDATE AgendaFuncionario SET ultimaAtualizacao=?, atividade=?, cargo_id=? WHERE funcionario_id=?";

        try (Connection con = ConnectionFactory.getConnection()) {

            try (PreparedStatement pst = con.prepareStatement(sqlFuncionario)) {
                pst.setString(1, funcionario.getNome());
                pst.setString(2, funcionario.getCpf());
                pst.setString(3, funcionario.getEmail());
                pst.setString(4, funcionario.getTelefone());
                pst.setInt(5, funcionario.getCargo().getId());
                pst.setInt(6, funcionario.getArea().getId());
                pst.executeUpdate();
            }

            if (funcionario.getAgendaFuncionario() != null) {
                try (PreparedStatement pstAgenda = con.prepareStatement(sqlAgenda)) {
                    pstAgenda.setString(3, funcionario.getAgendaFuncionario().getAtividade());
                    pstAgenda.setInt(4, funcionario.getCargo().getId());
                    pstAgenda.setInt(5, funcionario.getId());
                    pstAgenda.executeUpdate();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // DELETE FUNCIONÁRIO E AGENDAFUNCIONARIO
    // ______________________________________________________

    public static void delete(int id) {

        String sqlDeleteAgenda = "DELETE FROM AgendaFuncionario WHERE funcionario_id=?";
        String sqlDeleteFuncionario = "DELETE FROM Funcionario WHERE funcionario_id=?";

        try (Connection con = ConnectionFactory.getConnection()) {

            try (PreparedStatement pst = con.prepareStatement(sqlDeleteAgenda)) {
                pst.setInt(1, id);
                pst.executeUpdate();
            }

            try (PreparedStatement pst = con.prepareStatement(sqlDeleteFuncionario)) {
                pst.setInt(1, id);
                pst.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // SELECT COMPLETO
    // ______________________________________________________

    public static Funcionario select(int id) {

        String sql = "SELECT f.funcionario_id, f.nome, " +
                "c.cargo_id AS cargo_id, c.nome AS cargo_nome, c.salario, c.cargaHoraria, c.senha, " +
                "a.agendaFuncionario_id AS agenda_id, a.atividade " +
                "FROM funcionario f " +
                "LEFT JOIN cargo c ON f.cargo_id = c.id " +
                "LEFT JOIN agendaFuncionario a ON f.funcionario_id = a.funcionario_id " +
                "WHERE f.funcionario_id = ?";

        Funcionario funcionario = null;

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                funcionario = new Funcionario();
                funcionario.setId(rs.getInt("funcionario_id"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setTelefone(rs.getString("telefone"));

                // Cargo
                Cargo cargo = new Cargo();
                cargo.setId(rs.getInt("cargo_id"));
                cargo.setNome(rs.getString("cargo_nome"));
                cargo.setSalario(rs.getString("salario"));
                cargo.setCargaHoraria(rs.getString("cargaHoraria"));
                cargo.setSenha(rs.getString("senha"));
                funcionario.setCargo(cargo);

                // AgendaFuncionario
                if (rs.getInt("agenda_id") > 0) {
                    AgendaFuncionario agenda = new AgendaFuncionario();
                    agenda.setId(rs.getInt("agenda_id"));
                    agenda.setAtividade(rs.getString("atividade"));
                    agenda.setFuncionario(funcionario);
                    funcionario.setAgendaFuncionario(agenda);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return funcionario;
    }

    // LISTAR SIMPLES
    // _____________________________________________________________________________

    public static ArrayList<Funcionario> listar() {
        ArrayList<Funcionario> funcionarios = new ArrayList<>();

        String sql = "SELECT funcionario_id, nome, cpf, email, telefone FROM Funcionario ORDER BY nome";

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sql);
                ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId(rs.getInt("funcionario_id"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setTelefone(rs.getString("telefone"));

                funcionarios.add(funcionario);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar Funcionários: " + e.getMessage());
        }

        return funcionarios;
    }

    // LISTAR COMPLETO
    // ______________________________________________________

    public static ArrayList<Funcionario> listarCompleto() {
        ArrayList<Funcionario> funcionarios = new ArrayList<>();

        String sql = "SELECT f.funcionario_id, f.nome, " +
                "c.cargo_id AS cargo_id, c.nome AS cargo_nome, c.salario, c.cargaHoraria, c.senha, " +
                "a.agendaFuncionario_id AS agenda_id, a.atividade " +
                "FROM funcionario f " +
                "LEFT JOIN cargo c ON f.cargo_id = c.id " +
                "LEFT JOIN agendaFuncionario a ON f.funcionario_id = a.funcionario_id " +
                "ORDER BY f.nome";

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sql);
                ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId(rs.getInt("funcionario_id"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setTelefone(rs.getString("telefone"));

                // Cargo
                Cargo cargo = new Cargo();
                cargo.setId(rs.getInt("cargo_id"));
                cargo.setNome(rs.getString("cargo_nome"));
                cargo.setSalario(rs.getString("salario"));
                cargo.setCargaHoraria(rs.getString("cargaHoraria"));
                cargo.setSenha(rs.getString("senha"));
                funcionario.setCargo(cargo);

                // AgendaFuncionario
                if (rs.getInt("agenda_id") > 0) {
                    AgendaFuncionario agenda = new AgendaFuncionario();
                    agenda.setId(rs.getInt("agenda_id"));

                    agenda.setAtividade(rs.getString("atividade"));

                    agenda.setFuncionario(funcionario);

                    funcionario.setAgendaFuncionario(agenda);
                }

                funcionarios.add(funcionario);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar Funcionários: " + e.getMessage());
        }

        return funcionarios;
    }

    // METODOS AUXILIARES
    // ------------------------------------------------------

    // BUSCAR FUNCIONÁRIOS PELO CARGO
    // ______________________________________________________

    public static ArrayList<Funcionario> buscarFuncionariosPorCargo(int cargoId) {
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT funcionario_id, nome, cpf, email, telefone FROM Funcionario WHERE cargo_id=?";

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, cargoId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId(rs.getInt("funcionario_id"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setTelefone(rs.getString("telefone"));
                // Não carrego agendaFuncionario e cargo para evitar ciclo
                funcionarios.add(funcionario);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return funcionarios;
    }

    // BUSCAR FUNCIONÁRIOS POR ÁREA
    // ______________________________________________________
    public static ArrayList<Funcionario> buscarFuncionariosPorArea(int areaId) {
        ArrayList<Funcionario> funcionarios = new ArrayList<>();

        String sql = "SELECT funcionario_id, nome, cpf, email, telefone FROM Funcionario WHERE area_id=?";

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, areaId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId(rs.getInt("funcionario_id"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setTelefone(rs.getString("telefone"));
                funcionarios.add(funcionario);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return funcionarios;
    }

    // BUSCAR FUNCIONARIO POR ID - DEVOLVE FUNCIONARIO
    // ___________________________________________________________________
    public static Funcionario buscarFuncionarioPor_ID(int id) {
        Funcionario funcionario = null;
        String sql = "SELECT funcionario_id, nome, cpf, telefone, email FROM Funcionario WHERE funcionario_id=?";

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                funcionario = new Funcionario();
                funcionario.setId(rs.getInt("funcionario_id"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setTelefone(rs.getString("telefone"));
                funcionario.setEmail(rs.getString("email"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return funcionario;
    }

    // BUSCA FUNCIONARIO POR ID - DEVOLVE ID
    // ______________________________________________________________________

    public static int buscaFuncionario_ID(int funcionario_id) {

String sqlFuncionario = "SELECT funcionario_id FROM Funcionario WHERE funcionario_id = ?";
        int id = -1;

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(sqlFuncionario)) {
            ps.setInt(1, funcionario_id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                id = rs.getInt("funcionario_id");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;

    }

}
