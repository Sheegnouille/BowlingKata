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

    @Test
    @Parameters({ZERO_PIN_LINE, ONE_PIN_FIRST_LINE, ONE_PIN_LAST_LINE,
                ONE_PIN_MIDDLE_LINE, ONE_PIN_EACH_FRAME_MIDDLE_LINE, TWO_PIN_FIRST_LINE,
                HEARTBREAK})
    public void given_a_game_should_return_score(String line, int score) {
        assertThat(new Bowling().computeScore(line)).isEqualTo(score);
    }
}
