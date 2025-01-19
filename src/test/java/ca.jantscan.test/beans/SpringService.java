package ca.jantscan.test.beans;

import ca.jantscan.test.annotations.Autowired;
import ca.jantscan.test.annotations.Service;

@Service
public class SpringService {
    private final Bean1 bean1;
    private final Bean2 bean2;

    // Our hypothetical Dependency Injection framework
    // also supports Autowired in constructor
    public SpringService(@Autowired Bean1 bean1, @Autowired Bean2 bean2) {
        this.bean1 = bean1;
        this.bean2 = bean2;
    }

    public Bean1 getBean1() {
        return bean1;
    }

    public Bean2 getBean2() {
        return bean2;
    }
}
