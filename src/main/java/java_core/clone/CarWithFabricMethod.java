package java_core.clone;

public class CarWithFabricMethod {
    private final String brand;
    private final int speed;

    private CarWithFabricMethod(String brand, int speed) { // Приватный конструктор
        this.brand = brand;
        this.speed = speed;
    }

    // Статический фабричный метод для создания объектов
    public static CarWithFabricMethod of(String brand, int speed) {
        return new CarWithFabricMethod(brand, speed);
    }

    // Фабричный метод для копирования с изменениями
    public CarWithFabricMethod withSpeed(int newSpeed) {
        return new CarWithFabricMethod(this.brand, newSpeed); // Новый объект
    }

    //или такой вариант
//    public static CarWithFabricMethod copyOf(CarWithFabricMethod original) {
//        CarWithFabricMethod copy = new CarWithFabricMethod();
//        copy.name = original.name;
//        return copy;
//    }

    @Override
    public String toString() {
        return brand + " moving at " + speed + " km/h";
    }
}
