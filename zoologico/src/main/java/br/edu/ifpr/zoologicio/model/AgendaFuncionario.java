package br.edu.ifpr.zoologicio.model;

public class AgendaFuncionario {

    private Integer  id;
    private String atividade;
    private Funcionario funcionario;

    public AgendaFuncionario() {

    } 

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getAtividade() {
        return atividade;
    }

    public void setAtividade(String atividade) {
        this.atividade = atividade;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }


}
