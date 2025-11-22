package br.edu.ifpr.zoologicio.controller;

import java.util.ArrayList;

import br.edu.ifpr.zoologicio.model.Area;
import br.edu.ifpr.zoologicio.model.dao.AreaDAO;

public class AreaController {

     private AreaDAO dao;

    public AreaController(){
        this.dao = new AreaDAO();
    }


    public void cadastrarArea(Area area){
        if(area.getNome() == null){
            System.out.println("Nome não pode ser vazio!");
            return;
        }

        dao.cadastrar(area);
    }

    public void editarArea(Area area){
        if(area.getNome() == null || area.getNome().isEmpty()){
            System.out.println("Nome não pode ser vazio!");
            return;
        }

        if(area.getId() <= 0){
            System.out.println("id invalido");
            return;
        }

        dao.editar(area);
    }

    public void deleteArea(int id){
       
        if(id <= 0){

            System.out.println("id invalido");
            return;
        }

        dao.delete(id);
    }

    public void selecionarArea(int id){

        if(id <= 0){

            System.out.println("id invalido");
            return;
        }

        dao.select(id);
    }

    public ArrayList<Area> listarAreas() {
        return dao.listar();
    }

     public ArrayList<Area> listarAreasCompleto() {
        return dao.listarCompleto();
    }
    
}
