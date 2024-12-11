package oncall;

import oncall.model.assignment.WorkingMonth;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ValidationTest {
    @Test
    void 잘못된_월을_입력하면_예외가_발생한다() {
        Assertions.assertThatThrownBy(() -> WorkingMonth.of(0, "월"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 잘못된_요일을_입력하면_예외가_발생한다() {
        Assertions.assertThatThrownBy(() -> WorkingMonth.of(10, "톹"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
