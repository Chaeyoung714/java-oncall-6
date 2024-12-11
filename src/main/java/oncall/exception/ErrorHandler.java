package oncall.exception;

public class ErrorHandler {

    public static void handleUserError(IllegalArgumentException e) {
        System.out.println("[ERROR] " + e.getMessage());
    }

    private ErrorHandler() {
    }
}
