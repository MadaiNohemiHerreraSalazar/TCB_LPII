package br.edu.ifpr.zoologicio.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.edu.ifpr.zoologicio.model.RotinaAlimentar;
import br.edu.ifpr.zoologicio.model.dao.AgendaAnimalDAO;
import br.edu.ifpr.zoologicio.model.dao.ConnectionFactory;
import br.edu.ifpr.zoologicio.model.dao.FornecedorDAO;
import br.edu.ifpr.zoologicio.model.dao.RotinaAlimentarDAO;

public class RotinaAlimentarController {

    private RotinaAlimentarDAO dao;

    public RotinaAlimentarController() {
        this.dao = new RotinaAlimentarDAO();
    }

    public void cadastrarRotinaAlimentar(RotinaAlimentar rotinaAlimentar, int fornecedor_id) throws SQLException {
        // Esto va a ser sobre el nombre del Animal

        int idAgendaAnimal = AgendaAnimalController.buscarAgendaAnimal(rotinaAlimentar.getAgendaAnimal().getAnimal().getId());

        if (idAgendaAnimal == -1) {
            throw new SQLException("AgendaAnimal não encontrada! Cadastre o animal primeiro.");
        }

        Integer fornecedorId = FornecedorController.buscaFornecedor_ID(fornecedor_id);

        if (fornecedorId == null) {
            throw new SQLException("Fornecedor não possui ID!");
        }

        if (rotinaAlimentar.getData() == null) {
            System.out.println("Data não pode ser vazio!");
            return;
        }

        dao.cadastrar(rotinaAlimentar, idAgendaAnimal, fornecedorId);
        System.out.println("RotinaAlimentar cadastrada com sucesso!");
    }

    // BUSCAR FORNECEDOR
    // ______________________________________________________

    public static int buscaRotinaAlimentar_ID(int rotinaAlimentar_id) {

        String sqlRotinaAlimentar = "SELECT from rotinasAlimentares WHERE id= ?";
        int id = -1;

        try (Connection con = ConnectionFactory.getConnection();
        PreparedStatement ps = con.prepareStatement(sqlRotinaAlimentar);) {
            ps.setInt(1, rotinaAlimentar_id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;

    }

    public void editaRotinaAlimentar(RotinaAlimentar rotinaAlimentar, int fornecedor_id) throws SQLException {
        if (rotinaAlimentar.getData() == null || rotinaAlimentar.getData().isEmpty()) {
            System.out.println("Data não pode ser vazio!");
            return;
        }

        if (rotinaAlimentar.getId() <= 0) {
            System.out.println("id invalido");
            return;
        }

         Integer fornecedorId = FornecedorController.buscaFornecedor_ID(fornecedor_id);

        if (fornecedorId == null) {
            throw new SQLException("Fornecedor não possui ID!");
        }

        dao.editar(rotinaAlimentar, fornecedor_id);
        System.out.println("RotinaAlimentar atualizada com sucesso!");
    }

    public void deleteRotinaAlimentar(int id) {

        if (id <= 0) {

            System.out.println("id invalido");
            return;
        }

        dao.delete(id);
        System.out.println("RotinaAlimentar excluída com sucesso!");
    }

    public void selecionarRotinaAlimentar(int id) {

        if (id <= 0) {

            System.out.println("id invalido");
            return;
        }

        dao.select(id);
    }

    public ArrayList<RotinaAlimentar> listarRotinasAlimentares() {
        return dao.listar();
    }
}
