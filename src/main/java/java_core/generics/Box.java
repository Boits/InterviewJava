package java_core.generics;

import java.util.List;

public class Box<T> {
    private final T item;

    Box(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }

    public void qwe(List<Integer> list) {
        list.add(1);
    }

    //Нельзя оверлодить
//    public void qwe(List<String> list) {
//        list.add("String");
//    }
}
