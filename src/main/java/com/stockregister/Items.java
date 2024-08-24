package com.stockregister;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Items {

    static JPanel panel;
    static JTable table;
    static JScrollPane scrollPane;
    String item_name, category_name, party_name, price_unit;
    double purchase_price, selling_price, opening_stock, current_quantity;
    static PriorityQueue<Items> priorityQueue;

    public Items(){}

    public Items(String item_name, String category_name, String party_name,
                 String price_unit, double purchase_price, double selling_price,
                 double opening_stock) {
        this.item_name = item_name;
        this.category_name = category_name;
        this.party_name = party_name;
        this.price_unit = price_unit;
        this.purchase_price = purchase_price;
        this.selling_price = selling_price;
        this.opening_stock = opening_stock;
    }

    // name, category, purchase_price, selling_price, price_unit, quantity
    public Items(String name, String category, double purchase,
                 double selling, String price_unit, double current_quantity){
        this.item_name = name;
        this.category_name = category;
        this.purchase_price = purchase;
        this.selling_price = selling;
        this.price_unit = price_unit;
        this.current_quantity = current_quantity;
    }

    protected void setPanel(){
        panel = new JPanel();
        panel.setLayout(null);
        setJTable();
        panel.add(scrollPane);
    }

    protected JPanel getPanel(){
        return panel;
    }

    public double getCurrentQuantity(){
        return current_quantity;
    }

    private static DefaultTableModel getTableModel() {
        DefaultTableModel tableModel = new DefaultTableModel();
        String[] columnNames = {"Name", "Category", "Purchase_Price", "Selling_Price", "Price_Unit", "Quantity"}; // Modify column names as per your table
        tableModel.setColumnIdentifiers(columnNames);

        String operation = "select ";
        String columns = "name, category, purchase_price, selling_price, price_unit, quantity";
        String table_name = "items";
        int user_id = User.getUserId();
        String order = "order by created_at;";
        String query = operation + columns + " from " + table_name + " where user_id = ? " + order; // Modify query as needed

        try {

            Database.prepareStatement(query);
            Database.pst.setInt(1, user_id);
            ResultSet rs = Database.pst.executeQuery();
            priorityQueue = new PriorityQueue<>(Comparator.comparing(Items::getCurrentQuantity));

            while (rs.next()) {
                String name = rs.getString("name");
                String category = rs.getString("category");
                double purchase_price = rs.getDouble("purchase_price");
                double selling_price = rs.getDouble("selling_price");
                String price_unit = rs.getString("price_unit");
                double quantity = rs.getDouble("quantity");

                // add all details in priority queue
                priorityQueue.add(new Items(name, category, purchase_price, selling_price, price_unit, quantity));
            }

            // add items according to their available stock
            while (!priorityQueue.isEmpty()){
                tableModel.addRow(getItemArray());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableModel;
    }

    protected static void setJTable(){

        if(table != null){
            // already exists
            table.removeAll();
            table.setModel(getTableModel());
            scrollPane.repaint();
            table.repaint();
            return;
        }

        table = new JTable(getTableModel());
        table.setFont(new Font("Rockwell", Font.PLAIN, 14));
        table.setRowHeight(20);

        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 0, 1035, 640);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(40);
    }

    // name, category, purchase_price, selling_price, price_unit, quantity
    public static Object[] getItemArray(){

        Items item = priorityQueue.poll();

        return new Object[] {item.item_name, item.category_name, item.purchase_price,
        item.selling_price, item.price_unit, item.current_quantity};

    }
}
