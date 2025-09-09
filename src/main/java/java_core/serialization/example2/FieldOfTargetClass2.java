package java_core.serialization.example2;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class FieldOfTargetClass2 implements Externalizable {
    private static final long serialVersionUID = 1L;

    private int fieldId;
    private String fieldString;

    public FieldOfTargetClass2() { }

    public FieldOfTargetClass2(int i, String s) {
        this.fieldId = i;
        this.fieldString = s;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(fieldId);
        out.writeUTF(fieldString);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException {
        this.fieldId = in.readInt();
        this.fieldString = in.readUTF();
    }

    @Override
    public String toString() {
        return "{FieldOfTargetClass2 = " +
                "fieldId = " + fieldId +
                ", fieldString='" + fieldString + "'}";
    }
}