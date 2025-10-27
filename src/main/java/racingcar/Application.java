package racingcar;

import racingcar.domain.Race;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.List;

public class Application { // controller 계층

    private final InputView inputView;
    private final OutputView outputView;

    public Application() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public static void main(String[] args) {
        Application app = new Application();
        app.run();
    }

    public void run() {
        // InputView 입력 받기
        List<String> carNames = inputView.readCarNames();
        int tryCount = inputView.readTryCount();

        // Race(Domain) 객체 생성
        Race race = new Race(carNames);

        // OutputView 실행 결과 출력
        outputView.printStartMessage();

        // race + outputView 횟수만큼 게임 라운드 실행 및 결과 출력
        for (int i = 0; i < tryCount; i++) {
            race.runRound();
            outputView.printRoundResult(race.getCars()); // [View] 1 라운드 결과 출력
        }

        // 최종 우승자 발표
        List<String> winners = race.getWinners(); // 우승자 판별
        outputView.printWinners(winners); // 우승자 출력
    }
}
