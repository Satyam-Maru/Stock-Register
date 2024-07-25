package com.stockregister;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class User {

    protected String email, password, store_name;

    protected int user_id;

    // DS
    // -----------------------------------------------------------------------------
    protected static HashMap<String, String> users;
    // -----------------------------------------------------------------------------

    // call this constructor if a new user sign up to ask store_name
    public User(String email, String password, String store_name, int user_id) {
        this.email = email;
        this.password = password;
        this.store_name = store_name;
        this.user_id = user_id;
    }

    // call this constructor if a user already exists
    public User(String email, String password, String store_name) {
        this.email = email;
        this.password = password;
        this.store_name = store_name;
    }

    // return current active user's email
    protected String getEmail(){
        return email;
    }

    // return current active user's password
    protected String getPassword(){
        return password;
    }

    // return current active user_id
    protected int getUser_id(){
        return user_id;
    }

    // prepare list of total registered users
    static protected void getUsers(){

        String query = "SELECT user_email, user_password FROM users";

        try{
            // Storing the user's data into HashMap
            Database.prepareStatement(query);

            users = new HashMap<>();

            ResultSet rs = Database.pst.executeQuery();

            while(rs.next()){

                String email = rs.getString("user_email");
                String password = rs.getString("user_password");

                users.put(email, password);
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
