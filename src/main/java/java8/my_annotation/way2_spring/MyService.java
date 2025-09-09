package java8.my_annotation.way2_spring;

public class MyService {

    @LogExecutionTime
    public void serve() {
        // Долгая операция
    }
}

