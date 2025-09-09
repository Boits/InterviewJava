package java_core.serialization.example2;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Target2 implements Externalizable {
    private static final long serialVersionUID = 1L;
    private int integerField;
    private String stringField;
    private FieldOfTargetClass2 field;
    private transient int transientField;

    public Target2() {
    }

    public Target2(int i, String s, FieldOfTargetClass2 f, int trn) {
        this.integerField = i;
        this.stringField = s;
        this.field = f;
        this.transientField = trn;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(integerField);
        out.writeUTF(stringField);
        out.writeObject(field);
        out.writeInt(transientField);
    }

    /**
     * чтение полей осуществляется в том же порядке,
     * в котором мы их записываем в поток.
     * Поскольку мы сериализуем поля вручную,
     * записывая их значения в поток, поле transient мы тоже можем сериализовать.
     */

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.integerField = in.readInt();
        this.stringField = in.readUTF();
        this.field = (FieldOfTargetClass2) in.readObject();
        this.transientField = in.readInt();
    }

    @Override
    public String toString() {
        return "{Target2 = " +
                "integerField: " + integerField +
                ", stringField: " + stringField +
                ", customObjectField: " + field +
                ", transientField: " + transientField + "'}";
    }
}