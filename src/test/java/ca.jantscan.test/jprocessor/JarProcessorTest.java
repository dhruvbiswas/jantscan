package ca.jantscan.test.jprocessor;

import ca.jantscan.exception.JarProcessorException;
import ca.jantscan.jprocessor.JarProcessor;
import org.junit.Assert;
import org.junit.Test;

public class JarProcessorTest {

    private static void processJarFile(String filename) throws JarProcessorException {
        JarProcessor jarProcessor = new JarProcessor(filename);
        jarProcessor.init();
        jarProcessor.process();
    }

    @Test
    public void valid_jar_test() {
        String filename = this.getClass().getClassLoader().getResource("jantscan-1.0-SNAPSHOT.jar").getFile();

        try {
            JarProcessorTest.processJarFile(filename);
        } catch (JarProcessorException e) {
            // This should never happen for a valid jar file
            Assert.assertTrue(false);
        }
    }

    @Test
    public void not_a_jar_test() {
        String filename = this.getClass().getClassLoader().getResource("LICENSE.jar").getFile();

        try {
            JarProcessorTest.processJarFile(filename);

            Assert.assertTrue(false);
        } catch (JarProcessorException e) {
            // This should happen for not a jar file
            Assert.assertTrue(true);
        }
    }
}
