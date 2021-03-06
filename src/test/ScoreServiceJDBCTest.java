import sk.tuke.gamestudio.service.ScoreServiceJDBC;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Created by Kubo Brehuv with <3 (10.3.2018)
 */
public class ScoreServiceJDBCTest extends ScoreServiceTest {

    public static final String DELETE = "DELETE FROM score";

    public static final String URL = "jdbc:mysql://localhost/gamestudio";
    public static final String USER = "root";
    public static final String PASSWORD = "Hackathon16";

    public ScoreServiceJDBCTest() {
        super.scoreService = new ScoreServiceJDBC();
    }

    @Before
    public void setUp() throws Exception {
        Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement s = c.createStatement();
        s.execute(DELETE);
    }

    @Test
    public void testDbInit() throws Exception {
        super.testDbInit();
    }

    @Test
    public void testAddScore() throws Exception {
        super.testAddScore();
    }

    @Test
    public void testGetBestScores() throws Exception {
        super.testGetBestScores();
    }
}
