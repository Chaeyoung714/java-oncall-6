package oncall.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public String readMonth() {
        System.out.print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ");
        return Console.readLine();
    }
}
