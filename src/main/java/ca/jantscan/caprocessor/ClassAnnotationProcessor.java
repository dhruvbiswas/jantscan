package ca.jantscan.caprocessor;

import ca.jantscan.beans.DiscoveredAnnotations;
import ca.jantscan.constants.AnnotationFunctions;
import ca.jantscan.constants.Functions;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ClassAnnotationProcessor implements IClassAnnotationProcessor {

    /*
     * Discovers class, class attribute, constructor, constructor parameter, method,
     * method parameter annotations
     * Since a jar has a flatnamespace of classes this method does not do
     * a depth first traversal of the class hierarchy on nested class attributes
     *
     * @param clazz a Class representation of the entity being scanned
     * @return DiscoveredAnnotation instance that contains all discovered annotations
     */
    public DiscoveredAnnotations process(Class clazz) {
        DiscoveredAnnotations discoveredAnnotations = new DiscoveredAnnotations();

        System.out.println("Scanning class: " + clazz.getName());
        discoveredAnnotations.setClassname(clazz.getName());

        // Discover class level annotation
        discoveredAnnotations.setClassLevelAnnotationsList(AnnotationFunctions.discoverClassAnnotations.apply(clazz));

        // Discover class-attribute level annotation
        discoveredAnnotations.setClassAttributeAnnotationMap(AnnotationFunctions.discoverClassAttributeAnnotations.apply(clazz));

        // Discover constructor annotations
        AnnotationFunctions.discoverClassConstructorAnnotations.apply(clazz, discoveredAnnotations);

        // Discover method annotations
        AnnotationFunctions.discoverClassMethodAnnotations.apply(clazz, discoveredAnnotations);

        return discoveredAnnotations;
    }
}
