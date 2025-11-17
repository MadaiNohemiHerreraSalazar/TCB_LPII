package br.edu.ifpr.zoologicio.model;

public class AgendaFuncionario {

    private int id;
    private String criadoPor;
    private String ultimaAtualizacao;
    private String atividade;
    // private Cargo cargo;
    private int cargo_ID;
    // private Funcionario funcionario;
    private int funcionario_ID;

    public AgendaFuncionario() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCriadoPor() {
        return criadoPor;
    }

    public void setCriadoPor(String criadoPor) {
        this.criadoPor = criadoPor;
    }

    public String getUltimaAtualizacao() {
        return ultimaAtualizacao;
    }

    public void setUltimaAtualizacao(String ultimaAtualizacao) {
        this.ultimaAtualizacao = ultimaAtualizacao;
    }

    public String getAtividade() {
        return atividade;
    }

    public void setAtividade(String atividade) {
        this.atividade = atividade;
    }

    public int getCargo_ID() {
        return cargo_ID;
    }

    public void setCargo_ID(int cargo_ID) {
        this.cargo_ID = cargo_ID;
    }

    public int getFuncionario_ID() {
        return funcionario_ID;
    }

    public void setFuncionario_ID(int funcionario_ID) {
        this.funcionario_ID = funcionario_ID;
    }


}
