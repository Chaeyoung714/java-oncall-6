package oncall.service;

import java.util.ArrayList;
import java.util.List;
import oncall.dto.AssignmentDto;
import oncall.dto.EmployeeOrderDto;
import oncall.model.assignment.Employee;
import oncall.model.assignment.WorkingDay;
import oncall.model.assignment.WorkingMonth;
import oncall.model.calendar.DateType;
import oncall.model.assignment.EmployeeOrder;
import oncall.repository.OrderRepository;

public class AssignService {
    private final OrderRepository orderRepository;

    public AssignService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void registerOrders(List<EmployeeOrderDto> totalOrders) {
        totalOrders.forEach((e) -> {
            orderRepository.save(new EmployeeOrder(e.employee(), e.order(), e.dateType()));
        });
    }

    public List<AssignmentDto> assignEmployeesInOrderOn(WorkingMonth month) {
        List<AssignmentDto> assignment = new ArrayList<>();
        for (WorkingDay workingDay : month.getDays()) {
            DateType dateType = workingDay.getDateType();
            EmployeeOrder employeeOrder = orderRepository.findFirstNotAssignedEmployeeAt(dateType);
            if (isDuplicated(assignment, employeeOrder)) {
                employeeOrder = exchangeOrder(employeeOrder, dateType);
            }
            employeeOrder.assign();
            assignment.add(new AssignmentDto(workingDay, employeeOrder.getEmployee()));
        }
        return assignment;
    }

    private EmployeeOrder exchangeOrder(EmployeeOrder duplicatedEmployeeOrder, DateType dateType) {
        EmployeeOrder nextEmployeeOrder = orderRepository.findFirstNotAssignedEmployeeExcept(duplicatedEmployeeOrder,
                dateType);
        return nextEmployeeOrder;
    }

    private boolean isDuplicated(List<AssignmentDto> assignment, EmployeeOrder employeeOrder) {
        if (assignment.isEmpty()) {
            return false;
        }
        Employee lastEmployee = assignment.get(assignment.size() - 1).employee();
        Employee currentEmployee = employeeOrder.getEmployee();
        if (lastEmployee.equals(currentEmployee)) {
            return true;
        }
        return false;
    }
}
