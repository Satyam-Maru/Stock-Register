package com.stockregister;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.stockregister.Stock.initLabel;
import static com.stockregister.Stock.initTextField;

public class RemoveItem {

    protected static JPanel removePanel;
    protected static JButton removeCategoryOKBtn,removeDoneBtn;
    protected static JLabel removeCategoryLabel, removeItemLabel;
    protected static JTextField removeCategoryTF;
    protected static JComboBox<String>  removeItemComboBox;

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
        removeCategoryTF = initTextField();
        removeCategoryTF.setBounds(removeCategoryLabel.getX(),
                removeCategoryLabel.getY() + removeCategoryLabel.getHeight() + 7, 150, 30);
        removePanel.add(removeCategoryTF);

        removeCategoryOKBtn = initButton("OK");
        removeCategoryOKBtn.setBounds(removeCategoryTF.getX() + removeCategoryTF.getWidth() + 10,
                removeCategoryTF.getY(), 60, 30);
        removeCategoryOKBtn.setContentAreaFilled(false);
        removeCategoryOKBtn.setFont(new Font("Rockwell", Font.PLAIN, 15));
        removeCategoryOKBtn.setForeground(Color.BLACK);
        removeCategoryOKBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("remove category ok btn");
            }
        });
        removePanel.add(removeCategoryOKBtn);

        removeItemComboBox = new JComboBox<>(new String[]{"ok", "nice man"});
        removeItemComboBox.setBounds(removeItemLabel.getX(),
                removeItemLabel.getY() + removeItemLabel.getHeight() + 7, 150, 30);
        removePanel.add(removeItemComboBox);

        removeDoneBtn = initButton("Done");
        removeDoneBtn.setBounds(removeCategoryTF.getX() + removeCategoryTF.getWidth(),
                removeCategoryTF.getY() + removeCategoryTF.getHeight() + 100, 120, 50);
        removeDoneBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("remove done btn");
            }
        });
        removePanel.add(removeDoneBtn);
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
