package oncall.view;


public class InputHandler {
    private final InputView inputView;

    public InputHandler(InputView inputView) {
        this.inputView = inputView;
    }

//    public String read() {
//        return RetryHandler.retryUntilSuccessAndReturn(() -> {
//            String answer = inputView.read();
//            InputValidator.validate(answer);
//            return answer;
//        });
//    }
}
