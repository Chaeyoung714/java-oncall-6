package oncall.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
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
        Optional<EmployeeOrder> firstNotAssignedEmployee = findNotAssignedEmployeeOrdersAt(dateType)
                .findFirst();
        if (firstNotAssignedEmployee.isEmpty()) {
            initializeAssignmentAt(dateType);
            return findNotAssignedEmployeeOrdersAt(dateType)
                    .findFirst()
                    .orElseThrow(IllegalStateException::new);
        }
        return firstNotAssignedEmployee.get();
    }

    public EmployeeOrder findFirstNotAssignedEmployeeExcept(EmployeeOrder duplicatedEmployeeOrder, DateType dateType) {
        return findNotAssignedEmployeeOrdersAt(dateType)
                .filter(eo -> !eo.equals(duplicatedEmployeeOrder))
                .findFirst()
                .orElse(findFirstEmployeeOrderAt(dateType)); //TODO : 마지막 순번이 중복돼서 다음 순번을 찾을 때에 해당
    }

    private EmployeeOrder findFirstEmployeeOrderAt(DateType dateType) {
        return orders.stream()
                .filter(eo -> eo.getDateType().equals(dateType))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    //TODO : 문제생김, initialize 안하는 방식으로 생각해보기
    private void initializeAssignmentAt(DateType dateType) {
        orders.stream().filter(eo -> eo.getDateType().equals(dateType))
                .forEach(eo -> {
                    eo.unAssign();
                });
    }

    private Stream<EmployeeOrder> findNotAssignedEmployeeOrdersAt(DateType dateType) {
        return orders.stream()
                .filter(eo -> eo.getDateType().equals(dateType))
                .filter(eo -> eo.isNotAssigned());
    }
}
