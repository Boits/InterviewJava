package java_core.class_types;

import java.util.List;

public class ClassTypesExample {

    public static void innerExternalClass() {
        // Создание экземпляра статического вложенного класса
        ExternalClass.StaticNestedClass nestedObject = new ExternalClass.StaticNestedClass();
        nestedObject.display();

        // Создание объекта внутреннего класса через объект внешнего класса
        ExternalClass external = new ExternalClass();
        ExternalClass.InnerClass inner = external.new InnerClass();
        inner.display();

        external.someMethod(); // Local class

        external.createAnonymousClass(); //Anonymous Class
    }
    public static void immutableClass() {
        //нельзя менять поля после создания (ни в setter, ни в методах)
        ImmutableClass ic = new ImmutableClass("NAME", 1, List.of("hobby1", "hobby2"));
        List<String> hobbies = ic.getHobbies();
        System.out.println(hobbies);//[hobby1, hobby2]
        hobbies.add("hobby3");
        System.out.println(hobbies); //[hobby1, hobby2, hobby3] - копия, не оригинальный объект
        System.out.println(ic.getHobbies()); //[hobby1, hobby2]
    }

    public static void helperClass() {
        HelperClass hc = new HelperClass("senderEmail", "senderName");
        HelperClass.isValidEmail("");
        hc.sendEmail("", "", "");
    }

    public static void utilClass() {
        //Util class
        UtilClass.staticMethod("static parametrized method");
        UtilClass.staticMethod(1.1);
    }
}
