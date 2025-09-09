package java_core.clone;

public class Car implements Cloneable {

    private final int param; //Нет setter(), должна быть проинициализированна в констукторе
    private String color;
    private Integer wheelCount;
    private Wheel wheel;
    private final StringBuilder sb; //может быть толко поверхностное клонирование,
    // т.к. final и => при изменении в копии будет изменение в оригинале

    public Car(int param, String color, Integer wheelCount, Wheel wheel) {
        this.param = param;
        this.color = color;
        this.wheelCount = wheelCount;
        this.wheel = wheel;
        sb = new StringBuilder("Test");
    }

    @Override
    public Car clone() {
        try {
            Car clone = (Car) super.clone(); //поверхностное клонирование
            /**
             * String и Integer копируются в поверхностном клонировании
             */
//            clone.setColor(this.color); //не обязательно
//            clone.setWheelCount(this.wheelCount); //не обязательно

            /**
             * Если Wheel не был клонируемым, то:
             *
             * Integer cloneWheelX = clone.getWheel().getX();
             * Integer cloneWheelY = clone.getWheel().getY();
             * clone.setWheel(new Wheel(cloneWheelX, cloneWheelY));
             */
            Wheel cloneWheel = wheel.clone();
            clone.setWheel(cloneWheel);

            return clone;
        } catch (CloneNotSupportedException e) {
            System.out.println("Car не клонируемый!!!");
            throw new AssertionError();
        }
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setWheelCount(Integer wheelCount) {
        this.wheelCount = wheelCount;
    }

    public Wheel getWheel() {
        return wheel;
    }

    public StringBuilder getSb() {
        return sb;
    }

    public void setWheel(Wheel wheel) {
        this.wheel = wheel;
    }

    @Override
    public String toString() {
        return "Car: param = " + param + "; color = " + color +
                "; wheelCount = " + wheelCount + "; " + wheel.toString() +
                "; sb = " + sb;
    }
}
