package br.edu.ifpr.zoologicio.controller;

import java.util.ArrayList;
import br.edu.ifpr.zoologicio.model.Veterinario;
import br.edu.ifpr.zoologicio.model.dao.VeterinarioDAO;

public class VeterinarioController {

    // Cadastrar
    public void cadastrarVeterinario(Veterinario veterinario){
        if (veterinario == null) {
            System.out.println("Objeto Veterinario não pode ser nulo!");
            return;
        }

        if (veterinario.getNome() == null || veterinario.getNome().isEmpty()) {
            System.out.println("Nome não pode ser vazio!");
            return;
        }

        VeterinarioDAO.cadastrar(veterinario);
    }

    // Editar
    public void editarVeterinario(Veterinario veterinario){
        if (veterinario == null) {
            System.out.println("Objeto Veterinario não pode ser nulo!");
            return;
        }

        if (veterinario.getId() == null || veterinario.getId() <= 0) {
            System.out.println("ID inválido!");
            return;
        }

        if (veterinario.getNome() == null || veterinario.getNome().isEmpty()) {
            System.out.println("Nome não pode ser vazio!");
            return;
        }

        VeterinarioDAO.editar(veterinario);
    }

    // Excluir
    public void deleteVeterinario(int id){
        if (id <= 0) {
            System.out.println("ID inválido!");
            return;
        }

        VeterinarioDAO.delete(id);
    }

    // Buscar por ID
    public Veterinario selecionarVeterinario(int id){
        if (id <= 0) {
            System.out.println("ID inválido!");
            return null;
        }

        return VeterinarioDAO.select(id);
    }

    // Listar todos
    public ArrayList<Veterinario> listarVeterinarios() {
        return VeterinarioDAO.listar();
    }

    // Listar completo (com animais)
    public ArrayList<Veterinario> listarVeterinariosCompleto() {
        return VeterinarioDAO.listarCompleto();
    }
}
