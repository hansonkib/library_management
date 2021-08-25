package interfaces;

import model.Book;
import model.Student;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface LogicI <T>{
    boolean add(T t) throws SQLException;
    boolean update(T t, int bookId) throws SQLException;

    ResultSet read(T t) throws SQLException;
    boolean delete(T t) throws SQLException;
    ResultSet search(String sql) throws SQLException;

    ResultSet displayAll() throws SQLException;
}
