package tasks;

import java.util.Map;
import java.util.Optional;

public class StreamSwitch {

    private static final Map<Integer, String> dayMap = Map.of(
            1, "Monday",
            2, "Tuesday",
            3, "Wednesday",
            4, "Thursday",
            5, "Friday",
            6, "Saturday",
            7, "Sunday"
    );

    public static String getDayName(int day) {
        return Optional.ofNullable(dayMap.get(day))
                .orElse("Invalid day");
    }
}
