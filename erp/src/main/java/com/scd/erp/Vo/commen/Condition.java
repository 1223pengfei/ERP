package com.scd.erp.Vo.commen;


import java.io.Serializable;

public class Condition implements Serializable {

    private String mainClass;
    private Object value;
    private Integer type;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Condition{");
        sb.append("mainClass='").append(mainClass).append('\'');
        sb.append(", value=").append(value);
        sb.append(", type=").append(type);
        sb.append(", realValue=").append(realValue());
        sb.append(", MainType='").append(MainType()).append('\'');
        sb.append(", SubType='").append(SubType()).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Object getValue() {
          return value;
        }

    public Object realValue(){
        if (null != type)
        switch (type) {
            case 1:
                return value + "%";

            case 2:
                return "%" + value;

            case 3:
                return "%" + value + "%";

            default:
                return value;
        }
        return value;
    }

    public void setValue(Object value) {

        this.value = value;
    }

    public Condition(String mainClass, Object value) {
        this.mainClass = mainClass;
        this.value = value;
    }

    public String getMainClass() {
        return mainClass;
    }

    public void setMainClass(String mainClass) {
        this.mainClass = mainClass;
    }


    public Condition() {
    }

    public String MainType(){
       return this.mainClass.split("_")[0];
    }
    public String SubType(){
        return this.mainClass.split("_")[1];
    }

}
