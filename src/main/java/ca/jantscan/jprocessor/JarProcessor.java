package ca.jantscan.jprocessor;

import ca.jantscan.beans.DiscoveredAnnotations;
import ca.jantscan.beans.JarManagerClassLoader;
import ca.jantscan.caprocessor.ClassAnnotationProcessor;
import ca.jantscan.constants.Functions;
import ca.jantscan.exception.JarProcessorException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JarProcessor implements IJProcessor {
    private final String jarPath;
    private JarManagerClassLoader jarManagerClassLoader;
    private List<DiscoveredAnnotations> discoveredAnnotationsList = new ArrayList<>();

    public JarProcessor(String jarPath) {
        this.jarPath = jarPath;
    }

    public List<DiscoveredAnnotations> getDiscoveredAnnotationsList() {
        return discoveredAnnotationsList;
    }

    @Override
    public void init() throws JarProcessorException {
        try {
            // Process jar file to create an instance of
            // jar manager class loader
            this.jarManagerClassLoader = Functions.processJarFile.apply(this.jarPath);
        } catch (IOException e) {
            throw new JarProcessorException(e.getMessage(), e);
        }
    }

    @Override
    public void process() throws JarProcessorException {
        if (jarManagerClassLoader != null
                && jarManagerClassLoader.getClassNames() != null
                && jarManagerClassLoader.getClassNames().size() > 0) {

            ClassAnnotationProcessor classAnnotationProcessor =  new ClassAnnotationProcessor();

            for (String className : this.jarManagerClassLoader.getClassNames()) {
                try {
                    // System.out.println("Running class.forName on: " + className);

                    Class clazz = Class.forName(className,
                            true,
                            this.jarManagerClassLoader.getJarManagerClassLoader());

                    this.discoveredAnnotationsList.add(classAnnotationProcessor.process(clazz));
                } catch (ClassNotFoundException e) {
                    System.out.println("WARN: class not found " + className + " ....SKIPPING");
                } catch (Exception e) {
                    System.out.println(e.getMessage() + className + " ....SKIPPING");
                }
            }
        } else {
            throw new JarProcessorException("No classes to scan");
        }
    }
}
