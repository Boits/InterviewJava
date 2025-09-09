package java_core.serialization.example1;

import java.io.Serializable;

public class FieldOfTargetClass implements Serializable {

    private static final long serialVersionUID = 1L;
    private int fieldInt;
    private String fieldString;

    public FieldOfTargetClass(int i, String s) {
        this.fieldInt = i;
        this.fieldString = s;
    }

    @Override
    public String toString() {
        return "{FieldOfTargetClass = " +
                "fieldId = " + fieldInt +
                ", fieldString='" + fieldString + "'}";
    }
}