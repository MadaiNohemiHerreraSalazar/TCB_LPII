package br.edu.ifpr.zoologicio.model;

import java.util.ArrayList;

public class CadastroVeterinario {

        private ArrayList<Veterinario> veterinarios = new ArrayList<Veterinario>();


    public CadastroVeterinario(){

    }


    public ArrayList<Veterinario> getVeterinarios() {
        return veterinarios;
    }


    public void setVeterinarios(ArrayList<Veterinario> veterinarios) {
        this.veterinarios = veterinarios;
    }
    
}
