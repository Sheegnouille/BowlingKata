package bowling;

import java.util.ArrayList;

class Frame {
    public static final int MAX_PINS_PER_FRAME = 10;
    public static final int MAX_ROLLS_PER_FRAME = 2;
    private ArrayList<Integer> rolls = new ArrayList<>();
    private Frame nextFrame;
    private boolean bonusFrame;

    Frame(boolean isBonusFrame) {
        bonusFrame = isBonusFrame;
    }

    private Frame getNextFrame() {
        return nextFrame;
    }

    private boolean hasNextFrame() {
        return nextFrame != null;
    }

    boolean isBonusFrame() {
        return bonusFrame;
    }

    private Integer getPins() {
        return rolls.stream().reduce(Integer::sum).orElse(0);
    }

    private Integer getFirstRoll() {
        if (rolls.size() > 0) {
            return rolls.get(0);
        }
        return 0;
    }

    boolean isFinished() {
        return allPinsFell() || rolls.size() == MAX_ROLLS_PER_FRAME;
    }

    private boolean allPinsFell() {
        return getPins() == MAX_PINS_PER_FRAME;
    }

    private boolean isStrike() {
        return rolls.size() == 1 && allPinsFell();
    }

    private boolean isSpare() {
        return rolls.size() == MAX_ROLLS_PER_FRAME && allPinsFell();
    }

    void addRoll(String roll) {
        rolls.add(translate(roll));
    }

    private int translate(String roll) {
        switch (roll) {
            case "X":
                return MAX_PINS_PER_FRAME;
            case "/":
                return MAX_PINS_PER_FRAME - getPins();
            case "-":
                return 0;
            default:
                return Integer.parseInt(roll);
        }
    }

    Frame newFrame(boolean isBonusFrame) {
        nextFrame = new Frame(isBonusFrame);
        return nextFrame;
    }

    private int calculateStrikeBonus() {
        Frame nextFrame = getNextFrame();
        if (nextFrame.isStrike()) {
            Frame secondNextFrame = nextFrame.getNextFrame();
            return nextFrame.getFirstRoll() +
                    secondNextFrame.getFirstRoll();
        }
        return nextFrame.getPins();
    }

    private int calculateSpareBonus() {
        if (hasNextFrame()) {
            return getNextFrame().getFirstRoll();
        }
        return 0;
    }

    int getFrameScore() {
        if (isSpare()) {
            return getPins() + calculateSpareBonus();
        }
        if (isStrike()) {
            return getPins() + calculateStrikeBonus();
        }
        return getPins();
    }
}
