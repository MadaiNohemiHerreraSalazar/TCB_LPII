package br.edu.ifpr.zoologicio.controller;

import java.util.ArrayList;
import br.edu.ifpr.zoologicio.model.Fornecedor;
import br.edu.ifpr.zoologicio.model.dao.FornecedorDAO;

public class FornecedorController {

    // Cadastrar
    public void cadastrarFornecedor(Fornecedor fornecedor) {
        if (fornecedor == null) {
            System.out.println("Objeto Fornecedor não pode ser nulo!");
            return;
        }

        if (fornecedor.getNome() == null || fornecedor.getNome().isEmpty()) {
            System.out.println("Nome não pode ser vazio!");
            return;
        }

        FornecedorDAO.cadastrar(fornecedor);
    }

    // Editar
    public void editarFornecedor(Fornecedor fornecedor) {
        if (fornecedor == null) {
            System.out.println("Objeto Fornecedor não pode ser nulo!");
            return;
        }

        if (fornecedor.getId() == null || fornecedor.getId() <= 0) {
            System.out.println("ID inválido!");
            return;
        }

        if (fornecedor.getNome() == null || fornecedor.getNome().isEmpty()) {
            System.out.println("Nome não pode ser vazio!");
            return;
        }

        FornecedorDAO.editar(fornecedor);
    }

    // Deletar
    public void deletarFornecedor(int id) {
        if (id <= 0) {
            System.out.println("ID inválido!");
            return;
        }

        FornecedorDAO.delete(id);
    }

    // Buscar por ID
    public Fornecedor selecionarFornecedor(int id) {
        if (id <= 0) {
            System.out.println("ID inválido!");
            return null;
        }

        return FornecedorDAO.select(id);
    }

    // Listar todos
    public ArrayList<Fornecedor> listarFornecedores() {
        return FornecedorDAO.listar();
    }
}
