package br.edu.ifpr.zoologicio.controller;

import java.util.ArrayList;
import br.edu.ifpr.zoologicio.model.Alimento;
import br.edu.ifpr.zoologicio.model.dao.AlimentoDAO;

public class AlimentoController {

    // Cadastrar
    public void cadastrarAlimento(Alimento alimento) {
        if (alimento == null) {
            System.out.println("Objeto Alimento não pode ser nulo!");
            return;
        }

        if (alimento.getNome() == null || alimento.getNome().isEmpty()) {
            System.out.println("Nome não pode ser vazio!");
            return;
        }

        AlimentoDAO.cadastrar(alimento);
    }

    // Editar
    public void editarAlimento(Alimento alimento) {
        if (alimento == null) {
            System.out.println("Objeto Alimento não pode ser nulo!");
            return;
        }

        if (alimento.getId() == null || alimento.getId() <= 0) {
            System.out.println("ID inválido!");
            return;
        }

        if (alimento.getNome() == null || alimento.getNome().isEmpty()) {
            System.out.println("Nome não pode ser vazio!");
            return;
        }

        AlimentoDAO.editar(alimento);
    }

    // Deletar
    public void deletarAlimento(int id) {
        if (id <= 0) {
            System.out.println("ID inválido!");
            return;
        }

        AlimentoDAO.delete(id);
    }

    // Selecionar por ID
    public Alimento selecionarAlimento(int id) {
        if (id <= 0) {
            System.out.println("ID inválido!");
            return null;
        }

        return AlimentoDAO.select(id);
    }

    // Listar todos
    public ArrayList<Alimento> listarAlimentos() {
        return AlimentoDAO.listar();
    }

}
