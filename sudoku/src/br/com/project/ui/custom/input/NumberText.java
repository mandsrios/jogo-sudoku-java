package br.com.project.ui.custom.input;

import br.com.project.model.Espaco;
import br.com.project.service.EventEnum;
import br.com.project.service.EventListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

import static br.com.project.service.EventEnum.LIMPAR_ESPACO;
import static java.awt.Font.PLAIN;

public class NumberText extends JTextField implements EventListener {

    private final Espaco espaco;

    public NumberText(final Espaco espaco) {
        this.espaco = espaco;
        var dimension = new Dimension(50, 50);
        this.setSize(dimension);
        this.setPreferredSize(dimension);
        this.setVisible(true);
        this.setFont(new Font("Arial", PLAIN, 20));
        this.setHorizontalAlignment(CENTER);
        this.setDocument(new NumberTextLimit());

        //Se for um numero fixo, não permite edição
        this.setEnabled(!espaco.isFixo());
        //Se for um numero fixo, pode inserir o texto
        if (espaco.isFixo()){
            this.setText(espaco.getAtual().toString());
        }

        this.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(final DocumentEvent e) {
                changeSpace();
            }

            @Override
            public void removeUpdate(final DocumentEvent e) {
                changeSpace();
            }

            @Override
            public void changedUpdate(final DocumentEvent e) {
                changeSpace();
            }

            private void changeSpace(){
                if (getText().isEmpty()){
                    espaco.limparEspaco();
                    return;
                }
                espaco.setAtual(Integer.parseInt(getText()));
            }

        });
    }

    @Override
    public void alterar(EventEnum eventType) {
        if (eventType.equals(LIMPAR_ESPACO) && (this.isEnabled())){
            this.setText("");
        }

    }
}
