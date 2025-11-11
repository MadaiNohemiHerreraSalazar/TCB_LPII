package br.edu.ifpr.zoologicio.model;

import java.util.ArrayList;

public class CadastroFuncionario {

    private ArrayList<Funcionario>  funcionarios = new ArrayList<Funcionario>();


    public CadastroFuncionario(){

    }


    public ArrayList<Funcionario> getFuncionarios() {
        return funcionarios;
    }


    public void setFuncionarios(ArrayList<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }
    
}