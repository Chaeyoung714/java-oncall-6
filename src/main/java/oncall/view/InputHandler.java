package oncall.view;


import java.util.List;
import oncall.dto.MonthDto;
import oncall.exception.RetryHandler;

public class InputHandler {
    private final InputView inputView;

    public InputHandler(InputView inputView) {
        this.inputView = inputView;
    }

    public MonthDto readMonth() {
        return RetryHandler.retryUntilSuccessAndReturn(() -> {
            String answer = inputView.readMonth();
            return parseMonth(answer);
        });
    }

    private MonthDto parseMonth(String answer) {
        String[] parsedAnswer = answer.split(",");
        //TODO : 검증추가
        return new MonthDto(Integer.parseInt(parsedAnswer[0]), parsedAnswer[1]);
    }
}
