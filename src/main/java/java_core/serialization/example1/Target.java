package java_core.serialization.example1;

import java_core.serialization.example1.FieldOfTargetClass;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Target implements Serializable {

    private static final long serialVersionUID = 1L;
    private int intField;
    private String strField;
    private FieldOfTargetClass field;
    private transient int transField;

    public Target(int i, String s, FieldOfTargetClass f, int trn) {
        this.intField = i;
        this.strField = s;
        this.field = f;
        this.transField = trn;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        System.out.println("writeObject was executed");
        out.defaultWriteObject();
        out.writeInt(transField);
    }

    /**
     * считывать поля в методе readObject нужно в том же порядке,
     * в котором мы их записывали в методе writeObject.
     */
    private void readObject(ObjectInputStream input) throws IOException, ClassNotFoundException {
        System.out.println("readObject was executed");
        input.defaultReadObject();
        this.transField = input.readInt();
    }

    @Override
    public String toString() {
        return "{Target = " +
                "integerField: " + intField +
                ", stringField: " + strField +
                ", transientField: " + transField +
                ", customObjectField: " + field.toString() + "}";
    }
}