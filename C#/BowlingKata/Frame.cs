using System.Collections.Generic;
using System.Linq;

namespace BowlingKata
{
    public class Frame
    {
        private static readonly int MaxNumberOfPin = 10; 
        private static readonly int MaxRoolPerFrame = 2;
        private List<int> rolls = new List<int>();

        public int Pins => rolls.Sum();

        public bool IsFinished => Pins == MaxNumberOfPin || rolls.Count == MaxRoolPerFrame;
    
        public void AddRoll(string roll)
        {
            this.rolls.Add(TranslateRollToScore(roll));
        }

        private int TranslateRollToScore(string roll)
        {
            if ("-" == roll) return 0;
            return int.Parse(roll);
        }
    
    }
}
