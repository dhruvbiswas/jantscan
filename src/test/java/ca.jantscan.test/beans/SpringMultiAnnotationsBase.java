package ca.jantscan.test.beans;

import ca.jantscan.test.annotations.Bean;
import ca.jantscan.test.annotations.Value;

public class SpringMultiAnnotationsBase {
    @Value("300")
    protected Integer integerBase;

    public SpringMultiAnnotationsBase(Integer integerBase) {
        this.integerBase = integerBase;
    }

    @Bean(destroyMethod = "")
    protected Integer method1() {
        return new Integer(100);
    }

    @Bean(destroyMethod = "")
    public Integer method2() {
        return new Integer(100);
    }
}
