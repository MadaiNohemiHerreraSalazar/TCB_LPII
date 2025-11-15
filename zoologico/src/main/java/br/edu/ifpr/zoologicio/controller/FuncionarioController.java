package br.edu.ifpr.zoologicio.controller;

import br.edu.ifpr.zoologicio.model.Funcionario;
import br.edu.ifpr.zoologicio.model.dao.FuncionarioDAO;

public class FuncionarioController {

     private FuncionarioDAO dao;

    public FuncionarioController(){
        this.dao = new FuncionarioDAO();
    }


    public void cadastrarFuncionario(Funcionario funcionario){
        if(funcionario.getNome() == null){
            System.out.println("Nome não pode ser vazio!");
            return;
        }

        dao.cadastrar(funcionario);
    }

    public void editarFuncionario(Funcionario funcionario){
        if(funcionario.getNome() == null || funcionario.getNome().isEmpty()){
            System.out.println("Nome não pode ser vazio!");
            return;
        }

        if(funcionario.getId() <= 0){
            System.out.println("id invalido");
            return;
        }

        dao.editar(funcionario);
    }

    public void deleteFuncionario(int id){
       
        if(id <= 0){

            System.out.println("id invalido");
            return;
        }

        dao.delete(id);
    }

    public void selecionarFuncionario(int id){

        if(id <= 0){

            System.out.println("id invalido");
            return;
        }

        dao.select(id);
    }

    //public void listarFuncionario()
    
}
