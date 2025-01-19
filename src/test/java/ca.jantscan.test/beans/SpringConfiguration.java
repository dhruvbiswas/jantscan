package ca.jantscan.test.beans;

import ca.jantscan.test.annotations.Bean;
import ca.jantscan.test.annotations.Configuration;

@Configuration
public class SpringConfiguration {

    // Test an annotation on a method
    @Bean(destroyMethod = "someMethod")
    public Bean1 getBean1() {
        return new Bean1();
    }

    @Bean(destroyMethod = "someMethod")
    public Bean2 getBean2() {
        return new Bean2();
    }
}
