package bowling;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class BowlingTest {

    private static final String ZERO_PIN_LINE = "--------------------, 0";
    private static final String ONE_PIN_FIRST_LINE = "1-------------------, 1";
    private static final String ONE_PIN_LAST_LINE = "-------------------1, 1";
    private static final String ONE_PIN_MIDDLE_LINE = "--------1-----------, 1";
    private static final String ONE_PIN_EACH_FRAME_MIDDLE_LINE = "1-1-1-1-1-1-1-1-1-1-, 10";
    private static final String TWO_PIN_FIRST_LINE = "2-------------------, 2";
    private static final String HEARTBREAK = "9-9-9-9-9-9-9-9-9-9-, 90";
    private static final String ONE_STRIKE_FIRST_LINE = "X------------------, 10";
    private static final String TWO_STRIKES_FIRST_LINE = "XX----------------, 30";
    private static final String PERFECT_GAME = "XXXXXXXXXXXX, 300";
    private static final String FIVE_SPARE_EVERY_FRAME = "5/5/5/5/5/5/5/5/5/5/5, 150";
    private static final String NINE_SPARE_EVERY_FRAME = "9/9/9/9/9/9/9/9/9/9/9, 190";
    private static final String STRIKE_ZERO_SPARE = "X-/X-/X-/X-/X-/X, 200";

    @Test
    @Parameters({ZERO_PIN_LINE, ONE_PIN_FIRST_LINE, ONE_PIN_LAST_LINE,
                ONE_PIN_MIDDLE_LINE, ONE_PIN_EACH_FRAME_MIDDLE_LINE, TWO_PIN_FIRST_LINE,
                HEARTBREAK, ONE_STRIKE_FIRST_LINE, TWO_STRIKES_FIRST_LINE,
                PERFECT_GAME, FIVE_SPARE_EVERY_FRAME, NINE_SPARE_EVERY_FRAME,
                STRIKE_ZERO_SPARE})
    public void given_a_game_should_return_score(String line, int score) {
        assertThat(Bowling.computeScore(line)).isEqualTo(score);
    }
}
