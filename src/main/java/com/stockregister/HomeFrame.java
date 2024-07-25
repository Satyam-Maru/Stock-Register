package com.stockregister;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeFrame extends JFrame implements ActionListener {

    JButton backBtn, dbBtn;
    JFrame frame;

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
        this.setSize(LoginFrame.frameWidth, LoginFrame.frameHeight); // 1020, 650
        this.setResizable(false);
        this.setLayout(new FlowLayout()); // managing the layout self
        this.setLocationRelativeTo(null); // sets the frame in center of the window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(Color.WHITE);

        JLabel label = new JLabel("Welcome :)");
        this.add(label);

        setButtons();

        this.add(backBtn);
        this.add(dbBtn);
        this.setVisible(true);
    }

    protected void setButtons() {

        backBtn = new JButton();
        backBtn.setText("Go Back");
        backBtn.setFont(new Font("Consolas", Font.BOLD, 16));
        backBtn.setForeground(Color.WHITE);
        backBtn.setBackground(new Color(50, 205, 50));
        backBtn.addActionListener(this);

        dbBtn = new JButton();
        dbBtn.setText("DB");
        dbBtn.setFont(new Font("Consolas", Font.BOLD, 16));
        dbBtn.setForeground(Color.WHITE);
        dbBtn.setBackground(new Color(50, 205, 50));
        dbBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == backBtn) {
            this.setVisible(false);
            frame.setVisible(true);
        } else if (e.getSource() == dbBtn) {

            System.out.println("HELLO: " + LoginFrame.getEmail());
            System.out.println("Home Frame User Id: " + User.getUser_id());
            System.out.println("------------------After Exception----------------");
            System.out.println("Home Frame store_name: " + User.getStore_name());
        }

    }
}