package br.edu.ifpr.zoologicio.model;

public class AgendaAnimal {

    private int id;
    private String consulta;
    private String banho;
    private String medicacao;
    private String atividade;
    private Animal animal;
    private Veterinario veterinario;
    private RotinaAlimentar rotinaAlimentar;

    public AgendaAnimal() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConsulta() {
        return consulta;
    }

    public void setConsulta(String consulta) {
        this.consulta = consulta;
    }

    public  Animal getAnimal() {
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