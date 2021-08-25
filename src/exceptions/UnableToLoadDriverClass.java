package exceptions;

public class UnableToLoadDriverClass extends Exception{
    public UnableToLoadDriverClass() {
        super("Unable to load mysql jdbc driver class");
    }
}
