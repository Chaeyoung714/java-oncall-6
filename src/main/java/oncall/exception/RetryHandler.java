package oncall.exception;

import java.util.function.Supplier;

public class RetryHandler {

    public static void retryUntilSuccess(Runnable task) {
        while (true) {
            try {
                task.run();
                return;
            } catch (IllegalArgumentException e) {
                ErrorHandler.handleUserError(e);
            }
        }
    }

    public static <T> T retryUntilSuccessAndReturn(Supplier<T> inputSupplier) {
        while (true) {
            try {
                return inputSupplier.get();
            } catch (IllegalArgumentException e) {
                ErrorHandler.handleUserError(e);
            }
        }
    }

    private RetryHandler() {
    }
}
