package ca.jantscan.test.beans;

import ca.jantscan.test.annotations.Autowired;
import ca.jantscan.test.annotations.Autowired2;
import ca.jantscan.test.annotations.Component;
import ca.jantscan.test.annotations.Value;

@Component
public class SpringComponent {

    @Autowired
    protected Bean1 bean1;

    @Autowired
    protected Bean2 bean2;

    @Value("100")
    private Integer someInteger;

    public SpringComponent() {super();}


    public SpringComponent(@Autowired Bean1 bean1) {
        this.bean1 = bean1;
    }

    public SpringComponent(@Autowired @Autowired2 Bean2 bean2) {
        this.bean2 = bean2;
    }

    public Bean1 getBean1() {
        return bean1;
    }

    public void setBean1(@Value("100") @Autowired @Autowired2 Bean1 bean1) {
        this.bean1 = bean1;
    }

    public Bean2 getBean2() {
        return bean2;
    }

    public void setBean2(Bean2 bean2) {
        this.bean2 = bean2;
    }
}
