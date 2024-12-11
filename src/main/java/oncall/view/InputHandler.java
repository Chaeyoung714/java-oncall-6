package oncall.view;


import java.util.Arrays;
import java.util.List;
import oncall.dto.MonthDto;
import oncall.exception.RetryHandler;

public class InputHandler {
    private static final String PARSING_DELIMITER = ",";
    private static final int MONTH = 0;
    private static final int DAY = 1;

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
        String[] parsedAnswer = answer.split(PARSING_DELIMITER);
        InputValidator.validateMonthInformation(parsedAnswer);
        return new MonthDto(Integer.parseInt(parsedAnswer[MONTH]), parsedAnswer[DAY]);
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
        String[] parsedAnswer = answer.split(PARSING_DELIMITER, -1);
        InputValidator.validateOrders(parsedAnswer);
        return Arrays.stream(parsedAnswer).toList();
    }
}
