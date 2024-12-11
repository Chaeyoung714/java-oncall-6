package oncall.controller;

import java.util.List;
import oncall.dto.AssignmentDto;
import oncall.dto.EmployeeOrderDto;
import oncall.dto.MonthDto;
import oncall.exception.RetryHandler;
import oncall.model.calendar.DateType;
import oncall.model.assignment.WorkingMonth;
import oncall.service.AssignService;
import oncall.service.EmployeeService;
import oncall.view.InputHandler;
import oncall.view.OutputView;

public class OncallController {
    private final InputHandler inputHandler;
    private final OutputView outputView;
    private final EmployeeService employeeService;
    private final AssignService assignService;

    public OncallController(InputHandler inputHandler, OutputView outputView, EmployeeService employeeService,
                            AssignService assignService) {
        this.inputHandler = inputHandler;
        this.outputView = outputView;
        this.employeeService = employeeService;
        this.assignService = assignService;
    }

    public void run() {
        WorkingMonth workingMonth = registerMonth();
        registerOrders();
        List<AssignmentDto> assignment = assignService.assignEmployeesInOrderOn(workingMonth);
        outputView.printAssignmentResult(workingMonth, assignment);
    }

    private WorkingMonth registerMonth() {
        return RetryHandler.retryUntilSuccessAndReturn(() -> {
            MonthDto monthInput = inputHandler.readMonth();
            return WorkingMonth.of(monthInput.month(), monthInput.startDay());
        });
    }

    private void registerOrders() {
        RetryHandler.retryUntilSuccess(() -> {
            List<String> weekdayOrdersInput = inputHandler.readWeekdayOrders();
            List<EmployeeOrderDto> weekdayOrders = employeeService.registerEmployees(weekdayOrdersInput,
                    DateType.WEEKDAY);
            List<String> holidayOrdersInput = inputHandler.readHolidayOrders();
            List<EmployeeOrderDto> holidayOrders = employeeService.registerEmployees(holidayOrdersInput,
                    DateType.HOLIDAY);
            List<EmployeeOrderDto> totalOrders = employeeService.registerTotalEmployeeOrders(weekdayOrders,
                    holidayOrders);
            assignService.registerOrders(totalOrders);
        });
    }
}
