package br.edu.ifpr.zoologicio.controller;

import java.util.ArrayList;

import br.edu.ifpr.zoologicio.model.Visitante;
import br.edu.ifpr.zoologicio.model.dao.VisitanteDAO;

public class VisitanteController {


    public void cadastrarVisitante(Visitante visitante) {
        if (visitante.getNome() == null) {
            System.out.println("Nome não pode ser vazio!");
            return;
        }

        VisitanteDAO.cadastrar(visitante);
    }

    public void editarVisitante(Visitante visitante) {
        if (visitante.getNome() == null || visitante.getNome().isEmpty()) {
            System.out.println("Nome não pode ser vazio!");
            return;
        }

        if (visitante.getId() <= 0) {
            System.out.println("id invalido");
            return;
        }

        VisitanteDAO.editar(visitante);
    }

    public void deleteVisitante(int id) {

        if (id <= 0) {

            System.out.println("id invalido");
            return;
        }

        VisitanteDAO.delete(id);
    }

    public void selecionarVisitante(int id) {

        if (id <= 0) {

            System.out.println("id invalido");
            return;
        }

        VisitanteDAO.select(id);
    }

    public ArrayList<Visitante> listarVisitantes() {
        return VisitanteDAO.listar();
    }

}
