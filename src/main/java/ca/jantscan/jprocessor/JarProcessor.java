package ca.jantscan.jprocessor;

import ca.jantscan.beans.DiscoveredAnnotations;
import ca.jantscan.beans.JarManagerClassLoader;
import ca.jantscan.caprocessor.ClassAnnotationProcessor;
import ca.jantscan.constants.Functions;
import ca.jantscan.exception.JarProcessorException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
                    System.out.println("Running class.forName on " + className);
                    Class clazz = Class.forName(className,
                            true,
                            this.jarManagerClassLoader.getJarManagerClassLoader());

                    System.out.println("Discovering annotations for class: " + clazz.getName());
                    this.discoveredAnnotationsList.add(classAnnotationProcessor.process(clazz));
                } catch (Exception e) {
                    throw new JarProcessorException(e.getMessage(), e);
                }
            }

        } else {
            throw new JarProcessorException("No classes to scan");
        }
    }
}
