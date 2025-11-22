package br.edu.ifpr.zoologicio.controller;

import java.util.ArrayList;
import br.edu.ifpr.zoologicio.model.Cargo;
import br.edu.ifpr.zoologicio.model.dao.CargoDAO;

public class CargoController {

    // Cadastrar
    public void cadastrarCargo(Cargo cargo) {
        if (cargo == null) {
            System.out.println("Objeto Cargo não pode ser nulo!");
            return;
        }

        if (cargo.getNome() == null || cargo.getNome().isEmpty()) {
            System.out.println("Nome não pode ser vazio!");
            return;
        }

        CargoDAO.cadastrar(cargo);
    }

    // Editar
    public void editarCargo(Cargo cargo) {
        if (cargo == null) {
            System.out.println("Objeto Cargo não pode ser nulo!");
            return;
        }

        if (cargo.getId() == null || cargo.getId() <= 0) {
            System.out.println("ID inválido!");
            return;
        }

        if (cargo.getNome() == null || cargo.getNome().isEmpty()) {
            System.out.println("Nome não pode ser vazio!");
            return;
        }

        CargoDAO.editar(cargo);
    }

    // Excluir
    public void deletarCargo(int id) {
        if (id <= 0) {
            System.out.println("ID inválido!");
            return;
        }

        CargoDAO.delete(id);
    }

    // Buscar por ID
    public Cargo selecionarCargo(int id) {
        if (id <= 0) {
            System.out.println("ID inválido!");
            return null;
        }

        return CargoDAO.select(id);
    }

    // Listar todos
    public ArrayList<Cargo> listarCargos() {
        return CargoDAO.listar();
    }

    // Listar completo (com funcionários)
    public ArrayList<Cargo> listarCargosCompleto() {
        return CargoDAO.listarCompleto();
    }
}
