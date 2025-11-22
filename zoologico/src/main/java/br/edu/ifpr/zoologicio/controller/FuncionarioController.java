package br.edu.ifpr.zoologicio.controller;

import java.util.ArrayList;
import br.edu.ifpr.zoologicio.model.Funcionario;
import br.edu.ifpr.zoologicio.model.dao.FuncionarioDAO;

public class FuncionarioController {

    // Cadastrar
    public void cadastrarFuncionario(Funcionario funcionario) {
        if (funcionario == null) {
            System.out.println("Objeto Funcionario não pode ser nulo!");
            return;
        }

        if (funcionario.getNome() == null || funcionario.getNome().isEmpty()) {
            System.out.println("Nome não pode ser vazio!");
            return;
        }

        FuncionarioDAO.cadastrar(funcionario);
    }

    // Editar
    public void editarFuncionario(Funcionario funcionario) {
        if (funcionario == null) {
            System.out.println("Objeto Funcionario não pode ser nulo!");
            return;
        }

        if (funcionario.getId() == null || funcionario.getId() <= 0) {
            System.out.println("ID inválido!");
            return;
        }

        if (funcionario.getNome() == null || funcionario.getNome().isEmpty()) {
            System.out.println("Nome não pode ser vazio!");
            return;
        }

        FuncionarioDAO.editar(funcionario);
    }

    // Excluir
    public void deletarFuncionario(int id) {
        if (id <= 0) {
            System.out.println("ID inválido!");
            return;
        }

        FuncionarioDAO.delete(id);
    }

    // Buscar por ID
    public Funcionario selecionarFuncionario(int id) {
        if (id <= 0) {
            System.out.println("ID inválido!");
            return null;
        }

        return FuncionarioDAO.buscarFuncionarioPor_ID(id); // Usa o método correto
    }

    // Listar simples
    public ArrayList<Funcionario> listarFuncionarios() {
        return FuncionarioDAO.listar();
    }

    // Listar completo
    public ArrayList<Funcionario> listarFuncionariosCompleto() {
        return FuncionarioDAO.listarCompleto();
    }

    // Buscar por Cargo
    public ArrayList<Funcionario> buscarFuncionariosPorCargo(int cargoId) {
        if (cargoId <= 0) {
            System.out.println("Cargo inválido!");
            return null;
        }
        return FuncionarioDAO.buscarFuncionariosPorCargo(cargoId);
    }

    // Buscar por Área
    public ArrayList<Funcionario> buscarFuncionariosPorArea(int areaId) {
        if (areaId <= 0) {
            System.out.println("Área inválida!");
            return null;
        }
        return FuncionarioDAO.buscarFuncionariosPorArea(areaId);
    }
}
