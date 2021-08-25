package controller;

import exceptions.InvalidDateFormatException;
import exceptions.UnableToLoadDriverClass;
import interfaces.LogicI;
import model.Book;
import model.Librarian;
import model.Student;
import utils.DbUtil;
import utils.Utility;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class LibrarianLogics implements LogicI<Librarian> {
    DbUtil db = new DbUtil();

    public LibrarianLogics() throws SQLException, UnableToLoadDriverClass {
    }

    @Override
    public boolean add(Librarian librarian) throws SQLException {
        return (db.create("INSERT INTO librarian_tbl VALUES('"+librarian.getSsNO()+"','"+librarian.getName()+"','"+librarian.getGender()+"','"+librarian.getPassword()+"')")==0)?false:true;

    }

    @Override
    public boolean update(Librarian librarian,int SSNO) throws SQLException {
        return (db.update("UPDATE librarian_tbl SET ssNo='"+librarian.getSsNO()+"',name='"+librarian.getName()+"',gender='"+librarian.getGender()+"',faculty='"+librarian.getPassword()+"' WHERE ssNo='"+SSNO+"'"))?false:true;
    }

    @Override
    public ResultSet read(Librarian librarian) throws SQLException {
        return db.readData("SELECT FROM librarian_tbl WHERE ssNo = '"+librarian.getSsNO()+"'");
    }

    @Override
    public boolean delete(Librarian librarian) throws SQLException {
        return db.delete("DELETE FROM librarian_tbl WHERE regNo='"+librarian.getSsNO()+"'");
    }

    @Override
    public ResultSet search(String sql) throws SQLException {
        return db.readData(sql);
    }

    @Override
    public ResultSet displayAll() throws SQLException {
        return this.search("select * from librarian_tbl");
    }

    public ResultSet displaySingle(int ssNo) throws SQLException {
        return this.search("SELECT * FROM librarian_tbl WHERE ssNo='"+ssNo+"'");
    }

    private boolean checkIfExists(int ssNo) throws SQLException {
        ResultSet rs = db.readData("SELECT * FROM librarian_tbl WHERE ssNo='"+ssNo+"'");
        if (rs.next()){
            return true;
        }
        else {
            return false;
        }
    }
    private boolean login(int ssNo, String pass) throws SQLException {
        boolean res = false;
        ResultSet rs = db.readData("SELECT * FROM librarian_tbl WHERE ssNo='"+ssNo+"' AND password= '"+pass+"'");
        if (rs.next()){
            res = true;
        }else {
            res = false;
            System.out.println("Wrong password please try again");
        }
        return res;
    }
    public boolean issueBooks(int regNo, int bookId) throws SQLException, UnableToLoadDriverClass, ParseException, InvalidDateFormatException {
        BookLogics bl = new BookLogics();
        StudentLogics sl = new StudentLogics();
        DbUtil db = new DbUtil();
        if (sl.checkIfExists(regNo)){
            ArrayList<Student> student = new Utility().resulSetToStudentObjects(new StudentLogics().displaySingle(regNo));
            Student studen = student.get(0);
            if (bl.checkIfExists(bookId)){
                ArrayList<Book> bk = new Utility().resulSetToObjects(new BookLogics().displaySingle(bookId));
                Book book = bk.get(0);
                return (db.create("INSERT INTO borrowedBooks_tbl VALUES('"+studen.getRegNo()+"','"+studen.getName()+"','"+book.getBookId()+"','"+book.getTitle()+"')") ==0)?false:true;
            }else {
                System.out.println("Book with such Id not found");
            }
        }
        else {
            System.out.println("Student with such registration not found");
        }
        return false;
    }
}
