package bowling;

import java.util.ArrayList;

class Bowling {

    private static final int MAX_FRAMES_PER_GAME = 10;
    private ArrayList<Frame> frames;
    private Frame currentFrame;

    int computeScore(String line) {
        frames = new ArrayList<>();
        convertLineToFrames(line);

        return frames.stream()
                .filter(frame -> !frame.isBonusFrame())
                .reduce(0, (score, frame) -> score + frame.getFrameScore(), Integer::sum);
    }

    private void convertLineToFrames(String line) {
        for (String roll : line.split("")) {
            currentFrame().addRoll(roll);
        }
    }

    private Frame currentFrame() {
        if (currentFrame == null) {
            currentFrame = new Frame(false);
            frames.add(currentFrame);
        }
        if (currentFrame.isFinished()) {
            currentFrame = currentFrame.newFrame(isMaxFrameReached());
            frames.add(currentFrame);
        }
        return currentFrame;
    }

    private boolean isMaxFrameReached() {
        return frames.size() >= MAX_FRAMES_PER_GAME;
    }

}
