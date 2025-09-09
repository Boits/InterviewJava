package collections.comparator_comparable;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorComparableExample {

    public static void method() {
        comparableExample(); //Естественный порядок
        comparatorExample();
    }

    private static void comparableExample() {
        System.out.println("Comparable: ");
        Student student = new Student(1, "John Doe", "Java");
        Student student2 = new Student(2, "Jane Doe", "Java");
        Student student3 = new Student(3, "Mike", "Java");
        List<Student> students = Arrays.asList(student, student2, student3);
        System.out.println(students); //в порядке вставки

        Collections.sort(students);
        System.out.println(students); //отсортированный по именам студентов
    }

    private static void comparatorExample() {
        System.out.println("Comparator: ");
        Student student = new Student(1, "Jane", "Dev-Java");
        Student student2 = new Student(2, "John", "Dev-React");
        Student student3 = new Student(3, "Mike", "BA");
        Student student4 = new Student(4, "Boris", "Analyst");
        Student student5 = new Student(5, "Alise", "Designer");
        List<Student> students = Arrays.asList(student, student2, student3, student4, student5);
        System.out.println("Without sort: " + students); //1, 2, 3, 4, 5

        students.sort(new StudentDeptComparator());
        System.out.println("Sort by dept: " + students); //4, 3, 5, 1, 2
        //или by dept
        students.sort(Comparator.comparing(Student::getDepartment));
        System.out.println("Sort by dept: " + students); //4, 3, 5, 1, 2

        students.sort(new StudentNameComparator());
        System.out.println("Sort by name: " + students); //5, 4, 1, 2, 3
        //или by name
        Comparator<Student> byName = Comparator.comparing(Student::getName);
        students.sort(byName);
        System.out.println("Sort by name: " + students); //5, 4, 1, 2, 3

        students.sort(new StudentNameComparator().thenComparing(new StudentDeptComparator()));
        System.out.println("Sort by name, then by dept: " + students); //5, 4, 1, 2, 3
    }
}
