package ca.jantscan.jmanager;

import ca.jantscan.exception.JarManagerException;
import ca.jantscan.exception.JarProcessorException;
import ca.jantscan.jprocessor.JarProcessor;

public class JarManager {
    private final JarProcessor jarProcessor;

    public JarManager(String jarPath) {
        this.jarProcessor = new JarProcessor(jarPath);
    }

    public void init() throws JarManagerException {
        try {
            this.jarProcessor.init();
        } catch (JarProcessorException e) {
            throw new JarManagerException(e.getMessage(), e);
        }
    }

    public void process() throws JarManagerException {
        try {
            this.jarProcessor.process();
        } catch (Exception e) {
            throw new JarManagerException(e.getMessage(), e);
        }
    }

    public void report() {
        this.jarProcessor.getDiscoveredAnnotationsList().stream().forEach(e -> {
            System.out.println(e.toString());
        });
    }
}
