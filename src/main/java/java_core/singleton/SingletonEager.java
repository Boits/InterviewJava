package java_core.singleton;

public final class SingletonEager {
    /*
    Экземпляр создается сразу при загрузке класса.
    Этот метод гарантирует потокобезопасность и простоту,
    но может быть неэффективным, если экземпляр создается, даже если он не используется.
     */
    private static final SingletonEager instance = new SingletonEager();

    private SingletonEager() {
        // Приватный конструктор предотвращает создание экземпляров вне класса
    }

    public static SingletonEager getInstance() {
        return instance;
    }
}
