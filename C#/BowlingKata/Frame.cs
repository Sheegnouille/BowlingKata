using System.Collections.Generic;
using System.Linq;

namespace BowlingKata
{
    public class Frame
    {
        private static readonly int MaxNumberOfPin = 10; 
        private static readonly int MaxRoolPerFrame = 2;
        private List<int> rolls = new List<int>();
        private Frame nextFrame;
        private bool bonusFrame;

        public Frame(bool isBonusFrame)
        {
            bonusFrame = isBonusFrame;
        }

        private Frame GetNextFrame()
        {
            return nextFrame;
        }

        private bool HasNextFrame()
        {
            return nextFrame != null;
        }

        public bool IsBonusFrame()
        {
            return bonusFrame;
        }

        private int GetFirstRoll()
        {
            if(rolls.Count > 0)
            {
                return rolls.ElementAt(0);
            }
            return 0;
        }

        public int Pins => rolls.Sum();

        public bool IsFinished => Pins == MaxNumberOfPin || rolls.Count == MaxRoolPerFrame;

        private bool IsStrike()
        {
            return rolls.Count() == 1 && Pins == MaxNumberOfPin;
        }

        private bool IsSpare()
        {
            return rolls.Count() == 2 && Pins == MaxNumberOfPin;
        }

        public void AddRoll(string roll)
        {
            this.rolls.Add(TranslateRollToScore(roll));
        }

        private int TranslateRollToScore(string roll)
        {
            switch (roll)
            {
                case "-":
                    return 0;
                case "X":
                    return 10;
                case "/":
                    return MaxNumberOfPin - Pins;
                default:
                    return int.Parse(roll);
            }
        }

        public Frame NewFrame(bool isBonusFrame)
        {
            nextFrame = new Frame(isBonusFrame);
            return nextFrame;
        }

        private int CalculateStrikeBonus()
        {
            Frame nextFrame = GetNextFrame();
            if (nextFrame.IsStrike())
            {
                Frame secondNextFrame = nextFrame.GetNextFrame();
                return nextFrame.GetFirstRoll() +
                        secondNextFrame.GetFirstRoll();
            }
            return nextFrame.Pins;
        }

        private int CalculateSpareBonus()
        {
            if (HasNextFrame())
            {
                return GetNextFrame().GetFirstRoll();
            }
            return 0;
        }

        public int GetFrameScore()
        {
            int frameScore;
            frameScore = Pins;
            if (IsSpare())
            {
                frameScore += CalculateSpareBonus();
            }
            if (IsStrike())
            {
                frameScore += CalculateStrikeBonus();
            }
            return frameScore;
        }
    }
}
