package view;

import Exceptions.InvalidDateFormat;
import Exceptions.UnableToLoadDriverClass;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class MainUi {
    public void userMenu() throws SQLException, UnableToLoadDriverClass, ParseException, InvalidDateFormat {
        LibrarianUi lu = new LibrarianUi();
        StudentUi studentUi = new StudentUi();
        Scanner scanner = new Scanner(System.in);
        int choice;
        System.out.println("Welcome to Library Management System");
        do {
            System.out.println("Choose your option");
            System.out.println(" 1. Join as Librarian\n" +
                    " 2. Join as Student\n" +
                    " 0. Exit\n");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice){
                case 1:
                    lu.userMenu();
                    break;
                case 2:
                    studentUi.userMenu();
                    break;
            }
        }while (choice !=0);

    }

}
