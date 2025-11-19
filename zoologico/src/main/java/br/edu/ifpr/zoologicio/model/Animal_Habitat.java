package br.edu.ifpr.zoologicio.model;

public class Animal_Habitat {

    private Integer id;
    private int animal_id;
    private int habitat_id;
    
    public Animal_Habitat(){
        
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
    public int getHabitat_id() {
        return habitat_id;
    }
    public void setHabitat_id(int habitat_id) {
        this.habitat_id = habitat_id;
    }
    
    
    
}
