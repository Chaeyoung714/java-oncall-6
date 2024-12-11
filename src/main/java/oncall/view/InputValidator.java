package oncall.view;

import java.util.regex.Pattern;
import oncall.exception.ExceptionMessages;

public class InputValidator {
    private static final int MONTH = 0;
    private static final int DAY = 1;


    public static void validateMonthInformation(String[] parsedAnswer) {
        try {
            if (parsedAnswer.length != 2) {
                throw new IllegalArgumentException(ExceptionMessages.WRONG_INPUT_VALUE.getMessage());
            }
            if (parsedAnswer[DAY] == null || parsedAnswer[DAY].isBlank()) {
                throw new IllegalArgumentException(ExceptionMessages.WRONG_INPUT_VALUE.getMessage());
            }
            Integer.parseInt(parsedAnswer[MONTH]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ExceptionMessages.WRONG_INPUT_VALUE.getMessage());
        }
    }

    public static void validateOrders(String[] parsedAnswer) {
        for (String order : parsedAnswer) {
            if (order == null || order.isBlank()) {
                throw new IllegalArgumentException(ExceptionMessages.WRONG_INPUT_VALUE.getMessage());
            }
            if (!Pattern.matches("^[0-9a-zA-Zㄱ-ㅎ가-힣]*$", order)) {
                throw new IllegalArgumentException(ExceptionMessages.WRONG_INPUT_VALUE.getMessage());
            }
        }
    }
}
