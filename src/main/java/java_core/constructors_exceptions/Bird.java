package java_core.constructors_exceptions;

public class Bird {

    private String title;
    private int speed;

    static {
        //Выполняется во время загрузки класса 1 раз в самом начале
        System.out.println("Статический блок класса Bird");
    }

    {
        //Выполняется каждый раз перед конструктором, перед созданием объекта
        System.out.println("Не статический блок класса Bird");
    }

    /**
     * При наличии конструктора/ов с параметром
     * при необходимости дефолтный нужно создать отдельно.
     * Не будет автоматически создан.
     */
    public Bird() {
        System.out.println("Конструктор Bird по умолчанию");
    }

    public Bird(String title) {
        this.title = title;
        System.out.println("Конструктор Bird c 1 параметром");
    }

    // Перегрузка конструктора
    public Bird(String title, int speed) {
        this.title = title; //this(title); //Конструктор Bird c 1 параметром
        this.speed = speed;
        System.out.println("Конструктор Bird c 2мя параметрами");
    }

    public void fly() {
        System.out.println("fly(): " + title + " летит " + speed + " км/ч");
    }
}
