package com.stockregister;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import static com.stockregister.Stock.initLabel;
import static com.stockregister.Stock.initTextField;

public class  StockInOut {

    protected static JPanel stockInOutPanel;
    protected static JButton stockInBtn, stockOutBtn, stockInOutCategoryOkBtn;
    protected static JLabel stPartyLabel, stCategoryLabel, stItemLabel, stQuantityLabel, stPriceLabel;
    protected static JTextField stPartyTF, stQuantityTF, stPriceTF;
    protected static JComboBox<String>  stCategoryComboBox, stItemComboBox;
    static String[] items = new String[0];

    protected static JPanel set_getStockInOutPanel(){
        stockInOutPanel = new JPanel();
        stockInOutPanel.setLayout(null);
        setStockInOutLabels();
        setStockInOutTextF_ComboBox();
        return stockInOutPanel;
    }

    private static void setStockInOutLabels(){
        stPartyLabel = initLabel("Party");
        stPartyLabel.setBounds(80, 80, 170, 30);
        stockInOutPanel.add(stPartyLabel);

        stCategoryLabel = initLabel("Category");
        stCategoryLabel.setBounds(stPartyLabel.getWidth() + 250, stPartyLabel.getY(), 150, 30);
        stockInOutPanel.add(stCategoryLabel);

        stItemLabel = initLabel("Item");
        stItemLabel.setBounds(stPartyLabel.getX(), stPartyLabel.getY() + 100, 200, 30);
        stockInOutPanel.add(stItemLabel);

        stQuantityLabel = initLabel("Quantity");
        stQuantityLabel.setBounds(stPartyLabel.getX(), stItemLabel.getY() + 100, 200, 30);
        stockInOutPanel.add(stQuantityLabel);

        stPriceLabel = initLabel("Price");
        stPriceLabel.setBounds(stCategoryLabel.getX(), stQuantityLabel.getY(), 80 , 30);
        stockInOutPanel.add(stPriceLabel);
    }

    private static void setStockInOutTextF_ComboBox(){

        stPartyTF = initTextField();
        stPartyTF.setBounds(stPartyLabel.getX(), stPartyLabel.getY() + stPartyLabel.getHeight() + 7, 150, 30);
        stockInOutPanel.add(stPartyTF);

        stCategoryComboBox = new JComboBox<>(Database.getCategories());
        stCategoryComboBox.setBounds(stCategoryLabel.getX(), stCategoryLabel.getY() + stCategoryLabel.getHeight() + 7, 150, 30);
        stCategoryComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String category = String.valueOf(stCategoryComboBox.getSelectedItem());
                String q = "select name from items where cat_id = (?) and user_id = (?);";

                try{
                    String getCategoryId = "select id from category where name = (?)";
                    Database.prepareStatement(getCategoryId);
                    Database.pst.setString(1, category);
                    ResultSet rs = Database.pst.executeQuery();
                    int cat_id = 0;

                    if(rs.next()){
                        cat_id = rs.getInt(1);
                    }

                    String countRows = "select count(id) from items where cat_id = (?) and user_id = (?);";
                    Database.prepareStatement(countRows);
                    Database.pst.setInt(1, cat_id);
                    Database.pst.setInt(2, User.getUserId());
                    rs = Database.pst.executeQuery();

                    if(rs.next()){
                        items = new String[rs.getInt("count")];
                    }

                    Database.prepareStatement(q);
                    Database.pst.setInt(1, cat_id);
                    Database.pst.setInt(2, User.getUserId());
                    rs = Database.pst.executeQuery();
                    int i = 0;
                    while(rs.next()){
                        items[i++] = rs.getString(1);
                    }

                    stItemComboBox.removeAllItems();
                    for(String item: items){
                        stItemComboBox.addItem(item);
                    }

                }catch(SQLException ex){
                    System.out.println(ex.getMessage());
                }
            }
        });
        stockInOutPanel.add(stCategoryComboBox);

        stItemComboBox = new JComboBox<>(items);
        stItemComboBox.setBounds(stItemLabel.getX(), stItemLabel.getY() + stItemLabel.getHeight() + 7, 150, 30);
        stockInOutPanel.add(stItemComboBox);

        stQuantityTF = initTextField();
        stQuantityTF.setBounds(stQuantityLabel.getX(), stQuantityLabel.getY() + stQuantityLabel.getHeight() + 7, 80, 30);
        stockInOutPanel.add(stQuantityTF);

        stPriceTF = initTextField();
        stPriceTF.setBounds(stPriceLabel.getX(), stPriceLabel.getY() + stPriceLabel.getHeight() + 7, 80, 30);
        stockInOutPanel.add(stPriceTF);

        stockInBtn = initButton("Stock In");
        stockInBtn.setBounds(stQuantityLabel.getX() + stQuantityLabel.getWidth() - 150,
                            stQuantityTF.getY() + stQuantityTF.getHeight() + 200 ,150, 50);
        stockInBtn.setForeground(Color.WHITE);
        stockInBtn.setBackground(Color.BLACK);
        stockInBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("stock in btn");
            }
        });
        stockInOutPanel.add(stockInBtn);

        stockOutBtn = initButton("Stock Out");
        stockOutBtn.setBounds(stockInBtn.getX() + stockInBtn.getWidth() + 150,
                                stockInBtn.getY() ,180, 50);
        stockOutBtn.setForeground(Color.WHITE);
        stockOutBtn.setBackground(Color.BLACK);
        stockOutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("stock out btn");
            }
        });
        stockInOutPanel.add(stockOutBtn);
    }

    protected static JButton initButton(String buttonName){

        JButton button = new JButton(buttonName);
        button.setBackground(new Color(139, 139, 129));
        button.setFont(new Font("Rockwell", Font.BOLD, 25));
        button.setForeground(new Color(206, 206, 206));
        button.setFocusable(false);

        return button;
    }
}