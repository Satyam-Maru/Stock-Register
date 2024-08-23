package com.stockregister;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Party {

    static JPanel panel;
    static JTable table;
    static JScrollPane scrollPane;

    protected void setPanel(){
        panel = new JPanel();
        panel.setLayout(null);

        setJTable();
    }

    protected JPanel getPanel(){
        return panel;
    }

    private static DefaultTableModel getTableModel() {
        DefaultTableModel tableModel = new DefaultTableModel();
        String[] columnNames = {"Name", "Purchased_Item", "Sold_Item", "Quantity", "Amount", "Date"}; // Modify column names as per your table
        tableModel.setColumnIdentifiers(columnNames);

        String operation = "select ";
        String columns = "p.name, purchased_item, sold_item, quantity, amount, TO_CHAR(h.created_at, 'YYYY-MM-DD HH24:MI:SS') AS time";
        String table_name = "history h INNER JOIN party p ON h.party_id = p.id AND user_id = ? ";
        int user_id = User.getUserId();
        String order = "order by h.created_at;";
        String query = operation + columns + " from " + table_name + order; // Modify query as needed

        try {

            Database.prepareStatement(query);
            Database.pst.setInt(1, user_id);
            ResultSet rs = Database.pst.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String purchased_item = rs.getString("purchased_item");
                String sold_item = rs.getString("sold_item");
                double quantity = rs.getDouble("quantity");
                double amount = rs.getDouble("amount");
                String date_time = rs.getString("time");
                // Add row to the table model
                tableModel.addRow(new Object[]{name, purchased_item, sold_item, quantity, amount, date_time });
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

        panel.add(scrollPane);
    }
}
