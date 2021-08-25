package exceptions;

public class InvalidDateFormatException extends Exception {
    public InvalidDateFormatException() {
        super("Invalid date entered");
    }
}
