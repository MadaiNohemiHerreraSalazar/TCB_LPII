
package br.edu.ifpr.zoologicio.controller;

import java.util.ArrayList;

import br.edu.ifpr.zoologicio.model.CargoPermissao;
import br.edu.ifpr.zoologicio.model.dao.CargoPermissaoDAO;

public class CargoPermissaoController {

    private CargoPermissaoDAO dao;

    public CargoPermissaoController(){
        this.dao = new CargoPermissaoDAO();
    }


    public void cadastrarCargoPermissao(CargoPermissao cargoPermissao){
        if(cargoPermissao.getNome() == null){
            System.out.println("Data não pode ser vazio!");
            return;
        }

        dao.cadastrar(cargoPermissao);
    }

    public void editarCargoPermissao(CargoPermissao cargoPermissao){
        if(cargoPermissao.getNome() == null || cargoPermissao.getNome().isEmpty()){
            System.out.println("Data não pode ser vazio!");
            return;
        }

        if(cargoPermissao.getId() <= 0){
            System.out.println("id invalido");
            return;
        }

        dao.editar(cargoPermissao);
    }

    public void deleteCargoPermissao(int id){
       
        if(id <= 0){

            System.out.println("id invalido");
            return;
        }

        dao.delete(id);
    }

    public void selecionarCargoPermissao(int id){

        if(id <= 0){

            System.out.println("id invalido");
            return;
        }

        dao.select(id);
    }

    public ArrayList<CargoPermissao> listarCargoPermissoes() {
        return dao.listar();
    }
    
}
