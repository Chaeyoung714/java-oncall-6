package oncall.model.dateInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Day {
    SUNDAY("일"),
    MONDAY("월"),
    TUESDAY("화"),
    WEDNESDAY("수"),
    THURSDAY("목"),
    FRIDAY("금"),
    SATURDAY("토");

    private static final int DAY_COUNT = 7;

    private final String name;

    Day(String name) {
        this.name = name;
    }

    public static Day findByName(String name) {
        return Arrays.stream(Day.values())
                .filter(d -> d.getName().equals(name))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    public static List<Day> getAllFromStartDay(Day startDay) {
        int startDayIndex = List.of(Day.values()).indexOf(startDay);
        List<Day> daysFromStartDay = new ArrayList<>();
        for (int i = startDayIndex; i < DAY_COUNT; i++) {
            daysFromStartDay.add(Day.values()[i]);
        }
        for (int i = 0; i < startDayIndex; i++) {
            daysFromStartDay.add(Day.values()[i]);
        }
        return daysFromStartDay;
    }

    public static boolean isWeekday(Day day) {
        return !day.equals(SUNDAY) && !day.equals(SATURDAY);
    }

    public String getName() {
        return name;
    }

    public static boolean isWeekend(Day day) {
        return day.equals(Day.SATURDAY) || day.equals(Day.SUNDAY);
    }

}
