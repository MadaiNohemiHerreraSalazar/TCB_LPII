
package br.edu.ifpr.zoologicio.controller;

import java.util.ArrayList;

import br.edu.ifpr.zoologicio.model.Cargo_Permissao;
import br.edu.ifpr.zoologicio.model.dao.CargoPermissao_DAO;

public class CargoPermissaoController {

    private CargoPermissao_DAO dao;

    public CargoPermissaoController(){
        this.dao = new CargoPermissao_DAO();
    }


    public void cadastrarCargoPermissao(Cargo_Permissao cargoPermissao){
        if(cargoPermissao.getNome() == null){
            System.out.println("Data não pode ser vazio!");
            return;
        }

        dao.cadastrar(cargoPermissao);
    }

    public void editarCargoPermissao(Cargo_Permissao cargoPermissao){
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

    // public ArrayList<CargoPermissao> listarCargoPermissoes() {
    //     return dao.listar();
    // }
    
}
