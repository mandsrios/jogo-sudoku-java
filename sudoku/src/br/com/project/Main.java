package br.com.project;

import br.com.project.model.Espaco;
import br.com.project.model.Tabuleiro;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static br.com.project.util.TabuleiroTemplate.TABULEIRO_TEMPLATE;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class Main {

    public final static Scanner scanner = new Scanner(System.in);

    private static Tabuleiro tabuleiro;

    private final static int TABULEIRO_LIMITE = 9;

    public static void main(String[] args) {
        final var posicoes = Stream.of(args)
                .collect(Collectors.toMap(
                        k -> k.split(";")[0],
                        v -> v.split(";")[1]
                ));
        var opcoes = 1;
        while(true){
            System.out.println("Selecione uma das opções a seguir:");
            System.out.println("1 - Iniciar um novo jogo");
            System.out.println("2 - Colocar um novo número");
            System.out.println("3 - Remover um número");
            System.out.println("4 - Visualizar jogo atual");
            System.out.println("5 - Verificar status do jogo");
            System.out.println("6 - Limpar jogo");
            System.out.println("7 - Finalizar jogo");
            System.out.println("8 - Sair");

            opcoes = scanner.nextInt();

            switch (opcoes){
                case 1 -> iniciarJogo(posicoes);
                case 2 -> inserirNumero();
                case 3 -> removerNumero();
                case 4 -> visualizarJogoAtual();
                case 5 -> visualizarStatus();
                case 6 -> limparJogo();
                case 7 -> finalizarJogo();
                case 8 -> System.exit(0);
                default -> System.out.println("Opção inválida. Selecione uma das opções disponíveis no menu!");
            }
        }
    }

    private static void iniciarJogo(final Map<String, String> posicoes) {
        if (nonNull(tabuleiro)){
            System.out.println("O jogo já foi iniciado!");
            return;
        }

        List<List<Espaco>> espaco = new ArrayList<>();
        for (int i = 0; i < TABULEIRO_LIMITE; i++){
            espaco.add(new ArrayList<>());
            for (int j = 0; j < TABULEIRO_LIMITE; j++){
                //Busca a posição no mapa
                var configurarPosicoes = posicoes.get("%s,%s".formatted(i,j));
                //Busca o valor inteiro no mapa
                var esperado = Integer.parseInt(configurarPosicoes.split(",")[0]);
                //Busca o valor booleano no mapa
                var fixo = Boolean.parseBoolean(configurarPosicoes.split(",")[1]);
                //Cria o espaço atual
                var espacoAtual = new Espaco(esperado, fixo);

                espaco.get(i).add(espacoAtual);
            }
        }

        tabuleiro = new Tabuleiro(espaco);
        System.out.println("O jogo está pronto para começar!");
    }

    private static void inserirNumero() {
        if (isNull(tabuleiro)){
            System.out.println("O jogo ainda não foi iniciado!");
            return;
        }

        System.out.println("Informe a coluna em que o número será inserido:");
        var coluna = executarAteNumeroValido(0,8);
        System.out.println("Informe a linha em que o número será inserido:");
        var linha = executarAteNumeroValido(0,8);
        System.out.printf("Informe o número que vai entrar na posição [%s,%s]:\n", coluna, linha);
        var valor = executarAteNumeroValido(1,9);

        //Se a posição escolhida já possui um valor fixo, não deixa inserir
        if (!tabuleiro.changeValues(coluna, linha, valor)){
            System.out.printf("A posição [%s,%s] tem um valor fixo\n",coluna, linha);
        }
    }

    private static void removerNumero() {
        if (isNull(tabuleiro)){
            System.out.println("O jogo ainda não foi iniciado!");
            return;
        }

        System.out.println("Informe a coluna em que o número será inserido:");
        var coluna = executarAteNumeroValido(0,8);
        System.out.println("Informe a linha em que o número será inserido:");
        var linha = executarAteNumeroValido(0,8);

        //Verifica se a posição escolhida tem um valor fixo
        if (!tabuleiro.limparValores(coluna, linha)){
            System.out.printf("A posição [%s,%s] tem um valor fixo\n",coluna, linha);
        }
    }

    private static void visualizarJogoAtual() {
        if (isNull(tabuleiro)){
            System.out.println("O jogo ainda não foi iniciado!");
            return;
        }

        var args = new Object[81];
        var argPos = 0;

        for (int i = 0; i < TABULEIRO_LIMITE; i++){
            for (var col: tabuleiro.getEspacos()){
                args[argPos ++] = " " + (isNull(col.get(i).getAtual()) ? " " : col.get(i).getAtual());
            }
        }

        System.out.println("Seu jogo se encontra da seguinte forma:");
        System.out.printf((TABULEIRO_TEMPLATE) + "\n", args);
    }

    private static void visualizarStatus() {
        if (isNull(tabuleiro)){
            System.out.println("O jogo ainda não foi iniciado!");
            return;
        }

        System.out.printf("O jogo atualmente se encontra no status: %s\n", tabuleiro.getStatus().getLabel());

        if (tabuleiro.possuiErros()){
            System.out.println("O jogo contém erros!");
        }else{
            System.out.println("O jogo não contém erros!");
        }
    }

    private static void limparJogo() {
        if (isNull(tabuleiro)){
            System.out.println("O jogo ainda não foi iniciado!");
            return;
        }

        System.out.println("Deseja realmente limpar o jogo? Você perderá todo o seu progresso!");
        var confirma = scanner.next();

        while (!confirma.equalsIgnoreCase("sim") && !confirma.equalsIgnoreCase("não")){
            System.out.println("Informe 'sim' ou 'não'!");
            confirma = scanner.next();
        }

        if (confirma.equalsIgnoreCase("sim")){
            tabuleiro.reset();
        }
    }

    private static void finalizarJogo() {
        if (isNull(tabuleiro)){
            System.out.println("O jogo ainda não foi iniciado!");
            return;
        }

        if (tabuleiro.JogoFinalizado()){
            System.out.println("Parabéns! Você concluiu o jogo!");
            visualizarJogoAtual();
            tabuleiro = null;
        }else if (tabuleiro.possuiErros()){
            System.out.println("Seu jogo contém erros! Verifique seu tabuleiro e ajuste-o!");
        }else{
            System.out.println("Você ainda precisa preencher algum espaço!");
        }
    }

    private static int executarAteNumeroValido (final int min, final int max){
        var atual = scanner.nextInt();
        while(atual < min || atual > max){
            System.out.printf("Informe um número entre %s e %s\n!", min, max);
            atual = scanner.nextInt();
        }
        return atual;
    }


}