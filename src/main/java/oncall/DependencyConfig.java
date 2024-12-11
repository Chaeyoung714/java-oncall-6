package oncall;

import oncall.controller.OnCallController;
import oncall.repository.EmployeeRepository;
import oncall.repository.OrderRepository;
import oncall.service.AssignService;
import oncall.service.EmployeeService;
import oncall.view.InputHandler;
import oncall.view.InputView;
import oncall.view.OutputView;

public class DependencyConfig {
    private final EmployeeRepository employeeRepository = new EmployeeRepository();
    private final OrderRepository orderRepository = new OrderRepository();

    private EmployeeService employeeService() {
        return new EmployeeService(employeeRepository);
    }

    private AssignService assignService() {
        return new AssignService(orderRepository);
    }

    public OnCallController controller() {
        return new OnCallController(
                new InputHandler(new InputView())
                , new OutputView()
                , employeeService()
                , assignService()
        );
    }
}
