package java_core.serialization.example3;

public class NonSerializableSuperClass {
    public int superValue;

    public NonSerializableSuperClass() {
        System.out.println("Конструктор NonSerializableSuperClass вызван");
        this.superValue = 100; // Будет переинициализирован после десериализации
    }
}
