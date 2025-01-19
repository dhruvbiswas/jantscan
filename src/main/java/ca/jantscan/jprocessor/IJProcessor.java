package ca.jantscan.jprocessor;

import ca.jantscan.exception.JarProcessorException;

public interface IJProcessor {
    public void init() throws JarProcessorException;
    public void process() throws JarProcessorException;

}
