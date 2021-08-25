package view;

import Exceptions.InvalidDateFormat;
import Exceptions.UnableToLoadDriverClass;
import controller.BookLogics;
import controller.StudentLogics;
import model.Student;
import Enum.Gender;
import utils.Utility;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentUi {
    public void userMenu() throws SQLException, UnableToLoadDriverClass, ParseException, InvalidDateFormat {
        int choice;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to student module");
do {
    System.out.println("Choose your option");
    System.out.println(" 1. View available books\n" +
            " 2. Return books\n" +
            " 3. search Book" +
            " 0. Exit");
    choice = scanner.nextInt();
    scanner.nextLine();
    switch (choice){
        case 1:
            displayAllBooks();
            break;
        case 2:
            returnBook();
            break;
        case 3:
            searchBook();
            break;
    }
}while (choice != 0);

    }
    public Student captureStudentData(){
        Utility ut = new Utility();
        Student student = new Student();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter student details below");
        System.out.println("Enter registration number");
        student.setRegNo(sc.nextInt());
        sc.nextLine();
        System.out.println("Enter name");
        student.setName(sc.nextLine());
        System.out.println("Enter gender");
        student.setGender(Gender.valueOf(ut.toCamelCase(sc.nextLine())));
        System.out.println("Enter faculty");
        student.setFaculty(sc.nextLine());
        return student;

    }
    public void displayStudents(ArrayList<Student> students){
        for (Student std : students)
            System.out.println(std);
    }
    public int searchStudent(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter registration Number");
        int id= sc.nextInt();
        sc.nextLine();
        return id;
    }
    public Student updateStudent() throws ParseException {
        Student student = new Student();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter new Registration Number");
        student.setRegNo(sc.nextInt());
        sc.nextLine();
        System.out.println("Enter new name");
        student.setName(sc.nextLine());
        System.out.println("Enter new Gender");
        student.setGender(Gender.valueOf(new Utility().toCamelCase(sc.nextLine())));
        System.out.println("Enter new faculty");
        student.setFaculty(sc.nextLine());
        return student;
    }
    private static void displayAllBooks() throws SQLException, UnableToLoadDriverClass, ParseException, InvalidDateFormat {
        Utility ut = new Utility();
        BookLogics bl = new BookLogics();
        new BookUi().displayBooks(ut.resulSetToObjects(bl.displayAll()));
    }
    private static void searchBook() throws SQLException, UnableToLoadDriverClass, ParseException, InvalidDateFormat {
        BookLogics bl = new BookLogics();
        bl.displaySingle(new BookUi().searchBook());
    }
    public void returnBook() throws SQLException, UnableToLoadDriverClass, ParseException, InvalidDateFormat {
        StudentLogics sl = new StudentLogics();
        StudentUi su = new StudentUi();
        BookUi bu = new BookUi();
        System.out.println((sl.returnBook(su.searchStudent(), bu.searchBook()))?"returned successfully":"failed to return");
    }
}
