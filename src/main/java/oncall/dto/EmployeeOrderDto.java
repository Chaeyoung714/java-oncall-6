package oncall.dto;

import oncall.model.calendar.DateType;
import oncall.model.assignment.Employee;

public record EmployeeOrderDto(Employee employee, int order, DateType dateType) {
}
