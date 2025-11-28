package br.edu.ifpr.zoologicio.controller;

import java.util.ArrayList;
import br.edu.ifpr.zoologicio.model.AgendaAnimal;
import br.edu.ifpr.zoologicio.model.dao.AgendaAnimalDAO;

public class AgendaAnimalController {

    // Cadastrar
    public void cadastrarAgendaAnimal(AgendaAnimal agendaAnimal) {
        if (agendaAnimal == null) {
            System.out.println("Objeto AgendaAnimal não pode ser nulo!");
            return;
        }

        if (agendaAnimal.getAtividade() == null || agendaAnimal.getAtividade().isEmpty()) {
            System.out.println("Atividade não pode ser vazia!");
            return;
        }

        AgendaAnimalDAO.cadastrar(agendaAnimal);
    }

    // Editar
    public static void editarAgendaAnimal(AgendaAnimal agendaAnimal) {
        if (agendaAnimal == null) {
            System.out.println("Objeto AgendaAnimal não pode ser nulo!");
            return;
        }

        if (agendaAnimal.getId() == null || agendaAnimal.getId() <= 0) {
            System.out.println("ID inválido!");
            return;
        }

        if (agendaAnimal.getAtividade() == null || agendaAnimal.getAtividade().isEmpty()) {
            System.out.println("Atividade não pode ser vazia!");
            return;
        }

        AgendaAnimalDAO.editar(agendaAnimal);
    }

    // Excluir
    public void deletarAgendaAnimal(int id) {
        if (id <= 0) {
            System.out.println("ID inválido!");
            return;
        }

        AgendaAnimalDAO.delete(id);
    }

    // Buscar por ID
    public AgendaAnimal selecionarAgendaAnimal(int id) {
        if (id <= 0) {
            System.out.println("ID inválido!");
            return null;
        }

        return AgendaAnimalDAO.select(id);
    }

    // Listar todos
    public ArrayList<AgendaAnimal> listarAgendaAnimais() {
        return AgendaAnimalDAO.listar();
    }
}
