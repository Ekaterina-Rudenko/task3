package by.epam.task3.exception;

public class GemException extends Exception{
    public GemException() {}

    public GemException(String message) {
        super(message);
    }

    public GemException(String message, Exception e) {
        super(message, e);
    }

    public GemException(Exception e) {
        super(e);
    }
}
