package controller;

import dbInfo.databaseInfo;

import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class main {
    static databaseInfo dbInfo = new databaseInfo();

    static String url = dbInfo.getDbName();
    static String user = dbInfo.getUserName();
    static String password = dbInfo.getPassword();
    private static Connection conn;

    public static void main(String[] args) {
        String sql="";
        //start program
        while (true) {
            //기본 문구
            System.out.println("========================================");
            System.out.println("(0) 종료");
            System.out.println("(1) 릴레이션 생성 및 데이터 추가");
            System.out.println("(2) 제목을 이용한 검색");
            System.out.println("(3) 관객수를 이용한 검색");
            System.out.println("(4) 개봉일을 이용한 검색");
            System.out.println("========================================");

            //입력 시작
            Scanner sc = new Scanner(System.in);
            System.out.println("원하는 번호를 입력 하시오");
            String s = sc.next();
            switch (s) {

                case "0":
                    break;
                case "1":
                    function_1();
                    System.out.println("create relation... ");
                    System.out.println("Success!");
                    continue;


                case "2":
                    sc=new Scanner(System.in);
                    System.out.println("키워드를 입력하세요 : ");
                    String userKeyword=sc.next();
                    System.out.println("search for title... ");
                    System.out.println("<Result> ");
                    function_2(userKeyword);
                    //System.out.println(userKeyword);

                    continue;

                case "3":
                    sc=new Scanner(System.in);
                    System.out.println("관객수를 입력하세요 : ");
                    String userInputTotalnum=sc.next();
                    System.out.println("search for totalnum... ");
                    System.out.println("<Result> ");
                    function_3(userInputTotalnum);
                    continue;
                case "4":
                    sc=new Scanner(System.in);
                    System.out.println("검색할 날짜를 입력하세요 1 (yyyy-mm-dd) : ");
                    String userInputDate1=sc.next();
                    System.out.println("검색할 날짜를 입력하세요 2 (yyyy-mm-dd) : ");
                    String userInputDate2=sc.next();

                    System.out.println("search for releasedate ");
                    System.out.println("<Result> ");
                    function_4(userInputDate1,userInputDate2);
                    continue;
            }
            System.out.println("프로그램 종료");
            break;
        }
    }

    public static void function_1() {
        try {
            conn = DriverManager.getConnection(url,
                    user, password);

            //create table 문
            //Scanner sc = new Scanner(new FileReader("src/main/java/create_table.txt"));
            Scanner sc = new Scanner(new InputStreamReader(new FileInputStream("create_table.txt"),"UTF-8"));
            String str = "";
            while (sc.hasNextLine()) {
                str += sc.nextLine();
            }
            String sql = str;
            try {
                Statement stmt = conn.createStatement();
                stmt.execute(sql);

                //한글 인코딩 관련 코드
                sql = "ALTER TABLE movie convert to charset utf8";
                try {
                    stmt = conn.createStatement();
                    stmt.execute(sql);
                } catch (SQLException e) {
                    System.out.println(e.getMessage());

                }
            } catch (SQLException e) {
                //System.out.println(e.getMessage());
                System.out.println("릴레이션 생성은 이미 수행하였습니다. 다른 기능을 입력해 주세요. "); //테이블이 이미 생성 되었는지 체크
            }




            //insert 문
            sc = new Scanner(new InputStreamReader(new FileInputStream("movie_data.txt"),"UTF-8"));
            //sc = new Scanner(new FileReader("src/main/java/movie_data.txt"));
            String str2 = "";
            PreparedStatement ps = null;
            int insertCount = 0;
            while (sc.hasNext()) {
                str2 = sc.nextLine();
                String[] result = str2.split("\\|");

                String sql2 = "insert into movie values(?,?,?,?,?,?,?,?,?)";
                ps = conn.prepareStatement(sql2);

                ps.setString(1, result[1]);
                ps.setString(2, result[2]);
                ps.setString(3, result[3]);
                ps.setString(4, result[4]);
                ps.setString(5, result[5]);
                ps.setString(6, String.valueOf(result[6]));
                ps.setString(7, String.valueOf(result[7]));
                ps.setString(8, String.valueOf(result[8]));
                ps.setString(9, result[9]);

                insertCount = ps.executeUpdate();
            }
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

    }
    public static void function_2(String userInput){
        try {
            conn = DriverManager.getConnection(url,
                    user, password);

            //select 문
            String sql="select * from movie where title LIKE ? order by id asc";
            PreparedStatement ps = null;
            ResultSet rs=null;
            ps=conn.prepareStatement(sql);
            ps.setString(1,"%"+userInput+"%");
            rs=ps.executeQuery();

            while (rs.next()) {
                //System.out.println("while문 실행");
                String id = rs.getString("id");
                String title = rs.getString("title");
                String company = rs.getString("company");
                String releasedate = rs.getString("releasedate");
                String country = rs.getString("country");
                String totalscreen = rs.getString("totalscreen");
                String profit = rs.getString("profit");
                String totalnum = rs.getString("totalnum");
                String grade = rs.getString("grade");

                System.out.println(id + " " + title + " " +company + " " +releasedate + " " +
                        country + " " +totalscreen + " " +profit + " " +totalnum + " " +grade);
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("먼저 릴레이션을 생성해주세요.");
            //throw new RuntimeException(e);
        }
    }
    public static void function_3(String userInput){
        try {
            conn = DriverManager.getConnection(url,
                    user, password);

            //select 문
            String sql="select * from movie where totalnum > ? order by id asc";
            PreparedStatement ps = null;
            ResultSet rs=null;
            ps=conn.prepareStatement(sql);
            ps.setString(1,userInput);
            rs=ps.executeQuery();

            while (rs.next()) {
                //System.out.println("while문 실행");
                String id = rs.getString("id");
                String title = rs.getString("title");
                String company = rs.getString("company");
                String releasedate = rs.getString("releasedate");
                String country = rs.getString("country");
                String totalscreen = rs.getString("totalscreen");
                String profit = rs.getString("profit");
                String totalnum = rs.getString("totalnum");
                String grade = rs.getString("grade");

                System.out.println(id + " " + title + " " +company + " " +releasedate + " " +
                        country + " " +totalscreen + " " +profit + " " +totalnum + " " +grade);
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("먼저 릴레이션을 생성해주세요.");
            throw new RuntimeException(e);
        }
    }
    public static void function_4(String userInput1,String userInput2){
        try {
            conn = DriverManager.getConnection(url,
                    user, password);

            //select 문
            String sql="select * from movie where releasedate between ? and ? order by id asc";
            PreparedStatement ps = null;
            ResultSet rs=null;
            ps=conn.prepareStatement(sql);
            ps.setString(1,userInput1);
            ps.setString(2,userInput2);
            rs=ps.executeQuery();

            while (rs.next()) {
                //System.out.println("while문 실행");
                String id = rs.getString("id");
                String title = rs.getString("title");
                String company = rs.getString("company");
                String releasedate = rs.getString("releasedate");
                String country = rs.getString("country");
                String totalscreen = rs.getString("totalscreen");
                String profit = rs.getString("profit");
                String totalnum = rs.getString("totalnum");
                String grade = rs.getString("grade");

                System.out.println(id + " " + title + " " +company + " " +releasedate + " " +
                        country + " " +totalscreen + " " +profit + " " +totalnum + " " +grade);
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("먼저 릴레이션을 생성해주세요.");
            throw new RuntimeException(e);
        }
    }
}
