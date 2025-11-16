package br.edu.ifpr.zoologicio.controller;

import java.util.ArrayList;
import br.edu.ifpr.zoologicio.model.AgendaFuncionario;
import br.edu.ifpr.zoologicio.model.dao.AgendaFuncionarioDAO;

public class AgendaFuncionarioController {


    private AgendaFuncionarioDAO dao;

    public  AgendaFuncionarioController() {
        this.dao = new AgendaFuncionarioDAO();
    }


    public void cadastrarAgendaFuncionario(AgendaFuncionario agendaFuncionario){

        if(agendaFuncionario.getAtividade() == null){
            System.out.println("Atividade não pode ser vazio!");
            return;
        }

        dao.cadastrar(agendaFuncionario);
    }

    public void editarAgendaFuncionario(AgendaFuncionario agendaFuncionario){
        if(agendaFuncionario.getAtividade() == null || agendaFuncionario.getAtividade().isEmpty()){
            System.out.println("Atividade não pode ser vazio!");
            return;
        }

        if(agendaFuncionario.getId() <= 0){
            System.out.println("id invalido");
            return;
        }

        dao.editar(agendaFuncionario);
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

    public ArrayList<AgendaFuncionario> listarAgendaFuncionario() {
        return dao.listar();
    }
    
}
    

