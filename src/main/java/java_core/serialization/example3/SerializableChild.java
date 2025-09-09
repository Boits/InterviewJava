package java_core.serialization.example3;

import java.io.Serializable;

public class SerializableChild extends NonSerializableSuperClass implements Serializable {
    public int childValue;

    public SerializableChild(int childValue) {
        System.out.println("Конструктор SerializableChild вызван");
        this.childValue = childValue;
    }
}