package br.com.project.ui.custom.button;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ChecarStatusJogoBotao extends JButton {

    public ChecarStatusJogoBotao(final ActionListener actionListener){
        this.setText("Verificar Jogo");
        this.addActionListener(actionListener);
    }
}
