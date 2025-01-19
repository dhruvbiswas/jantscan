package ca.jantscan.test.annotations;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Service {

    @AliasFor(annotation = Component.class)
    String value() default "";

}
