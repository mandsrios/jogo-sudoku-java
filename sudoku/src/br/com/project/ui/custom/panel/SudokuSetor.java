package br.com.project.ui.custom.panel;

import br.com.project.ui.custom.input.NumberText;

import javax.sound.sampled.Line;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;

import static java.awt.Color.black;

public class SudokuSetor extends JPanel {

    public SudokuSetor(final List<NumberText> textFields){
        var dimensao = new Dimension(170, 170);
        this.setSize(dimensao);
        this.setPreferredSize(dimensao);
        this.setBorder(new LineBorder(black, 2,true));
        this.setVisible(true);
        textFields.forEach(this::add);
    }
}
