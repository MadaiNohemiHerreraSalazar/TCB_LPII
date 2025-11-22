package br.edu.ifpr.zoologicio.controller;

import java.util.ArrayList;
import br.edu.ifpr.zoologicio.model.AgendaFuncionario;
import br.edu.ifpr.zoologicio.model.dao.AgendaFuncionarioDAO;

public class AgendaFuncionarioController {

    // Cadastrar
    public void cadastrarAgendaFuncionario(AgendaFuncionario agendaFuncionario) {
        if (agendaFuncionario == null) {
            System.out.println("Objeto AgendaFuncionario não pode ser nulo!");
            return;
        }

        if (agendaFuncionario.getAtividade() == null || agendaFuncionario.getAtividade().isEmpty()) {
            System.out.println("Atividade não pode ser vazia!");
            return;
        }

        AgendaFuncionarioDAO.cadastrar(agendaFuncionario);
    }

    // Editar
    public void editarAgendaFuncionario(AgendaFuncionario agendaFuncionario) {
        if (agendaFuncionario == null) {
            System.out.println("Objeto AgendaFuncionario não pode ser nulo!");
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

    // Selecionar por ID
    public AgendaFuncionario selecionarAgendaFuncionario(int id) {
        if (id <= 0) {
            System.out.println("ID inválido!");
            return null;
        }

        return AgendaFuncionarioDAO.select(id);
    }

    // Listar simples
    public ArrayList<AgendaFuncionario> listarAgendaFuncionarios() {
        return AgendaFuncionarioDAO.listar();
    }

    // Listar completo com Cargo + Funcionario
    public ArrayList<AgendaFuncionario> listarAgendaFuncionariosCompleto() {
        return AgendaFuncionarioDAO.listarCompleto();
    }
}
