package oncall.model.dateInfo;

public enum DateType {
    WEEKDAY,
    HOLIDAY,
    ;

    public static DateType findByDate(Month month, int date, Day day) {
        if (LegalHoliday.isLegalHoliday(month, date) || Day.isWeekend(day)) {
            return HOLIDAY;
        }
        return WEEKDAY;
    }
}
