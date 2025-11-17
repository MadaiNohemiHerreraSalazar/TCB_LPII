//precisa ter o funcionario já cadatrado
//Eu acho que o Cargo no Objeto AgendaFuncionario é meio obvio, já que o funcionario tem seu cargo.
//Arrumar AgendaFuncionario

package br.edu.ifpr.zoologicio.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.edu.ifpr.zoologicio.model.AgendaFuncionario;

public class AgendaFuncionarioDAO {

    public static void cadastrar(AgendaFuncionario agendaFuncionario) {

        Connection con = ConnectionFactory.getConnection();


        try {

            String sqlFuncionarioAgenda = "INSERT INTO agendaFuncionarios(criadorPor, ultimaAtualizacao, atividade) VALUES (?,?,?)";

            PreparedStatement psFuncionarioAgenda = con.prepareStatement(sqlFuncionarioAgenda);

            psFuncionarioAgenda.setString(1, agendaFuncionario.getCriadoPor());
            psFuncionarioAgenda.setString(2, agendaFuncionario.getUltimaAtualizacao());
            psFuncionarioAgenda.setString(3, agendaFuncionario.getAtividade());

            psFuncionarioAgenda.executeUpdate();
            System.out.println("AgendaFuncionario inserida com sucesso");

        } catch (Exception e) {

            // TODO: handle exception
            e.printStackTrace();

        }

    }

    public static void editar(AgendaFuncionario agendaFuncionario) {

        Connection con = ConnectionFactory.getConnection();

        try {

            String sql = "UPDATE agendaFuncionarios SET criadoPor=?, ultimaAtualizacao=?, atividade=?, WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, agendaFuncionario.getCriadoPor());
            pst.setString(2, agendaFuncionario.getUltimaAtualizacao());
            pst.setString(3, agendaFuncionario.getAtividade());
            pst.setInt(4, agendaFuncionario.getId());

            pst.executeUpdate();
            System.out.println("agendaFuncionario atualizada com sucesso");

        } catch (Exception e) {

            // TODO: handle exception
            System.out.println(e.getMessage());

        }

    }

    public void delete(int id) {
        Connection con = ConnectionFactory.getConnection();

        try {

            String sql = "DELETE FROM agendaFuncionario WHERE id= ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("agendaFuncionario excluida com sucesso");

        } catch (Exception e) {

            // TODO: handle exception
            e.printStackTrace();

        }
    }

    public ArrayList<AgendaFuncionario> select(int id) {

        Connection con = ConnectionFactory.getConnection();
        ArrayList<AgendaFuncionario> agendaFuncionarios = new ArrayList<>();

        try {

            String sql = "SELECT * FROM agendaFuncionarios WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                AgendaFuncionario agendaFuncionario = new AgendaFuncionario();
                agendaFuncionario.setId(rs.getInt("id"));
                agendaFuncionario.setCriadoPor("criadoPor");
                agendaFuncionario.setUltimaAtualizacao("ultimaAtualizacao");
                agendaFuncionario.setAtividade("atividade");
                agendaFuncionarios.add(agendaFuncionario);

            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }

        return agendaFuncionarios;
    }

    public ArrayList<AgendaFuncionario> listar() {

        Connection con = ConnectionFactory.getConnection();

        ArrayList<AgendaFuncionario> agendaFuncionarios = new ArrayList<>();

        try {

            String sql = "SELECT * FROM agendaFuncionarios";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                AgendaFuncionario agendaFuncionario = new AgendaFuncionario();
                agendaFuncionario.setId(rs.getInt("id"));
                agendaFuncionario.setCriadoPor("criadoPor");
                agendaFuncionario.setUltimaAtualizacao("ultimaAtualizacao");
                agendaFuncionario.setAtividade("atividade");
                agendaFuncionarios.add(agendaFuncionario);

            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }

        return agendaFuncionarios;
    }

}
