import com.gamestudio.service.RatingServiceJDBC;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class RatingServiceJDBCTest extends RatingServiceTest {

    public static final String DELETE = "DELETE FROM rating";

    public static final String URL = "jdbc:mysql://localhost/gamestudio";
    public static final String USER = "root";
    public static final String PASSWORD = "Hackathon16";

    public RatingServiceJDBCTest() {
        super.ratingService = new RatingServiceJDBC();
    }

    @Before
    public void setUp() throws Exception {
        Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement s = c.createStatement();
        s.execute(DELETE);
    }

    @Test
    public void testAddRating() throws Exception {
        super.testAddRating();
    }
}
