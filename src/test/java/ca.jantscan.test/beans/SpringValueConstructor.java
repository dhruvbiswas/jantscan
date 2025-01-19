package ca.jantscan.test.beans;

import ca.jantscan.test.annotations.Component;
import ca.jantscan.test.annotations.Value;

@Component
public class SpringValueConstructor {
    private Integer someInt;

    public SpringValueConstructor(@Value("100") Integer someInt) {
        this.someInt = someInt;
    }

    public Integer getSomeInt() {
        return someInt;
    }
}
