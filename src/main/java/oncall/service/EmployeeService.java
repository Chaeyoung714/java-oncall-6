package oncall.service;

import java.util.ArrayList;
import java.util.List;
import oncall.dto.EmployeeOrderDto;
import oncall.model.dateInfo.DateType;
import oncall.model.assignment.Employee;
import oncall.repository.EmployeeRepository;

public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeOrderDto> registerEmployees(List<String> weekendOrdersInput, DateType dateType) {
        List<EmployeeOrderDto> employeeOrderDtos = new ArrayList<>();
        for (String employeeName : weekendOrdersInput) {
            Employee employee = employeeRepository.findOrCreate(employeeName);
            int order = weekendOrdersInput.indexOf(employeeName);
            employeeOrderDtos.add(new EmployeeOrderDto(employee, order, dateType));
        }
        return employeeOrderDtos;
    }
}
