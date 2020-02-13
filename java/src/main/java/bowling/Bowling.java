package bowling;

import java.util.ArrayList;
import java.util.List;

class Bowling {

    private Frame currentFrame;

    int computeScore(String line) {
        return convertLineToFrames(line)
                .stream()
                .reduce(0, (score, frame) -> score + (int) frame.getPins(), Integer::sum);
    }

    private List<Frame> convertLineToFrames(String line) {
        List<Frame> frames = new ArrayList<Frame>();
        for (String roll : line.split("")) {
            currentFrame(frames).addRoll(roll);
        }
        return frames;
    }

    private Frame currentFrame(List<Frame> frames) {
        if ((currentFrame == null) || (currentFrame.isFinished())) {
            currentFrame = new Frame();
            frames.add(currentFrame);
        }
        return currentFrame;
    }

}
