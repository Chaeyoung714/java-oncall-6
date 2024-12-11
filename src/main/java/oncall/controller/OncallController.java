package oncall.controller;

import oncall.dto.MonthDto;
import oncall.model.organization.WorkingDay;
import oncall.model.organization.WorkingMonth;
import oncall.view.InputHandler;
import oncall.view.OutputView;

public class OncallController {
    private final InputHandler inputHandler;
    private final OutputView outputView;

    public OncallController(InputHandler inputHandler, OutputView outputView) {
        this.inputHandler = inputHandler;
        this.outputView = outputView;
    }

    public void run() {
        MonthDto monthInput = inputHandler.readMonth();
        WorkingMonth workingMonth = WorkingMonth.of(monthInput.month(), monthInput.startDay());

    }
}
