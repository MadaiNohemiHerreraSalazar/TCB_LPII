package br.edu.ifpr.zoologicio.controller;

import java.util.ArrayList;

import br.edu.ifpr.zoologicio.model.Animal;
import br.edu.ifpr.zoologicio.model.dao.AnimalDAO;

public class AnimalController {

    private AnimalDAO dao;

    public AnimalController(){
        this.dao = new AnimalDAO();
    }


    public void cadastrarAnimal(Animal animal){
        if(animal.getNome() == null){
            System.out.println("Nome não pode ser vazio!");
            return;
        }

        dao.cadastrar(animal);
    }

    public void editarAnimal(Animal animal){
        if(animal.getNome() == null || animal.getNome().isEmpty()){
            System.out.println("Nome não pode ser vazio!");
            return;
        }

        if(animal.getId() <= 0){
            System.out.println("id invalido");
            return;
        }

        dao.editar(animal);
    }

    public void deleteAnimal(int id){
       
        if(id <= 0){

            System.out.println("id invalido");
            return;
        }

        dao.delete(id);
    }

    public void selecionarAnimal(int id){

        if(id <= 0){

            System.out.println("id invalido");
            return;
        }

        dao.select(id);
    }

    public ArrayList<Animal> listarAnimais() {
        return dao.listar();
    }
    
    
}
