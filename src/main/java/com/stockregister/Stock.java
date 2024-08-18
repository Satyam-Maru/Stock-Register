package com.stockregister;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Stock extends JFrame implements ActionListener {

    public static void main(String[] args) {

        new Stock();
    }

    // Swing components
    // ------------------------------------------------------------------------------------------
    protected JPanel stockPanel, buttonsPanel, addItemPanel, panelStack;
    protected JButton addBtn, stockInBtn, stockOutBtn, removeBtn, updateBtn, prevBtn;
    protected JLabel itemLabel, categoryLabel, purchaseLabel, sellingLabel,
                     mrpLabel, priceUnitLabel, openingStockLabel, openingStockDateLabel;
    protected JTextField itemTxtF, purchaseTxtF, sellingTxtF, mrpTxtF, openingStockTxtF, openingStockDateTxtF;
    // ------------------------------------------------------------------------------------------

    // Variables
    // ------------------------------------------------------------------------------------------
    protected String item_name, category, price_unit;
    protected double purchase_price, selling_price, MRP;
    // ------------------------------------------------------------------------------------------

    Stock(){
//        this.setSize(HomeFrame.HomeFrameWidth, HomeFrame.HomeFrameHeight);
//        this.setLayout(null);
//        this.setLocationRelativeTo(null);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        // Panel work
//        setPanel();
//        setPanelStack();
////        this.add(getPanel()); // removed for adjusting the stockPanel
//        //
//        this.setVisible(true);
//        System.out.println("Opening Stock Class");
    }

    protected void setPanel(){
        stockPanel = new JPanel();
        stockPanel.setBounds(140, 60, 1035, 640);
        stockPanel.setLayout(null);
        stockPanel.setBackground(Color.WHITE);

        buttonsPanel = new JPanel();
        buttonsPanel.setBounds(0, 0, 260, 640);
        buttonsPanel.setLayout(null);
        buttonsPanel.setBackground(new Color(139, 139, 129));

        setButtons();
        stockPanel.add(buttonsPanel);
    }

    protected void setButtons(){

        // 50-50 gap between y
        addBtn = initButton(addBtn, "Add new Item");
        addBtn.setBounds(5, 50, 250, 40);

        stockInBtn = initButton(stockInBtn, "Stock In");
        stockInBtn.setBounds(55, 140, 135, 40);

        stockOutBtn = initButton(stockOutBtn, "Stock Out");
        stockOutBtn.setBounds(40, 230, 160, 40); // x occupied = 390

        removeBtn = initButton(removeBtn, "Remove an Item");
        removeBtn.setBounds(5, 320, 250, 40);

        updateBtn = initButton(updateBtn, "Update an Item");
        updateBtn.setBounds(5, 410, 250, 40); // y occupied = 300

        buttonsPanel.add(addBtn);
        buttonsPanel.add(stockInBtn);
        buttonsPanel.add(stockOutBtn);
        buttonsPanel.add(removeBtn);
        buttonsPanel.add(updateBtn);
    }

    protected JPanel getPanel(){
        return stockPanel;
    }

    private JButton initButton(JButton button, String buttonName){

        button = new JButton(buttonName);
        button.setBackground(new Color(139, 139, 129));
        button.setFont(new Font("Rockwell", Font.BOLD, 25));
        button.setForeground(new Color(206, 206, 206));
        button.addActionListener(this);
        button.setFocusable(false);

        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == addBtn){
            System.out.println("add btn");
            highlightBtn(addBtn);
            showPanelStack("Add Item Panel");
        }
        else if (e.getSource() == stockInBtn){
            highlightBtn(stockInBtn);
            System.out.println("stockIn btn");
        }
        else if (e.getSource() == stockOutBtn){
            highlightBtn(stockOutBtn);
            System.out.println("stockOut btn");
        }
        else if (e.getSource() == removeBtn){
            highlightBtn(removeBtn);
            System.out.println("remove btn");
        }
        else if (e.getSource() == updateBtn){
            highlightBtn(updateBtn);
            System.out.println("Update btn");
        }
    }

    protected JPanel setPanelStack(){

        panelStack = new JPanel(new CardLayout());
        panelStack.setBounds(140, 60, 1035, 640);

        panelStack.add(getPanel(), "Stock Panel");
        panelStack.add(addItemPanel(), "Add Item Panel");

        this.add(panelStack);

        return panelStack;
    }

    // Displays the panel stored in workPanel's CardLayout wrt panel's name
    private void showPanelStack(String panelName){
        CardLayout cl = (CardLayout) (panelStack.getLayout());
        cl.show(panelStack, panelName);
    }

    protected JPanel addItemPanel(){

        addItemPanel = new JPanel();
        addItemPanel.setSize(400, 600);

        JButton btn = new JButton("Hello");
        addItemPanel.add(btn);

        return addItemPanel;
    }

    private void highlightBtn(JButton currentBtn){

        if(prevBtn == null){
            currentBtn.setForeground(Color.BLACK);
        }else{
            prevBtn.setForeground(new Color(206, 206, 206));
            currentBtn.setForeground(Color.BLACK);
        }

        prevBtn = currentBtn;
    }
}