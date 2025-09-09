package java_core.singleton;

/*
Этот метод является самым безопасным и рекомендованным для большинства случаев.
enum в Java гарантирует, что будет только один экземпляр.
 */
public enum SingletonEnum {
    INSTANCE;

    // Добавьте методы и поля, если нужно

    public void someMethod() {
        // Реализация метода
    }

    /*
    Использование

    SingletonEnum singleton = SingletonEnum.INSTANCE;
    singleton.someMethod();

     */
}
