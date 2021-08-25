package utils;

import Exceptions.UnableToLoadDriverClass;

import java.sql.*;

public class DbUtil {
    private Connection conn;
    private Statement stm;
    private final String url = "jdbc:mysql://localhost:3306/libraryDb";
    private final String username = "root";
    private final String password = "";
    private final String className = "com.mysql.jdbc.Driver";

    public DbUtil() throws SQLException, UnableToLoadDriverClass {
        this.conn = DriverManager.getConnection(url, username, password);
        this.stm = conn.createStatement();
        try {
            Class.forName(className);
        }catch (Exception e){
            throw new UnableToLoadDriverClass();
        }
    }

    public int create(String sql) throws SQLException {
        return stm.executeUpdate(sql);
    }
    public ResultSet readData(String sql) throws SQLException {
        return stm.executeQuery(sql);
    }
    public boolean update(String sql) throws SQLException {
        return (stm.executeUpdate(sql) == 0)?false:true;
    }
    public boolean delete(String sql) throws SQLException {
        return (stm.executeUpdate(sql) == 0)?false:true;
    }
    public void getCount(String sql){

    }

    /**
     * @deprecated
     */
    @Override
    protected void finalize() throws Throwable {
        this.closeConnection();
    }

    private void closeConnection() throws SQLException {
        stm.close();
        conn.close();
    }
}
