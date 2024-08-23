package com.stockregister;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.stockregister.Stock.initLabel;
import static com.stockregister.Stock.initTextField;

public class AddNewItem {

    static JPanel addItemPanel;
    static JLabel itemLabel, categoryLabel, purchaseLabel, sellingLabel,
                    priceUnitLabel, openingStockLabel, partyLabel;
    static JTextField itemTF, categoryTF, purchaseTF, sellingTF, partyTF, openingStockTF;
    static JButton addItemDoneBtn;
    static JComboBox<String> priceUnit;
    static Queue queue;

    static JPanel set_getAddItemPanel(){
        addItemPanel = new JPanel();
        // setBounds not needed as the panelStack is working in CardLayout
        addItemPanel.setLayout(null);
        queue = new Queue();
        setAddItemLabels();
        setAddItemTextField();
        return addItemPanel;
    }

    static void setAddItemLabels() {

        itemLabel = initLabel("Item Name");
        itemLabel.setBounds(50, 55, 170, 30);
        addItemPanel.add(itemLabel);

        categoryLabel = initLabel("Category");
        categoryLabel.setBounds(itemLabel.getWidth() + 250, itemLabel.getY(), 150, 30);
        addItemPanel.add(categoryLabel);

        purchaseLabel = initLabel("Purchase Price");
        purchaseLabel.setBounds(itemLabel.getX(), itemLabel.getY() + 100, 200, 30);
        addItemPanel.add(purchaseLabel);

        sellingLabel = initLabel("Selling Price");
        sellingLabel.setBounds(itemLabel.getWidth() + 250, purchaseLabel.getY(), 200, 30);
        addItemPanel.add(sellingLabel);

        partyLabel = initLabel("Party");
        partyLabel.setBounds(itemLabel.getX(), purchaseLabel.getY() + 100, 80, 30);
        addItemPanel.add(partyLabel);

        priceUnitLabel = initLabel("Price Unit");
        priceUnitLabel.setBounds(itemLabel.getWidth() + 250, partyLabel.getY(), 200, 30);
        addItemPanel.add(priceUnitLabel);

        openingStockLabel = initLabel("Opening Stock");
        openingStockLabel.setBounds(itemLabel.getX(), partyLabel.getY() + 100, 200, 30);
        addItemPanel.add(openingStockLabel);
    }

    static void setAddItemTextField() {

        itemTF = initTextField();
        itemTF.setBounds(itemLabel.getX(), itemLabel.getY() + itemLabel.getHeight() + 7, 200, 30);
        addItemPanel.add(itemTF);

        categoryTF = initTextField();
        categoryTF.setBounds(categoryLabel.getX(), categoryLabel.getY() + categoryLabel.getHeight() + 7, 140, 30);
        addItemPanel.add(categoryTF);

        purchaseTF = initTextField();
        purchaseTF.setBounds(purchaseLabel.getX(), purchaseLabel.getY() + purchaseLabel.getHeight() + 7, 120, 30);
        addItemPanel.add(purchaseTF);

        sellingTF = initTextField();
        sellingTF.setBounds(sellingLabel.getX(), sellingLabel.getY() + sellingLabel.getHeight() + 7, 120, 30);
        addItemPanel.add(sellingTF);

        partyTF = initTextField();
        partyTF.setBounds(partyLabel.getX(), partyLabel.getY() + partyLabel.getHeight() + 7, 150, 30);
        addItemPanel.add(partyTF);

        // for price unit (combo box)
        String[] elements = {"Packet", "Piece", "Litre"};
        priceUnit = new JComboBox<>(elements);
        priceUnit.setBounds(priceUnitLabel.getX(), priceUnitLabel.getY() + priceUnitLabel.getHeight() + 7, 120, 30);
        addItemPanel.add(priceUnit);

        openingStockTF = initTextField();
        openingStockTF.setBounds(openingStockLabel.getX(), openingStockLabel.getY() + openingStockLabel.getHeight() + 7, 120, 30);
        addItemPanel.add(openingStockTF);

        addItemDoneBtn = initButton();
        addItemDoneBtn.setBounds(categoryLabel.getX() - 150,openingStockTF.getY() + openingStockTF.getHeight() + 100, 150, 50);
        addItemPanel.add(addItemDoneBtn);
    }

    private static JButton initButton(){

        JButton button = new JButton("Done");
        button.setForeground(Color.WHITE);
        button.setBackground(Color.BLACK);
        button.setFont(new Font("Rockwell", Font.BOLD, 25));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                queue.enqueue(new Items(
                        itemTF.getText(), categoryTF.getText(),
                        partyTF.getText(), (String) priceUnit.getSelectedItem(),
                        Double.parseDouble(purchaseTF.getText()),
                        Double.parseDouble(sellingTF.getText()),
                        Double.parseDouble(openingStockTF.getText())));
            }
        });
        button.setFocusable(false);

        return button;
    }
}