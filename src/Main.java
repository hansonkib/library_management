import exceptions.InvalidDateFormatException;
import exceptions.UnableToLoadDriverClass;
import view.MainUi;

import java.sql.SQLException;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws SQLException, UnableToLoadDriverClass, ParseException, InvalidDateFormatException {
        MainUi mainUi = new MainUi();
        mainUi.userMenu();
    }
}
