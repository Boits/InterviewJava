package collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class MapExample {

    public static void method() {
        hashMap();
        linkedHashMap();
        identityHashMap();
    }

    private static void hashMap() {
        System.out.println("=============== HashMap ===============");
        Map<Integer, String> hashMap = new HashMap<>();
        hashMap.put(1, "Name1");
        hashMap.put(2, "Name2");
        hashMap.put(3, "Name3");
        hashMap.put(1, "Name4"); //перезапишет значение Name1

        String name2 = hashMap.get(2);
        System.out.println(name2); //Name2

        hashMap.remove(2);

        Set<Integer> keys = hashMap.keySet();
        System.out.println("Ключи: " + keys); //Ключи: [1, 3]

        List<String> values = new ArrayList<>(hashMap.values());
        System.out.println("Значения: " + values); //[Name4, Name3]

        for (Map.Entry entry: hashMap.entrySet()) {
            System.out.println("Пара: " + entry);
        }

        System.out.println("HashMap: " + hashMap); //{1=Name4, 3=Name3}
    }

    private static void linkedHashMap() {
        System.out.println("=============== LinkedHashMap ===============");
        Map<Integer, String> map = new LinkedHashMap<>(16, 0.75f, true);
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");

        System.out.println(map); // {1=one, 2=two, 3=three}

        map.get(2); // доступ к ключу 2
        System.out.println(map); // {1=one, 3=three, 2=two}

        map.get(1);
        System.out.println(map); // {3=three, 2=two, 1=one}
    }

    private static void identityHashMap() {
        System.out.println("=============== IdentityHashMap ===============");
        Map<String, String> map = new IdentityHashMap<>();

        String a = new String("key");
        String b = new String("key");

        map.put(a, "value1");
        map.put(b, "value2");

        System.out.println(map.size()); // 2 !!!
    }

}
