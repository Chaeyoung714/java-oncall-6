package oncall.model.calendar;

import static oncall.model.calendar.Month.AUGUST;
import static oncall.model.calendar.Month.DECEMBER;
import static oncall.model.calendar.Month.JANUARY;
import static oncall.model.calendar.Month.JUNE;
import static oncall.model.calendar.Month.MARCH;
import static oncall.model.calendar.Month.MAY;
import static oncall.model.calendar.Month.OCTOBER;

public enum LegalHoliday {
    신정(JANUARY, 1),
    삼일절(MARCH, 1),
    어린이날(MAY, 5),
    현충일(JUNE, 6),
    광복절(AUGUST, 15),
    개천절(OCTOBER, 3),
    한글날(OCTOBER, 9),
    성탄절(DECEMBER, 25),
    ;

    private final Month month;
    private final int date;

    LegalHoliday(Month month, int date) {
        this.month = month;
        this.date = date;
    }

    public static boolean isLegalHoliday(Month month, int date) {
        for (LegalHoliday legalHoliday : LegalHoliday.values()) {
            if (legalHoliday.month.equals(month) && legalHoliday.date == date) {
                return true;
            }
        }
        return false;
    }
}
