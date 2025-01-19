package ca.jantscan.constants;

import ca.jantscan.beans.JarManagerClassLoader;
import ca.jantscan.beans.JarProcessorBean;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Functions {

    public static IOExceptionCheckedFunction<String, JarManagerClassLoader> processJarFile = (path) -> {
        JarManagerClassLoader jarManagerClassLoader = null;
        File file = new File(path);

        if (file.exists() && file.canRead()) {
            JarProcessorBean jarProcessorBean = new JarProcessorBean(file, new ArrayList<>(), new HashSet<>());

            Functions.processJar(jarProcessorBean);

            jarManagerClassLoader = new JarManagerClassLoader(
                    new URLClassLoader(jarProcessorBean.getClassUrls().toArray(new URL[0])),
                    jarProcessorBean.getClassNames());
        } else {
            throw new FileNotFoundException("File " + path + " does not exist");
        }

        return jarManagerClassLoader;
    };

    public static void processJar(JarProcessorBean jarProcessorBean) throws IOException {
        jarProcessorBean.getClassUrls().add(jarProcessorBean.getJarFile().toURI().toURL());

        try (JarFile jar = new JarFile(jarProcessorBean.getJarFile())) {
            Enumeration<JarEntry> entries = jar.entries();

            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();

                String name = entry.getName();
                System.out.println("jarentry: " + name);

                // Handle nested JARs
                if (name.endsWith(".jar")) {
                    // extract nested jar into temp file
                    File tempJar = extractNestedJarToTempFile.apply(jar, entry);

                    // Depth first and process nested jar
                    jarProcessorBean.setJarFile(tempJar);
                    processJar(jarProcessorBean);
                }

                // Found a class file
                if (name.endsWith(".class")) {
                    String className = name.replace("/", ".").replace(".class", "");
                    jarProcessorBean.getClassNames().add(className);
                }
            }
        }
    }

    public static IOExceptionCheckedBiFunction<JarFile, JarEntry, File> extractNestedJarToTempFile = (jar, entry) -> {
        File tempFile = File.createTempFile("nested", ".jar");
        tempFile.deleteOnExit();

        try (InputStream is = jar.getInputStream(entry); FileOutputStream fos = new FileOutputStream(tempFile)) {
            byte[] buffer = new byte[1024];

            int read;
            while ((read = is.read(buffer)) != -1) {
                fos.write(buffer, 0, read);
            }
        }

        return tempFile;
    };

    public static Function<Integer, String> intToString = (e -> {
        if (e != null) {
            return Integer.toString(e);
        } else {
            return null;
        }
    });

    public static Function<Map<String, List<Annotation>>, String> annotationMapToString = (m -> {
        StringBuilder builder = new StringBuilder();

        if (m != null && m.entrySet().size() > 0) {
            m.entrySet().stream().forEach(e -> {
                builder.append(e.getKey() + ":");

                builder.append("[");

                e.getValue().stream().forEach(v -> {
                    builder.append(v.annotationType().getName() + ",");
                });

                builder.append("]" + System.lineSeparator());
            });
        } else {
            builder.append("None" + System.lineSeparator());
        }
        return builder.toString();
    });

    public static BiFunction<String, List<Annotation>, String> classAnnotationListToString = (s, l) -> {
        StringBuilder builder = new StringBuilder();

        if (l != null && l.size() > 0) {
            builder.append(s + ":");
            builder.append("[");
            l.stream().forEach(a -> {
                builder.append(a.annotationType().getName() + ",");
            });
            builder.append("]" + System.lineSeparator());
        } else {
            builder.append("None" + System.lineSeparator());
        }

        return builder.toString();
    };

}
