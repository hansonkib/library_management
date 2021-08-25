import Exceptions.InvalidDateFormat;
import Exceptions.UnableToLoadDriverClass;
import view.MainUi;

import java.sql.SQLException;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws SQLException, UnableToLoadDriverClass, ParseException, InvalidDateFormat {
        MainUi mainUi = new MainUi();
        mainUi.userMenu();
    }
}
