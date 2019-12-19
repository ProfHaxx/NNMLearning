package exception;

public class InvalidConnectionException extends RuntimeException {
    public InvalidConnectionException() {
        super("Failed to build Connection => Invalid Connection");
    }
}
