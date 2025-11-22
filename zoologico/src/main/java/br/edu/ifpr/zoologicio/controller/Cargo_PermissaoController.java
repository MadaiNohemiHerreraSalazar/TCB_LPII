
package br.edu.ifpr.zoologicio.controller;

import java.util.ArrayList;

import br.edu.ifpr.zoologicio.model.Cargo_Permissao;
import br.edu.ifpr.zoologicio.model.dao.Cargo_PermissaoDAO;

public class Cargo_PermissaoController {

    private Cargo_PermissaoDAO dao;

    public Cargo_PermissaoController(){
        this.dao = new Cargo_PermissaoDAO();
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

    public ArrayList<Cargo_Permissao> listarCargoPermissoes() {
        return dao.listar();
    }
    
    
}
