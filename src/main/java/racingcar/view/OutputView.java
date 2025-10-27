package racingcar.view;

import racingcar.domain.Car;

import java.util.List;

public class OutputView {
    private static final String START_MESSAGE = "\n실행 결과";
    private static final String ROUND_RESULT_FORMAT = "%s : %s";
    private static final String POSITION_MARKER = "-";
    private static final String WINNER_ANNOUNCEMENT = "최종 우승자 : %s";
    private static final String WINNER_DELIMITER = ", ";

    public void printStartMessage() {
        System.out.println(START_MESSAGE);
    }

    public void printRoundResult(List<Car> cars) {
        for (Car car : cars) {
            String positionString = convertPositionToHyphens(car.getPosition());
            System.out.printf((ROUND_RESULT_FORMAT) + "%n", car.getName(), positionString);
        }
        System.out.println(); // 각 라운드 실행 후 빈 줄 출력
    }

    private String convertPositionToHyphens(int position) {
        return POSITION_MARKER.repeat(position);
    }

    public void printWinners(List<String> winners) {
        String winnerNames = String.join(WINNER_DELIMITER, winners);
        System.out.printf((WINNER_ANNOUNCEMENT) + "%n", winnerNames);
    }
}
