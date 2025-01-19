package ca.jantscan.beans;
/**
 * Java bean that holds the following
 * classname as String
 * List of annotations discovered at class level
 * Map of List of annotations discovered at field level
 * Map of List of annotations discovered at constructor level
 * Map of List of annotations discovered at field ethod level
 *
 * @author D.Biswas
 */
import ca.jantscan.constants.Functions;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiscoveredAnnotations {
    private String classname;
    private List<Annotation> classLevelAnnotationsList = new ArrayList<>();
    private Map<String, List<Annotation>> classAttributeAnnotationMap = new HashMap<>();
    private Map<String, List<Annotation>> classConstructorAnnotationsMap = new HashMap<>();
    private Map<String, List<Annotation>> classConstructorParamAnnotationMap = new HashMap<>();
    private Map<String, List<Annotation>> classMethodAnnotationMap = new HashMap<>();
    private Map<String, List<Annotation>> classMethodParameterAnnotationMap = new HashMap<>();

    /**
     * returns classname of the class that was used to discover annotations
     *
     * @return classname as string of the class that was used to discover annotation
     */
    public String getClassname() {
        return classname;
    }

    /**
     * sets classname of the class that was used to discover annotations
     *
     * @param classname  the classname parameter
     */
    public void setClassname(String classname) {
        this.classname = classname;
    }

    /**
     * returns list of annotations discovered at class level
     *
     * @return list of annotations discovered at class level
     */
    public List<Annotation> getClassLevelAnnotationsList() {
        return classLevelAnnotationsList;
    }

    public void setClassLevelAnnotationsList(List<Annotation> classLevelAnnotationsList) {
        this.classLevelAnnotationsList = classLevelAnnotationsList;
    }

    public Map<String, List<Annotation>> getClassAttributeAnnotationMap() {
        return classAttributeAnnotationMap;
    }

    public void setClassAttributeAnnotationMap(Map<String, List<Annotation>> classAttributeAnnotationMap) {
        this.classAttributeAnnotationMap = classAttributeAnnotationMap;
    }

    public Map<String, List<Annotation>> getClassConstructorAnnotationsMap() {
        return classConstructorAnnotationsMap;
    }

    public void setClassConstructorAnnotationsMap(Map<String, List<Annotation>> classConstructorAnnotationsMap) {
        this.classConstructorAnnotationsMap = classConstructorAnnotationsMap;
    }

    public Map<String, List<Annotation>> getClassConstructorParamAnnotationMap() {
        return classConstructorParamAnnotationMap;
    }

    public void setClassConstructorParamAnnotationMap(Map<String, List<Annotation>> classConstructorParamAnnotationMap) {
        this.classConstructorParamAnnotationMap = classConstructorParamAnnotationMap;
    }

    public Map<String, List<Annotation>> getClassMethodAnnotationMap() {
        return classMethodAnnotationMap;
    }

    public void setClassMethodAnnotationMap(Map<String, List<Annotation>> classMethodAnnotationMap) {
        this.classMethodAnnotationMap = classMethodAnnotationMap;
    }

    public Map<String, List<Annotation>> getClassMethodParameterAnnotationMap() {
        return classMethodParameterAnnotationMap;
    }

    public void setClassMethodParameterAnnotationMap(Map<String, List<Annotation>> classMethodParameterAnnotationMap) {
        this.classMethodParameterAnnotationMap = classMethodParameterAnnotationMap;
    }

    /**
     * Generates a String representation of discovered annotations
     *
     * @return string representation of discovered annotations
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(System.lineSeparator());
        builder.append("--------------------------------------------" + System.lineSeparator());
        builder.append("Scanned: " + this.classname + System.lineSeparator());

        builder.append("--Class Annotation--" + System.lineSeparator());
        builder.append(Functions.classAnnotationListToString.apply(this.classname, this.classLevelAnnotationsList));

        builder.append("--Field--" + System.lineSeparator());
        builder.append(Functions.annotationMapToString.apply(this.classAttributeAnnotationMap));

        Map<String, Boolean> visitedConstructors = new HashMap<>();
        builder.append("--Constructor--" + System.lineSeparator());
        if (this.getClassConstructorAnnotationsMap() != null && this.getClassConstructorAnnotationsMap().entrySet().size() > 0) {
            this.getClassConstructorAnnotationsMap().entrySet().stream().forEach(e -> {
                builder.append(e.getKey() + ":");
                builder.append("[");
                e.getValue().stream().forEach(v -> {
                    builder.append(v.annotationType().getName() + ",");
                });
                builder.append("]" + System.lineSeparator());

                // Check if this constructor has parameter annotations defined
                if (this.getClassConstructorParamAnnotationMap() != null
                        && this.getClassConstructorParamAnnotationMap().containsKey(e.getKey())) {
                    builder.append(e.getKey() + "_constructor_parameter: ");

                    builder.append("[");

                    this.getClassConstructorParamAnnotationMap().get(e.getKey()).stream().forEach(v -> {
                        builder.append(v.annotationType().getName() + ",");
                    });

                    builder.append("]" + System.lineSeparator());
                } else {
                    builder.append(e.getKey() + "_constructor_parameter: []" + System.lineSeparator());
                }

                visitedConstructors.put(e.getKey(), true);
            });

        } else {
            builder.append("method-level annotated constructors, found none" + System.lineSeparator());
        }

        // Check if we have constructors that have constructor param level annotation only
        // that is those constructors that do not have constructor-method-level annotation
        if (this.getClassConstructorParamAnnotationMap() != null && this.getClassConstructorParamAnnotationMap().entrySet().size() > 0) {
            this.getClassConstructorParamAnnotationMap().entrySet().stream().forEach(e -> {
                if (!visitedConstructors.containsKey(e.getKey())) {
                    builder.append(e.getKey() + "_constructor_parameter: ");
                    builder.append("[");
                    e.getValue().stream().forEach(v -> {
                        builder.append(v.annotationType().getName() + ",");
                    });
                    builder.append("]" + System.lineSeparator());
                }
            });
        } else {
            builder.append("only-method-parameter annotated constructors, found none" + System.lineSeparator());
        }

        Map<String, Boolean> visitedMethods = new HashMap<>();
        builder.append("--Methods--" + System.lineSeparator());
        if (this.getClassMethodAnnotationMap() != null && this.getClassMethodAnnotationMap().entrySet().size() > 0) {
            this.getClassMethodAnnotationMap().entrySet().stream().forEach(e -> {
                builder.append(e.getKey() + ":");

                builder.append("[");

                e.getValue().stream().forEach(v -> {
                    builder.append(v.annotationType().getName() + ",");
                });

                builder.append("]" + System.lineSeparator());

                // Check if this method has parameter annotations defined
                if (this.getClassMethodParameterAnnotationMap() != null
                        && this.getClassMethodParameterAnnotationMap().containsKey(e.getKey())) {
                    builder.append(e.getKey() + "_method_parameter: ");

                    builder.append("[");

                    this.getClassMethodParameterAnnotationMap().get(e.getKey()).stream().forEach(v -> {
                        builder.append(v.annotationType().getName() + ",");
                    });

                    builder.append("]" + System.lineSeparator());
                }

                visitedMethods.put(e.getKey(), true);
            });

        } else {
            builder.append("method-level annotated methods, found none" + System.lineSeparator());
        }

        // Check if we have methods that have method param level annotation only
        // that is those methods that do not have method-level annotations on the method
        if (this.getClassMethodParameterAnnotationMap() != null && this.getClassMethodParameterAnnotationMap().entrySet().size() > 0) {
            this.getClassMethodParameterAnnotationMap().entrySet().stream().forEach(e -> {
                if (!visitedMethods.containsKey(e.getKey())) {
                    builder.append(e.getKey() + "_method_parameter: ");
                    builder.append("[");
                    e.getValue().stream().forEach(v -> {
                        builder.append(v.annotationType().getName() + ",");
                    });
                    builder.append("]" + System.lineSeparator());
                }
            });
        } else {
            builder.append("only-method-parameter annotated methods, found none" + System.lineSeparator());
        }

        builder.append("------------------------------------------" + System.lineSeparator());
        return builder.toString();
    }
}
