package com.stockregister;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeFrame extends JFrame implements ActionListener {

    public static void main(String[] args) {
        new HomeFrame();
    }

    protected static final int HomeFrameWidth = 1200, HomeFrameHeight = 750;

    JButton stockBtn, partyBtn, itemsBtn, salesBtn, backBtn, refreshBtn, prevBtn;
    JFrame frame;
    JPanel headPanel, leftPanel, workPanel;
    JLabel store_name_label;

    Color greyPanel = new Color(139, 139, 129);
    Color greyFont = new Color(206, 206, 206);

    HomeFrame() {
        initFrame();
    }

    HomeFrame(JFrame frame) {
        this();
        this.frame = frame;
    }

    protected void initFrame() {

        this.setTitle("Home Frame");
        this.setIconImage(LoginFrame.loginFrameLogo.getImage());
        this.setSize(HomeFrameWidth, HomeFrameHeight);
        this.setResizable(false);
        this.setLayout(null); // managing the layout self
        this.setLocationRelativeTo(null); // sets the frame in center of the window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(Color.WHITE);

        setPanels();
        setWorkPanel();
        setButtons();

        this.setVisible(true);
    }

    protected void setPanels(){

        int x = (HomeFrameWidth / 2) - 80;
        store_name_label = new JLabel();
        store_name_label.setBounds(x, 0, 250, 50);
        store_name_label.setText(User.getStoreName());
        store_name_label.setFont(new Font("Rockwell", Font.BOLD, 25));
        store_name_label.setForeground(Color.BLACK);

        // headPanel contains backBtn, store_name_label, refreshBtn
        headPanel = new JPanel();
        headPanel.setLayout(null);
        headPanel.setBounds(0, 0, HomeFrameWidth, 50);
        headPanel.setBackground(greyPanel);
        headPanel.add(store_name_label);

        // leftPanel contains stockBtn, partyBtn, itemsBtn
        leftPanel = new JPanel();
        leftPanel.setLayout(null);
        leftPanel.setBounds(0, 50, 130, HomeFrameHeight - headPanel.getBounds().height);
        leftPanel.setBackground(greyPanel);

        this.add(headPanel);
        this.add(leftPanel);
    }

    protected void setButtons() {

        backBtn = initButton("Back");
        backBtn.setBounds(15, 0, 90, headPanel.getBounds().height);
        backBtn.setFont(new Font("Rockwell", Font.BOLD, 22));
        backBtn.setMargin(new Insets(4, 2, 0, 0));

        refreshBtn = initButton("Refresh");
        refreshBtn.setBounds(1070, 0, 120, headPanel.getBounds().height);
        refreshBtn.setFont(new Font("Rockwell", Font.BOLD, 22));

        // leftPanel contains stockBtn, partyBtn, itemsBtn
        stockBtn =  initButton("Stock");
        stockBtn.setBounds(0, 50, leftPanel.getBounds().width, 40);

        partyBtn =  initButton("Party");
        partyBtn.setBounds(0, 120, leftPanel.getBounds().width, 40);

        itemsBtn = initButton("Items");
        itemsBtn.setBounds(0, 190, leftPanel.getBounds().width, 40);

        salesBtn = initButton("Sales"); // 30 gap in y
        salesBtn.setBounds(0, 260, leftPanel.getBounds().width, 40);
        salesBtn.setForeground(Color.BLACK);
        prevBtn = salesBtn;

        headPanel.add(backBtn);
        headPanel.add(refreshBtn);

        leftPanel.add(stockBtn);
        leftPanel.add(partyBtn);
        leftPanel.add(itemsBtn);
        leftPanel.add(salesBtn);
    }

    private JButton initButton(String buttonName){

        JButton button = new JButton(buttonName);
        button.setBackground(greyPanel);
        button.setFont(new Font("Rockwell", Font.BOLD, 25));
        button.setForeground(greyFont);
        button.addActionListener(this);
        button.setFocusable(false);

        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == backBtn) {
            this.setVisible(false);
            frame.setVisible(true);
        }
        else if (e.getSource() == refreshBtn) {
            // call method which will refresh the frame OR reload the
            System.out.println("Refresh btn");
            highlightBtn(refreshBtn);

            String[] categories = Database.getCategories();

            // for refresh (add condition to run only if the queue is not empty)
            StockInOut.stCategoryComboBox.removeAllItems();
            UpdateItem.updateCategoryComboBox.removeAllItems();
            RemoveItem.removeCategoryComboBox.removeAllItems();

            for(String item: categories){
                StockInOut.stCategoryComboBox.addItem(item);
                UpdateItem.updateCategoryComboBox.addItem(item);
                RemoveItem.removeCategoryComboBox.addItem(item);
            }
        }
        else if (e.getSource() == stockBtn) {

            highlightBtn(stockBtn);

            showWorkPanel("Stock Panel");
        }
        else if (e.getSource() == partyBtn) {

            highlightBtn(partyBtn);

            showWorkPanel("Party Panel");
        }
        else if (e.getSource() == itemsBtn) {

            highlightBtn(itemsBtn);

            showWorkPanel("Items Panel");
        }
        else if(e.getSource() == salesBtn){

            highlightBtn(salesBtn);

            showWorkPanel("Sales Panel");
        }
    }

    // By-default Sales button will be highlighted
    private void highlightBtn(JButton currentBtn){
        prevBtn.setForeground(greyFont);
        currentBtn.setForeground(Color.BLACK);
        prevBtn = currentBtn;
    }

    private void setWorkPanel(){

        // CardLayout acts like a stack of panels
        workPanel = new JPanel(new CardLayout());
        workPanel.setBounds(140, 60, 1035, 640);

        // Initialize panels
        Stock stock = new Stock();
        Party party = new Party();
        Items items = new Items();
        Sales sales = new Sales();

        stock.setPanel();
        party.setPanel();
        items.setPanel();
        sales.setPanel();

        // after this addition, workPanel stores the different panels
        workPanel.add(sales.getPanel(), "Sales Panel");
        workPanel.add(stock.getPanel(), "Stock Panel");
        workPanel.add(party.getPanel(), "Party Panel");
        workPanel.add(items.getPanel(), "Items Panel");

        this.add(workPanel);
    }

    // Displays the panel stored in workPanel's CardLayout wrt panel's name
    private void showWorkPanel(String panelName){
        CardLayout cl = (CardLayout) (workPanel.getLayout());
        cl.show(workPanel, panelName);
    }
}
