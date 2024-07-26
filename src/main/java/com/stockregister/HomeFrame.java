package com.stockregister;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeFrame extends JFrame implements ActionListener {

    public static void main(String[] args) {
        new HomeFrame();
    }

    protected static final int HomeFrameWidth = 1200, HomeFrameHeight = 750;

    JButton stockBtn, partyBtn, itemsBtn, backBtn, refreshBtn;
    JFrame frame;
    JPanel headPanel, leftPanel;
    JLabel store_name_label;

    HomeFrame() {
        initFrame();
    }

    HomeFrame(JFrame frame) {
        this();
        this.frame = frame;
    }

    protected void initFrame() {

        this.setTitle("Home Frame");
        this.setIconImage(LoginFrame.loginFrameLogo.getImage());
        this.setSize(HomeFrameWidth, HomeFrameHeight);
        this.setResizable(false);
        this.setLayout(null); // managing the layout self
        this.setLocationRelativeTo(null); // sets the frame in center of the window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(Color.WHITE);

        setPanels();
        setButtons();

        this.setVisible(true);
    }

    protected void setPanels(){

        int x = (HomeFrameWidth / 2) - 100;
        store_name_label = new JLabel();
        store_name_label.setBounds(x, 0, 250, 50);
        store_name_label.setText("Interior Gallery");
        store_name_label.setFont(new Font("Rockwell", Font.BOLD, 25));
        store_name_label.setForeground(Color.GREEN);

        // headPanel contains backBtn, store_name_label, refreshBtn
        headPanel = new JPanel();
        headPanel.setLayout(null);
        headPanel.setBounds(0, 0, HomeFrameWidth, 50);
        headPanel.setBackground(Color.BLACK);
        headPanel.add(store_name_label);

        // leftPanel contains stockBtn, partyBtn, itemsBtn
        leftPanel = new JPanel();
        leftPanel.setLayout(null);
        leftPanel.setBounds(0, 50, 150, HomeFrameHeight - headPanel.getBounds().height);
        leftPanel.setBackground(Color.MAGENTA);

        this.add(headPanel);
        this.add(leftPanel);
    }

    protected void setButtons() {

        backBtn = new JButton();
        backBtn.setBounds(0, 0, headPanel.getBounds().height, headPanel.getBounds().height);
        backBtn.setBackground(Color.cyan);
        backBtn.addActionListener(this);
        backBtn.setFocusable(false);

        refreshBtn = new JButton();
        refreshBtn.setBounds(1140, 0, headPanel.getBounds().height, headPanel.getBounds().height);
        refreshBtn.setBackground(Color.RED);
        refreshBtn.addActionListener(this);
        refreshBtn.setFocusable(false);

        // leftPanel contains stockBtn, partyBtn, itemsBtn
        stockBtn =  initButton(stockBtn, "Stock");
        stockBtn.setBounds(0, 50, leftPanel.getBounds().width, 40);

        partyBtn =  initButton(partyBtn, "Party");
        partyBtn.setBounds(0, 120, leftPanel.getBounds().width, 40);

        itemsBtn = initButton(itemsBtn, "Items");
        itemsBtn.setBounds(0, 190, leftPanel.getBounds().width, 40);


        headPanel.add(backBtn);
        headPanel.add(refreshBtn);

        leftPanel.add(stockBtn);
        leftPanel.add(partyBtn);
        leftPanel.add(itemsBtn);
    }

    private JButton initButton(JButton button, String buttonName){

        button = new JButton(buttonName);
        button.setBackground(Color.BLACK);
        button.setFont(new Font("Rockwell", Font.BOLD, 25));
        button.setForeground(Color.WHITE);
        button.addActionListener(this);
        button.setFocusable(false);

        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == backBtn) {
            this.setVisible(false);
            frame.setVisible(true);
        }
        else if (e.getSource() == refreshBtn) {
            System.out.println("Refresh btn");
        }
        else if (e.getSource() == stockBtn) {
            System.out.println("Stock btn");
        }
        else if (e.getSource() == partyBtn) {
            System.out.println("Party Btn");
        }
        else if (e.getSource() == itemsBtn) {
            System.out.println("Items btn");
        }
    }
}