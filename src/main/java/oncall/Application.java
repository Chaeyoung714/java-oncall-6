package oncall;

import oncall.controller.OnCallController;

public class Application {
    public static void main(String[] args) {
        DependencyConfig config = new DependencyConfig();
        OnCallController controller = config.controller();
        controller.run();
    }
}
