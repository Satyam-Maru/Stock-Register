package com.stockregister;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {

    protected static Connection con = null;

    protected static String url = "jdbc:postgresql://aws-0-ap-south-1.pooler.supabase.com:6543/postgres";

    protected static String user = "postgres.cvqrsctgiehsqykhldou";

    protected static String pass = "Satyam@7874";

    protected static PreparedStatement pst = null;

    protected static void getConnection() throws SQLException {

        con = DriverManager.getConnection(url, user, pass);

        if (con != null)
            System.out.println("Connection Successfully");
        else
            System.out.println("Connection Failed");

    }

    protected static void prepareStatement(String query) throws SQLException{
        pst = con.prepareStatement(query);
    }
}
