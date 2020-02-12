package bowling;

import java.util.ArrayList;

public class Frame {
    private ArrayList<Integer> rolls = new ArrayList<>();
    private Frame nextFrame;
    private boolean bonusFrame;

    public Frame(boolean isBonusFrame) {
        bonusFrame = isBonusFrame;
    }

    public Frame getNextFrame() {
        return nextFrame;
    }

    public boolean hasNextFrame() {
        return nextFrame != null;
    }

    public boolean isBonusFrame() {
        return bonusFrame;
    }

    public Integer getPins() {
        return rolls.stream().reduce(Integer::sum).orElse(0);
    }

    public Integer getFirstRoll() {
        return rolls.get(0);
    }

    public boolean isFinished() {
        return getPins() == 10 || rolls.size() == 2;
    }

    public boolean isStrike() {
        return rolls.size() == 1 && getPins() == 10;
    }

    public boolean isSpare() {
        return rolls.size() == 2 && getPins() == 10;
    }

    void addRoll(String roll) {
        int rollValue;
        switch (roll) {
            case "X" :
                rollValue = 10;
                break;
            case "/" :
                rollValue = 10 - getPins();
                break;
            case "-" :
                rollValue = 0;
                break;
            default:
                rollValue = Integer.parseInt(roll);
        }
        this.rolls.add(rollValue);
    }

    public Frame newFrame(boolean isBonusFrame) {
        nextFrame = new Frame(isBonusFrame);
        return nextFrame;
    }
}
