package com.stockregister;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.stockregister.Stock.initLabel;

public class RemoveItem {

    protected static JPanel removePanel;
    protected static JButton removeDoneBtn;
    protected static JLabel removeCategoryLabel, removeItemLabel;
    protected static JComboBox<String> removeCategoryComboBox, removeItemComboBox;
    static String[] items = new String[0];

    protected static JPanel set_getRemovePanel(){
        removePanel = new JPanel();
        removePanel.setLayout(null);
        setRemoveLabels();
        setRemoveTextField_ComboBox();
        return removePanel;
    }

    private static void setRemoveLabels(){
        removeCategoryLabel = initLabel("Category");
        removeCategoryLabel.setBounds(80, 80, 150, 30);
        removePanel.add(removeCategoryLabel);

        removeItemLabel = initLabel("Item");
        removeItemLabel.setBounds(removeCategoryLabel.getX() + removeCategoryLabel.getWidth() + 100,
                removeCategoryLabel.getY(), 80, 30);
        removePanel.add(removeItemLabel);
    }

    private static void setRemoveTextField_ComboBox(){

        removeCategoryComboBox = new JComboBox<>(Database.getCategories());
        removeCategoryComboBox.setBounds(removeCategoryLabel.getX(),
                removeCategoryLabel.getY() + removeCategoryLabel.getHeight() + 7, 150, 30);
        removeCategoryComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String category = String.valueOf(removeCategoryComboBox.getSelectedItem());
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

                    removeItemComboBox.removeAllItems();
                    for(String item: items){
                        removeItemComboBox.addItem(item);
                    }

                }catch(SQLException ex){
                    System.out.println(ex.getMessage());
                }
            }
        });
        removePanel.add(removeCategoryComboBox);

        removeItemComboBox = new JComboBox<>(items);
        removeItemComboBox.setBounds(removeItemLabel.getX(),
                removeItemLabel.getY() + removeItemLabel.getHeight() + 7, 150, 30);
        removePanel.add(removeItemComboBox);

        removeDoneBtn = initButton();
        removeDoneBtn.setBounds(removeCategoryComboBox.getX() + removeCategoryComboBox.getWidth(),
                removeCategoryComboBox.getY() + removeCategoryComboBox.getHeight() + 100, 120, 50);
        removeDoneBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove_item(User.getUserId(), (String) removeItemComboBox.getSelectedItem());
            }
        });
        removePanel.add(removeDoneBtn);
    }

    private static JButton initButton(){

        JButton button = new JButton("Done");
        button.setFont(new Font("Rockwell", Font.BOLD, 25));
        button.setFocusable(false);
        button.setForeground(Color.WHITE);
        button.setBackground(Color.BLACK);

        return button;
    }

    private static void remove_item(int user_id, String item_name){
        try{
            String query = "select remove_item (?, ?)";
            Database.prepareStatement(query);
            Database.pst.setInt(1, user_id);
            Database.pst.setString(2, item_name);

            Database.pst.executeQuery();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}