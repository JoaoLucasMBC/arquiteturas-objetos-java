package entities.user;

import entities.mensagem.Mensagem;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String nome;
    private Integer idade;
    private List<Mensagem> mensagemList = new ArrayList<>();

    public User(String nome, Integer idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public List<Mensagem> getMensagemList() {
        return mensagemList;
    }


    public void postMensagem(Mensagem msg) {
        mensagemList.add(msg);
    }

    public void eraseMensagem(Mensagem msg) {
        mensagemList.remove(msg);
    }

    @Override
    public String toString() {
        return nome + " - " + idade + " - " + mensagemList.size() + " mensagens";
    }
}
