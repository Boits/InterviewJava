package java_core.class_types;

import java.util.ArrayList;
import java.util.List;

public final class ImmutableClass {

    /**
     * 1) Declare the class as final: This prevents other classes from subclassing it.
     * 2) Make all fields private and final: This ensures that the fields cannot be modified after the object is constructed.
     * 3) Do not provide setter methods: This prevents modification of the fields.
     * 4) Initialize fields via constructor: All fields should be assigned values through a constructor.
     * 5) If a field is a mutable object, return a copy:
     * If your class contains fields that refer to mutable objects,
     * you should return a copy of the object instead of the actual object.
     * (Верните копии изменяемых объектов, если они хранятся в полях,
     * вместо того чтобы возвращать ссылки на оригинальные объекты.)
     */

    private final String name;
    private final int id;
    private final List<String> hobbies; //нельзя менять ссылку, но можно состояние объекта.

    public ImmutableClass(String name, int id, List<String> hobbies) {
        this.name = name;
        this.id = id;
        this.hobbies = new ArrayList<>(hobbies); // Глубокое копирование списка
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public List<String> getHobbies() {
        // Return a new object to maintain immutability
        return new ArrayList<>(hobbies); // Возвращаем копию, а не оригинал
    }
}
