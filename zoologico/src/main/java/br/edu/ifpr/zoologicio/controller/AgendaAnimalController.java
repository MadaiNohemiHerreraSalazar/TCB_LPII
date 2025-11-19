package br.edu.ifpr.zoologicio.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import br.edu.ifpr.zoologicio.model.AgendaAnimal;
import br.edu.ifpr.zoologicio.model.dao.AgendaAnimalDAO;
import br.edu.ifpr.zoologicio.model.dao.ConnectionFactory;

public class AgendaAnimalController {


    private AgendaAnimalDAO dao;

    public AgendaAnimalController(){
        this.dao = new AgendaAnimalDAO();
    }


    public void cadastrarAgendaAnimal(AgendaAnimal agendaAnimal){
        //Esto va a ser sobre el nombre del Animal

        if(agendaAnimal.getAtividade() == null){
            System.out.println("Atividade não pode ser vazio!");
            return;
        }

        dao.cadastrar(agendaAnimal);
    }

    // BUSCAR AGENDA ANIMAL
    // ______________________________________________________

    public static int buscarAgendaAnimal(int animal_id) {

        String sqlAgendaAnimal = "SELECT * FROM agendaAnimais WHERE id = ?";
        int id = -1;

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(sqlAgendaAnimal)) {

            pst.setInt(1, animal_id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    id = rs.getInt("id");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;
    }

    public void editarAgendaAnimal(AgendaAnimal agendaAnimal){
        if(agendaAnimal.getAtividade() == null || agendaAnimal.getAtividade().isEmpty()){
            System.out.println("Nome não pode ser vazio!");
            return;
        }

        if(agendaAnimal.getId() <= 0){
            System.out.println("id invalido");
            return;
        }

        dao.editar(agendaAnimal);
    }

    public void deleteAgendaAnimal(int id){
       
        if(id <= 0){

            System.out.println("id invalido");
            return;
        }

        dao.delete(id);
    }

    public void selecionarAgendaAnimal(int id){

        if(id <= 0){

            System.out.println("id invalido");
            return;
        }

        dao.select(id);
    }

    // public ArrayList<AgendaAnimal> listarAgendaAnimais() {
    //     return dao.listar();
    // }
    
}
