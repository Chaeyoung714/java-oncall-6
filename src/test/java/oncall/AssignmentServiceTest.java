package oncall;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import oncall.dto.AssignmentDto;
import oncall.dto.EmployeeOrderDto;
import oncall.model.assignment.Employee;
import oncall.model.assignment.WorkingMonth;
import oncall.model.calendar.DateType;
import oncall.repository.OrderRepository;
import oncall.service.AssignService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AssignmentServiceTest {
    private OrderRepository orderRepository;
    private AssignService assignService;

    private Employee employee1 = new Employee("준팍");
    private Employee employee2 = new Employee("도밥");
    private Employee employee3 = new Employee("고니");
    private Employee employee4 = new Employee("수아");
    private Employee employee5 = new Employee("루루");
    private Employee employee6 = new Employee("글로");

    @BeforeEach
    void setUp() {
        orderRepository = new OrderRepository();
        assignService = new AssignService(orderRepository);
    }

    @Test
    @DisplayName("""
            평일 비상 근무 순번 : 준팍, 도밥, 고니, 수아, 루루, 글로
            휴일 비상 근무 순번 : 수아, 루루, 글로, 준팍, 도밥, 고니일 때
            수아가 평일인 목요일에 비상 근무를 서고, 다음 날인 금요일이 휴일이면서 순번상 또다시 수아가 근무해야 할 경우, 
            다음 휴일 근무자(루루)와 순서를 바꿔서 근무한다.
            """)
    void 연속_이틀_비상근무시_다음_근무자와_교체한다_1() {
        WorkingMonth workingMonth = WorkingMonth.of(5, "월");

        assignService.registerOrders(List.of(
                new EmployeeOrderDto(employee1, 0, DateType.WEEKDAY)
                , new EmployeeOrderDto(employee2, 1, DateType.WEEKDAY)
                , new EmployeeOrderDto(employee3, 2, DateType.WEEKDAY)
                , new EmployeeOrderDto(employee4, 3, DateType.WEEKDAY)
                , new EmployeeOrderDto(employee5, 4, DateType.WEEKDAY)
                , new EmployeeOrderDto(employee6, 5, DateType.WEEKDAY)

                , new EmployeeOrderDto(employee4, 0, DateType.HOLIDAY)
                , new EmployeeOrderDto(employee5, 1, DateType.HOLIDAY)
                , new EmployeeOrderDto(employee6, 2, DateType.HOLIDAY)
                , new EmployeeOrderDto(employee1, 3, DateType.HOLIDAY)
                , new EmployeeOrderDto(employee2, 4, DateType.HOLIDAY)
                , new EmployeeOrderDto(employee3, 5, DateType.HOLIDAY)
        ));

        List<AssignmentDto> assignmentResult = assignService.assignEmployeesInOrderOn(workingMonth);

        assertThat(assignmentResult.get(0).employee()).isEqualTo(employee1);
        assertThat(assignmentResult.get(1).employee()).isEqualTo(employee2);
        assertThat(assignmentResult.get(2).employee()).isEqualTo(employee3);
        assertThat(assignmentResult.get(3).employee()).isEqualTo(employee4);
        assertThat(assignmentResult.get(4).employee()).isEqualTo(employee5);
        assertThat(assignmentResult.get(5).employee()).isEqualTo(employee4);
        assertThat(assignmentResult.get(6).employee()).isEqualTo(employee6);
    }

    @Test
    @DisplayName("""
            평일 순번 : 준팍, 도밥, 수아, 루루, 글로
            휴일 순번 : 수아, 루루, 글로, 준팍, 도밥일 때
            만약에 법정공휴일인 수요일에 수아가 비상 근무를 서고 다음 날 평일 순번이 수아인 경우에는,
            다음 평일 근무자(루루)와 순서를 바꿔서 근무한다.
            이 경우 원래 (월)준팍 > (화)도밥 > (수)수아 > (목)수아 > (금)루루 > (토)루루의 순서이지만,
            앞의 날짜부터 순서를 변경하므로 연속 근무가 첫 발견된 목요일의 근무자 순서부터 바꾼다.
            """)
    void 연속_이틀_비상근무시_다음_근무자와_교체한다_2() {
        WorkingMonth workingMonth = WorkingMonth.of(10, "월");

        assignService.registerOrders(List.of(
                new EmployeeOrderDto(employee1, 0, DateType.WEEKDAY)
                , new EmployeeOrderDto(employee2, 1, DateType.WEEKDAY)
                , new EmployeeOrderDto(employee4, 2, DateType.WEEKDAY)
                , new EmployeeOrderDto(employee5, 3, DateType.WEEKDAY)
                , new EmployeeOrderDto(employee6, 4, DateType.WEEKDAY)

                , new EmployeeOrderDto(employee4, 0, DateType.HOLIDAY)
                , new EmployeeOrderDto(employee5, 1, DateType.HOLIDAY)
                , new EmployeeOrderDto(employee6, 2, DateType.HOLIDAY)
                , new EmployeeOrderDto(employee1, 3, DateType.HOLIDAY)
                , new EmployeeOrderDto(employee2, 4, DateType.HOLIDAY)
        ));

        List<AssignmentDto> assignmentResult = assignService.assignEmployeesInOrderOn(workingMonth);


        assertThat(assignmentResult.get(0).employee()).isEqualTo(employee1);
        assertThat(assignmentResult.get(1).employee()).isEqualTo(employee2);
        assertThat(assignmentResult.get(2).employee()).isEqualTo(employee4);
        assertThat(assignmentResult.get(3).employee()).isEqualTo(employee5);
        assertThat(assignmentResult.get(4).employee()).isEqualTo(employee4);
        assertThat(assignmentResult.get(5).employee()).isEqualTo(employee5);
        assertThat(assignmentResult.get(6).employee()).isEqualTo(employee6);
    }
}
