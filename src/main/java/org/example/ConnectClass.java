package org.example;

import java.sql.*;
import java.util.Comparator;
import java.util.Scanner;

public class ConnectClass {
    private final  static String url = "jdbc:mysql://localhost:3306/spark";
    private final static String username = "root";
    private final static String password = "amaansalik2004";
    static  Scanner sc = new Scanner(System.in);


    //create table
    public static boolean creatTable(String tablename){
        boolean flag  = false;
        try{
            Connection connection= DriverManager.getConnection(url,username,password);
            Statement statement = connection.createStatement();
            String sql = "Create table "+tablename+
                    "(id int,"+
            "name varchar(30),"+
            "email varchar(30)," +
            "phoneno varchar(10))";
            statement.executeUpdate(sql);
            flag = true;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return flag;
    }


    public static void connectMethod(int choice){
        int st_id;
        try{
            Connection connection = DriverManager.getConnection(url,username,password);
            Statement statement = connection.createStatement();
            switch (choice){
                case 1:
                    System.out.println();
                    Operations.fetchData(statement);
                    System.out.println();
                    break;

                case 2:
                    System.out.println();
                    System.out.println("HOW MANY STUDENT'S DATA YOU WANT TO STORE");
                    int n = sc.nextInt();
                    if(n==1)
                         Operations.insertData(statement);
                    else
                        Operations.insertDataBulk(statement,n);
                    break;

                case 3:
                    System.out.println();
                    System.out.println("ENTER ID OF STUDENT");
                    st_id = sc.nextInt();
                    System.out.println();
                    Operations.Update(statement,st_id);
                    break;


                case 4:
                    System.out.println();
                    System.out.println("1. DELETE DATA OF ONE STUDENT \n2. DELETE DATA OF ALL STUDENTS\n");
                    int ch = sc.nextInt();
                    Operations.deleteData(statement,ch);
                    break;

                default:
                    System.out.println("PLZZ ENTER CORRECT OPTION");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
