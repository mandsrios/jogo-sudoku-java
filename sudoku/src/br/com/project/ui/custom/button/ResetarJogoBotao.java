package br.com.project.ui.custom.button;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ResetarJogoBotao extends JButton {

    public ResetarJogoBotao(final ActionListener actionListener){
        this.setText("Reiniciar Jogo");
        this.addActionListener(actionListener);
    }
}
