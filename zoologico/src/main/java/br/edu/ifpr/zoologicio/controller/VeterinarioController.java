package br.edu.ifpr.zoologicio.controller;

import br.edu.ifpr.zoologicio.model.Veterinario;
import br.edu.ifpr.zoologicio.model.dao.VeterinarioDAO;

public class VeterinarioController {

    private VeterinarioDAO dao;

    public VeterinarioController(){
        this.dao = new VeterinarioDAO();
    }


    public void cadastrarVeterinario(Veterinario veterinario){
        if(veterinario.getNome() == null){
            System.out.println("Nome não pode ser vazio!");
            return;
        }

        dao.cadastrar(veterinario);
    }

    public void editarVeterinario(Veterinario veterinario){
        if(veterinario.getNome() == null || veterinario.getNome().isEmpty()){
            System.out.println("Nome não pode ser vazio!");
            return;
        }

        if(veterinario.getId() <= 0){
            System.out.println("id invalido");
            return;
        }

        dao.editar(veterinario);
    }

    public void deleteVeterinario(int id){
       
        if(id <= 0){

            System.out.println("id invalido");
            return;
        }

        dao.delete(id);
    }

    public void selecionarVeterinario(int id){

        if(id <= 0){

            System.out.println("id invalido");
            return;
        }

        dao.select(id);
    }

    //public void listarVeterinarios()
    
}
