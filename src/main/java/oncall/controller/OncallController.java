package oncall.controller;

import java.util.List;
import oncall.dto.EmployeeOrderDto;
import oncall.dto.MonthDto;
import oncall.model.dateInfo.DateType;
import oncall.model.assignment.WorkingMonth;
import oncall.service.EmployeeService;
import oncall.view.InputHandler;
import oncall.view.OutputView;

public class OncallController {
    private final InputHandler inputHandler;
    private final OutputView outputView;
    private final EmployeeService employeeService;

    public OncallController(InputHandler inputHandler, OutputView outputView, EmployeeService employeeService) {
        this.inputHandler = inputHandler;
        this.outputView = outputView;
        this.employeeService = employeeService;
    }

    public void run() {
        MonthDto monthInput = inputHandler.readMonth();
        WorkingMonth workingMonth = WorkingMonth.of(monthInput.month(), monthInput.startDay());
        List<String> weekendOrdersInput = inputHandler.readWeekendOrders();
        List<EmployeeOrderDto> weekendOrders = employeeService.registerEmployees(weekendOrdersInput, DateType.WEEKDAY);





    }
}
