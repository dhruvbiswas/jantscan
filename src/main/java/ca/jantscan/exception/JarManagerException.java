package ca.jantscan.exception;

public class JarManagerException extends Exception {

    public JarManagerException() {
        super();
    }

    public JarManagerException(String message) {
        super(message);
    }

    public JarManagerException(Throwable t) {
        super(t);
    }

    public JarManagerException(String message, Throwable t) {
        super(message, t);
    }
}
