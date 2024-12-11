package oncall.dto;

import oncall.model.assignment.Employee;
import oncall.model.assignment.WorkingDay;

public record AssignmentDto(WorkingDay workingDay, Employee employee) {
}
