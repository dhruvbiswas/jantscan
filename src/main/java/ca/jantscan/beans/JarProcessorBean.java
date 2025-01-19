package ca.jantscan.beans;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Set;

public class JarProcessorBean {
    private File jarFile;
    private List<URL> classUrls = null;
    private Set<String> classNames = null;

    public JarProcessorBean(File jarFile, List<URL> classUrls, Set<String> classNames) {
        this.jarFile = jarFile;
        this.classUrls = classUrls;
        this.classNames = classNames;
    }

    public File getJarFile() {
        return jarFile;
    }

    public void setJarFile(File jarFile) {
        this.jarFile = jarFile;
    }

    public List<URL> getClassUrls() {
        return classUrls;
    }

    public void setClassUrls(List<URL> classUrls) {
        this.classUrls = classUrls;
    }

    public Set<String> getClassNames() {
        return classNames;
    }

    public void setClassNames(Set<String> classNames) {
        this.classNames = classNames;
    }
}
