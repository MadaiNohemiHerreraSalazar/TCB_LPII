package br.edu.ifpr.zoologicio.controller;

import br.edu.ifpr.zoologicio.model.Cargo;
import br.edu.ifpr.zoologicio.model.dao.CargoDAO;

public class CargoController {

     private CargoDAO dao;

    public CargoController(){
        this.dao = new CargoDAO();
    }


    public void cadastrarCargo(Cargo cargo){
        if(cargo.getNome() == null){
            System.out.println("Nome não pode ser vazio!");
            return;
        }

        dao.cadastrar(cargo);
    }

    public void editarCargo(Cargo cargo){
        if(cargo.getNome() == null || cargo.getNome().isEmpty()){
            System.out.println("Nome não pode ser vazio!");
            return;
        }

        if(cargo.getId() <= 0){
            System.out.println("id invalido");
            return;
        }

        dao.editar(cargo);
    }

    public void deleteCargo(int id){
       
        if(id <= 0){

            System.out.println("id invalido");
            return;
        }

        dao.delete(id);
    }

    public void selecionarCargo(int id){

        if(id <= 0){

            System.out.println("id invalido");
            return;
        }

        dao.select(id);
    }

    //public void listarCargo()
    
    
}
