using BowlingKata;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace BowlingKataTest
{
    [TestClass]
    public class BowlingTest
    {
        private static readonly string  ZERO_PIN_LINE = "--------------------, 0";
        private static readonly string  ONE_PIN_LAST_LINE = "-------------------1, 1";
        private static readonly string  ONE_PIN_MIDDLE_LINE = "--------1-----------, 1";
        private static readonly string  ONE_PIN_EACH_FRAME_MIDDLE_LINE = "1-1-1-1-1-1-1-1-1-1-, 10";
        private static readonly string  TWO_PIN_FIRST_LINE = "2-------------------, 2";
        private static readonly string  HEARTBREAK = "9-9-9-9-9-9-9-9-9-9-, 90";

        [DataTestMethod]
        [DataRow("--------------------", 0)]
        [DataRow("1-------------------", 1)]
        [DataRow("-------------------1", 1)]
        [DataRow("--------1-----------", 1)]
        [DataRow("1-1-1-1-1-1-1-1-1-1-", 10)]
        [DataRow("2-------------------", 2)]
        [DataRow("9-9-9-9-9-9-9-9-9-9-", 90)]
        public void GivenAGameShouldReturnScore(string line, int score)
        {
            Assert.AreEqual(new Bowling().ComputeScore(line), score);
        }
    }
}
