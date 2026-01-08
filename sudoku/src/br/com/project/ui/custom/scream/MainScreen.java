package br.com.project.ui.custom.scream;

import br.com.project.model.Espaco;
import br.com.project.service.EventEnum;
import br.com.project.service.NotificacaoService;
import br.com.project.service.TabuleiroService;
import br.com.project.ui.custom.button.ChecarStatusJogoBotao;
import br.com.project.ui.custom.button.FinalizarJogoBotao;
import br.com.project.ui.custom.button.ResetarJogoBotao;
import br.com.project.ui.custom.frame.MainFrame;
import br.com.project.ui.custom.input.NumberText;
import br.com.project.ui.custom.panel.MainPanel;
import br.com.project.ui.custom.panel.SudokuSetor;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static br.com.project.service.EventEnum.LIMPAR_ESPACO;
import static javax.swing.JOptionPane.*;

public class MainScreen {

    private final static Dimension dimensao = new Dimension(600,600);

    private final TabuleiroService tabuleiroService;
    private final NotificacaoService notificacaoService;

    private JButton finalizarJogo;
    private JButton checarStatusJogoBotao;
    private JButton resetarJogoBotao;

    public MainScreen(final Map<String, String> configJogo) {
        this.tabuleiroService = new TabuleiroService(configJogo);
        this.notificacaoService = new NotificacaoService();
    }

    public void buildMainScreen(){
        JPanel mainPanel = new MainPanel(dimensao);
        JFrame mainFrame = new MainFrame(dimensao, mainPanel);
        for (int r = 0; r < 9; r+=3) {
            var endRow = r + 2;
            for (int c = 0; c < 9; c+=3) {
                var endCol = c + 2;
                var spaces = getEspacosSector(tabuleiroService.getEspacos(), c, endCol, r, endRow);
                JPanel sector = generateSection(spaces);
                mainPanel.add(sector);
            }
        }
        addResetarJogoBotao(mainPanel);
        addChecarStatusJogoBotao(mainPanel);
        addFinalizarJogoBotao(mainPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    private List<Espaco> getEspacosSector(final List<List<Espaco>> espacos,
                                            final int initCol, final int endCol,
                                            final int initRow, final int endRow){
        List<Espaco> espacoSector = new ArrayList<>();
        for (int r = initRow; r <= endRow; r++) {
            for (int c = initCol; c <= endCol; c++) {
                espacoSector.add(espacos.get(c).get(r));
            }
        }
        return espacoSector;
    }

    private JPanel generateSection(final List<Espaco> espacos){
        List<NumberText> fields = new ArrayList<>(espacos.stream().map(NumberText::new).toList());
        fields.forEach(t -> notificacaoService.subscrever(LIMPAR_ESPACO, t));
        return new SudokuSetor(fields);
    }

    private void addFinalizarJogoBotao(JPanel mainPanel) {
         finalizarJogo = new FinalizarJogoBotao(e ->{
            if (tabuleiroService.JogoFinalizado()){
                showMessageDialog(null, "Parabéns! Você concluiu o jogo!");
                resetarJogoBotao.setEnabled(false);
                checarStatusJogoBotao.setEnabled(false);
                finalizarJogo.setEnabled(false);
            }else{
                showMessageDialog(null, "Seu jogo possui alguma inconsistência, ajuste e tente novamente!");
            }
        });
        mainPanel.add(finalizarJogo);
    }

    private void addChecarStatusJogoBotao(JPanel mainPanel) {
        checarStatusJogoBotao = new ChecarStatusJogoBotao(e ->{
            var possuiErros = tabuleiroService.possuiErros();
            var statusJogo = tabuleiroService.getStatus();
            var mensagem = switch (statusJogo){
                case NAOINICIADO -> "O jogo não foi iniciado";
                case INCOMPLETO -> "O jogo está incompleto";
                case COMPLETO -> "O jogo está completo";
            };
            mensagem += possuiErros ? " e contém erros" : " e não contém erros";
            showMessageDialog(null, mensagem);
        });

        mainPanel.add(checarStatusJogoBotao);
    }

    private void addResetarJogoBotao(JPanel mainPanel) {
        resetarJogoBotao = new ResetarJogoBotao(e ->{
            var dialogo = showConfirmDialog(
                    null,
                    "Deseja realmente reiniciar o jogo? Você perderá todo o seu progresso!",
                    "Limpar o jogo!",
                    YES_NO_OPTION,
                    QUESTION_MESSAGE
            );
            //Se o usuário selecionou "Sim"
            if (dialogo == 0){
                tabuleiroService.reset();
                notificacaoService.notify(LIMPAR_ESPACO);
            }
        });
        mainPanel.add(resetarJogoBotao);
    }
}
