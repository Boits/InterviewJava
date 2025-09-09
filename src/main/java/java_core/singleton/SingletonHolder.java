package java_core.singleton;

public final class SingletonHolder {

    /*
    Инициализация при загрузке класса (Initialization-on-demand holder idiom)

    Этот подход использует вложенный статический класс для ленивой инициализации.
    Это потокобезопасно и не требует явной синхронизации.
     */

    private SingletonHolder() {
        // Приватный конструктор предотвращает создание экземпляров вне класса
    }

    private static class Holder {
        private static final SingletonHolder INSTANCE = new SingletonHolder();
    }

    public static SingletonHolder getInstance() {
        return Holder.INSTANCE;
    }
}
