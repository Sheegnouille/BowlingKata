package bowling;

import java.util.ArrayList;

class Frame {
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
        return rolls.get(0);
    }

    boolean isFinished() {
        return getPins() == 10 || rolls.size() == 2;
    }

    private boolean isStrike() {
        return rolls.size() == 1 && getPins() == 10;
    }

    private boolean isSpare() {
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
        int frameScore;
        frameScore = getPins();
        if (isSpare()) {
            frameScore += calculateSpareBonus();
        }
        if (isStrike()) {
            frameScore += calculateStrikeBonus();
        }
        return frameScore;
    }
}
