package oncall.model.dateInfo;

public enum DateType {
    WEEKDAY,
    HOLIDAY,
    ;

    public static DateType findByDate(Month month, int date, Day day) {
        //TODO : 일단 휴일은 휴일이라고만 함
        if (LegalHoliday.isLegalHoliday(month, date) || Day.isWeekend(day)) {
            return HOLIDAY;
        }
        return WEEKDAY;
    }
}
