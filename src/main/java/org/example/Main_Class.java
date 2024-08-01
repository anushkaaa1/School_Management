package org.example;

import java.sql.SQLException;
import java.util.Scanner;

public class Main_Class {
    static String tablename;
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("........WELCOME........\n");
        System.out.println("1. LOGIN \n2.SIGN IN\n");
        int c = sc.nextInt();
        boolean op = choose(c);
        if (op){
            options();
            int choice;
            while (true) {
                 System.out.println("\n..ENTER CHOICE..");
                 choice = sc.nextInt();
                 if (choice == 5)
                     break;
            ConnectClass.connectMethod(choice);
            }
        }
        System.out.println("THANKYOU");
    }

    private static void options(){
        System.out.println("\n..SELECT ANY ONE OPTION..\n");
        System.out.println("1. FETCH");
        System.out.println("2. INSERT");
        System.out.println("3. UPDATE");
        System.out.println("4. DELETE");
        System.out.println("5. EXIST");
    }


    private static boolean choose(int c)  {
        Scanner sc = new Scanner(System.in);
        boolean select = false;
        if(c==1){
            System.out.println("\nENTER TABLE NAME");
            tablename = sc.next();
            select = true;
        }
        else if(c==2){
            System.out.println("\nENTER TABLE NAME WHICH YOU WANT TO CREATE");
            String t_name = sc.next();
            boolean flag = ConnectClass.creatTable(t_name);
            if(flag){
                System.out.println("...TABLE CREATED...");
                tablename = t_name;
                select =  true;
            }
            else {
                System.out.println("...TABLE NOT CREATED...");
                select = false;
            }
        }
        else
            System.out.println("..PLZZ ENTER CORRECT OPTION");
        return select;
    }
}


