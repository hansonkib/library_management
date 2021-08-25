package utils;

import exceptions.InvalidDateFormatException;
import model.Book;
import model.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Utility {
    SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-YYYY");
    public String toCamelCase(String genderCase){
        return genderCase.substring(0,1).toUpperCase()+genderCase.substring(1);
    }
    public String dateFormatter(Date date){
        return sdf.format(date);
    }
    public Date dateParser(String date) throws InvalidDateFormatException {
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            throw new InvalidDateFormatException();
        }
    }
    public ArrayList<Book> resulSetToObjects(ResultSet rs) throws SQLException, ParseException, InvalidDateFormatException {
        Utility ut = new Utility();
        ArrayList<Book> records = new ArrayList<>();
        while (rs.next()){
            records.add(new Book(rs.getInt("bookId"),rs.getString("title"),rs.getString("category"),rs.getString("author"),ut.dateParser(rs.getString("date"))));
        }
        return records;
    }
    public ArrayList<Student> resulSetToStudentObjects(ResultSet rs) throws SQLException, ParseException {
        Utility ut = new Utility();
        ArrayList<Student> records = new ArrayList<>();
        while (rs.next()){
            records.add(new Student(rs.getInt("regNo"),rs.getString("name"),rs.getString("gender"),rs.getString("faculty")));
        }
        return records;
    }
    public String IntegerToString(int b){
        return String.valueOf(b);
    }
}
