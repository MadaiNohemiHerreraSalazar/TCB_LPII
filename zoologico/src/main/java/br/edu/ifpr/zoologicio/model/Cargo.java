package br.edu.ifpr.zoologicio.model;

public class Cargo {

    String salario;
    String cargaHoraroia;
    String sennha;
    String nome;
    Funcionario funcionario;

    public Cargo() {

    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public String getCargaHoraroia() {
        return cargaHoraroia;
    }

    public void setCargaHoraroia(String cargaHoraroia) {
        this.cargaHoraroia = cargaHoraroia;
    }

    public String getSennha() {
        return sennha;
    }

    public void setSennha(String sennha) {
        this.sennha = sennha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
}
