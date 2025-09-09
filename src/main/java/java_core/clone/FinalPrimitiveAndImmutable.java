package java_core.clone;

public class FinalPrimitiveAndImmutable implements Cloneable {

    /**
     * Клонируются, но не изменяются
     */

    final int number = 10;  // Примитив (клонируется нормально)
    final String text = "Hello"; // String (клонируется нормально)

    @Override
    public FinalPrimitiveAndImmutable clone() {
        try {
            return (FinalPrimitiveAndImmutable) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public String toString() {
        return "FinalPrimitiveAndImmutable: number = " + number + "; text = " + text;
    }
}
