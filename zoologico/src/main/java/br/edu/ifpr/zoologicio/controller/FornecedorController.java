package br.edu.ifpr.zoologicio.controller;

import java.util.ArrayList;

import br.edu.ifpr.zoologicio.model.Fornecedor;
import br.edu.ifpr.zoologicio.model.dao.FornecedorDAO;

public class FornecedorController {

    private FornecedorDAO dao;

    public FornecedorController(){
        this.dao = new FornecedorDAO();
    }


    public void cadastrarFornecedor(Fornecedor fornecedor){
        if(fornecedor.getNome() == null){
            System.out.println("Nome não pode ser vazio!");
            return;
        }

        dao.cadastrar(fornecedor);
    }

    public void editarFornecedor(Fornecedor fornecedor){
        if(fornecedor.getNome() == null || fornecedor.getNome().isEmpty()){
            System.out.println("Nome não pode ser vazio!");
            return;
        }

        if(fornecedor.getId() <= 0){
            System.out.println("id invalido");
            return;
        }

        dao.editar(fornecedor);
    }

    public void deleteFornecedor(int id){
       
        if(id <= 0){

            System.out.println("id invalido");
            return;
        }

        dao.delete(id);
    }

    public void selecionarFornecedor(int id){

        if(id <= 0){

            System.out.println("id invalido");
            return;
        }

        dao.select(id);
    }

    public ArrayList<Fornecedor> listarFornecedores() {
        return dao.listar();
    }
    
}
