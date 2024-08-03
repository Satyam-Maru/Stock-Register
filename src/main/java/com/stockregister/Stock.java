package com.stockregister;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Stock extends JFrame implements ActionListener {

    public static void main(String[] args) {

        new Stock();
    }

    static JPanel stockPanel, buttonsPanel;
    static JButton addBtn, stockInBtn, stockOutBtn, removeBtn;

    // make a method which returns a panel containing all the stuff to display

    Stock(){
//        this.setSize(HomeFrame.HomeFrameWidth, HomeFrame.HomeFrameHeight);
//        this.setLayout(null);
//        this.setLocationRelativeTo(null);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        // Panel work
//        setPanel();
//        setButtons();
//        this.add(getPanel());
//        //
//        this.setVisible(true);
        System.out.println("Opening Stock Class");
    }

    protected void setPanel(){
        stockPanel = new JPanel();
        stockPanel.setBounds(140, 60, 1035, 640);
        stockPanel.setLayout(null);

        buttonsPanel = new JPanel();
        buttonsPanel.setBounds(340, 220, 390, 220);
        buttonsPanel.setLayout(null);

        setButtons();
        stockPanel.add(buttonsPanel);
    }

    protected void setButtons(){

        // 30-30 gap between y
        addBtn = initButton(addBtn, "Add new Item");
        addBtn.setBounds(70, 0, 250, 40);

        stockInBtn = initButton(stockInBtn, "Stock In");
        stockInBtn.setBounds(0, 90, 135, 40);

        stockOutBtn = initButton(stockOutBtn, "Stock Out");
        stockOutBtn.setBounds(230, 90, 160, 40); // x occupied = 390

        removeBtn = initButton(removeBtn, "Remove an Item");
        removeBtn.setBounds(70, 180, 250, 40); // y occupied = 220

        buttonsPanel.add(addBtn);
        buttonsPanel.add(stockInBtn);
        buttonsPanel.add(stockOutBtn);
        buttonsPanel.add(removeBtn);
    }

    protected JPanel getPanel(){
        return stockPanel;
    }

    private JButton initButton(JButton button, String buttonName){

        button = new JButton(buttonName);
        button.setBackground(Color.WHITE);
        button.setFont(new Font("Rockwell", Font.BOLD, 25));
        button.setForeground(Color.BLACK);
        button.addActionListener(this);
        button.setFocusable(false);

        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == addBtn){
            System.out.println("add btn");
        }
        else if(e.getSource() == stockInBtn){
            System.out.println("stockIn btn");
        }
        else if(e.getSource() == stockOutBtn){
            System.out.println("stockOut btn");
        }
        else if (e.getSource() == removeBtn) {
            System.out.println("remove btn");
        }
    }
}