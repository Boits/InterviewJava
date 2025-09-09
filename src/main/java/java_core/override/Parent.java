package java_core.override;

public class Parent {

    private static final String me = "parent";
    String me2 = "parent";

    //виртуальный метод, который может быть переопределен в наследнике
    void method() {
        System.out.println("Parent");
    }

    void method(String s) {
        System.out.println("Parent");
    }

    //нельзя перегрузить с другим возвращаемым типом
//    String method(String s1) {
//        System.out.println("Parent");
//        return s1;
//    }

    void method(String s1, String s2) {
        System.out.println("Parent");
    }

    Object getMe() {
        return me;
    }

    Object getMe2() {
        return me2;
    }

    Parent getObject() {
        return new Parent();
    }
}
