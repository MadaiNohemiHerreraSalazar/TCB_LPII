package br.edu.ifpr.zoologicio.model;

public class AgendaAnimal{

   private  Animal animal;
  private   Veterinario veterinario;
   private  RotinaAlimentar rotinaAlimentar;
  private   String consultaData;
  private   String conultaHorario;
  private   String banho;
  private   String medicacao;
  private   String atividade;
    
    public AgendaAnimal(){

    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }

    public RotinaAlimentar getRotinaAlimentar() {
        return rotinaAlimentar;
    }

    public void setRotinaAlimentar(RotinaAlimentar rotinaAlimentar) {
        this.rotinaAlimentar = rotinaAlimentar;
    }

    public String getConsultaData() {
        return consultaData;
    }

    public void setConsultaData(String consultaData) {
        this.consultaData = consultaData;
    }

    public String getConultaHorario() {
        return conultaHorario;
    }

    public void setConultaHorario(String conultaHorario) {
        this.conultaHorario = conultaHorario;
    }

    public String getBanho() {
        return banho;
    }

    public void setBanho(String banho) {
        this.banho = banho;
    }

    public String getMedicacao() {
        return medicacao;
    }

    public void setMedicacao(String medicacao) {
        this.medicacao = medicacao;
    }

    public String getAtividade() {
        return atividade;
    }

    public void setAtividade(String atividade) {
        this.atividade = atividade;
    }
}