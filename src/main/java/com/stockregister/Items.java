package com.stockregister;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Items {

    JPanel panel;

    protected void setPanel(){
        panel = new JPanel();
        panel.setBounds(140, 60, 1035, 640); // Set bounds if needed
        panel.setLayout(null);
        panel.setBackground(Color.GREEN);

        JTable table = new JTable(getTableModel());
        table.setFont(new Font("Rockwell", Font.PLAIN, 14));
        table.setRowHeight(20);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 0, 1035, 640);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(40);

        panel.add(scrollPane);
    }

    protected JPanel getPanel(){
        return panel;
    }

    private DefaultTableModel getTableModel() {
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

            while (rs.next()) {
                String name = rs.getString("name");
                String category = rs.getString("category");
                double purchase_price = rs.getDouble("purchase_price");
                double selling_price = rs.getDouble("selling_price");
                String price_unit = rs.getString("price_unit");
                double quantity = rs.getDouble("quantity");
                // Add row to the table model
                tableModel.addRow(new Object[]{name, category, purchase_price, selling_price, price_unit, quantity});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableModel;
    }

}
