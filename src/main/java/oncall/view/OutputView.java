package oncall.view;

import java.util.List;
import oncall.dto.AssignmentDto;
import oncall.model.assignment.WorkingDay;
import oncall.model.assignment.WorkingMonth;
import oncall.model.dateInfo.Day;
import oncall.model.dateInfo.LegalHoliday;
import oncall.model.dateInfo.Month;

public class OutputView {

    public void printAssignmentResult(WorkingMonth workingMonth, List<AssignmentDto> assignment) {
        int monthOrdinal = workingMonth.getMonth().getOrdinal();
        System.out.println();
        for (AssignmentDto dto : assignment) {
            System.out.println(String.format(
                    "%d월 %d일 %s %s"
                    , monthOrdinal, dto.workingDay().getDate(), getDayPhrase(workingMonth, dto.workingDay()), dto.employee().getName()
            ));
        }
    }

    private String getDayPhrase(WorkingMonth workingMonth, WorkingDay workingDay) {
        Month month = workingMonth.getMonth();
        int date = workingDay.getDate();
        Day day = workingDay.getDay();

        if (workingDay.isWeekday() && LegalHoliday.isLegalHoliday(month, date)) {
            return String.format("%s(휴일)", day.getName());
        }
        return day.getName();
    }
}
