package br.edu.ifpr.zoologicio.controller;

import java.util.ArrayList;

import br.edu.ifpr.zoologicio.model.Animal;
import br.edu.ifpr.zoologicio.model.dao.AnimalDAO;
import br.edu.ifpr.zoologicio.model.dao.VeterinarioDAO;

public class AnimalController {

    public void cadastrarAnimal(Animal animal, int veterinarioId) {

        if (animal == null || animal.getNome() == null || animal.getNome().trim().isEmpty()) {
            System.out.println("Nome não pode ser vazio!");
            return;
        }

        if (animal.getHabitat() == null || animal.getHabitat().getId() == null) {
            System.out.println("Habitat inválido!");
            return;
        }

        Integer veterinario_Id = VeterinarioDAO.buscaVeterinario_ID(veterinarioId);
        
        if (veterinario_Id == null) {
            System.out.println("Fornecedor inválido!");
            return;
        }

        AnimalDAO.cadastrar(animal, veterinario_Id);
    }

    public void editarAnimal(Animal animal, int veterinarioId) {
        if (animal == null) {
            System.out.println("Objeto Animal não pode ser nulo!");
            return;
        }

        if (animal.getId() == null || animal.getId() <= 0) {
            System.out.println("ID inválido!");
            return;
        }

        if (animal.getNome() == null || animal.getNome().trim().isEmpty()) {
            System.out.println("Nome não pode ser vazio!");
            return;
        }

         Integer veterinario_Id = VeterinarioDAO.buscaVeterinario_ID(veterinarioId);
        
        if (veterinario_Id == null) {
            System.out.println("Fornecedor inválido!");
            return;
        }

        AnimalDAO.editar(animal, veterinario_Id);
    }

    public void deleteAnimal(int id) {
        if (id <= 0) {
            System.out.println("ID inválido!");
            return;
        }

        AnimalDAO.delete(id);
    }

    public Animal selecionarAnimal(int id) {
        if (id <= 0) {
            System.out.println("ID inválido!");
            return null;
        }

        return AnimalDAO.buscarAnimalPorId(id); 
    }

    public ArrayList<Animal> listarAnimais() {
        return AnimalDAO.listar();
    }
}
