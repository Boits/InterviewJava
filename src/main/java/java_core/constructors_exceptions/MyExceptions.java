package java_core.constructors_exceptions;

import java.io.*;
import java.nio.file.AccessDeniedException;

public class MyExceptions {

    public void exception() {
        try {
            throwChecked();
//            throwUnchecked();

            FileReader file = new FileReader("test.txt");
//            int result = 10 / 0;
        } catch (FileNotFoundException e) { //checked (обязательно проверить)
            System.out.println("Файл не найден."); //когда int result = 10 / 0;
            e.printStackTrace();
        } catch (ArithmeticException e) { //uncheched (необязательно проверить)
            System.out.println("Деление на ноль.");
            e.printStackTrace();
        } catch (Error e) {
            System.out.println("Выпала ошибка Error.");
            e.printStackTrace();
        } catch (RuntimeException e) {
            System.out.println("Выпала ошибка RuntimeException."); //когда throwUnchecked()
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Выпала ошибка Exception."); // когда throwChecked()
            e.printStackTrace();
        }
        System.out.println("exception(): После блока try-catch");
    }

    private void throwChecked() throws Exception {
        throw new Exception();
    }

    private void throwUnchecked() {
        throw new RuntimeException();
    }

    public void exception2() {
        try {
//            Throwable t = new Exception(); // и лететь будет Exception
//            throw t; // но тут ошибка компиляции (определяет по типу ссылки, а не объекта)
            Exception ex = new Exception();
            throw ex;
        } catch (Exception e) {
            System.out.println("Перехвачено!");
        }
    }

    public void tryWithResources() {
        // ресурсы будут закрыты в обратном порядке
        // ресурсы должны implements Closeable(throws IOException) или AutoCloseable(throws Exception)
        try (
                BufferedReader reader = new BufferedReader(new FileReader("test.txt"));
                BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))
        ) {
            writer.write(reader.readLine());
        } catch (IOException e) {
            System.out.println("Словили IOException");
            e.printStackTrace();
        }
        System.out.println("tryWithResources(): После блока try-catch");
    }

    public void inheritanceException() {
        Animal d = new Dog("Brain Dog", "Heart Dog", "Tail Dog");
        try {
            System.out.println("======================");
            d.testException();
        } catch (AccessDeniedException e) {
            System.out.println("Словили AccessDeniedException");
        } catch (IOException e) {
            System.out.println("Словили IOException");
        } finally {
            System.out.println("Блок finally в любом случае выводится");
        }

        /**
         * testException() в суперклассе Animal
         * Словили IOException
         * Блок finally в любом случае выводится
         */
    }

}
