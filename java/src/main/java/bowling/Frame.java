package bowling;

import java.util.ArrayList;

class Frame {
    private static final int MAX_NUMBER_OF_PIN = 10;
    private static final int MAX_ROLL_PER_FRAME = 2;
    private ArrayList<Integer> rolls = new ArrayList<>();

    Integer getPins() {
        return rolls.stream().mapToInt(Integer::intValue).sum();
    }

    boolean isFinished() {
        return getPins() == MAX_NUMBER_OF_PIN || rolls.size() == MAX_ROLL_PER_FRAME;
    }

    void addRoll(String roll) {
        this.rolls.add(translateRollToScore(roll));
    }

    private int translateRollToScore(String roll) {
        if ("-".equals(roll)) {
            return 0;
        }
        return Integer.parseInt(roll);
    }
}
