package org.example;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

class Operations {
    static final String table = Main_Class.tablename;
    protected static void fetchData(Statement statement) {
        try {
            ResultSet resultSet = statement.executeQuery("SELECT  * FROM " + table);
            while (resultSet.next()) {
                System.out.println("\nSTUDENT ID    = " + resultSet.getInt("id"));
                System.out.println("STUDENT NAME    = " + resultSet.getString("name"));
                System.out.println("STUDENT EMAIL   = " + resultSet.getString("email"));
                System.out.println("STUDENT PHONENO = " + resultSet.getString("phoneno"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    protected static void insertDataBulk(Statement statement, int n) {
        int maxid = 0;
        try {
            ResultSet resultSet = statement.executeQuery("SELECT MAX(id) AS MAX_ID FROM " + table);
            if (resultSet.next()) {
                maxid = resultSet.getInt("MAX_ID");
            }
            String[] bulkqueries = new String[n];
            int i = 0;
            while (bulkqueries.length > i) {
                maxid++;
                System.out.println("\nENTER YOUR NAME");
                String name = ConnectClass.sc.next();

                System.out.println("ENTER YOUR EMAIL");
                String email = ConnectClass.sc.next();

                System.out.println("ENTER YOUR PHONE NUMBER");
                String phone = ConnectClass.sc.next();

                statement.addBatch("INSERT INTO " + table + " VALUE(" + maxid + ",'" + name + "','" + email + "','" + phone + "')");
                System.out.println();
                i++;
            }
            int rowcount[] = statement.executeBatch();
            boolean flag = true;
            for (int j = 0; j < rowcount.length; j++) {
                if (rowcount[j] <= 0) {
                    flag = false;
                    break;
                }
            }
            if (flag == true) {
                System.out.println("...INSERTION COMPLETED.....");
            } else {
                System.out.println("...INSERTION FAILED....");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    protected static void insertData(Statement statement) {
        int rowcount = 0;
        int maxid = 0;
        System.out.println("\nENTER NAME OF STUDENT");
        String name = ConnectClass.sc.next();
        System.out.println("ENTER EMAIL");
        String email = ConnectClass.sc.next();
        System.out.println("ENTER PHONE NUMBER");
        String phone = ConnectClass.sc.next();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT MAX(id) AS MAX_ID FROM " + table);
            if (resultSet.next())
                maxid = resultSet.getInt("MAX_ID");
            maxid++;
            rowcount = statement.executeUpdate("INSERT INTO " + table + " VALUE(" + maxid + ",'" + name + "','" + email + "','" + phone + "')");
            if (rowcount > 0)
                System.out.println("...DATA INSERTED...");
            else
                System.out.println("...DATA NOT INSERTED...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    protected static void Update(Statement statement, int st_id) {
        Scanner sc = new Scanner(System.in);
        System.out.println("WHAT YOU WANT TO UPDATE? \n1. NAME\n2. EMAIL\n3 PHONE NO");
        int ch = sc.nextInt();
        int rowcount =0;
        try {
            switch (ch){
                case 1:
                    System.out.println("\nENTER NEW NAME");
                    String newname = ConnectClass.sc.next();
                    rowcount = statement.executeUpdate("UPDATE " + table + " SET name = '" + newname + "' WHERE id =" + st_id);
                    break;

                case 2:
                    System.out.println("\nENTER NEW EMAIL");
                    String newemail = ConnectClass.sc.next();
                    rowcount = statement.executeUpdate("UPDATE " + table + " SET email = '" + newemail + "' WHERE id =" + st_id);
                    break;

                case 3:
                    System.out.println("\nENTER NEW PHONE NUMBER");
                    String newphone = ConnectClass.sc.next();
                    rowcount = statement.executeUpdate("UPDATE " + table + " SET phoneno = '" + newphone + "' WHERE id =" + st_id);
                    break;
            }
            if (rowcount > 0)
                System.out.println("..DATA UPDATED..");
            else
                System.out.println("..DATA UPDATION FAILED..");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    protected static void deleteData(Statement statement, int choice) {
        Scanner sc = new Scanner(System.in);
        try {
            if (choice == 1) {
                System.out.println("ENTER ID OF STUDENT");
                int st_id = sc.nextInt();
                statement.executeUpdate("DELETE FROM " + table + " WHERE id = " + st_id);
            } else if (choice == 2) {
                statement.executeUpdate("TRUNCATE TABLE " + table);
            }
            System.out.println("...DATA DELETED...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
