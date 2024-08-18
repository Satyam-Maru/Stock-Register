package com.stockregister;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Stock extends JFrame implements ActionListener {

    public static void main(String[] args) {

        new Stock();
    }

    // Swing components
    // ------------------------------------------------------------------------------------------
    protected JPanel stockPanel, buttonsPanel, addItemPanel, panelStack, stockInOutPanel;
    protected JButton addBtn, stockInOutBtn, removeBtn, updateBtn, prevBtn, addItemDoneBtn,
                        stockInBtn, stockOutBtn;
    protected JLabel itemLabel, categoryLabel, purchaseLabel, sellingLabel, // addItemPanel
                      priceUnitLabel, openingStockLabel, partyLabel;        // addItemPanel
    protected JLabel stPartyLabel, stCategoryLabel, stItemLabel, stQuantityLabel, stPriceLabel;
    protected JTextField itemTF, categoryTF, purchaseTF, sellingTF, partyTF, openingStockTF; // addItemPanel
    protected JTextField stPartyTF, stQuantityTF, stPriceTF;
    private JComboBox<String> priceUnit, stCategoryComboBox, stItemComboBox;
    // ------------------------------------------------------------------------------------------

    // Variables
    // ------------------------------------------------------------------------------------------
    protected String item_name, category, price_unit;
    protected double purchase_price, selling_price, MRP;
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
        addBtn = initButton(addBtn, "Add new Item");
        addBtn.setBounds(5, 50, 250, 40);
        addBtn.setForeground(Color.BLACK);
        prevBtn = addBtn;

        stockInOutBtn = initButton(stockInOutBtn, "Stock In/Out");
        stockInOutBtn.setBounds(5, 140, 250, 40);

        removeBtn = initButton(removeBtn, "Remove an Item");
        removeBtn.setBounds(5, 230, 250, 40);

        updateBtn = initButton(updateBtn, "Update an Item");
        updateBtn.setBounds(5, 320, 250, 40); // y occupied = 300

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
        else if (e.getSource() == removeBtn){
            highlightBtn(removeBtn);
            System.out.println("remove btn");
        }
        else if (e.getSource() == updateBtn){
            highlightBtn(updateBtn);
            System.out.println("Update btn");
        }
    }

    // adders / setters
    // ------------------------------------------------------------------------------
    private void setAddItemLabels() {

        itemLabel = initLabel(itemLabel, "Item Name");
        itemLabel.setBounds(50, 55, 170, 30);
        addItemPanel.add(itemLabel);

        categoryLabel = initLabel(categoryLabel, "Category");
        categoryLabel.setBounds(itemLabel.getWidth() + 250, itemLabel.getY(), 150, 30);
        addItemPanel.add(categoryLabel);

        purchaseLabel = initLabel(purchaseLabel, "Purchase Price");
        purchaseLabel.setBounds(itemLabel.getX(), itemLabel.getY() + 100, 200, 30);
        addItemPanel.add(purchaseLabel);

        sellingLabel = initLabel(sellingLabel, "Selling Price");
        sellingLabel.setBounds(itemLabel.getWidth() + 250, purchaseLabel.getY(), 200, 30);
        addItemPanel.add(sellingLabel);

        partyLabel = initLabel(partyLabel, "Party");
        partyLabel.setBounds(itemLabel.getX(), purchaseLabel.getY() + 100, 80, 30);
        addItemPanel.add(partyLabel);

        priceUnitLabel = initLabel(priceUnitLabel, "Price Unit");
        priceUnitLabel.setBounds(itemLabel.getWidth() + 250, partyLabel.getY(), 200, 30);
        addItemPanel.add(priceUnitLabel);

        openingStockLabel = initLabel(openingStockLabel, "Opening Stock");
        openingStockLabel.setBounds(itemLabel.getX(), partyLabel.getY() + 100, 200, 30);
        addItemPanel.add(openingStockLabel);
    }

    private void setAddItemTextField() {

        itemTF = initTextField(itemTF);
        itemTF.setBounds(itemLabel.getX(), itemLabel.getY() + itemLabel.getHeight() + 7, 200, 30);
        addItemPanel.add(itemTF);

        categoryTF = initTextField(categoryTF);
        categoryTF.setBounds(categoryLabel.getX(), categoryLabel.getY() + categoryLabel.getHeight() + 7, 140, 30);
        addItemPanel.add(categoryTF);

        purchaseTF = initTextField(purchaseTF);
        purchaseTF.setBounds(purchaseLabel.getX(), purchaseLabel.getY() + purchaseLabel.getHeight() + 7, 120, 30);
        addItemPanel.add(purchaseTF);

        sellingTF = initTextField(sellingTF);
        sellingTF.setBounds(sellingLabel.getX(), sellingLabel.getY() + sellingLabel.getHeight() + 7, 120, 30);
        addItemPanel.add(sellingTF);

        partyTF = initTextField(partyTF);
        partyTF.setBounds(partyLabel.getX(), partyLabel.getY() + partyLabel.getHeight() + 7, 150, 30);
        addItemPanel.add(partyTF);;

        // for price unit (combo box)
        String[] elements = {"Hello", "Hi"};
        priceUnit = new JComboBox<>(elements);
        priceUnit.setBounds(priceUnitLabel.getX(), priceUnitLabel.getY() + priceUnitLabel.getHeight() + 7, 120, 30);
        addItemPanel.add(priceUnit);

        openingStockTF = initTextField(openingStockTF);
        openingStockTF.setBounds(openingStockLabel.getX(), openingStockLabel.getY() + openingStockLabel.getHeight() + 7, 120, 30);
        addItemPanel.add(openingStockTF);

        addItemDoneBtn = initButton(addItemDoneBtn, "Done");
        addItemDoneBtn.setBounds(categoryLabel.getX() - 150,openingStockTF.getY() + openingStockTF.getHeight() + 100, 150, 50);
        addItemDoneBtn.setForeground(Color.WHITE);
        addItemDoneBtn.setBackground(Color.BLACK);
        addItemPanel.add(addItemDoneBtn);
    }

    private void setStockInOutLabels(){
        stPartyLabel = initLabel(stPartyLabel, "Party");
        stPartyLabel.setBounds(80, 80, 170, 30);
        stockInOutPanel.add(stPartyLabel);

        stCategoryLabel = initLabel(stCategoryLabel, "Category");
        stCategoryLabel.setBounds(stPartyLabel.getWidth() + 250, stPartyLabel.getY(), 150, 30);
        stockInOutPanel.add(stCategoryLabel);

        stItemLabel = initLabel(stItemLabel, "Item");
        stItemLabel.setBounds(stPartyLabel.getX(), stPartyLabel.getY() + 100, 200, 30);
        stockInOutPanel.add(stItemLabel);

        stQuantityLabel = initLabel(stQuantityLabel, "Quantity");
        stQuantityLabel.setBounds(stPartyLabel.getX(), stItemLabel.getY() + 100, 200, 30);
        stockInOutPanel.add(stQuantityLabel);

        stPriceLabel = initLabel(stPriceLabel, "Price");
        stPriceLabel.setBounds(stCategoryLabel.getX(), stQuantityLabel.getY(), 80 , 30);
        stockInOutPanel.add(stPriceLabel);
    }

    private void setStockInOutTextF_ComboBox(){
        stPartyTF = initTextField(stPartyTF);
        stPartyTF.setBounds(stPartyLabel.getX(), stPartyLabel.getY() + stPartyLabel.getHeight() + 7, 80, 30);
        stockInOutPanel.add(stPartyTF);

        String elements[] = {"nice", "ohk"};
        stCategoryComboBox = new JComboBox<>(elements);
        stCategoryComboBox.setBounds(stCategoryLabel.getX(), stCategoryLabel.getY() + stCategoryLabel.getHeight() + 7, 150, 30);
        stockInOutPanel.add(stCategoryComboBox);

        stItemComboBox = new JComboBox<>(elements);
        stItemComboBox.setBounds(stItemLabel.getX(), stItemLabel.getY() + stItemLabel.getHeight() + 7, 150, 30);
        stockInOutPanel.add(stItemComboBox);

        stQuantityTF = initTextField(stQuantityTF);
        stQuantityTF.setBounds(stQuantityLabel.getX(), stQuantityLabel.getY() + stQuantityLabel.getHeight() + 7, 80, 30);
        stockInOutPanel.add(stQuantityTF);

        stPriceTF = initTextField(stPriceTF);
        stPriceTF.setBounds(stPriceLabel.getX(), stPriceLabel.getY() + stPriceLabel.getHeight() + 7, 80, 30);
        stockInOutPanel.add(stPriceTF);

        stockInBtn = initButton(stockInBtn, "Stock In");
        stockInBtn.setBounds(stQuantityLabel.getX() + stQuantityLabel.getWidth() - 150,
                             stQuantityTF.getY() + stQuantityTF.getHeight() + 200 ,150, 50);
        stockInBtn.setForeground(Color.WHITE);
        stockInBtn.setBackground(Color.BLACK);
        stockInOutPanel.add(stockInBtn);

        stockOutBtn = initButton(stockOutBtn, "Stock Out");
        stockOutBtn.setBounds(stockInBtn.getX() + stockInBtn.getWidth() + 150,
                                stockInBtn.getY() ,180, 50);
        stockOutBtn.setForeground(Color.WHITE);
        stockOutBtn.setBackground(Color.BLACK);
        stockInOutPanel.add(stockOutBtn);
    }
    // ------------------------------------------------------------------------------

    // initializers
    // ------------------------------------------------------------------------------
    private JLabel initLabel(JLabel label, String name){
        label = new JLabel(name);
        label.setBackground(Color.BLACK);
        label.setFont(new Font("Rockwell", Font.BOLD, 25));
        return label;
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

    private JTextField initTextField(JTextField TxtF){
        TxtF = new JTextField();
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

    protected JPanel set_getAddItemPanel(){
        addItemPanel = new JPanel();
        // setBounds not needed as the panelStack is working in CardLayout
        addItemPanel.setLayout(null);
        addItemPanel.setBackground(Color.WHITE);
        setAddItemLabels();
        setAddItemTextField();
        return addItemPanel;
    }

    private JPanel set_getStockInOutPanel(){
        stockInOutPanel = new JPanel();
        stockInOutPanel.setLayout(null);
        stockInOutPanel.setBackground(Color.WHITE);
        setStockInOutLabels();
        setStockInOutTextF_ComboBox();
        return stockInOutPanel;
    }

    protected void setPanelStack(){
        int x = buttonsPanel.getWidth() + 10;
        int width = stockPanel.getWidth() - buttonsPanel.getWidth() - 10;

        panelStack = new JPanel(new CardLayout());
        panelStack.setBounds(x, 0, width, stockPanel.getHeight());

        panelStack.add(set_getAddItemPanel(), "Add Item Panel");
        panelStack.add(set_getStockInOutPanel(), "Stock In Out Panel");
        stockPanel.add(panelStack);
    }

    // Displays the panel stored in workPanel's CardLayout wrt panel's name
    private void showPanelStack(String panelName){
        CardLayout cl = (CardLayout) (panelStack.getLayout());
        cl.show(panelStack, panelName);
    }
}
