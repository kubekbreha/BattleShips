import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import sk.tuke.gamestudio.game.battleships.brehuv.core.util.Util;

/**
 * Created by Kubo Brehuv with <3 (14.3.2018)
 */
public class TestRunner {

    /**
     * Run all tests at same time.
     * @param args of main.
     */
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(AITest.class, BoardTest.class, GameStateTest.class,
                ShipTest.class, ShootTest.class, CommentServiceJDBCTest.class,
                RatingServiceJDBCTest.class, ScoreServiceJDBCTest.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        if (result.wasSuccessful()) {
            System.out.println(Util.ANSI_GREEN + "All tests was successful." + Util.ANSI_RESET);
        } else {
            System.out.println(Util.ANSI_RED + "Some test failed." + Util.ANSI_RESET);
        }
    }
}