package oncall.model.assignment;

import oncall.model.dateInfo.DateType;
import oncall.model.dateInfo.Day;

public class WorkingDay {
    private final int date;
    private final Day day;
    private final DateType dateType;

    public WorkingDay(int date, Day day, DateType dateType) {
        this.date = date;
        this.day = day;
        this.dateType = dateType;
    }

    public boolean isHoliday() {
        return dateType.equals(DateType.HOLIDAY);
    }

    public boolean isWeekday() {
        return dateType.equals(DateType.WEEKDAY);
    }


    public Day getDay() {
        return day;
    }

    public int getDate() {
        return date;
    }

    public DateType getDateType() {
        return dateType;
    }
}
