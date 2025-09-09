package java_core;

public final class DayOfWeekEnum {

    private final String title;

    private DayOfWeekEnum(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public final static DayOfWeekEnum SUNDAY = new DayOfWeekEnum("Воскресенье");
    public final static DayOfWeekEnum MONDAY = new DayOfWeekEnum("Понедельник");
    public final static DayOfWeekEnum TUESDAY = new DayOfWeekEnum("Вторник");
    public final static DayOfWeekEnum WEDNESDAY = new DayOfWeekEnum("Среда");
    public final static DayOfWeekEnum THURSDAY = new DayOfWeekEnum("Четверг");
    public final static DayOfWeekEnum FRIDAY = new DayOfWeekEnum("Пятница");
    public final static DayOfWeekEnum SATURDAY = new DayOfWeekEnum("Суббота");

    @Override
    public String toString() {
        return "DayOfWeek{" +
                "title='" + title + '\'' +
                '}';
    }
}
