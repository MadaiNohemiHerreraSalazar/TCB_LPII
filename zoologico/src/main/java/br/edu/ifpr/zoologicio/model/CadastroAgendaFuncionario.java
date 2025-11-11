package br.edu.ifpr.zoologicio.model;

import java.util.ArrayList;

public class CadastroAgendaFuncionario {

            private ArrayList<AgendaFuncionario> agendaFuncionarios = new ArrayList<AgendaFuncionario>();


    public CadastroAgendaFuncionario (){

    }


    public ArrayList<AgendaFuncionario> getAgendaFuncionarios() {
        return agendaFuncionarios;
    }


    public void setAgendaFuncionarios(ArrayList<AgendaFuncionario> agendaFuncionarios) {
        this.agendaFuncionarios = agendaFuncionarios;
    }
    
}
