package oncall.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import oncall.model.assignment.Employee;

public class EmployeeRepository {
    private final List<Employee> employees;

    public EmployeeRepository() {
        this.employees = new ArrayList<>();
    }

    public Employee findOrCreate(String employeeName) {
        Optional<Employee> employee = findByNameOrElseEmpty(employeeName);
        if (employee.isPresent()) {
            return employee.get();
        }
        Employee newEmployee = new Employee(employeeName);
        this.employees.add(newEmployee);
        return newEmployee;
    }

    private Optional<Employee> findByNameOrElseEmpty(String name) {
        return employees.stream()
                .filter(e -> e.getName().equals(name))
                .findFirst();
    }

}
