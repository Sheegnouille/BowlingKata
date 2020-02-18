using System.Collections.Generic;
using System.Linq;

namespace BowlingKata
{
    public class Bowling
    {
        private static readonly int MaxNumberOfRoll = 10;
        private Frame currentFrame;

        public int ComputeScore(string line)
        {
            var score = 0;
            //ConvertLineToFrame(line).ForEach(frame =>
            //{
            //    score += frame.Pins;
            //});
            score = ConvertLineToFrame(line)
                .Where(frame => !frame.IsBonusFrame())
                .Select(frame => frame.GetFrameScore())
                .Sum();
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
            if (currentFrame == null)
            {
                currentFrame = new Frame(false);
                frames.Add(currentFrame);
            }
            else if (currentFrame.IsFinished)
            {
                currentFrame = currentFrame.NewFrame(frames.Count >= MaxNumberOfRoll);
                frames.Add(currentFrame);
            }
            return currentFrame;

        }
    }
}
