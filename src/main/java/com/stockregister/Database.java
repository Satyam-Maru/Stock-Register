package com.stockregister;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

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

    static String[] getCategories(){

        String[] categories = new String[0];

        try{

            String countRows = "select count(id) from category where id in (select cat_id from items where user_id = (?))";
            Database.prepareStatement(countRows);
            Database.pst.setInt(1, User.getUserId());
            ResultSet rs = Database.pst.executeQuery();

            if(rs.next()){
                categories = new String[rs.getInt("count")];
            }

            String query = "select name from category where id IN (select cat_id from items where user_id = (?));";
            Database.prepareStatement(query);
            Database.pst.setInt(1, User.getUserId());
            rs = Database.pst.executeQuery();
            int i = 0;
            while(rs.next()){
                categories[i++] = rs.getString(1);
            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return categories;
    }
}
