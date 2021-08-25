package controller;

import Exceptions.InvalidDateFormat;
import Exceptions.UnableToLoadDriverClass;
import interfaces.LogicI;
import model.Book;
import utils.DbUtil;
import utils.Utility;
import view.BookUi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class BookLogics implements LogicI<Book> {
    DbUtil db = new DbUtil();
    Utility ut = new Utility();

    public BookLogics() throws SQLException, UnableToLoadDriverClass, ParseException, InvalidDateFormat {
    }

    @Override
    public boolean add(Book book) throws SQLException {
        return (db.create("INSERT INTO books_tbl VALUES('"+book.getBookId()+"','"+book.getTitle()+"','"+book.getCategory()+"','"+book.getAuthor()+"','"+ut.dateFormatter(book.getDate())+"')")==0)?false:true;
    }

    @Override
    public boolean update(Book book, int bookId) throws SQLException {
        return (db.update("UPDATE books_tbl SET bookId='"+book.getBookId()+"',title='"+book.getTitle()+"',category='"+book.getCategory()+"',author='"+book.getAuthor()+"',`date`='"+ut.dateFormatter(book.getDate())+"' WHERE bookId='"+bookId+"'"))?false:true;
    }

    @Override
    public ResultSet read(Book book) throws SQLException {
        return db.readData("SELECT FROM books_tbl WHERE bookId = '"+book.getBookId()+"'");
    }

    @Override
    public boolean delete(Book book) throws SQLException {
        return db.delete("DELETE FROM books_tbl WHERE bookId='"+book.getBookId()+"'");
    }

    @Override
    public ResultSet search(String sql) throws SQLException {
        return db.readData(sql);
    }
    @Override
    public ResultSet displayAll() throws SQLException {
        return this.search("select * from books_tbl");
    }
    public ResultSet displaySingle(int bookId) throws SQLException {
        return this.search("SELECT * FROM books_tbl WHERE bookId='"+bookId+"'");
    }
    public void deleteBook(int bookId) throws SQLException, ParseException, InvalidDateFormat {
        if (this.checkIfExists(bookId)){
            System.out.println("You are about to delete the book with the following details");
            ArrayList<Book> bk = new Utility().resulSetToObjects(this.displaySingle(bookId));
            Book book = bk.get(0);
            System.out.println(book);
            System.out.println("Do you want to proceed? y/n");
            Scanner sc = new Scanner(System.in);
            if (sc.nextLine().equalsIgnoreCase("y")){
                System.out.println((this.delete(book))?"Successfully deleted":"failed to delete");
            }
        }
        else {
            System.out.println("book With such Id not found");
        }
    }
    public void updateBook(int bookId) throws SQLException, ParseException, InvalidDateFormat {
        if (this.checkIfExists(bookId)){
            System.out.println("You are about to update the book with the following details");
            ArrayList<Book> bk = new Utility().resulSetToObjects(this.displaySingle(bookId));
            Book book = bk.get(0);
            System.out.println(book);
            System.out.println("Do you want to proceed? y/n");
            Scanner sc = new Scanner(System.in);
            if (sc.nextLine().equalsIgnoreCase("y")){
                Book newBook = new BookUi().updateBook();
                System.out.println((this.update(newBook,bookId))?"failed to update":"successfully updated");
            }
        }
        else {
            System.out.println("book not found");
        }
    }
    public boolean checkIfExists(int bookId) throws SQLException {
        ResultSet rs = db.readData("SELECT * FROM books_tbl WHERE bookId='"+bookId+"'");
        if (rs.next()){
            return true;
        }
        else {
            return false;
        }
//        boolean available = false;
//        Book book = new Book();
//        Utility ut = new Utility();
//        ArrayList<Book> list = new ArrayList<>();
//        list = ut.resulSetToObjects(this.displayAll());
//        for (Book bk : list){
//            String id = ut.IntegerToString(bk.getBookId());
//            if ((id).equals(bookId)){
//                System.out.println("found book record");
//            }
//            else {
//                System.out.println("not found");
//            }
//        }

    }
    ArrayList<Book> books = ut.resulSetToObjects(this.displayAll());
    public ArrayList<Book> sortById() throws SQLException, ParseException {
        ArrayList<Book> res = new ArrayList<>();
        Collections.sort(books);
        for (Book b : books){
            res.add(b);
        }
        return res;
    }
    public ArrayList<Book> sortByTitle() throws SQLException, ParseException {
        ArrayList<Book> res = new ArrayList<>();
        Collections.sort(books,Book.bookTitleComparator);
        for (Book b : books){
            res.add(b);
        }
        return res;
    }
    public ArrayList<Book> sortByCategory() throws SQLException, ParseException {
        ArrayList<Book> res = new ArrayList<>();
        Collections.sort(books);
        for (Book b : books){
            res.add(b);
        }
        return res;
    }
    public ArrayList<Book> sortByAuthor() throws SQLException, ParseException {
        ArrayList<Book> res = new ArrayList<>();
        Collections.sort(books);
        for (Book b : books){
            res.add(b);
        }
        return res;
    }
    public static Comparator<Book> bookTitleComparator = new Comparator<Book>() {

        @Override
        public int compare(Book book, Book t1) {
            String title1 = book.getTitle();
            String title2 = book.getTitle();
            return title2.compareTo(title1);
        }
    };
    public static Comparator<Book> bookCategoryComparator = new Comparator<Book>() {

        @Override
        public int compare(Book book, Book t1) {
            String category1 = book.getTitle();
            String category2 = book.getTitle();
            return category1.compareTo(category2);
        }
    };
}
