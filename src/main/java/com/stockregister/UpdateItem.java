package com.stockregister;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.stockregister.Stock.initLabel;
import static com.stockregister.Stock.initTextField;

public class UpdateItem {

    protected static JPanel updatePanel;
    protected static JButton updateDoneBtn, updateCategoryOkBtn;
    protected static JLabel updateCategoryLabel, updateItemLabel, updatePriceLabel, updateQuantityLabel;
    protected static JTextField updateQuantityTF, updatePriceTF;
    protected static JComboBox<String> updateCategoryComboBox, updateItemComboBox;
    static String[] items = new String[0];

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

        updateCategoryComboBox = new JComboBox<>(Database.getCategories());
        updateCategoryComboBox.setBounds(updateCategoryLabel.getX(), updateCategoryLabel.getY() + updateCategoryLabel.getHeight() + 7, 150, 30);
        updateCategoryComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String category = String.valueOf(updateCategoryComboBox.getSelectedItem());
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

                    updateItemComboBox.removeAllItems();
                    for(String item: items){
                        updateItemComboBox.addItem(item);
                    }

                }catch(SQLException ex){
                    System.out.println(ex.getMessage());
                }
            }
        });
        updatePanel.add(updateCategoryComboBox);

        updateQuantityTF = initTextField();
        updateQuantityTF.setBounds(updateQuantityLabel.getX(), updateQuantityLabel.getY() + updateQuantityLabel.getHeight() + 7, 80, 30);
        updatePanel.add(updateQuantityTF);

        updatePriceTF = initTextField();
        updatePriceTF.setBounds(updatePriceLabel.getX(), updatePriceLabel.getY() + updatePriceLabel.getHeight() + 7, 80, 30);
        updatePanel.add(updatePriceTF);

        updateItemComboBox = new JComboBox<>(items);
        updateItemComboBox.setBounds(updateItemLabel.getX(), updateItemLabel.getY() + updateItemLabel.getHeight() + 7, 150, 30);
        updatePanel.add(updateItemComboBox);

        updateDoneBtn = initButton();
        updateDoneBtn.setBounds(updatePriceTF.getX() + updatePriceTF.getWidth() + 70,
                updatePriceTF.getY() + updatePriceTF.getHeight() + 100, 120, 50);
        updateDoneBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                double price = -1, quantity = -1;
                if(updatePriceTF.getText().isBlank())
                    quantity = Double.parseDouble(updateQuantityTF.getText());
                else if (updateQuantityTF.getText().isBlank())
                    price = Double.parseDouble(updatePriceTF.getText());
                else{
                    price = Double.parseDouble(updatePriceTF.getText());
                    quantity = Double.parseDouble(updateQuantityTF.getText());
                }

                update_item(User.getUserId(),
                        (String) updateItemComboBox.getSelectedItem(),
                        (String) updateCategoryComboBox.getSelectedItem(), price, quantity);
            }
        });
        updatePanel.add(updateDoneBtn);
    }

    private static JButton initButton(){

        JButton button = new JButton("Done");
        button.setFont(new Font("Rockwell", Font.BOLD, 25));
        button.setFocusable(false);
        button.setForeground(Color.WHITE);
        button.setBackground(Color.BLACK);

        return button;
    }

    private static void update_item(int user_id, String item_name, String category_name,
                                    double price, double quantity){
        try{
            String query = "select update_item(?, ?, ?, ?, ?)";
            Database.prepareStatement(query);
            Database.pst.setInt(1, user_id);
            Database.pst.setString(2, item_name);
            Database.pst.setString(3, category_name);
            Database.pst.setDouble(4, price);
            Database.pst.setDouble(5, quantity);

            Database.pst.executeQuery();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}