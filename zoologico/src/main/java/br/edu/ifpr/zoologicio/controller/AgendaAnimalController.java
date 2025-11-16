package br.edu.ifpr.zoologicio.controller;

import java.util.ArrayList;
import br.edu.ifpr.zoologicio.model.AgendaAnimal;
import br.edu.ifpr.zoologicio.model.dao.AgendaAnimalDAO;

public class AgendaAnimalController {


    private AgendaAnimalDAO dao;

    public AgendaAnimalController(){
        this.dao = new AgendaAnimalDAO();
    }


    public void cadastrarAgendaAnimal(AgendaAnimal agendaAnimal){
        //Esto va a ser sobre el nombre del Animal

        if(agendaAnimal.getAtividade() == null){
            System.out.println("Atividade não pode ser vazio!");
            return;
        }

        dao.cadastrar(agendaAnimal);
    }

    public void editarAgendaAnimal(AgendaAnimal agendaAnimal){
        if(agendaAnimal.getAtividade() == null || agendaAnimal.getAtividade().isEmpty()){
            System.out.println("Nome não pode ser vazio!");
            return;
        }

        if(agendaAnimal.getId() <= 0){
            System.out.println("id invalido");
            return;
        }

        dao.editar(agendaAnimal);
    }

    public void deleteAgendaAnimal(int id){
       
        if(id <= 0){

            System.out.println("id invalido");
            return;
        }

        dao.delete(id);
    }

    public void selecionarAgendaAnimal(int id){

        if(id <= 0){

            System.out.println("id invalido");
            return;
        }

        dao.select(id);
    }

    public ArrayList<AgendaAnimal> listarAgendaAnimais() {
        return dao.listar();
    }
    
}
