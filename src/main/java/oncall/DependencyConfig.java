package oncall;

import oncall.controller.OncallController;
import oncall.view.InputHandler;
import oncall.view.InputView;
import oncall.view.OutputView;

public class DependencyConfig {

    public OncallController controller() {
        return new OncallController(
                new InputHandler(new InputView())
                , new OutputView()
        );
    }
}
