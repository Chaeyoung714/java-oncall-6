package oncall.view;

import java.util.regex.Pattern;
import oncall.exception.ExceptionMessages;

public class InputValidator {

    public static void validateMonthInformation(String[] parsedAnswer) {
        try {
            if (parsedAnswer.length != 2) {
                throw new IllegalArgumentException(ExceptionMessages.WRONG_INPUT_VALUE.getMessage());
            }
            if (parsedAnswer[1] == null || parsedAnswer[1].isBlank()) {
                throw new IllegalArgumentException(ExceptionMessages.WRONG_INPUT_VALUE.getMessage());
            }
            Integer.parseInt(parsedAnswer[0]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ExceptionMessages.WRONG_INPUT_VALUE.getMessage());
        }
    }

    public static void validateOrders(String[] parsedAnswer) {
        for (String order : parsedAnswer) {
            //TODO : 그외 영, 한만 허용 등
            if (order == null || order.isBlank()) {
                throw new IllegalArgumentException(ExceptionMessages.WRONG_INPUT_VALUE.getMessage());
            }
//            if (!order.matches(Pattern.quote("/^[ㄱ-ㅎ가-힣a-zA-Z]+$/"))) {
//                throw new IllegalArgumentException(ExceptionMessages.WRONG_INPUT_VALUE.getMessage());
//            }
        }
    }
}
