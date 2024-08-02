package com.stockregister;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Party {

    static JPanel panel;

    protected static void setPanel(){
        panel = new JPanel();
        panel.setBounds(140, 60, 1035, 640);
        panel.setLayout(null);
        panel.setBackground(Color.PINK);
    }

    protected static JPanel getPanel(){
        return panel;
    }

}
