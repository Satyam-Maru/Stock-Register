package com.stockregister;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {

    protected static Connection con = null;

    protected static PreparedStatement pst = null;

    protected static void getConnection() throws SQLException {

        FileReader fr;
        BufferedReader br;
        String [] arr=new String[3];

        try{
            fr = new FileReader("src\\main\\java\\com\\stockregister\\SupabaseData.txt");
            br=new BufferedReader(fr);
            String line=br.readLine();

            int i=0;
            while (line!=null){
                arr[i]=line;
                i++;
                line=br.readLine();
            }

        }catch(IOException e){
            System.out.println(e.getMessage());
        }

        con = DriverManager.getConnection(arr[0], arr[1], arr[2]);

        if (con != null)
            System.out.println("Connection Successfully");
        else
            System.out.println("Connection Failed");

    }

    protected static void prepareStatement(String query) throws SQLException{
        pst = con.prepareStatement(query);
    }
}
