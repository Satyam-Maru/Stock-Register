package com.stockregister;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Color;

public class Sales {

    static JPanel panel;

    protected void setPanel(){
        panel = new JPanel();
        panel.setBounds(140, 60, 1035, 640);
        panel.setLayout(null);
        panel.setBackground(Color.yellow);
    }

    protected JPanel getPanel(){
        return panel;
    }

}