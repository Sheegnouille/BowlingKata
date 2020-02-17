using System.Collections.Generic;

namespace BowlingKata
{
    public class Bowling
    {
        private Frame currentFrame;

        public int ComputeScore(string line)
        {
            var score = 0;
            ConvertLineToFrame(line).ForEach(frame =>
            {
                score += frame.Pins;
            });
            return score;

        }

        private List<Frame> ConvertLineToFrame(string line)
        {
            List<Frame> frames = new List<Frame>();

            foreach (var roll in line)
            {
                CurrentFrame(frames).AddRoll(roll.ToString());
            }
            return frames;
        }

        private Frame CurrentFrame(List<Frame> frames)
        {
            if (currentFrame == null || currentFrame.IsFinished)
            {
                currentFrame = new Frame();
                frames.Add(currentFrame);
            }
            return currentFrame;

        }
    }
}
