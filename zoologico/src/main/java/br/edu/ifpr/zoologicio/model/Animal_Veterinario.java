package br.edu.ifpr.zoologicio.model;

public class Animal_Veterinario {

    private Integer id;
    private int animal_id;
    private int veterinario_id;

    public Animal_Veterinario(){

    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public int getAnimal_id() {
        return animal_id;
    }
    public void setAnimal_id(int animal_id) {
        this.animal_id = animal_id;
    }
    public int getVeterinario_id() {
        return veterinario_id;
    }
    public void setVeterinario_id(int veterinario_id) {
        this.veterinario_id = veterinario_id;
    }
    
}
