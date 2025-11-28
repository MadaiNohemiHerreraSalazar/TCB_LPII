package br.edu.ifpr.zoologicio.controller;

import java.util.ArrayList;

import br.edu.ifpr.zoologicio.model.AgendaAnimal;
import br.edu.ifpr.zoologicio.model.RotinaAlimentar;
import br.edu.ifpr.zoologicio.model.dao.AgendaAnimalDAO;
import br.edu.ifpr.zoologicio.model.dao.FornecedorDAO;
import br.edu.ifpr.zoologicio.model.dao.RotinaAlimentarDAO;

public class RotinaAlimentarController {

    private RotinaAlimentarDAO dao;

    public RotinaAlimentarController() {
        this.dao = new RotinaAlimentarDAO();  
    }

    // CADASTRAR
    public void cadastrarRotinaAlimentar(RotinaAlimentar rotinaAlimentar, int fornecedor_id) {

        if (rotinaAlimentar == null) {
            System.out.println("Objeto RotinaAlimentar inválido!");
            return;
        }
        if (rotinaAlimentar.getData() == null || rotinaAlimentar.getData().isEmpty()) {
            System.out.println("Data não pode ser vazia!");
            return;
        }
        if (rotinaAlimentar.getAgendaAnimal() == null || 
            rotinaAlimentar.getAgendaAnimal().getAnimal() == null) {
            System.out.println("AgendaAnimal e Animal devem estar cadastrados!");
            return;
        }

         AgendaAnimal agenda = AgendaAnimalDAO.buscarAgendaAnimalPorID(rotinaAlimentar.getAgendaAnimal().getAnimal().getId());

        if (agenda.getId() <= 0) {
            System.out.println("AgendaAnimal não encontrada! Cadastre o animal primeiro.");
            return;
        }

        Integer fornecedorId = FornecedorDAO.buscaFornecedor_ID(fornecedor_id);
        
        RotinaAlimentarDAO.cadastrar(rotinaAlimentar, agenda.getId(), fornecedorId);
        System.out.println("RotinaAlimentar cadastrada com sucesso!");
    }

    // EDITAR
    public void editarRotinaAlimentar(RotinaAlimentar rotinaAlimentar, int fornecedor_id) {

        if (rotinaAlimentar == null) {
            System.out.println("Objeto RotinaAlimentar inválido!");
            return;
        }
        if (rotinaAlimentar.getId() == null || rotinaAlimentar.getId() <= 0) {
            System.out.println("ID inválido!");
            return;
        }
        if (rotinaAlimentar.getData() == null || rotinaAlimentar.getData().isEmpty()) {
            System.out.println("Data não pode ser vazia!");
            return;
        }

        Integer fornecedorId = FornecedorDAO.buscaFornecedor_ID(fornecedor_id);
        if (fornecedorId == null) {
            System.out.println("Fornecedor inválido!");
            return;
        }

        RotinaAlimentarDAO.editar(rotinaAlimentar, fornecedorId);
        System.out.println("RotinaAlimentar atualizada com sucesso!");
    }

    // DELETE
    public void deleteRotinaAlimentar(int id) {
        if (id <= 0) {
            System.out.println("ID inválido!");
            return;
        }

        RotinaAlimentarDAO.delete(id);
        System.out.println("RotinaAlimentar excluída com sucesso!");
    }

    // SELECT
    public RotinaAlimentar selecionarRotinaAlimentar(int id) {
        if (id <= 0) {
            System.out.println("ID inválido!");
            return null;
        }

        return dao.select(id);
    }

    // LISTAR
    public ArrayList<RotinaAlimentar> listarRotinasAlimentares() {
        return dao.listar();
    }
}
