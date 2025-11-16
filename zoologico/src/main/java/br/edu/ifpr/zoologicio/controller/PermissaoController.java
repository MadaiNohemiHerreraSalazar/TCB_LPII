package br.edu.ifpr.zoologicio.controller;

import java.util.ArrayList;

import br.edu.ifpr.zoologicio.model.Permissao;
import br.edu.ifpr.zoologicio.model.dao.PermissaoDAO;

public class PermissaoController {

    private PermissaoDAO dao;

    public PermissaoController() {
        this.dao = new PermissaoDAO();
    }

    public void cadastrarPermissao(Permissao permissao) {

        if (permissao.getNome() == null || permissao.getNome().isEmpty()) {
            System.out.println("Nome não pode ser vazio!");
            return;
        }

        dao.cadastrar(permissao);
    }

    public void editarPermissao(Permissao permissao) {

        if (permissao.getNome() == null || permissao.getNome().isEmpty()) {
            System.out.println("Nome não pode ser vazio!");
            return;
        }

        if (permissao.getId() <= 0) {
            System.out.println("ID inválido!");
            return;
        }

        dao.editar(permissao);
    }

    public void deletePermissao(int id) {

        if (id <= 0) {
            System.out.println("ID inválido!");
            return;
        }

        dao.delete(id);
    }

    public ArrayList<Permissao> selecionarPermissao(int id) {

        if (id <= 0) {
            System.out.println("ID inválido!");
            return null;
        }

        return dao.select(id);
    }

    public ArrayList<Permissao> listarPermissao() {
        return dao.listar();
    }

}
