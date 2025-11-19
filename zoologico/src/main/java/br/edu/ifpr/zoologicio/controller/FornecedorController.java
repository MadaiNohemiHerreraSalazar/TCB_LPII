package br.edu.ifpr.zoologicio.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.edu.ifpr.zoologicio.model.Fornecedor;
import br.edu.ifpr.zoologicio.model.dao.ConnectionFactory;
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

    // BUSCAR FORNECEDOR
    // ______________________________________________________

    public static int buscaFornecedor_ID(int fornecedor_id) {

        String sqlFornecedor = "SELECT from fornecedor WHERE id= ?";
        int id = -1;

        try (Connection con = ConnectionFactory.getConnection();
        PreparedStatement ps = con.prepareStatement(sqlFornecedor);) {
            ps.setInt(1, fornecedor_id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;

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
