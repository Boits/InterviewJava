package java_core;

/**
 * Day.MONDAY.getDayNumber()
 */
public enum DaysEnum {
    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(3),
    THURSDAY(4),
    FRIDAY(5),
    SATURDAY(6),
    SUNDAY(7);

    private final int dayNumber;

    DaysEnum(int dayNumber) { //всегда private конструктор
        this.dayNumber = dayNumber;
    }

    public int getDayNumber() {
        return this.dayNumber;
    }
}
