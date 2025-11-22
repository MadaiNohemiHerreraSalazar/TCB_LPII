package br.edu.ifpr.zoologicio.controller;

import java.util.ArrayList;

import br.edu.ifpr.zoologicio.model.Alimento_Rotina;
import br.edu.ifpr.zoologicio.model.dao.Alimento_RotinaDAO;

public class AlimentoRotinaController {

    private Alimento_RotinaDAO dao;

    public AlimentoRotinaController() {
        this.dao = new Alimento_RotinaDAO();
    }

    public void cadastrarAlimentoRotina(Alimento_Rotina alimentoRotina, int fornecedor_id, int rotinaAlimentar_id) throws Exception {

        // Verificacão de data
        Integer fornecedorId = FornecedorController.buscaFornecedor_ID(fornecedor_id);

        if (fornecedorId == null) {
            throw new Exception("Fornecedor não possui ID!");
        }

        Integer rotinaAlimentarId = RotinaAlimentarController.buscaRotinaAlimentar_ID(rotinaAlimentar_id);

        if (alimentoRotina.getId() == null) {
            System.out.println("Id não pode ser vazio!");
            return;
        }

        dao.cadastrar(alimentoRotina);
    }

    public void editarAlimentoRotina(Alimento_Rotina alimentoRotina) {

        if (alimentoRotina.getId() <= 0) {
            System.out.println("id invalido");
            return;
        }

        dao.editar(alimentoRotina);
    }

    public void deleteAlimentoRotina(int id) {

        if (id <= 0) {

            System.out.println("id invalido");
            return;
        }

        dao.delete(id);
    }

    public void selecionarAlimentoRotina(int id) {

        if (id <= 0) {

            System.out.println("id invalido");
            return;
        }

        dao.select(id);
    }

    public ArrayList<Alimento_Rotina> listarAlimentoRotina() {
        return dao.listar();
    }

}
