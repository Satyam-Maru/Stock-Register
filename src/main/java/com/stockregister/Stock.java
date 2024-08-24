package com.stockregister;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Stock extends JFrame implements ActionListener {

    // Swing components
    // ------------------------------------------------------------------------------------------
    protected JPanel stockPanel, buttonsPanel, panelStack;
    protected JButton addBtn, stockInOutBtn, removeBtn, updateBtn, prevBtn;
    // ------------------------------------------------------------------------------------------

    // getters
    protected JPanel getPanel(){
        return stockPanel;
    }

    // setters
    // ------------------------------------------------------------------------------
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
        setPanelStack();
        stockPanel.add(panelStack);
    }

    protected void setButtons(){

        // 50-50 gap between y
        addBtn = initButton("Add new Item");
        addBtn.setBounds(5, 50, 250, 40);
        addBtn.setForeground(Color.BLACK);
        prevBtn = addBtn;

        stockInOutBtn = initButton("Stock In/Out");
        stockInOutBtn.setBounds(5, 140, 250, 40);

        updateBtn = initButton("Update an Item");
        updateBtn.setBounds(5, 230, 250, 40); // y occupied = 300

        removeBtn = initButton("Remove an Item");
        removeBtn.setBounds(5, 320, 250, 40);

        buttonsPanel.add(addBtn);
        buttonsPanel.add(stockInOutBtn);
        buttonsPanel.add(removeBtn);
        buttonsPanel.add(updateBtn);
    }
    //  ------------------------------------------------------------------------------

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == addBtn){
            System.out.println("add btn");
            highlightBtn(addBtn);
            showPanelStack("Add Item Panel");
        }
        else if (e.getSource() == stockInOutBtn){
            highlightBtn(stockInOutBtn);
            System.out.println("stockIn btn");
            showPanelStack("Stock In Out Panel");
        }
        else if (e.getSource() == updateBtn){
            highlightBtn(updateBtn);
            System.out.println("Update btn");
            showPanelStack("Update Panel");
        }
        else if (e.getSource() == removeBtn){
            highlightBtn(removeBtn);
            System.out.println("remove btn");
            showPanelStack("Remove Panel");
        }
    }

    // initializers
    // ------------------------------------------------------------------------------
    protected static JLabel initLabel(String name){
        JLabel label = new JLabel(name);
        label.setBackground(Color.BLACK);
        label.setFont(new Font("Rockwell", Font.BOLD, 25));
        return label;
    }

    protected JButton initButton(String buttonName){

        JButton button = new JButton(buttonName);
        button.setBackground(new Color(139, 139, 129));
        button.setFont(new Font("Rockwell", Font.BOLD, 25));
        button.addActionListener(this);
        button.setForeground(new Color(206, 206, 206));
        button.setFocusable(false);

        return button;
    }

    protected static JTextField initTextField(){
        JTextField TxtF = new JTextField();
        TxtF.setMargin(new Insets(0, 4, 0, 4));
        TxtF.setFont(new Font("Rockwell", Font.PLAIN, 17));
        return TxtF;
    }
    // ------------------------------------------------------------------------------

    private void highlightBtn(JButton currentBtn){

        prevBtn.setForeground(new Color(206, 206, 206));
        currentBtn.setForeground(Color.BLACK);

        prevBtn = currentBtn;
    }

    protected void setPanelStack(){
        int x = buttonsPanel.getWidth() + 10;
        int width = stockPanel.getWidth() - buttonsPanel.getWidth() - 10;

        panelStack = new JPanel(new CardLayout());
        panelStack.setBounds(x, 0, width, stockPanel.getHeight());

        panelStack.add(AddNewItem.set_getAddItemPanel(), "Add Item Panel");
        panelStack.add(StockInOut.set_getStockInOutPanel(), "Stock In Out Panel");
        panelStack.add(UpdateItem.set_getUpdatePanel(), "Update Panel");
        panelStack.add(RemoveItem.set_getRemovePanel(), "Remove Panel");
        stockPanel.add(panelStack);
    }

    // Displays the panel stored in workPanel's CardLayout wrt panel's name
    private void showPanelStack(String panelName){
        CardLayout cl = (CardLayout) (panelStack.getLayout());
        cl.show(panelStack, panelName);
    }
}
