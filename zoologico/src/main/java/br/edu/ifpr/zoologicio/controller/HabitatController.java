package br.edu.ifpr.zoologicio.controller;

import java.util.ArrayList;

import br.edu.ifpr.zoologicio.model.Habitat;
import br.edu.ifpr.zoologicio.model.dao.HabitatDAO;

public class HabitatController {

    private HabitatDAO dao;

    public HabitatController(){
        this.dao = new HabitatDAO();
    }


    public void cadastrarHabitat(Habitat habitat){
        if(habitat.getNome() == null){
            System.out.println("Nome não pode ser vazio!");
            return;
        }

        dao.cadastrar(habitat);
    }

    public void editarHabitat(Habitat habitat){
        if(habitat.getNome() == null || habitat.getNome().isEmpty()){
            System.out.println("Nome não pode ser vazio!");
            return;
        }

        if(habitat.getId() <= 0){
            System.out.println("id invalido");
            return;
        }

        dao.editar(habitat);
    }

    public void deleteHabitat(int id){
       
        if(id <= 0){

            System.out.println("id invalido");
            return;
        }

        dao.delete(id);
    }

    public void selecionarHabitat(int id){

        if(id <= 0){

            System.out.println("id invalido");
            return;
        }

        dao.select(id);
    }

    public ArrayList<Habitat> listarHabitats() {
        return dao.listar();
    }
    
}
