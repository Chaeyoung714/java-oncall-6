package oncall.model.assignment;

import java.util.ArrayList;
import java.util.List;
import oncall.exception.ExceptionMessages;
import oncall.model.dateInfo.DateType;
import oncall.model.dateInfo.Day;
import oncall.model.dateInfo.Month;

public class WorkingMonth {
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
            List<Day> days = Day.getAllFromStartDay(startDay);

            int dayPointer = 0;
            int date = 1;
            List<WorkingDay> workingDays = new ArrayList<>();
            while (date <= month.getDateCount()) {
                Day day = days.get(dayPointer);
                workingDays.add(new WorkingDay(date, day, DateType.findByDate(month, date, day)));

                date++;
                if (dayPointer == 6) {
                    dayPointer = 0;
                    continue;
                }
                dayPointer++;
            }
            return new WorkingMonth(month, workingDays);
        } catch (IllegalStateException e) {
            throw new IllegalArgumentException(ExceptionMessages.WRONG_INPUT_VALUE.getMessage());
        }
    }

    public Month getMonth() {
        return month;
    }

    public List<WorkingDay> getDays() {
        return days;
    }
}
