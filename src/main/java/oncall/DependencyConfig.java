package oncall;

import oncall.controller.OncallController;
import oncall.repository.EmployeeRepository;
import oncall.service.EmployeeService;
import oncall.view.InputHandler;
import oncall.view.InputView;
import oncall.view.OutputView;

public class DependencyConfig {
    private final EmployeeRepository employeeRepository = new EmployeeRepository();

    private EmployeeService employeeService() {
        return new EmployeeService(employeeRepository);
    }

    public OncallController controller() {
        return new OncallController(
                new InputHandler(new InputView())
                , new OutputView()
                , employeeService()
        );
    }
}
