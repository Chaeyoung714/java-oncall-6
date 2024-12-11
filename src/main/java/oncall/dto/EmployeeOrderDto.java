package oncall.dto;

import oncall.model.dateInfo.DateType;
import oncall.model.assignment.Employee;

public record EmployeeOrderDto(Employee employee, int order, DateType dateType) {
}
