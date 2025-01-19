package ca.jantscan.beans;

import java.net.URLClassLoader;
import java.util.Set;

public class JarManagerClassLoader {
    private final Set<String> classNames;
    private final URLClassLoader jarManagerClassLoader;

    public JarManagerClassLoader(URLClassLoader jarManagerClassLoader,
                                 Set<String> classNames) {
        this.jarManagerClassLoader = jarManagerClassLoader;
        this.classNames = classNames;
    }

    public Set<String> getClassNames() {
        return classNames;
    }

    public URLClassLoader getJarManagerClassLoader() {
        return jarManagerClassLoader;
    }

}
