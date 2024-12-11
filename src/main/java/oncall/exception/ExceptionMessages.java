package oncall.exception;

public enum ExceptionMessages {
    WRONG_INPUT_VALUE("유효하지 않은 입력 값입니다. 다시 입력해 주세요.")
    ;

    private final String message;

    ExceptionMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
