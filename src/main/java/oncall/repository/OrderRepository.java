package oncall.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import oncall.model.dateInfo.DateType;
import oncall.model.assignment.EmployeeOrder;

public class OrderRepository {
    private final List<EmployeeOrder> orders;

    public OrderRepository() {
        this.orders = new ArrayList<>();
    }

    public void save(EmployeeOrder order) {
        this.orders.add(order);
    }

    public List<EmployeeOrder> findAll() {
        return Collections.unmodifiableList(orders);
    }

    public EmployeeOrder findFirstNotAssignedEmployeeAt(DateType dateType) {
        Optional<EmployeeOrder> firstNotAssignedEmployee = orders.stream()
                .filter(eo -> eo.getDateType().equals(dateType))
                .filter(eo -> eo.isNotAssigned())
                .findFirst();
        if (firstNotAssignedEmployee.isEmpty()) {
            initializeAssignmentAt(dateType);
            return orders.stream()
                    .filter(eo -> eo.getDateType().equals(dateType))
                    .filter(eo -> eo.isNotAssigned())
                    .findFirst()
                    .orElseThrow(IllegalStateException::new);
        }
        return firstNotAssignedEmployee.get();
    }

    private void initializeAssignmentAt(DateType dateType) {
        orders.stream().filter(eo -> eo.getDateType().equals(dateType))
                .forEach(eo -> {
                    eo.unAssign();
                });
    }
}
