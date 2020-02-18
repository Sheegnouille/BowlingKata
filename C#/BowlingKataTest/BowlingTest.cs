using BowlingKata;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace BowlingKataTest
{
    [TestClass]
    public class BowlingTest
    {
        [DataTestMethod]
        [DataRow("--------------------", 0)]
        [DataRow("1-------------------", 1)]
        [DataRow("-------------------1", 1)]
        [DataRow("--------1-----------", 1)]
        [DataRow("1-1-1-1-1-1-1-1-1-1-", 10)]
        [DataRow("2-------------------", 2)]
        [DataRow("9-9-9-9-9-9-9-9-9-9-", 90)]
        [DataRow("X------------------", 10)]
        [DataRow("XX----------------", 30)]
        [DataRow("XXXXXXXXXXXX", 300)]
        [DataRow("5/5/5/5/5/5/5/5/5/5/5", 150)]
        [DataRow("9/9/9/9/9/9/9/9/9/9/9", 190)]
        [DataRow("X-/X-/X-/X-/X-/X", 200)]
        public void GivenAGameShouldReturnScore(string line, int score)
        {
            Assert.AreEqual(score, new Bowling().ComputeScore(line));
        }
    }
}
