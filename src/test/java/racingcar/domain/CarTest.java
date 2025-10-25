package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CarTest {

    @Test
    @DisplayName("자동차 이름이 5자를 초과하면 예외가 발생한다.")
    void createCarWithInvalidNameLength() {
        String longName = "porsche"; // 7자 이름

        assertThatThrownBy(() -> new Car(longName))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("자동차 이름이 공백이면 예외가 발생한다.")
    void createCarWithBlankName() {
        String blankName = " ";

        assertThatThrownBy(() -> new Car(blankName))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("전진 조건(4 이상)을 만족하면 position이 1 증가한다.")
    void moveWhenConditionIsMet() {
        Car car = new Car("test");

        car.move(4); // 전진 조건인 4를 입력

        assertThat(car.getPosition()).isEqualTo(1);
    }

    @Test
    @DisplayName("정지 조건(4 미만)을 만족하면 position이 변하지 않는다.")
    void stopWhenConditionIsNotMet() {
        Car car = new Car("test");

        car.move(3); // 정지 조건인 3을 입력

        assertThat(car.getPosition()).isZero();
    }
}