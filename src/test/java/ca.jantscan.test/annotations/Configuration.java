package ca.jantscan.test.annotations;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Configuration {

    @AliasFor(annotation = Component.class)
    String value() default "";

    /**
     *
     * Kindly Note, this method has not been implemented yet in our custom
     * Spring like container core
     *
     * Specify whether {@code @Bean} methods should get proxied in order to enforce
     * bean lifecycle behavior, e.g. to return shared singleton bean instances even
     * in case of direct {@code @Bean} method calls in user code. This feature
     * requires method interception, implemented through a runtime-generated CGLIB
     * subclass which comes with limitations such as the configuration class and
     * its methods not being allowed to declare {@code final}.
     * <p>The default is {@code true}, allowing for 'inter-bean references' via direct
     * method calls within the configuration class as well as for external calls to
     * this configuration's {@code @Bean} methods, e.g. from another configuration class.
     * If this is not needed since each of this particular configuration's {@code @Bean}
     * methods is self-contained and designed as a plain factory method for container use,
     * switch this flag to {@code false} in order to avoid CGLIB subclass processing.
     * <p>Turning off bean method interception effectively processes {@code @Bean}
     * methods individually like when declared on non-{@code @Configuration} classes,
     * a.k.a. "@Bean Lite Mode" (see {@link Bean @Bean's javadoc}). It is therefore
     * behaviorally equivalent to removing the {@code @Configuration} stereotype.
     * @since 5.2
     */
    boolean proxyBeanMethods() default true;

}
