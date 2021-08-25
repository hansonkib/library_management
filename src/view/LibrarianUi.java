package view;

import exceptions.InvalidDateFormatException;
import exceptions.UnableToLoadDriverClass;
import controller.BookLogics;
import controller.LibrarianLogics;
import controller.StudentLogics;
import model.Book;
import model.Librarian;
import utils.Utility;
import Enum.Gender;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
public class LibrarianUi {
    public Librarian captureLibrarianData(){
        Utility ut = new Utility();
        Librarian librarian = new Librarian();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter SSN number");
        librarian.setSsNO(sc.nextInt());
        sc.nextLine();
        System.out.println("Enter name");
        librarian.setName(sc.nextLine());
        System.out.println("Enter gender");
        librarian.setGender(Gender.valueOf(ut.toCamelCase(sc.nextLine())));
        System.out.println("Enter password");
        librarian.setPassword(sc.nextLine());
        return librarian;

    }
    public void userMenu() throws SQLException, UnableToLoadDriverClass, ParseException, InvalidDateFormatException {
        Scanner sc =new Scanner(System.in);
        System.out.println("Librarian module");
        int option;
        do {
            System.out.println("Enter your option\n" +
                    "1. Add new Books\n" +
                    "2. View books available\n" +
                    "3. Issue book to student\n" +
                    "4. Remove Book from system\n" +
                    "5. update Book\n" +
                    "6. Search Book\n" +
                    "7. Add new Students to library system\n" +
                    "8. View students in library Database\n" +
                    "9. Search Student\n" +
                    "10. update Student data in the system\n" +
                    "11. delete student from system\n" +
                    "0. Exit\n");
            option = sc.nextInt();
            sc.nextLine();
            switch (option){
                case 1:
                    addNewBook();
                    break;
                case 2:
                    displayAllBooks();
                    break;
                case 3:
                    issueBooks();
                    break;
                case 4:
                    deleteBook();
                    break;
                case 5:
                    updateBooks();
                    break;
                case 6:
                    searchBook();
                    break;
                case 7:
                    addStudent();
                    break;
                case 8:
                    displayStudents();
                    break;
                case 9:
                    searchStudent();
                    break;
                case 10:
                    updateStudent();
                    break;
                case 11:
                    deleteStudent();
                    break;
            }
        }while (option !=0);
    }
    private static void deleteStudent() throws SQLException, UnableToLoadDriverClass, ParseException {
        StudentUi su = new StudentUi();
        StudentLogics sl = new StudentLogics();
        sl.deleteStudent(su.searchStudent());
    }
    private static void updateStudent() throws SQLException, UnableToLoadDriverClass, ParseException {
        StudentUi su = new StudentUi();
        StudentLogics sl = new StudentLogics();
        sl.updateStudent(su.searchStudent());
    }
    private static void searchStudent() throws SQLException, UnableToLoadDriverClass, ParseException {
        StudentLogics sl = new StudentLogics();
        StudentUi su =new StudentUi();
        ResultSet rs = sl.displaySingle(su.searchStudent());
        su.displayStudents(new Utility().resulSetToStudentObjects(rs));
    }
    private static void displayStudents() throws SQLException, ParseException, UnableToLoadDriverClass {
        new StudentUi().displayStudents(new Utility().resulSetToStudentObjects(new StudentLogics().displayAll()));
    }
    private static void addStudent() throws SQLException, UnableToLoadDriverClass {
        System.out.println((new StudentLogics().add(new StudentUi().captureStudentData()))?"Added successfully":"failed to add");
    }
    private static void searchBook() throws SQLException, UnableToLoadDriverClass, ParseException, InvalidDateFormatException {
        BookLogics bl = new BookLogics();
        bl.displaySingle(new BookUi().searchBook());
    }
    private static void updateBooks() throws ParseException, SQLException, UnableToLoadDriverClass, InvalidDateFormatException {
        BookLogics bl = new BookLogics();
        bl.updateBook(new BookUi().searchBook());
    }
    private static void deleteBook() throws SQLException, UnableToLoadDriverClass, ParseException, InvalidDateFormatException {
        int bookId = new BookUi().deleteBook();
        new BookLogics().deleteBook(bookId);
    }
    public static void addNewBook() throws ParseException, SQLException, UnableToLoadDriverClass, InvalidDateFormatException {
        Book book = new BookUi().createBookData();
        BookLogics bl = new BookLogics();
        System.out.println((bl.add(book))?"New book added successfully":"failed to add book");
    }
    private static void displayAllBooks() throws SQLException, UnableToLoadDriverClass, ParseException, InvalidDateFormatException {
        Utility ut = new Utility();
        BookLogics bl = new BookLogics();
        new BookUi().displayBooks(ut.resulSetToObjects(bl.displayAll()));
    }
    private static void issueBooks() throws SQLException, UnableToLoadDriverClass, ParseException, InvalidDateFormatException {
        StudentUi su = new StudentUi();
        BookUi bu = new BookUi();
        System.out.println((new LibrarianLogics().issueBooks(su.searchStudent(),bu.searchBook()))?"Book issued successfully":"failed");
    }
}
