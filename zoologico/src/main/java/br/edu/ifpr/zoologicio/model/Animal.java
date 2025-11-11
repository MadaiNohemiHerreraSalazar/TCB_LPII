package br.edu.ifpr.zoologicio.model;

public class Animal {
    
   private  String Nome;
   private  String Descricao;
   private  String Especie;
  private   String Genero;
   private  String Peso;
   private  String Altura;
  private   String Saude;
   private  Veterinario veterinario; 
   private  Habitat habitat;
    

    public Animal(){

    }


    public String getNome() {
        return Nome;
    }


    public void setNome(String nome) {
        Nome = nome;
    }


    public String getDescricao() {
        return Descricao;
    }


    public void setDescricao(String descricao) {
        Descricao = descricao;
    }


    public String getEspecie() {
        return Especie;
    }


    public void setEspecie(String especie) {
        Especie = especie;
    }


    public String getGenero() {
        return Genero;
    }


    public void setGenero(String genero) {
        Genero = genero;
    }


    public String getPeso() {
        return Peso;
    }


    public void setPeso(String peso) {
        Peso = peso;
    }


    public String getAltura() {
        return Altura;
    }


    public void setAltura(String altura) {
        Altura = altura;
    }


    public String getSaude() {
        return Saude;
    }


    public void setSaude(String saude) {
        Saude = saude;
    }


    public Veterinario getVeterinario() {
        return veterinario;
    }


    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }


    public Habitat getHabitat() {
        return habitat;
    }


    public void setHabitat(Habitat habitat) {
        this.habitat = habitat;
    }
}
