package br.com.project.service;

import br.com.project.model.Espaco;
import br.com.project.model.GameStatusEnum;
import br.com.project.model.Tabuleiro;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TabuleiroService {

    private final static int TABULEIRO_LIMITE = 9;

    private final Tabuleiro tabuleiro;

    public TabuleiroService (final Map<String, String> configJogo){
        this.tabuleiro = new Tabuleiro(initTabuleiro(configJogo));
    }

    public List<List<Espaco>> getEspacos(){
        return this.tabuleiro.getEspacos();
    }

    public void reset(){
        this.tabuleiro.reset();
    }

    public boolean possuiErros(){
        return this.tabuleiro.possuiErros();
    }

    public GameStatusEnum getStatus(){
        return this.tabuleiro.getStatus();
    }

    public boolean JogoFinalizado(){
        return this.tabuleiro.JogoFinalizado();
    }

    private List<List<Espaco>> initTabuleiro(Map<String, String> configJogo) {

        List<List<Espaco>> espaco = new ArrayList<>();
        for (int i = 0; i < TABULEIRO_LIMITE; i++){
            espaco.add(new ArrayList<>());
            for (int j = 0; j < TABULEIRO_LIMITE; j++){
                //Busca a posição no mapa
                var configurarPosicoes = configJogo.get("%s,%s".formatted(i,j));
                //Busca o valor inteiro no mapa
                var esperado = Integer.parseInt(configurarPosicoes.split(",")[0]);
                //Busca o valor booleano no mapa
                var fixo = Boolean.parseBoolean(configurarPosicoes.split(",")[1]);
                //Cria o espaço atual
                var espacoAtual = new Espaco(esperado, fixo);

                espaco.get(i).add(espacoAtual);
            }
        }

        return espaco;
    }
}
