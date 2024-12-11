package oncall.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import oncall.dto.EmployeeOrderDto;
import oncall.exception.ExceptionMessages;
import oncall.model.calendar.DateType;
import oncall.model.assignment.Employee;
import oncall.repository.EmployeeRepository;

public class EmployeeService {
    private static final int MIN_NICKNAME_LENGTH = 1;
    private static final int MAX_NICKNAME_LENGTH = 5;
    private static final int MIN_EMPLOYEE_COUNT = 5;
    private static final int MAX_EMPLOYEE_COUNT = 35;

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeOrderDto> registerTotalEmployeeOrders(List<EmployeeOrderDto> weekdayOrders,
                                                              List<EmployeeOrderDto> holidayOrders) {
        validateOrdersIdentical(weekdayOrders, holidayOrders);
        List<EmployeeOrderDto> totalEmployeeOrders = new ArrayList<>();
        totalEmployeeOrders.addAll(weekdayOrders);
        totalEmployeeOrders.addAll(holidayOrders);
        return totalEmployeeOrders;
    }

    public List<EmployeeOrderDto> registerEmployees(List<String> ordersInput, DateType dateType) {
        validateEmployeeNames(ordersInput);
        List<EmployeeOrderDto> employeeOrderDtos = new ArrayList<>();
        for (String employeeName : ordersInput) {
            Employee employee = employeeRepository.findOrCreate(employeeName);
            int order = ordersInput.indexOf(employeeName);
            employeeOrderDtos.add(new EmployeeOrderDto(employee, order, dateType));
        }
        return employeeOrderDtos;
    }

    private void validateOrdersIdentical(List<EmployeeOrderDto> weekdayOrders, List<EmployeeOrderDto> holidayOrders) {
        List<String> weekdayOrderNames = weekdayOrders.stream().map(e -> e.employee().getName()).toList();
        List<String> holidayOrderNames = holidayOrders.stream().map(e -> e.employee().getName()).toList();
        if (weekdayOrderNames.containsAll(holidayOrderNames) && holidayOrderNames.containsAll(weekdayOrderNames)) {
            return;
        }
        throw new IllegalArgumentException(ExceptionMessages.WRONG_INPUT_VALUE.getMessage());
    }

    private void validateEmployeeNames(List<String> ordersInput) {
        if (new HashSet<>(ordersInput).size() != ordersInput.size()) {
            throw new IllegalArgumentException(ExceptionMessages.WRONG_INPUT_VALUE.getMessage());
        }
        if (ordersInput.size() < MIN_EMPLOYEE_COUNT || ordersInput.size() > MAX_EMPLOYEE_COUNT) {
            throw new IllegalArgumentException(ExceptionMessages.WRONG_INPUT_VALUE.getMessage());
        }
        ordersInput.forEach(name -> {
            if (name.length() < MIN_NICKNAME_LENGTH || name.length() > MAX_NICKNAME_LENGTH) {
                throw new IllegalArgumentException(ExceptionMessages.WRONG_INPUT_VALUE.getMessage());
            }
        });
    }
}
