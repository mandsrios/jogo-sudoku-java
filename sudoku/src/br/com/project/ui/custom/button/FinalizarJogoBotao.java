package br.com.project.ui.custom.button;

import javax.swing.*;
import java.awt.event.ActionListener;

public class FinalizarJogoBotao extends JButton {

    public FinalizarJogoBotao(final ActionListener actionListener){
        this.setText("Concluir");
        this.addActionListener(actionListener);
    }
}
