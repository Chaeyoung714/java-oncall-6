package oncall.model.assignment;

import java.util.ArrayList;
import java.util.List;
import oncall.exception.ExceptionMessages;
import oncall.model.calendar.DateType;
import oncall.model.calendar.Day;
import oncall.model.calendar.Month;

public class WorkingMonth {
    private static final int START_DATE_OF_MONTH = 1;
    private static final int DAY_COUNT_IN_WEEK = 7;

    private final Month month;
    private final List<WorkingDay> days;

    public WorkingMonth(Month month, List<WorkingDay> days) {
        this.month = month;
        this.days = days;
    }

    public static WorkingMonth of(int monthInput, String startDayInput) {
        try {
            Day startDay = Day.findByName(startDayInput);
            Month month = Month.findByOrdinal(monthInput);
            List<Day> daysOfOneWeek = Day.getAllFromStartDay(startDay);
            List<WorkingDay> workingDays = calculateDaysOfAMonth(month, daysOfOneWeek);
            return new WorkingMonth(month, workingDays);
        } catch (IllegalStateException e) {
            throw new IllegalArgumentException(ExceptionMessages.WRONG_INPUT_VALUE.getMessage());
        }
    }

    private static List<WorkingDay> calculateDaysOfAMonth(Month month, List<Day> daysOfOneWeek) {
        int dayPointer = 0;
        int date = START_DATE_OF_MONTH;
        List<WorkingDay> workingDays = new ArrayList<>();
        while (date <= month.getDateCount()) {
            Day day = daysOfOneWeek.get(dayPointer);
            workingDays.add(new WorkingDay(date, day, DateType.findByDate(month, date, day)));
            date++;
            if (dayPointer == DAY_COUNT_IN_WEEK - 1) {
                dayPointer = 0;
                continue;
            }
            dayPointer++;
        }
        return workingDays;
    }

    public Month getMonth() {
        return month;
    }

    public List<WorkingDay> getDays() {
        return days;
    }
}
