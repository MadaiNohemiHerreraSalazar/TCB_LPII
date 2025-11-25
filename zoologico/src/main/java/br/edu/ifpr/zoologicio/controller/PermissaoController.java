package br.edu.ifpr.zoologicio.controller;

import java.sql.Connection;
import java.util.ArrayList;
import br.edu.ifpr.zoologicio.model.CargoPermissao;
import br.edu.ifpr.zoologicio.model.dao.PermissaoDAO;

public class PermissaoController {

    private PermissaoDAO dao;

    public PermissaoController() {
        this.dao = new PermissaoDAO();
    }

    // Cadastrar
    public void cadastrarPermissao(CargoPermissao permissao) {
        if (permissao == null) {
            System.out.println("Objeto Permissão inválido!");
            return;
        }
        if (permissao.getNome() == null || permissao.getNome().isEmpty()) {
            System.out.println("Nome não pode ser vazio!");
            return;
        }

        // PermissaoDAO.cadastrarPermissao(permissao);
    }

    // Editar
    public void editarPermissao(CargoPermissao permissao) {
        if (permissao == null) {
            System.out.println("Objeto Permissão inválido!");
            return;
        }
        if (permissao.getId() == null || permissao.getId() <= 0) {
            System.out.println("ID inválido!");
            return;
        }
        if (permissao.getNome() == null || permissao.getNome().isEmpty()) {
            System.out.println("Nome não pode ser vazio!");
            return;
        }

        // PermissaoDAO.editarPermissao(permissao);
    }

    // Excluir
    public void deletePermissao(int id) {
        if (id <= 0) {
            System.out.println("ID inválido!");
            return;
        }

        dao.delete(id);
    }

    // Selecionar por ID
    public ArrayList<CargoPermissao> selecionarPermissao(int id) {
        if (id <= 0) {
            System.out.println("ID inválido!");
            return null;
        }

        return dao.select(id);
    }

    // Listar tudo
    public ArrayList<CargoPermissao> listarPermissao() {
        return dao.listar();
    }
}
