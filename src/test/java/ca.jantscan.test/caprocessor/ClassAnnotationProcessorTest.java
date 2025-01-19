package ca.jantscan.test.caprocessor;

import ca.jantscan.beans.DiscoveredAnnotations;
import ca.jantscan.caprocessor.ClassAnnotationProcessor;
import org.junit.Assert;
import org.junit.Test;

public class ClassAnnotationProcessorTest {

    @Test
    public void testSpringComponentAnnotation() {
        try {
            Class clazz = Class.forName("ca.jantscan.test.beans.SpringComponent");

            ClassAnnotationProcessor classAnnotationProcessor = new ClassAnnotationProcessor();

            DiscoveredAnnotations discoveredAnnotations = classAnnotationProcessor.process(clazz);

            Assert.assertTrue(discoveredAnnotations.getClassLevelAnnotationsList() != null);
            Assert.assertTrue(discoveredAnnotations.getClassLevelAnnotationsList().size() == 1);

            Assert.assertTrue(discoveredAnnotations.getClassLevelAnnotationsList().get(0) != null);
            String expectedAnnotation = "ca.jantscan.test.annotations.Component";
            String actualAnnotation = discoveredAnnotations.getClassLevelAnnotationsList().get(0).annotationType().getName();
            Assert.assertTrue(actualAnnotation.equals(expectedAnnotation));

            System.out.println(discoveredAnnotations.toString());

        } catch (ClassNotFoundException e) {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testMultiAnnotationsAtClassLevel() {
        try {
            Class clazz = Class.forName("ca.jantscan.test.beans.SpringMultiAnnotations");

            ClassAnnotationProcessor classAnnotationProcessor = new ClassAnnotationProcessor();

            DiscoveredAnnotations discoveredAnnotations = classAnnotationProcessor.process(clazz);

            Assert.assertTrue(discoveredAnnotations.getClassLevelAnnotationsList() != null);
            Assert.assertTrue(discoveredAnnotations.getClassLevelAnnotationsList().size() == 4);

            Assert.assertTrue(discoveredAnnotations.getClassLevelAnnotationsList().get(0) != null);
            String expectedAnnotation = "ca.jantscan.test.annotations.ComponentScan";
            String actualAnnotation = discoveredAnnotations.getClassLevelAnnotationsList().get(0).annotationType().getName();
            Assert.assertTrue(actualAnnotation.equals(expectedAnnotation));

            Assert.assertTrue(discoveredAnnotations.getClassLevelAnnotationsList().get(1) != null);
            expectedAnnotation = "ca.jantscan.test.annotations.Component";
            actualAnnotation = discoveredAnnotations.getClassLevelAnnotationsList().get(1).annotationType().getName();
            Assert.assertTrue(actualAnnotation.equals(expectedAnnotation));

            Assert.assertTrue(discoveredAnnotations.getClassLevelAnnotationsList().get(2) != null);
            expectedAnnotation = "ca.jantscan.test.annotations.Service";
            actualAnnotation = discoveredAnnotations.getClassLevelAnnotationsList().get(2).annotationType().getName();
            Assert.assertTrue(actualAnnotation.equals(expectedAnnotation));

            Assert.assertTrue(discoveredAnnotations.getClassLevelAnnotationsList().get(3) != null);
            expectedAnnotation = "ca.jantscan.test.annotations.Configuration";
            actualAnnotation = discoveredAnnotations.getClassLevelAnnotationsList().get(3).annotationType().getName();
            Assert.assertTrue(actualAnnotation.equals(expectedAnnotation));

            Assert.assertTrue(discoveredAnnotations.getClassAttributeAnnotationMap() != null
                    && discoveredAnnotations.getClassAttributeAnnotationMap().entrySet().size() == 3);

            Assert.assertTrue(discoveredAnnotations.getClassConstructorAnnotationsMap() != null
                    && discoveredAnnotations.getClassConstructorAnnotationsMap().entrySet().size() == 3);

            System.out.println(discoveredAnnotations.toString());
        } catch (ClassNotFoundException e) {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testSpringComponentDerived() {
        try {
            Class clazz = Class.forName("ca.jantscan.test.beans.SpringComponentDerived");

            ClassAnnotationProcessor classAnnotationProcessor = new ClassAnnotationProcessor();

            DiscoveredAnnotations discoveredAnnotations = classAnnotationProcessor.process(clazz);

            Assert.assertTrue(discoveredAnnotations.getClassLevelAnnotationsList() != null);
            Assert.assertTrue(discoveredAnnotations.getClassLevelAnnotationsList().size() == 1);

            Assert.assertTrue(discoveredAnnotations.getClassLevelAnnotationsList().get(0) != null);
            String expectedAnnotation = "ca.jantscan.test.annotations.Component";
            String actualAnnotation = discoveredAnnotations.getClassLevelAnnotationsList().get(0).annotationType().getName();
            Assert.assertTrue(actualAnnotation.equals(expectedAnnotation));

            System.out.println(discoveredAnnotations.toString());
        } catch (ClassNotFoundException e) {
            Assert.assertTrue(false);
        }
    }
}
