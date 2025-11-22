package br.edu.ifpr.zoologicio.controller;

import java.util.ArrayList;

import br.edu.ifpr.zoologicio.model.Alimento;
import br.edu.ifpr.zoologicio.model.dao.AlimentoDAO;

public class AlimentoController {

    private AlimentoDAO dao;

    public AlimentoController(){
        this.dao = new AlimentoDAO();
    }


    public void cadastrarAlimento(Alimento alimento){
        if(alimento.getNome() == null){
            System.out.println("Nome não pode ser vazio!");
            return;
        }

        dao.cadastrar(alimento);
    }

    public void editarAlimento(Alimento alimento){
        if(alimento.getNome() == null || alimento.getNome().isEmpty()){
            System.out.println("Nome não pode ser vazio!");
            return;
        }

        if(alimento.getId() <= 0){
            System.out.println("id invalido");
            return;
        }

        dao.editar(alimento);
    }

    public void deleteAlimento(int id){
       
        if(id <= 0){

            System.out.println("id invalido");
            return;
        }

        dao.delete(id);
    }

    public void selecionarAlimento(int id){

        if(id <= 0){

            System.out.println("id invalido");
            return;
        }

        dao.select(id);
    }

    public ArrayList<Alimento> listarAlimentos() {
        return dao.listar();
    }
    
}
