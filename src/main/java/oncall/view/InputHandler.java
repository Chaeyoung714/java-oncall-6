package oncall.view;


import java.util.Arrays;
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
        InputValidator.validateMonthInformation(parsedAnswer);
        return new MonthDto(Integer.parseInt(parsedAnswer[0]), parsedAnswer[1]);
    }

    public List<String> readWeekdayOrders() {
        return RetryHandler.retryUntilSuccessAndReturn(() -> {
            String answer = inputView.readWeekdayOrders();
            return parseOrders(answer);
        });
    }

    public List<String> readHolidayOrders() {
        return RetryHandler.retryUntilSuccessAndReturn(() -> {
            String answer = inputView.readHolidayOrders();
            return parseOrders(answer);
        });
    }

    private List<String> parseOrders(String answer) {
        String[] parsedAnswer = answer.split(",", -1);
        InputValidator.validateOrders(parsedAnswer);
        return Arrays.stream(parsedAnswer).toList();
    }
}
