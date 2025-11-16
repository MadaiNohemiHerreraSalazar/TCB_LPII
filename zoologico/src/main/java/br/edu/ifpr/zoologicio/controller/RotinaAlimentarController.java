package br.edu.ifpr.zoologicio.controller;

import java.util.ArrayList;

import br.edu.ifpr.zoologicio.model.RotinaAlimentar;
import br.edu.ifpr.zoologicio.model.dao.RotinaAlimentarDAO;

public class RotinaAlimentarController {

    private RotinaAlimentarDAO dao;

    public RotinaAlimentarController(){
        this.dao = new RotinaAlimentarDAO();
    }


    public void cadastrarRotinaAlimentar(RotinaAlimentar rotinaAlimentar){
        //Esto va a ser sobre el nombre del Animal

        if(rotinaAlimentar.getData() == null){
            System.out.println("Data não pode ser vazio!");
            return;
        }

        dao.cadastrar(rotinaAlimentar);
    }

    public void editaRotinaAlimentar(RotinaAlimentar rotinaAlimentar){
        if(rotinaAlimentar.getData() == null || rotinaAlimentar.getData().isEmpty()){
            System.out.println("Data não pode ser vazio!");
            return;
        }

        if(rotinaAlimentar.getId() <= 0){
            System.out.println("id invalido");
            return;
        }

        dao.editar(rotinaAlimentar);
    }

    public void deleteRotinaAlimentar(int id){
       
        if(id <= 0){

            System.out.println("id invalido");
            return;
        }

        dao.delete(id);
    }

    public void selecionarRotinaAlimentar(int id){

        if(id <= 0){

            System.out.println("id invalido");
            return;
        }

        dao.select(id);
    }

    public ArrayList<RotinaAlimentar> listarRotinasAlimentares() {
        return dao.listar();
    }
}
