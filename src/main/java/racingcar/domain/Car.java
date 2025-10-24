package racingcar.domain;

public class Car {
    private static final int MAX_NAME_LENGTH = 5;
    private static final int FORWARD_CONDITION = 4;


    private final String name;
    private int position = 0;

    public Car(String name) {
        validateNameLength(name);
        this.name = name;
    }

    private void validateNameLength(String name) {
        if (name == null || name.isBlank() || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("자동차 이름은 1~5자만 가능합니다.");
        }
    }

    public void move(int randomNumber) {
        if (randomNumber >= FORWARD_CONDITION) {
            this.position++;
        }
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
