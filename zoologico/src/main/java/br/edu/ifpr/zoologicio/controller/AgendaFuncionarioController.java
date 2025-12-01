package br.edu.ifpr.zoologicio.controller;

import java.util.ArrayList;

import br.edu.ifpr.zoologicio.model.AgendaFuncionario;
import br.edu.ifpr.zoologicio.model.dao.AgendaFuncionarioDAO;

public class AgendaFuncionarioController {
    // Cadastrar
    public void cadastrarAgendaFuncionario(AgendaFuncionario agendaFuncionario) {
        if (agendaFuncionario == null) {
            System.out.println("Objeto AgendaAnimal não pode ser nulo!");
            return;
        }

        if (agendaFuncionario.getAtividade() == null || agendaFuncionario.getAtividade().isEmpty()) {
            System.out.println("Atividade não pode ser vazia!");
            return;
        }

        AgendaFuncionarioDAO.cadastrar(agendaFuncionario);
    }

    // Editar
    public static void editarAgendaFuncionario(AgendaFuncionario agendaFuncionario) {
        if (agendaFuncionario == null) {
            System.out.println("Objeto AgendaAnimal não pode ser nulo!");
            return;
        }

        if (agendaFuncionario.getId() == null || agendaFuncionario.getId() <= 0) {
            System.out.println("ID inválido!");
            return;
        }

        if (agendaFuncionario.getAtividade() == null || agendaFuncionario.getAtividade().isEmpty()) {
            System.out.println("Atividade não pode ser vazia!");
            return;
        }

        AgendaFuncionarioDAO.editar(agendaFuncionario);
    }

    // Excluir
    public void deletarAgendaFuncionario(int id) {
        if (id <= 0) {
            System.out.println("ID inválido!");
            return;
        }

        AgendaFuncionarioDAO.delete(id);
    }

    // Buscar por ID
    public AgendaFuncionario selecionarAgendaFuncionario(int id) {
        if (id <= 0) {
            System.out.println("ID inválido!");
            return null;
        }

        return AgendaFuncionarioDAO.select(id);
    }

    // Listar todos
    public ArrayList<AgendaFuncionario> listarAgendaFuncionarios() {
        return AgendaFuncionarioDAO.listar();
    }
}
