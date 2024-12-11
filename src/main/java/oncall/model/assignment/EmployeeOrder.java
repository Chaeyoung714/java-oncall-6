package oncall.model.assignment;

import oncall.model.dateInfo.DateType;

public class EmployeeOrder {
    private final Employee employee;
    private final int order;
    private final DateType dateType;
    private boolean isAssigned;

    public EmployeeOrder(Employee employee, int order, DateType dateType) {
        this.employee = employee;
        this.order = order;
        this.dateType = dateType;
        this.isAssigned = false;
    }

    public void assign() {
        this.isAssigned = true;
    }

    public void unAssign() {
        this.isAssigned = false;
    }

    public Employee getEmployee() {
        return employee;
    }

    public int getOrder() {
        return order;
    }

    public DateType getDateType() {
        return dateType;
    }

    public boolean isAssigned() {
        return isAssigned;
    }

    public boolean isNotAssigned() {
        return !isAssigned;
    }
}
