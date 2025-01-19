package ca.jantscan.test.beans;

import ca.jantscan.test.annotations.Autowired;
import ca.jantscan.test.annotations.Autowired2;
import ca.jantscan.test.annotations.AutowiredInParam;
import ca.jantscan.test.annotations.Bean;
import ca.jantscan.test.annotations.Component;
import ca.jantscan.test.annotations.ComponentScan;
import ca.jantscan.test.annotations.Configuration;
import ca.jantscan.test.annotations.Service;
import ca.jantscan.test.annotations.Value;

@ComponentScan(pkg = "ca.jantscan.test.beans")
@Component
@Service
@Configuration
public class SpringMultiAnnotations extends SpringMultiAnnotationsBase {

    private final Bean1 bean1;

    private Integer integer1;

    @Value("200")
    private Integer integer2;

    @Value("300")
    private static Integer integer3;

    @Autowired
    @Autowired2
    public SpringMultiAnnotations(Bean1 bean1) {
        super(100);
        this.bean1 = bean1;
    }

    @Autowired
    @Autowired2
    public SpringMultiAnnotations(@Autowired Bean1 bean1, Integer integer1, Integer integer2) {
        super(100);
        this.bean1 = bean1;
        this.integer1 = integer1;
        this.integer2 = integer2;
    }

    @Autowired
    @Autowired2
    public SpringMultiAnnotations(@Autowired @AutowiredInParam Bean1 bean1, @Value("100") Integer integer1) {
        super(100);
        this.bean1 = bean1;
        this.integer1 = integer1;
    }

    @Bean(destroyMethod = "someMethod")
    public Bean2 getBean2() {
        return new Bean2();
    }

    @Autowired
    @Autowired2
    @Bean(destroyMethod = "someMethod")
    public Integer getInteger2() {
        return integer2;
    }

    public void setInteger2(Integer integer2) {
        this.integer2 = integer2;
    }

    @Bean(destroyMethod = "someMethod")
    public void methodWithParamAnnotations(@Autowired @AutowiredInParam Bean1 bean1,
                                           @Value("100") Integer integer1) {
        System.out.println("");
    }

    @Bean(destroyMethod = "someMethod")
    public void methodWithParamAnnotations(@Autowired Bean1 bean1,
                                           Integer integer1,
                                           Integer integer2) {
        System.out.println("");
    }

    @Autowired
    @Autowired2
    @Bean(destroyMethod = "someMethod")
    public void methodWithParamAnnotations() {
        System.out.println("");
    }

    @Bean(destroyMethod = "someMethod")
    public static Integer getInteger3() {
        return SpringMultiAnnotations.integer3;
    }
}
