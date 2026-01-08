package br.com.project.model;

import java.util.Collection;
import java.util.List;

import static br.com.project.model.GameStatusEnum.*;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class Tabuleiro {

    private final List<List<Espaco>> espacos;

    public Tabuleiro(List<List<Espaco>> espacos) {
        this.espacos = espacos;
    }

    public List<List<Espaco>> getEspacos() {
        return espacos;
    }

    public GameStatusEnum getStatus(){
        //Verifica se a posição não é fixa e contém um valor inserido
        if (espacos.stream().flatMap(Collection::stream).noneMatch(s -> !s.isFixo() && nonNull(s.getAtual()))){
            return NAOINICIADO;
        }

        //Verifica se tem alguma posição na lista que não está com o espaço atual preenchido
        return espacos.stream().flatMap(Collection::stream).anyMatch(s -> isNull(s.getAtual())) ? INCOMPLETO : COMPLETO;
    }


    //Verifica se possui algum erro
    public boolean possuiErros(){
        //Se não foi iniciado, não poderá haver erros ainda
        if (getStatus() == NAOINICIADO){
            return false;
        }

        //Há algum espaço com a posição atual preenchida e com o valor diferente do esperado?
        //Se tiver = Possui erros
        return espacos.stream().flatMap(Collection::stream).anyMatch(s -> nonNull(s.getAtual()) && !s.getAtual().equals(s.getEsperado()));
    }

    //Mudar valor
    public boolean changeValues(final int col, final int row, final Integer value){
        var space = espacos.get(col).get(row);
        if (space.isFixo()){
            return false;
        }

        space.setAtual(value);
        return true;
    }

    //Limpar valor
    public boolean limparValores(final int col, final int row){
        var space = espacos.get(col).get(row);
        if (space.isFixo()){
            return false;
        }

        space.limparEspaco();
        return true;
    }

    //Resetar
    public void reset(){
        espacos.forEach(c -> c.forEach(Espaco::limparEspaco));
    }

    //Verificar se o jogo acabou
    public boolean JogoFinalizado(){
        return !possuiErros() && getStatus() == COMPLETO;
    }


}
