package ca.jantscan.test.beans;

import ca.jantscan.beans.JarManagerClassLoader;
import ca.jantscan.constants.Functions;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class JarManagerClassLoaderTest {

    @Test
    public void test() {
        String filename = this.getClass().getClassLoader().getResource("jantscan-1.0-SNAPSHOT.jar").getFile();
        File f = new File(filename);

        Assert.assertTrue(f.exists());
        Assert.assertTrue(f.canRead());

        try {
            JarManagerClassLoader jarManagerClassLoader = Functions.processJarFile.apply(filename);

            Assert.assertTrue(jarManagerClassLoader != null);
            Assert.assertTrue(jarManagerClassLoader.getJarManagerClassLoader() != null);
            Assert.assertTrue(jarManagerClassLoader.getJarManagerClassLoader().getURLs() != null);
            Assert.assertTrue(jarManagerClassLoader.getJarManagerClassLoader().getURLs().length > 0);
            Assert.assertTrue(jarManagerClassLoader.getClassNames() != null);
            Assert.assertTrue(jarManagerClassLoader.getClassNames().size() > 0);
        } catch (IOException e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void fail() {
        String filename = this.getClass().getClassLoader().getResource("LICENSE.jar").getFile();
        File f = new File(filename);

        Assert.assertTrue(f.exists());
        Assert.assertTrue(f.canRead());

        try {
            JarManagerClassLoader jarManagerClassLoader = Functions.processJarFile.apply(filename);

            Assert.assertTrue(false);
        } catch (IOException e) {
            Assert.assertTrue(true);
        }
    }
}
