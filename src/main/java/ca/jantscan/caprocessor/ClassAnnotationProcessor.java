package ca.jantscan.caprocessor;

import ca.jantscan.beans.DiscoveredAnnotations;
import ca.jantscan.constants.Functions;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ClassAnnotationProcessor implements IClassAnnotationProcessor {

    public DiscoveredAnnotations process(Class clazz) {
        DiscoveredAnnotations discoveredAnnotations = new DiscoveredAnnotations();

        System.out.println("Processing class: " + clazz.getName());
        discoveredAnnotations.setClassname(clazz.getName());

        // Discover class level annotation
        Annotation[] cl_annotations = clazz.getAnnotations();
        if (cl_annotations != null && cl_annotations.length > 0) {
            // Found class level annotation
            for (int i = 0; i < cl_annotations.length; i++) {
                discoveredAnnotations.getClassLevelAnnotationsList().add(cl_annotations[i]);
            }
        }

        // Discover class-attribute level annotation
        ClassFieldResolver classFieldResolver = new ClassFieldResolver();

        List<Field> declaredFields = new ArrayList<>();

        classFieldResolver.getClassFields(clazz, new Stack<>(), declaredFields);

        if (declaredFields != null && declaredFields.size() > 0) {
            for (int i = 0; i < declaredFields.size(); i++) {
                Field f = declaredFields.get(i);

                System.out.println("Discovered Field: " + f.getName());

                Annotation[] fieldAnnotations = f.getDeclaredAnnotations();
                if (fieldAnnotations != null && fieldAnnotations.length > 0) {
                    for (int j = 0 ; j < fieldAnnotations.length; j++) {
                        if (discoveredAnnotations.getClassAttributeAnnotationMap().containsKey(f.getName())) {
                            // we already discovered this attribute and we have multiple field annotations associated
                            // with this attribute
                            // Add the discovered annotation to the existing list
                            discoveredAnnotations.getClassAttributeAnnotationMap().get(f.getName()).add(fieldAnnotations[j]);
                        } else {
                            // we encountered this field for the first time, lets start collecting annotations
                            // for this field.
                            // Initialize a new list
                            List<Annotation> l = new ArrayList<>();
                            // Add the discovered annotation to the list
                            l.add(fieldAnnotations[j]);
                            // Add the list to this field
                            discoveredAnnotations.getClassAttributeAnnotationMap().put(f.getName(), l);
                        }
                    }
                }
            }
        }

        // Discover constructor annotations
        Constructor[] constructors = clazz.getConstructors();
        for (int i = 0; i < constructors.length; i++) {
            // All constructors would come with the same name
            // so we need a naming convention to map out constructors based on param count
            Constructor constructor = constructors[i];

            String cName = constructor.getName() + "_" + Functions.intToString.apply(constructor.getParameterCount());
            System.out.println("Discovered constructor: " + cName);

            // Check if the constructor has been annotated
            Annotation[] constructorMethodAnnotations = constructor.getDeclaredAnnotations();

            for (int j = 0 ; j < constructorMethodAnnotations.length; j++) {
                if (discoveredAnnotations.getClassConstructorAnnotationsMap().containsKey(cName)) {
                    // we already discovered this constructor and we have multiple field annotations associated
                    // with this constructor
                    // Add the discovered annotation to the existing list
                    discoveredAnnotations.getClassConstructorAnnotationsMap().get(cName).add(constructorMethodAnnotations[j]);
                } else {
                    // we encountered this constructor for the first time, lets start collecting annotations
                    // for this constructor.
                    // Initialize a new list
                    List<Annotation> l = new ArrayList<>();
                    // Add the discovered annotation to the list
                    l.add(constructorMethodAnnotations[j]);
                    // Add the list to this constructor
                    discoveredAnnotations.getClassConstructorAnnotationsMap().put(cName, l);
                }
            }

            // For each constructor we now discover constructor param annotations
            Annotation[][] constructorParameterAnnotations = constructor.getParameterAnnotations();

            for (int j = 0; j < constructorParameterAnnotations.length; j++) {

                if (constructorParameterAnnotations[j] != null && constructorParameterAnnotations[j].length > 0) {

                    if (discoveredAnnotations.getClassConstructorParamAnnotationMap().containsKey(cName)) {
                        // we already discovered this constructor and we have multiple constructor param annotations associated
                        // with this constructor
                        // Add the discovered annotation to the existing list
                        for (int k = 0; k < constructorParameterAnnotations[j].length; k++) {
                            discoveredAnnotations.getClassConstructorParamAnnotationMap().get(cName).add(constructorParameterAnnotations[j][k]);
                        }
                    } else {
                        // we encountered this constructor for the first time, lets start collecting annotations
                        // for all parameters in this constructor.
                        // Initialize a new list
                        List<Annotation> l = new ArrayList<>();
                        for (int k = 0; k < constructorParameterAnnotations[j].length; k++) {
                            // Add all discovered annotation to the list
                            l.add(constructorParameterAnnotations[j][k]);
                        }

                        // Add the list to this constructor
                        discoveredAnnotations.getClassConstructorParamAnnotationMap().put(cName, l);
                    }
                }
            }
        }

        // Discover methods (public and inherited)
        Method[] methods = clazz.getMethods();
        for (int i = 0 ; i < methods.length; i++) {
            Method method = methods[i];

            int paramCount = method.getParameterCount();

            String mName = method.getName() + "_" + Functions.intToString.apply(paramCount);

            System.out.println("Discovered method: " + mName);

            // Check if the method has been annotated
            Annotation[] methodAnnotations = method.getDeclaredAnnotations();
            for (int j = 0 ; j < methodAnnotations.length; j++) {
                if (discoveredAnnotations.getClassMethodAnnotationMap().containsKey(mName)) {
                    // we already discovered this method and we have multiple method param annotations associated
                    // in this method
                    // Add the discovered annotation to the existing list
                    discoveredAnnotations.getClassMethodAnnotationMap().get(mName).add(methodAnnotations[j]);
                } else {
                    // we encountered this method for the first time, lets start collecting annotations
                    // for this method
                    // Initialize a new list
                    List<Annotation> l = new ArrayList<>();
                    // Add the discovered annotation to the list
                    l.add(methodAnnotations[j]);
                    // Add the list to this method
                    discoveredAnnotations.getClassMethodAnnotationMap().put(mName, l);
                }
            }

            // For each method we now discover method parameter annotations
            Annotation[][] methodParameterAnnotations = method.getParameterAnnotations();
            for (int j = 0; j < methodParameterAnnotations.length; j++) {

                if (methodParameterAnnotations[j] != null && methodParameterAnnotations[j].length > 0) {

                    if (discoveredAnnotations.getClassMethodParameterAnnotationMap().containsKey(mName)) {
                        // we already discovered this method and we have multiple method param annotations inside
                        // this method
                        // Add the discovered annotation to the existing list
                        for (int k = 0; k < methodParameterAnnotations[j].length; k++) {
                            discoveredAnnotations.getClassMethodParameterAnnotationMap().get(mName).add(methodParameterAnnotations[j][k]);
                        }
                    } else {
                        // we encountered this method for the first time, lets start collecting annotations
                        // for all parameters in this method.
                        // Initialize a new list
                        List<Annotation> l = new ArrayList<>();
                        for (int k = 0; k < methodParameterAnnotations[j].length; k++) {
                            // Add all discovered annotation to the list
                            l.add(methodParameterAnnotations[j][k]);
                        }

                        // Add the list to this method
                        discoveredAnnotations.getClassMethodParameterAnnotationMap().put(mName, l);
                    }
                }
            }
        }


        return discoveredAnnotations;
    }
}
