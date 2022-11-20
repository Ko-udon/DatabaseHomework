package controller;

import dbInfo.databaseInfo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class test {
    static databaseInfo dbInfo = new databaseInfo();

    static String url = dbInfo.getDbName();
    static String user = dbInfo.getUserName();
    static String password = dbInfo.getPassword();
    private static Connection conn;
    public static void main(String[] args) throws FileNotFoundException {
        try {
            conn = DriverManager.getConnection(url,
                    user, password);


            /*//create table 문
            Scanner sc=new Scanner(new FileReader("src/main/java/create_table.txt"));
            String str="";
            while(sc.hasNextLine()){
                str+=sc.nextLine();
            }
            ResultSet rs=null;
            String sql=str;
            try{
                Statement stmt=conn.createStatement();
                stmt.execute(sql);

            }catch (SQLException e){
                System.out.println(e.getMessage());
            }*/



            //insert 문

            Scanner sc=new Scanner(new FileReader("src/main/java/movie_data.txt"));
            while(sc.hasNext()){
                String str=sc.nextLine();
                String[] result=str.split("\\|");

                System.out.println(result[9]);

            }


            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
