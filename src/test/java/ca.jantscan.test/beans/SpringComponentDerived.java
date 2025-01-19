package ca.jantscan.test.beans;

import ca.jantscan.test.annotations.Autowired;
import ca.jantscan.test.annotations.Component;
import ca.jantscan.test.annotations.Value;

@Component
public class SpringComponentDerived extends SpringComponent {

    @Autowired
    private Bean3 bean3;

    @Autowired
    @Value("1234")
    private Bean4 bean4;

    @Value("100")
    private Integer someInteger2;

    public SpringComponentDerived() {}

    public Bean3 getBean3() {
        return bean3;
    }

    public void setBean3(Bean3 bean3) {
        this.bean3 = bean3;
    }

    public Integer getSomeInteger2() {
        return someInteger2;
    }

    public void setSomeInteger2(Integer someInteger2) {
        this.someInteger2 = someInteger2;
    }

    public Bean4 getBean4() {
        return bean4;
    }

    public void setBean4(Bean4 bean4) {
        this.bean4 = bean4;
    }
}
