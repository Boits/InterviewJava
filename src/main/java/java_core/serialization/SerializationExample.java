package java_core.serialization;

import java_core.serialization.example1.FieldOfTargetClass;
import java_core.serialization.example1.Target;
import java_core.serialization.example2.FieldOfTargetClass2;
import java_core.serialization.example2.Target2;
import java_core.serialization.example3.SerializableChild;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Дискриптор - объект класса SerializationExample
 * Именно в нём выполняется создание десериализуемого объекта и восстановление его состояния.
 * Он содержит поля, описывающие наш сериализуемый класс.
 */
public class SerializationExample {

    public static void method() {
//        example1();
//        example2();

        //При наследовании
        example3();
    }


    /**
     * Запись в файл
     * <p>
     * {Target = integerField: 112, stringField: bzzz, transientField: 52, customObjectField: {FieldOfTargetClass = fieldId = 13, fieldString='Friday'}}
     * writeObject was executed
     * readObject was executed
     * {Target = integerField: 112, stringField: bzzz, transientField: 52, customObjectField: {FieldOfTargetClass = fieldId = 13, fieldString='Friday'}}
     */
    private static void example1() {
        Target target = new Target(112, "bzzz",
                new FieldOfTargetClass(13, "Friday"), 52);
        System.out.println(target);

        serialize(target, "ser_obj");
        Target result = (Target) deserialize("ser_obj");
        System.out.println(result);
    }

    /**
     * {Target2 = integerField: 112, stringField: bzzz,
     * customObjectField: {FieldOfTargetClass2 = fieldId = 13, fieldString='Friday'},
     * transientField: 52'}
     * <p>
     * {Target2 = integerField: 112, stringField: bzzz,
     * customObjectField: {FieldOfTargetClass2 = fieldId = 13, fieldString='Friday'},
     * transientField: 52'}
     */
    private static void example2() {
        Target2 target = new Target2(112, "bzzz",
                new FieldOfTargetClass2(13, "Friday"), 52);
        System.out.println(target);

        serialize(target, "ser_obj2");
        Target2 result = (Target2) deserialize("ser_obj2");
        System.out.println(result);
    }

    /**
     * Конструктор NonSerializableSuperClass вызван
     * Конструктор SerializableChild вызван
     * childValue до десериализации: 42
     * superValue до десериализации: 100
     * Конструктор NonSerializableSuperClass вызван
     * childValue после десериализации: 42
     * superValue после десериализации: 100
     * <p>
     * Сериализация: В файл сохранится только childValue, потому что superValue принадлежит несериализуемому классу.
     * Десериализация:
     * childValue будет восстановлен.
     * superValue не был сохранен, поэтому вызовется конструктор NonSerializableSuperClass, и superValue снова станет 100.
     * <p>
     * Если родительский класс НЕ сериализуемый,
     * то его состояние не было сохранено, и Java создает его заново,
     * вызвав его конструктор.
     */
    public static void example3() {
        SerializableChild obj = new SerializableChild(42);
        //Конструктор NonSerializableSuperClass вызван
        //Конструктор SerializableChild вызван
        System.out.println("childValue до десериализации: " + obj.childValue); //42
        System.out.println("superValue до десериализации: " + obj.superValue); //100

        serialize(obj, "ser_obj3");
        SerializableChild result = (SerializableChild) deserialize("ser_obj3"); //Конструктор NonSerializableSuperClass вызван
        System.out.println("childValue после десериализации: " + result.childValue); //42
        System.out.println("superValue после десериализации: " + result.superValue); //100
    }

    private static void serialize(Object target, String nameFile) {
        FileOutputStream fileOutput = null;
        try {
            fileOutput = new FileOutputStream(nameFile); //записывает байтовое представление нашего объекта в файл.
            var objectOutput = new ObjectOutputStream(fileOutput); //осуществляет сериализацию
            objectOutput.writeObject(target); //метод, вызываемый при сериализации. В нём осуществляется запись данных в поток;
            fileOutput.flush();
            objectOutput.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Object deserialize(String fileName) {
        FileInputStream fileInput = null;
        Object deserializeTarget = null;
        try {
            fileInput = new FileInputStream(fileName);
            var objectInput = new ObjectInputStream(fileInput);
            deserializeTarget = objectInput.readObject(); //метод, вызываемый при десериализации.
            // В нём осуществляется инициализация полей объекта значениями из потока.
            fileInput.close();
            objectInput.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException();
        }
        return deserializeTarget;
    }
}
