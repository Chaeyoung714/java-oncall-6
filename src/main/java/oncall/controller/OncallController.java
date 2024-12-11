package oncall.controller;

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

    }
}
