package br.edu.ifpr.zoologicio.controller;

import java.util.ArrayList;

import br.edu.ifpr.zoologicio.model.Area;
import br.edu.ifpr.zoologicio.model.dao.AreaDAO;

public class AreaController {

    public void cadastrarArea(Area area) {
        if (area == null || area.getNome() == null || area.getNome().trim().isEmpty()) {
            System.out.println("Nome da área não pode ser vazio!");
            return;
        }

        AreaDAO.cadastrar(area);
    }

    public void editarArea(Area area) {
        if (area == null) {
            System.out.println("Objeto Área não pode ser nulo!");
            return;
        }

        if (area.getId() == null || area.getId() <= 0) {
            System.out.println("ID inválido!");
            return;
        }

        if (area.getNome() == null || area.getNome().trim().isEmpty()) {
            System.out.println("Nome não pode ser vazio!");
            return;
        }

        AreaDAO.editar(area);
    }

    public void deleteArea(int id) {
        if (id <= 0) {
            System.out.println("ID inválido!");
            return;
        }

        AreaDAO.delete(id);
    }

    public Area selecionarArea(int id) {
        if (id <= 0) {
            System.out.println("ID inválido!");
            return null;
        }

        return AreaDAO.buscarAreaPorId(id);
    }

    public ArrayList<Area> listarAreas() {
        return AreaDAO.listar();
    }

    public ArrayList<Area> listarAreasCompleto() {
        return AreaDAO.listarCompleto();
    }
}
