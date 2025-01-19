package ca.jantscan.caprocessor;

import ca.jantscan.beans.DiscoveredAnnotations;

public interface IClassAnnotationProcessor {
    public DiscoveredAnnotations process(Class clazz);
}
