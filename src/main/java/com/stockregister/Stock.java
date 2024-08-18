package com.stockregister;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;


public class Stock extends JFrame implements ActionListener {

    public static void main(String[] args) {

        new Stock();
    }

    // Swing components
    // ------------------------------------------------------------------------------------------
    protected JPanel stockPanel, buttonsPanel, addItemPanel, panelStack;
    protected JButton addBtn, stockInBtn, stockOutBtn, removeBtn, updateBtn, prevBtn, doneBtn;
    protected JLabel itemLabel, categoryLabel, purchaseLabel, sellingLabel,
                      priceUnitLabel, openingStockLabel, partyLabel;
    protected JTextField itemTF, categoryTF, purchaseTF, sellingTF, partyTF, openingStockTF;
    private JComboBox<String> priceUnit;
    // ------------------------------------------------------------------------------------------

    // Variables
    // ------------------------------------------------------------------------------------------
    protected String item_name, category, price_unit;
    protected double purchase_price, selling_price, MRP;
    // ------------------------------------------------------------------------------------------

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
        addBtn = initButton(addBtn, "Add new Item");
        addBtn.setBounds(5, 50, 250, 40);
        addBtn.setForeground(Color.BLACK);
        prevBtn = addBtn;

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
            showPanelStack("check");
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

    protected void setPanelStack(){

        int x = buttonsPanel.getWidth() + 10;
        int width = stockPanel.getWidth() - buttonsPanel.getWidth() - 10;

        panelStack = new JPanel(new CardLayout());
        panelStack.setBounds(x, 0, width, stockPanel.getHeight());

        panelStack.add(addItemPanel(), "Add Item Panel");
        stockPanel.add(panelStack);
    }

    // Displays the panel stored in workPanel's CardLayout wrt panel's name
    private void showPanelStack(String panelName){
        CardLayout cl = (CardLayout) (panelStack.getLayout());
        cl.show(panelStack, panelName);
    }

    protected JPanel addItemPanel(){

        addItemPanel = new JPanel();
        // setBounds not needed as the panelStack is working in CardLayout
        addItemPanel.setLayout(null);
        addItemPanel.setBackground(Color.WHITE);

        setAddItemLabels();
        setAddItemTextField();

        return addItemPanel;
    }

    private void setAddItemLabels() {

        itemLabel = new JLabel("Item name");
        itemLabel.setBounds(50, 55, 170, 30);
        initLabel(itemLabel);

        categoryLabel = new JLabel("Category");
        categoryLabel.setBounds(itemLabel.getWidth() + 250, itemLabel.getY(), 150, 30);
        initLabel(categoryLabel);

        purchaseLabel = new JLabel("Purchase Price");
        purchaseLabel.setBounds(itemLabel.getX(), itemLabel.getY() + 100, 200, 30);
        initLabel(purchaseLabel);

        sellingLabel = new JLabel("Selling Price");
        sellingLabel.setBounds(itemLabel.getWidth() + 250, purchaseLabel.getY(), 200, 30);
        initLabel(sellingLabel);

        partyLabel = new JLabel("Party");
        partyLabel.setBounds(itemLabel.getX(), purchaseLabel.getY() + 100, 80, 30);
        initLabel(partyLabel);

        priceUnitLabel = new JLabel("Price Unit");
        priceUnitLabel.setBounds(itemLabel.getWidth() + 250, partyLabel.getY(), 200, 30);
        initLabel(priceUnitLabel);

        openingStockLabel = new JLabel("Opening Stock");
        openingStockLabel.setBounds(itemLabel.getX(), partyLabel.getY() + 100, 200, 30);
        initLabel(openingStockLabel);
    }

    private void initLabel(JLabel label){
        label.setBackground(Color.BLACK);
        label.setFont(new Font("Rockwell", Font.BOLD, 25));
        addItemPanel.add(label);
    }

    private void setAddItemTextField() {

        itemTF = new JTextField();
        itemTF.setBounds(itemLabel.getX(), itemLabel.getY() + itemLabel.getHeight() + 7, 200, 30);
        initTextField(itemTF);

        categoryTF = new JTextField();
        categoryTF.setBounds(categoryLabel.getX(), categoryLabel.getY() + categoryLabel.getHeight() + 7, 140, 30);
        initTextField(categoryTF);

        purchaseTF = new JTextField();
        purchaseTF.setBounds(purchaseLabel.getX(), purchaseLabel.getY() + purchaseLabel.getHeight() + 7, 120, 30);
        initTextField(purchaseTF);

        sellingTF = new JTextField();
        sellingTF.setBounds(sellingLabel.getX(), sellingLabel.getY() + sellingLabel.getHeight() + 7, 120, 30);
        initTextField(sellingTF);

        partyTF = new JTextField();
        partyTF.setBounds(partyLabel.getX(), partyLabel.getY() + partyLabel.getHeight() + 7, 150, 30);
        initTextField(partyTF);

        // for price unit (combo box)
        String[] elements = {"Hello", "Hi"};
        priceUnit = new JComboBox<>(elements);
        priceUnit.setBounds(priceUnitLabel.getX(), priceUnitLabel.getY() + priceUnitLabel.getHeight() + 7, 120, 30);
        addItemPanel.add(priceUnit);

        openingStockTF = new JTextField();
        openingStockTF.setBounds(openingStockLabel.getX(), openingStockLabel.getY() + openingStockLabel.getHeight() + 7, 120, 30);
        initTextField(openingStockTF);

        doneBtn = initButton(doneBtn, "Done");
        doneBtn.setBounds(categoryLabel.getX() - 150,openingStockTF.getY() + openingStockTF.getHeight() + 100, 150, 50);
        doneBtn.setForeground(Color.WHITE);
        doneBtn.setBackground(Color.BLACK);
        addItemPanel.add(doneBtn);
    }

    private void initTextField(JTextField TxtF){
        TxtF.setMargin(new Insets(0, 4, 0, 4));
        TxtF.setFont(new Font("Rockwell", Font.PLAIN, 17));
        addItemPanel.add(TxtF);
    }

    private void highlightBtn(JButton currentBtn){

        prevBtn.setForeground(new Color(206, 206, 206));
        currentBtn.setForeground(Color.BLACK);

        prevBtn = currentBtn;
    }
}