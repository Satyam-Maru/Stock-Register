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


public class Stock extends JFrame {

    public static void main(String[] args) {

        new Stock();
    }

    static JPanel panel;

    // make a method which returns a panel containing all the stuff to display

    Stock(){
        this.setSize(HomeFrame.HomeFrameWidth, HomeFrame.HomeFrameHeight);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Panel work
        setPanel();
        this.add(getPanel());
        //
        this.setVisible(true);
    }

    protected static void setPanel(){
        panel = new JPanel();
        panel.setBounds(140, 60, 1035, 640);
        panel.setLayout(null);
        panel.setBackground(Color.MAGENTA);
    }

    protected static JPanel getPanel(){
        return panel;
    }
}