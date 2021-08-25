package controller;

import Exceptions.InvalidDateFormat;
import Exceptions.UnableToLoadDriverClass;
import interfaces.LogicI;
import model.Student;
import utils.DbUtil;
import utils.Utility;
import view.StudentUi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentLogics implements LogicI<Student> {
    DbUtil db = new DbUtil();
    Utility ut = new Utility();

    public StudentLogics() throws SQLException, UnableToLoadDriverClass {
    }

    @Override
    public boolean add(Student student) throws SQLException {
        return (db.create("INSERT INTO students_tbl VALUES('"+student.getRegNo()+"','"+student.getName()+"','"+student.getGender()+"','"+student.getFaculty()+"')")==0)?false:true;

    }

    @Override
    public boolean update(Student student,int regNo) throws SQLException {
        return (db.update("UPDATE students_tbl SET regNo='"+student.getRegNo()+"',name='"+student.getName()+"',gender='"+student.getGender()+"',faculty='"+student.getFaculty()+"' WHERE regNo='"+regNo+"'"))?false:true;
    }

    @Override
    public ResultSet read(Student student) throws SQLException {
        return db.readData("SELECT FROM students_tbl WHERE regNo = '"+student.getRegNo()+"'");
    }

    @Override
    public boolean delete(Student student) throws SQLException {
        return db.delete("DELETE FROM students_tbl WHERE regNo='"+student.getRegNo()+"'");
    }

    @Override
    public ResultSet search(String sql) throws SQLException {
        return db.readData(sql);
    }

    @Override
    public ResultSet displayAll() throws SQLException {
        return this.search("select * from students_tbl");
    }
    public void deleteStudent(int regNo) throws SQLException, ParseException {
        if (this.checkIfExists(regNo)){
            System.out.println("You are about to delete the student with the following details");
            ArrayList<Student> stud = new Utility().resulSetToStudentObjects(this.displaySingle(regNo));
            Student student = stud.get(0);
            System.out.println(student);
            System.out.println("Do you want to proceed? y/n");
            Scanner sc = new Scanner(System.in);
            if (sc.nextLine().equalsIgnoreCase("y")){
                System.out.println((this.delete(student))?"Successfully deleted":"failed to delete");
            }
            else {
                System.exit(0);
            }
        }
        else {
            System.out.println("book With such Id not found");
        }
    }
    public void updateStudent(int regNO) throws SQLException, ParseException {
        if (this.checkIfExists(regNO)){
            System.out.println("You are about to update the student with the following details");
            ArrayList<Student> student = new Utility().resulSetToStudentObjects(this.displaySingle(regNO));
            Student stude = student.get(0);
            System.out.println(stude);
            System.out.println("Do you want to proceed? y/n");
            Scanner sc = new Scanner(System.in);
            if (sc.nextLine().equalsIgnoreCase("y")){
                Student newStudent = new StudentUi().updateStudent();
                System.out.println((this.update(newStudent,regNO))?"failed to update":"successfully updated");
            }
        }
        else {
            System.out.println("Student not found");
        }
    }

    public ResultSet displaySingle(int regNo) throws SQLException {
        return this.search("SELECT * FROM students_tbl WHERE regNo='"+regNo+"'");
    }

    public boolean checkIfExists(int regNo) throws SQLException {
        ResultSet rs = db.readData("SELECT * FROM students_tbl WHERE regNo='"+regNo+"'");
        if (rs.next()){
            return true;
        }
        else {
            return false;
        }
    }
    public boolean returnBook(int regNo, int bookId) throws SQLException, UnableToLoadDriverClass, ParseException, InvalidDateFormat {
        if (this.checkIfExists(regNo)){
            if (new BookLogics().checkIfExists(bookId)){
                ResultSet rs = db.readData("SELECT * FROM borrowedBooks_tbl WHERE regNo='"+regNo+"' AND bookId='"+bookId+"'");
                if (rs.next()){
                    return db.delete("DELETE FROM borrowedBooks_tbl WHERE regNo='"+regNo+"' AND bookId='"+bookId+"'");
                }else {
                    System.out.println("The book id you entered is not the one you had borrowed");
                }
            }
            else {
                System.out.println("You have entered incorrect BookId");
            }
        }
        else {
            System.out.println("you have entered incorrect registration number please re-enter");
        }
        return false;
    }
}
