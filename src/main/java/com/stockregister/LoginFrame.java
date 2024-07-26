package com.stockregister;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginFrame extends JFrame implements ActionListener {

    protected static final int frameWidth = 1020, frameHeight = 650;

    static ImageIcon loginFrameLogo = new ImageIcon("src\\main\\java\\com\\stockregister\\Images\\LoginFrameLogo.png");
    ImageIcon stockRegisterImg = new ImageIcon("src\\main\\java\\com\\stockregister\\Images\\StockRegisterImg.png");

    JPanel mainPanel;

    JLabel stockImgLabel, warningLabel, passwordWarner;

    static JTextField emailTxtF;
    JPasswordField passwordTxtF;

    JButton signUpBtn, signInBtn;

    // For Email Validation
    // -----------------------------------------------------------------------------
    private final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
    private final Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);
    // -----------------------------------------------------------------------------

    // For Password Validation
    // -----------------------------------------------------------------------------
    private final String PASSWORD_PATTERN = "^[\\S]{5,}$";
    private final Pattern passwordPattern = Pattern.compile(PASSWORD_PATTERN);
    // -----------------------------------------------------------------------------

    LoginFrame(){

        initMainPanel();
        initFrame();
    }


    protected void initFrame() {

        this.setTitle("Login Frame");
        this.setIconImage(loginFrameLogo.getImage());
        this.setSize(frameWidth, frameHeight);  // 1020, 650
        this.setResizable(false);
        this.setLayout(null);   // managing the layout self
        this.setLocationRelativeTo(null);   // sets the frame in center of the window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.BLACK);
        this.add(mainPanel);

        // TO-DO apply threading in Database.getConnection()
        try{
            Database.getConnection();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        this.setVisible(true);
    }

    protected void initMainPanel() {

        mainPanel = new JPanel();
        mainPanel.setBounds(170, 80, frameWidth - 170, 420);
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.BLACK);

        // setting the stock register image
        stockImgLabel = new JLabel();
        stockImgLabel.setIcon(stockRegisterImg);
        stockImgLabel.setBounds(-30, -9, 350, 440);
        mainPanel.add(stockImgLabel);

        setEmailPanel();
        setPasswordPanel();
        setButtons();
        initWarningLabel();
    }

    protected void setEmailPanel(){

        JLabel emailString = new JLabel();
        emailString.setText("Email");
        emailString.setBounds(380, 65, 80, 20);
        emailString.setFont(new Font("Consolas", Font.BOLD, 17));
        emailString.setForeground(Color.WHITE);
        mainPanel.add(emailString);

        emailTxtF = new JTextField();
        emailTxtF.setBounds(380, 90, 240, 35);
        emailTxtF.setMargin(new Insets(4, 10, 0, 10));
        emailTxtF.setFont(new Font("Consolas", Font.PLAIN, 16));
        emailTxtF.setForeground(Color.WHITE);
        emailTxtF.setCaretColor(Color.WHITE);
        emailTxtF.setBackground(new Color(27,27,27));
        mainPanel.add(emailTxtF);
    }

    protected void setPasswordPanel(){

        JLabel passString = new JLabel();
        passString.setText("Password");
        passString.setBounds(380, 150, 120, 20);
        passString.setFont(new Font("Consolas", Font.BOLD, 16));
        passString.setForeground(Color.WHITE);
        mainPanel.add(passString);

        passwordTxtF = new JPasswordField();
        passwordTxtF.setBounds(380, 175, 240, 35);
        passwordTxtF.setMargin(new Insets(2, 10, 0, 10));
        passwordTxtF.setBackground(new Color(27,27,27));
        passwordTxtF.setForeground(Color.WHITE);
        passwordTxtF.setCaretColor(Color.WHITE);
        mainPanel.add(passwordTxtF);
    }

    // TO-DO apply DATA_REDUNDANCY (make a method that init default btn settings)
    protected void setButtons(){

        signUpBtn = new JButton();
        signUpBtn.setBounds(390, 300, 100, 32);
        signUpBtn.setText("Sign Up");
        signUpBtn.setMargin(new Insets(3, 5, 0, 5));
        signUpBtn.setFont(new Font("Consolas", Font.BOLD, 16));
        signUpBtn.setForeground(Color.WHITE);
        signUpBtn.setBackground(new Color(50,205,50));
        signUpBtn.setFocusable(false);
        signUpBtn.addActionListener(this);
        mainPanel.add(signUpBtn);

        signInBtn = new JButton();
        signInBtn.setBounds(500, 300, 100, 32);
        signInBtn.setText("Sign In");
        signInBtn.setMargin(new Insets(3, 5, 0, 5));
        signInBtn.setFont(new Font("Consolas", Font.BOLD, 16));
        signInBtn.setForeground(Color.WHITE);
        signInBtn.setBackground(new Color(50,205,50));
        signInBtn.setFocusable(false);
        signInBtn.addActionListener(this);
        mainPanel.add(signInBtn);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        User.getUsers(); // init HashMap containing user's data

        if(e.getSource() == signUpBtn){

            // checks for value returned from TextField And PasswordField
            if(isValidEmail(getEmail()) && isValidPassword(getPassword())){

                // User.users => users = HashMap
                if(User.users.containsKey(getEmail())){
                    // will run if user is already signed up
                    warningLabel.setText("Already a user, please sign in.");
                    passwordWarner.setText("");
                }else{

                    // run for new users
                    try {

                        String store_name = getStoreName();

                        // Database Insertion
                        String query = "INSERT INTO users VAlUES (?, ?, ?)";
                        Database.prepareStatement(query);

                        Database.pst.setString(1, getEmail()); // JTextField
                        Database.pst.setString(2, getPassword()); // JPasswordField
                        Database.pst.setString(3, store_name);

                        Database.pst.executeUpdate();

                        User.current_user = new User(getEmail(), getPassword(), User.fetchStoreName(), User.fetchUserId());

                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }

                    HomeFrame frame = new HomeFrame(this);
                    frame.setVisible(true);
                    this.setVisible(false);
                }
            }

            if(!isValidEmail(getEmail())){

                warningLabel.setText("Invalid Email.");
                passwordWarner.setText("");
            }
            else if (!isValidPassword(getPassword())) {
                warningLabel.setText("Invalid Password.");
                passwordWarner.setText("More than 4 chars & no spacing.");
            }

        }else if(e.getSource() == signInBtn){
                                             // users.get(getEmail()) => returns password
            if(User.users.containsKey(getEmail()) && User.users.get(getEmail()).equals(getPassword())){

                    User.current_user = new User(getEmail(), getPassword(), User.fetchStoreName(), User.fetchUserId());

                    HomeFrame frame = new HomeFrame(this);
                    frame.setVisible(true);
                    this.setVisible(false);
            }else{
                passwordWarner.setText("");
                warningLabel.setText("Invalid Email OR Password.");
            }
        }
    }

    protected void initWarningLabel() {

        warningLabel = new JLabel();
        warningLabel.setBounds(380, 230, 290, 20);
        warningLabel.setFont(new Font("Consolas", Font.BOLD, 16));
        warningLabel.setForeground(Color.RED);
        mainPanel.add(warningLabel);

        passwordWarner = new JLabel();
        passwordWarner.setBounds(380, 251, 400, 20);
        passwordWarner.setFont(new Font("Consolas", Font.BOLD, 16));
        passwordWarner.setForeground(Color.RED);
        mainPanel.add(passwordWarner);
    }

    protected boolean isValidEmail(String email) {

        if (email == null) {
            return false;
        }

        Matcher matcher = emailPattern.matcher(email);
        return matcher.matches();
    }

    protected boolean isValidPassword(String password) {

        if (password == null) {
            return false;
        }

        Matcher matcher = passwordPattern.matcher(password);
        return matcher.matches();
    }

    // returns email from JTextField
    protected static String getEmail(){
        return emailTxtF.getText();
    }

    // returns password from JPasswordField
    protected String getPassword(){

        try{
            return passwordTxtF.getText();
        }catch (NullPointerException e){
            return "";
        }
    }

    protected String getStoreName(){

        // Create a custom JPanel with a background color
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY); // Set your desired background color

        // Create a JLabel and a JTextField
        JLabel label = new JLabel("Enter Store Name:");
        JTextField textField = new JTextField(20);

        // Set custom properties for the JTextField
        textField.setMargin(new Insets(3, 8, 0, 8));
        textField.setFont(new Font("Consolas", Font.PLAIN, 16));
        textField.setForeground(Color.WHITE);
        textField.setCaretColor(Color.WHITE);
        textField.setBackground(new Color(27,27,27));

        panel.add(label);
        panel.add(textField);

        // Display the custom JPanel in a JOptionPane
        int result = JOptionPane.showConfirmDialog(null, panel, "Input", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        // Process the result
        if (result == JOptionPane.OK_OPTION) {

            String value = textField.getText();

            if(value.isEmpty() || value.trim().isEmpty()){
                return getStoreName();
            }else{
                return value;
            }

        } else {
            return getStoreName();
        }
    }

}