package java_core.singleton;

public final class SingletonLazy {

    /*
    Этот подход откладывает создание экземпляра до первого запроса.
    Он потокобезопасным
    Это снижает производительность в многопоточных средах,
    так как каждый вызов будет ждать завершения работы метода в других потоках.
     */
    private static SingletonLazy instance;

    private SingletonLazy() {
        // Приватный конструктор предотвращает создание экземпляров вне класса
    }

    public static synchronized SingletonLazy getInstance() {
        if (instance == null) {
            instance = new SingletonLazy();
        }
        return instance;
    }
}
