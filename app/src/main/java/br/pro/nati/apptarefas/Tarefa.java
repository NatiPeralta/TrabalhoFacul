package br.pro.nati.apptarefas;

public class Tarefa {

    public int id;
    public String nome, horario, data;

    public Tarefa() {

    }

    public Tarefa(String nome, String horario, String data) {
        this.nome = nome;
        this.horario = horario;
        this.data = data;
    }

    public Tarefa(int id, String nome, String horario, String data) {
        this.id = id;
        this.nome = nome;
        this.horario = horario;
        this.data = data;
    }

    @Override
    public String toString() {
        return nome + "  -  " + horario ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}



