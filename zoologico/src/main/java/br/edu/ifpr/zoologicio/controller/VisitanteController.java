package br.edu.ifpr.zoologicio.controller;

import br.edu.ifpr.zoologicio.model.Visitante;
import br.edu.ifpr.zoologicio.model.dao.VisitanteDAO;

public class VisitanteController {

    private VisitanteDAO dao;

    public VisitanteController(){
        this.dao = new VisitanteDAO();
    }

    public void cadastrarVisitante(Visitante visitante) {
        if (visitante.getNome() == null) {
            System.out.println("Nome não pode ser vazio!");
            return;
        }

        dao.cadastrar(visitante);
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

        dao.editar(visitante);
    }

    public void deleteVisitante(int id) {

        if (id <= 0) {

            System.out.println("id invalido");
            return;
        }

        dao.delete(id);
    }

    public void selecionarVisitante(int id) {

        if (id <= 0) {

            System.out.println("id invalido");
            return;
        }

        dao.select(id);
    }

    // public void listarVisitante()

}
