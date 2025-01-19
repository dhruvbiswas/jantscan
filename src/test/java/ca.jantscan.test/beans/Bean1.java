package ca.jantscan.test.beans;

public class Bean1 {
    private String bean1String;
    private Integer bean1Integer;

    public Bean1() {}

    public Bean1(String bean1String, Integer bean1Integer) {
        this.bean1String = bean1String;
        this.bean1Integer = bean1Integer;
    }

    public String getBean1String() {
        return bean1String;
    }

    public void setBean1String(String bean1String) {
        this.bean1String = bean1String;
    }

    public Integer getBean1Integer() {
        return bean1Integer;
    }

    public void setBean1Integer(Integer bean1Integer) {
        this.bean1Integer = bean1Integer;
    }
}
