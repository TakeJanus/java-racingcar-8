package racingcar.domain;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static org.assertj.core.api.Assertions.assertThat;

class RaceTest extends NsTest {
    @Test
    @DisplayName("자동차 이름 목록으로 Race 객체를 생성할 수 있다.")
    void createRace() {
        List<String> carNames = List.of("pobi", "woni", "jun");

        Race race = new Race(carNames);
        List<Car> cars = race.getCars();

        assertThat(cars).hasSize(3);
        assertThat(cars.get(0).getName()).isEqualTo("pobi");
        assertThat(cars.get(1).getName()).isEqualTo("woni");
        assertThat(cars.get(2).getName()).isEqualTo("jun");
    }

    @Test
    @DisplayName("경주 라운드 실행 시 전진/정지 로직이 올바르게 동작한다.")
    void runRound_Moves_And_Stops() {
        Race race = new Race(List.of("pobi", "woni"));

        assertRandomNumberInRangeTest(
                () -> {
                    race.runRound(); // 1라운드 실행
                    List<Car> cars = race.getCars();
                    assertThat(cars.get(0).getPosition()).isEqualTo(1); // pobi 전진
                    assertThat(cars.get(1).getPosition()).isEqualTo(0); // woni 정지
                },
                4, // pobi의 랜덤 숫자 (전진)
                3  // woni의 랜덤 숫자 (정지)
        );
    }

    @Test
    @DisplayName("최종 우승자를 올바르게 판별한다. (단독 우승)")
    void getWinners_Single_Winner() {
        Race race = new Race(List.of("pobi", "woni"));

        assertRandomNumberInRangeTest(
                () -> {
                    race.runRound(); // 1라운드: pobi(1), woni(0)
                    race.runRound(); // 2라운드: pobi(2), woni(0)

                    List<String> winners = race.getWinners();
                    assertThat(winners).containsExactly("pobi");
                },
                5, 3, // 1라운드 랜덤 값 (pobi, woni)
                5, 3  // 2라운드 랜덤 값 (pobi, woni)
        );
    }

    @Test
    @DisplayName("최종 우승자를 올바르게 판별한다. (공동 우승)")
    void getWinners_Joint_Winners() {
        Race race = new Race(List.of("pobi", "woni", "jun"));

        assertRandomNumberInRangeTest(
                () -> {
                    race.runRound(); // pobi(1), woni(0), jun(1)

                    List<String> winners = race.getWinners();
                    assertThat(winners).containsExactlyInAnyOrder("pobi", "jun");
                },
                5, // pobi (전진)
                3, // woni (정지)
                9  // jun (전진)
        );
    }

    @Override
    protected void runMain() {}
}