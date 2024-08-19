package com.stockregister;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.stockregister.Stock.initLabel;
import static com.stockregister.Stock.initTextField;

public class UpdateItem {

    protected static JPanel updatePanel;
    protected static JButton updateDoneBtn, updateCategoryOkBtn;
    protected static JLabel updateCategoryLabel, updateItemLabel, updatePriceLabel, updateQuantityLabel;
    protected static JTextField updateCategoryTF, updateQuantityTF, updatePriceTF;
    protected static JComboBox<String> updateItemComboBox;

    protected static JPanel set_getUpdatePanel(){
        updatePanel = new JPanel();
        updatePanel.setLayout(null);
        setUpdateLabels();
        setUpdateTextFields_ComboBox();
        return updatePanel;
    }

    private static void setUpdateLabels() {
        updateCategoryLabel = initLabel("Category");
        updateCategoryLabel.setBounds(80, 80, 150, 30);
        updatePanel.add(updateCategoryLabel);

        updateItemLabel = initLabel("Item");
        updateItemLabel.setBounds(updateCategoryLabel.getX() + updateCategoryLabel.getWidth() + 150,
                updateCategoryLabel.getY(), 150, 30);
        updatePanel.add(updateItemLabel);

        updatePriceLabel = initLabel("Price");
        updatePriceLabel.setBounds(updateCategoryLabel.getX(),updateCategoryLabel.getY() + updateCategoryLabel.getHeight() + 100, 100, 30);
        updatePanel.add(updatePriceLabel);

        updateQuantityLabel = initLabel("Quantity");
        updateQuantityLabel.setBounds(updateItemLabel.getX(), updateItemLabel.getY() + updateItemLabel.getHeight() + 100, 150, 30);
        updatePanel.add(updateQuantityLabel);
    }

    private static void setUpdateTextFields_ComboBox(){
        updateCategoryTF = initTextField();
        updateCategoryTF.setBounds(updateCategoryLabel.getX(), updateCategoryLabel.getY() + updateCategoryLabel.getHeight() + 7, 150, 30);
        updatePanel.add(updateCategoryTF);

        updateItemComboBox = new JComboBox<>(new String[]{"check", "done"});
        updateItemComboBox.setBounds(updateItemLabel.getX(), updateItemLabel.getY() + updateItemLabel.getHeight() + 7, 150, 30);
        updatePanel.add(updateItemComboBox);

        updateQuantityTF = initTextField();
        updateQuantityTF.setBounds(updateQuantityLabel.getX(), updateQuantityLabel.getY() + updateQuantityLabel.getHeight() + 7, 80, 30);
        updatePanel.add(updateQuantityTF);

        updatePriceTF = initTextField();
        updatePriceTF.setBounds(updatePriceLabel.getX(), updatePriceLabel.getY() + updatePriceLabel.getHeight() + 7, 80, 30);
        updatePanel.add(updatePriceTF);

        updateCategoryOkBtn = initButton("OK");
        updateCategoryOkBtn.setBounds(updateCategoryTF.getX() + updateCategoryTF.getWidth() + 10, updateCategoryTF.getY(), 60, 30);
        updateCategoryOkBtn.setContentAreaFilled(false);
        updateCategoryOkBtn.setFont(new Font("Rockwell", Font.PLAIN, 15));
        updateCategoryOkBtn.setForeground(Color.BLACK);
        updateCategoryOkBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("update category ok btn");
            }
        });
        updatePanel.add(updateCategoryOkBtn);

        updateDoneBtn = initButton("Done");
        updateDoneBtn.setBounds(updatePriceTF.getX() + updatePriceTF.getWidth() + 70,
                updatePriceTF.getY() + updatePriceTF.getHeight() + 100, 120, 50);
        updateDoneBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("update done btn");
            }
        });
        updatePanel.add(updateDoneBtn);
    }

    private static JButton initButton(String buttonName){

        JButton button = new JButton(buttonName);
        button.setFont(new Font("Rockwell", Font.BOLD, 25));
        button.setFocusable(false);
        button.setForeground(Color.WHITE);
        button.setBackground(Color.BLACK);

        return button;
    }
}
