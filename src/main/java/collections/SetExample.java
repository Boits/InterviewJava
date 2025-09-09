package collections;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

public class SetExample {

    public static void method() {
//        hashSet();
//        operationsHashSet();
//        enumSet();
//        linkedHashSet();
        treeSet();
    }

    private static void hashSet() {
        Set<String> hashSet = new HashSet<>();
        boolean a1 = hashSet.add("A");
        System.out.println(a1); //true
        hashSet.add("B");
        boolean a2 = hashSet.add("A");//ошибки не будет
        System.out.println(a2); //false
        hashSet.add("C");
        hashSet.add(null);
        hashSet.add(null);

        String check = "A";
        System.out.println("Contains " + check + " " + hashSet.contains(check)); //true

        System.out.println(hashSet); //[null, A, B, C]

        Iterator<String> iterator = hashSet.iterator();
        iterator.forEachRemaining(System.out::print);//nullABC
    }

    private static void operationsHashSet() {
        Set<Integer> a = new HashSet<>();
        a.addAll(Arrays.asList(new Integer[]{1, 3, 2, 4, 8, 9, 0}));
        System.out.println("HashSet a: " + a); //[0, 1, 2, 3, 4, 8, 9]

        Set<Integer> b = new HashSet<>();
        b.addAll(Arrays.asList(new Integer[]{1, 3, 7, 5, 4, 0, 7, 5}));
        System.out.println("HashSet b: " + b); //[0, 1, 3, 4, 5, 7]

        // To find union
        Set<Integer> union = new HashSet<>(a);
        union.addAll(b);
        System.out.println("Union of the two Set: " + union); //[0, 1, 2, 3, 4, 5, 7, 8, 9]

        // To find intersection - пересечения
        Set<Integer> intersection = new HashSet<>(a);
        intersection.retainAll(b);
        System.out.println("Intersection of the two Set: " + intersection); //[0, 1, 3, 4]

        // To find the symmetric difference
        Set<Integer> difference = new HashSet<>(a);
        difference.removeAll(b);
        System.out.println("Difference of the two Set: " + difference); //[2, 8, 9]
    }

    enum Student {Geek1, Geek2, Geek3, Geek4, Geek5}

    enum Code {CODE, LEARN, CONTRIBUTE, QUIZ, MCQ}

    ;

    private static void enumSet() {
        EnumSet<Student> enumSet = EnumSet.of(Student.Geek1, Student.Geek2, Student.Geek3);
        System.out.println("EnumSet: " + enumSet); //[Geek1, Geek2, Geek3]

        EnumSet<Code> s1, s2, s3, s4;
        // Adding elements
        s1 = EnumSet.of(Code.QUIZ, Code.CONTRIBUTE, Code.LEARN, Code.CODE); //[CODE, LEARN, CONTRIBUTE, QUIZ]
        s2 = EnumSet.complementOf(s1); //[MCQ]
        s3 = EnumSet.allOf(Code.class); //[CODE, LEARN, CONTRIBUTE, QUIZ, MCQ]
        s4 = EnumSet.range(Code.CODE, Code.CONTRIBUTE); //[CODE, LEARN, CONTRIBUTE]

        // Printing corresponding elements in Sets
        System.out.println("EnumSet 1: " + s1);
        System.out.println("EnumSet 2: " + s2);
        System.out.println("EnumSet 3: " + s3);
        System.out.println("EnumSet 4: " + s4);
    }

    private static void linkedHashSet() {
        Set<String> lhs = new LinkedHashSet<>();
        lhs.add("A");
        lhs.add("B");
        lhs.add("C");
        lhs.add(null);

        Iterator<String> iterator = lhs.iterator();

        while (iterator.hasNext())
            System.out.print(iterator.next() + ", "); //A, B, C, null,
    }

    private static void treeSet() {
        NavigableSet<String> ts = new TreeSet<>();
        ts.add("AA");
//        ts.add(null);//NPE
        ts.add("QQ");
        ts.add("Geek");
        ts.add("BB");
        ts.add("Geeks");
        System.out.println("TreeSet: " + ts); //[AA, BB, Geek, Geeks, QQ]

        String valueGeeks = "Geeks";

        System.out.println("Contains: " + valueGeeks + " " + ts.contains(valueGeeks)); //true

        System.out.println("First Value: " + ts.first()); //AA
        System.out.println("Last Value: " + ts.last()); //QQ

        String valueGeek = "Geek";

        // Find the values just greater and smaller than the above string
        System.out.println("Higher: " + ts.higher(valueGeek)); //Geeks
        System.out.println("Lower: " + ts.lower(valueGeek)); //BB

        for (String value : ts)
            System.out.print(value + ", "); //AA, BB, Geek, Geeks, QQ,
    }
}
