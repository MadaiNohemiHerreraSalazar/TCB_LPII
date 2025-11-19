package br.edu.ifpr.zoologicio.model;

public class Alimento_Rotina {

    private Integer  id;
    private int alimento_id;
    private int fornecedor_id;
    private int rotinaAlimentar_id;

    public Alimento_Rotina(){

    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

     public int getAlimento_id() {
        return alimento_id;
    }

    public void setAlimento_id(int alimento_id) {
        this.alimento_id = alimento_id;
    }
   
    public int getFornecedor_id() {
        return fornecedor_id;
    }
    public void setFornecedor_id(int fornecedor_id) {
        this.fornecedor_id = fornecedor_id;
    }
    public int getRotinaAlimentar_id() {
        return rotinaAlimentar_id;
    }
    public void setRotinaAlimentar_id(int rotinaAlimentar_id) {
        this.rotinaAlimentar_id = rotinaAlimentar_id;
    }
    
}
