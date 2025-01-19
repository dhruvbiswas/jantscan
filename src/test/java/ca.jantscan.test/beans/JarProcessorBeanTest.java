package ca.jantscan.test.beans;

import ca.jantscan.beans.JarProcessorBean;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;

public class JarProcessorBeanTest {

    @Test
    public void test() {
        JarProcessorBean jarProcessorBean = new JarProcessorBean(
                new File(this.getClass().getClassLoader().getResource("jantscan-1.0-SNAPSHOT.jar").getFile()),
                new ArrayList<>(),
                new HashSet<>());

        Assert.assertTrue(jarProcessorBean != null);

        Assert.assertTrue(jarProcessorBean.getJarFile().exists());
        Assert.assertTrue(jarProcessorBean.getJarFile().canRead());

        Assert.assertTrue(jarProcessorBean.getClassNames() != null);

        Assert.assertTrue(jarProcessorBean.getClassUrls() != null);
    }
}
