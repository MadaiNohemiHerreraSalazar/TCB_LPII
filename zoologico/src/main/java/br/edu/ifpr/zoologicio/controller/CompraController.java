package br.edu.ifpr.zoologicio.controller;

import java.util.ArrayList;

import br.edu.ifpr.zoologicio.model.Compra;
import br.edu.ifpr.zoologicio.model.dao.CompraDAO;

public class CompraController {

    private CompraDAO dao;

    public CompraController(){
        this.dao = new CompraDAO();
    }


    public void cadastrarCompra(Compra compra){
        if(compra.getData() == null){
            System.out.println("Data não pode ser vazio!");
            return;
        }

        dao.cadastrar(compra);
    }

    public void editarCompra(Compra compra){
        if(compra.getData() == null || compra.getData().isEmpty()){
            System.out.println("Data não pode ser vazio!");
            return;
        }

        if(compra.getId() <= 0){
            System.out.println("id invalido");
            return;
        }

        dao.editar(compra);
    }

    public void deleteCompra(int id){
       
        if(id <= 0){

            System.out.println("id invalido");
            return;
        }

        dao.delete(id);
    }

    public void selecionarCompra(int id){

        if(id <= 0){

            System.out.println("id invalido");
            return;
        }

        dao.select(id);
    }

    public ArrayList<Compra> listarCompras() {
        return dao.listar();
    }
    
}
