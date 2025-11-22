package br.edu.ifpr.zoologicio.controller;

import java.util.ArrayList;
import br.edu.ifpr.zoologicio.model.Compra;
import br.edu.ifpr.zoologicio.model.dao.CompraDAO;
import br.edu.ifpr.zoologicio.model.dao.FuncionarioDAO;
import br.edu.ifpr.zoologicio.model.dao.VisitanteDAO;

public class CompraController {

    // Cadastrar
    public void cadastrarCompra(Compra compra, int visitante_id, int funcionario_id) {
        if (compra == null) {
            System.out.println("Objeto Compra não pode ser nulo!");
            return;
        }

        if (compra.getData() == null || compra.getData().isEmpty()) {
            System.out.println("Data não pode ser vazia!");
            return;
        }

         int visitanteID = VisitanteDAO.buscaVisitante_ID(visitante_id);
        
        if (visitanteID <= 0) {
            System.out.println("Fornecedor inválido!");
            return;
        }

        Integer funcionarioID = FuncionarioDAO.buscaFuncionario_ID(funcionario_id);
        
        if (visitanteID <= 0 ) {
            System.out.println("Fornecedor inválido!");
            return;
        }

        CompraDAO.cadastrar(compra, visitanteID, funcionarioID);
    }

    // Editar
    public void editarCompra(Compra compra, int visitante_id, int funcionario_id) {
        if (compra == null) {
            System.out.println("Objeto Compra não pode ser nulo!");
            return;
        }

        if (compra.getId() == null || compra.getId() <= 0) {
            System.out.println("ID inválido!");
            return;
        }

        if (compra.getData() == null || compra.getData().isEmpty()) {
            System.out.println("Data não pode ser vazia!");
            return;
        }

         int visitanteID = VisitanteDAO.buscaVisitante_ID(visitante_id);
        
        if (visitanteID <= 0) {
            System.out.println("Fornecedor inválido!");
            return;
        }

        Integer funcionarioID = FuncionarioDAO.buscaFuncionario_ID(funcionario_id);
        
        if (visitanteID <= 0 ) {
            System.out.println("Fornecedor inválido!");
            return;
        }

        CompraDAO.editar(compra, visitante_id, funcionarioID);
    }

    // Deletar
    public void deletarCompra(int id) {
        if (id <= 0) {
            System.out.println("ID inválido!");
            return;
        }

        CompraDAO.delete(id);
    }

    // Buscar por ID
    public Compra selecionarCompra(int id) {
        if (id <= 0) {
            System.out.println("ID inválido!");
            return null;
        }

        return CompraDAO.select(id);
    }

    // Listar todas
    public ArrayList<Compra> listarCompras() {
        return CompraDAO.listar();
    }
}
