package ca.jantscan.exception;

public class JarProcessorException extends Exception {

    public JarProcessorException() {
        super();
    }

    public JarProcessorException(String message) {
        super(message);
    }

    public JarProcessorException(Throwable t) {
        super(t);
    }

    public JarProcessorException(String message, Throwable t) {
        super(message, t);
    }
}
