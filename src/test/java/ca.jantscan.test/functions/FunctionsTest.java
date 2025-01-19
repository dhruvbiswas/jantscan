package ca.jantscan.test.functions;

import ca.jantscan.beans.JarManagerClassLoader;
import ca.jantscan.constants.Functions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class FunctionsTest {

    private String fPath;

    @Before
    public void setup() {
        this.fPath = this.getClass().getClassLoader().getResource("jantscan-1.0-SNAPSHOT.jar").getFile();
    }

    @Test
    public void test() {
        File f = new File(this.fPath);

        Assert.assertTrue(f.exists());
        Assert.assertTrue(f.canRead());

        try {
            JarManagerClassLoader jarManagerClassLoader = Functions.processJarFile.apply(this.fPath);
            Assert.assertTrue(jarManagerClassLoader != null);
            Assert.assertTrue(jarManagerClassLoader.getClassNames() != null);
            Assert.assertTrue(jarManagerClassLoader.getClassNames().size() > 0);

            Assert.assertTrue(jarManagerClassLoader.getJarManagerClassLoader() != null);
            Assert.assertTrue(jarManagerClassLoader.getJarManagerClassLoader().getURLs() != null);

            System.out.println("Number of classes discovered: " + jarManagerClassLoader.getClassNames().size());
            System.out.println("Number of URLs in classloader: " + jarManagerClassLoader.getJarManagerClassLoader().getURLs().length);
        } catch (IOException e) {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testIntToString() {
        Assert.assertTrue(Functions.intToString.apply(10).equals("10"));
        Assert.assertTrue(Functions.intToString.apply(0).equals("0"));
        Assert.assertNull(Functions.intToString.apply(null));
    }
}
