package ca.jantscan.test.beans;

public class Bean2 {
    private String bean2String;
    private Integer bean2Integer;

    public Bean2() {}

    public Bean2(String bean2String, Integer bean2Integer) {
        this.bean2String = bean2String;
        this.bean2Integer = bean2Integer;
    }

    public String getBean2String() {
        return bean2String;
    }

    public void setBean2String(String bean2String) {
        this.bean2String = bean2String;
    }

    public Integer getBean2Integer() {
        return bean2Integer;
    }

    public void setBean2Integer(Integer bean2Integer) {
        this.bean2Integer = bean2Integer;
    }
}
