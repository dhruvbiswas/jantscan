package ca.jantscan.constants;

import java.io.IOException;

@FunctionalInterface
public interface IOExceptionCheckedBiFunction<T, U, V> {
    V apply(T t, U u) throws IOException;
}
