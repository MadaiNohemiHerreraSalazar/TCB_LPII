package br.edu.ifpr.zoologicio.controller;

import java.util.ArrayList;
import br.edu.ifpr.zoologicio.model.Habitat;
import br.edu.ifpr.zoologicio.model.dao.HabitatDAO;

public class HabitatController {

    // Cadastrar
    public void cadastrarHabitat(Habitat habitat) {
        if (habitat == null) {
            System.out.println("Objeto Habitat não pode ser nulo!");
            return;
        }

        if (habitat.getNome() == null || habitat.getNome().isEmpty()) {
            System.out.println("Nome não pode ser vazio!");
            return;
        }

        if (habitat.getArea() == null || habitat.getArea().getId() == null || habitat.getArea().getId() <= 0) {
            System.out.println("Área inválida!");
            return;
        }

        HabitatDAO.cadastrar(habitat);
    }

    // Editar
    public void editarHabitat(Habitat habitat) {
        if (habitat == null) {
            System.out.println("Objeto Habitat não pode ser nulo!");
            return;
        }

        if (habitat.getId() == null || habitat.getId() <= 0) {
            System.out.println("ID inválido!");
            return;
        }

        if (habitat.getNome() == null || habitat.getNome().isEmpty()) {
            System.out.println("Nome não pode ser vazio!");
            return;
        }

        HabitatDAO.editar(habitat);
    }

    // Excluir
    public void deletarHabitat(int id) {
        if (id <= 0) {
            System.out.println("ID inválido!");
            return;
        }

        HabitatDAO.delete(id);
    }

    // Buscar por ID
    public Habitat selecionarHabitat(int id) {
        if (id <= 0) {
            System.out.println("ID inválido!");
            return null;
        }

        return HabitatDAO.select(id);
    }

    // Listar simples
    public ArrayList<Habitat> listarHabitats() {
        return HabitatDAO.listar();
    }

    // Listar completo (com animais e área)
    public ArrayList<Habitat> listarHabitatsCompleto() {
        return HabitatDAO.listarCompleto();
    }

    // Buscar habitats por área
    public ArrayList<Habitat> buscarHabitatsPorArea(int areaId) {
        if (areaId <= 0) {
            System.out.println("ID de área inválido!");
            return null;
        }

        return HabitatDAO.buscarHabitatsPorArea(areaId);
    }
}
