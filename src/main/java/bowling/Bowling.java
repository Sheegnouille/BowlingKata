package bowling;

import java.util.ArrayList;

class Bowling {

    public static final int MAX_FRAMES_PER_GAME = 10;
    private static ArrayList<Frame> frames;
    private static Frame currentFrame;

    static int computeScore(String line) {
        frames = new ArrayList<>();
        currentFrame = null;
        convertLineToFrames(line);

        return frames.stream()
                .filter(frame -> !frame.isBonusFrame())
                .reduce(0, (score, frame) -> score + getFrameScore(frame), Integer::sum);
    }

    private static int getFrameScore(Frame frame) {
        int frameScore;
        frameScore = frame.getPins();
        if (frame.isSpare()) {
            frameScore += calculateSpareBonus(frame);
        }
        if (frame.isStrike()) {
            frameScore += calculateStrikeBonus(frame);
        }
        return frameScore;
    }

    private static int calculateSpareBonus(Frame frame) {
        if (frame.hasNextFrame()) {
            return frame.getNextFrame().getFirstRoll();
        }
        return 0;
    }

    private static int calculateStrikeBonus(Frame frame) {
        Frame nextFrame = frame.getNextFrame();
        if (nextFrame.isStrike()) {
            Frame secondNextFrame = nextFrame.getNextFrame();
            return nextFrame.getFirstRoll() +
                    secondNextFrame.getFirstRoll();
        }
        return nextFrame.getPins();
    }

    private static void convertLineToFrames(String line) {
        for (String roll : line.split("")) {
            currentFrame().addRoll(roll);
        }
    }

    private static Frame currentFrame() {
        if (currentFrame == null) {
            currentFrame = new Frame(false);
            frames.add(currentFrame);
        }
        if (currentFrame.isFinished()) {
            currentFrame = currentFrame.newFrame(frames.size() >= MAX_FRAMES_PER_GAME);
            frames.add(currentFrame);
        }
        return currentFrame;
    }

}
