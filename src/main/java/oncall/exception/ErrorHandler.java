package oncall.exception;

public class ErrorHandler {

    public static void handleUserError(IllegalArgumentException e) {
        System.out.println("[ERROR] " + e.getMessage());
    }

    public static void handleSystemError(IllegalStateException e) {
        System.out.println("[SYSTEM] " + e.getMessage());
    }

    private ErrorHandler() {
    }
}
