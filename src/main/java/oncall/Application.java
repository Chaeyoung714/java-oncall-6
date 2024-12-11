package oncall;

import oncall.controller.OncallController;

public class Application {
    public static void main(String[] args) {
        DependencyConfig config = new DependencyConfig();
        OncallController controller = config.controller();
        controller.run();
    }
}
