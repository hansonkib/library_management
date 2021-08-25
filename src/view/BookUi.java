package view;

import Exceptions.InvalidDateFormat;
import model.Book;
import utils.Utility;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class BookUi {
    Scanner sc = new Scanner(System.in);
    Book book = new Book();
    Utility utility = new Utility();
    public Book createBookData() throws ParseException, InvalidDateFormat {
        System.out.println("Enter book ID");
        book.setBookId(sc.nextInt());
        sc.nextLine();
        System.out.println("Enter book title");
        book.setTitle(sc.nextLine());
        System.out.println("Enter book  category");
        book.setCategory(sc.nextLine());
        System.out.println("author");
        book.setAuthor(sc.nextLine());
        System.out.println("publication date");
        book.setDate(utility.dateParser(sc.nextLine()));
        return book;
    }
    public Book updateBook() throws ParseException, InvalidDateFormat {
        System.out.println("Enter new book ID");
        book.setBookId(sc.nextInt());
        sc.nextLine();
        System.out.println("Enter new book title");
        book.setTitle(sc.nextLine());
        System.out.println("Enter new book  category");
        book.setCategory(sc.nextLine());
        System.out.println("Enter new author");
        book.setAuthor(sc.nextLine());
        System.out.println("Enter new publication date");
        book.setDate(utility.dateParser(sc.nextLine()));
        return book;
    }
    public int searchBook(){
        System.out.println("Enter book Id");
        int id= sc.nextInt();
        sc.nextLine();
        return id;
    }
    public void displayBooks(ArrayList<Book> book){
        for (Book book1 : book)
            System.out.println(book1);
    }
    public int deleteBook() {
        System.out.println("Enter details of book you want to delete");
        return searchBook();
    }
    public int userMenu(){
        int choice;
        System.out.println("Welcome to Library management system");
        System.out.println("Select your option bellow");
        System.out.println(" 1. Add new Book\n" +
                " 2. Sort and View Books\n" +
                " 3. Delete Books\n" +
                " 4. Search Books\n" +
                " 5. Update Books\n" +
                " 0. Exit\n");
        choice = sc.nextInt();
        sc.nextLine();
        return choice;
    }
    public int booksSortMenu(){
        System.out.println("Sort books for easy search");
        System.out.println(" 1. Sort by Title\n" +
                " 2. Sort by Book Id\n" +
                " 3. Sort by Author\n" +
                " 4. Sort by category\n" +
                " 0. Exit\n");
        int choice = sc.nextInt();
        sc.nextLine();
        return choice;
    }
}
