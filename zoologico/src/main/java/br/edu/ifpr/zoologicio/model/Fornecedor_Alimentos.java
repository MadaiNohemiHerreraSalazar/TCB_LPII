package br.edu.ifpr.zoologicio.model;

public class Fornecedor_Alimentos {

    private Integer id;
    private int fornecedor_id;
    private int alimento_id;

    public Fornecedor_Alimentos() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getFornecedor_id() {
        return fornecedor_id;
    }

    public void setFornecedor_id(int fornecedor_id) {
        this.fornecedor_id = fornecedor_id;
    }

    public int getAlimento_id() {
        return alimento_id;
    }

    public void setAlimento_id(int alimento_id) {
        this.alimento_id = alimento_id;
    }

}
