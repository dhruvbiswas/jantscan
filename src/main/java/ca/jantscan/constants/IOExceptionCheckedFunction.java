package ca.jantscan.constants;

import java.io.IOException;

@FunctionalInterface
public interface IOExceptionCheckedFunction<T, R> {
    R apply(T t) throws IOException;
}
