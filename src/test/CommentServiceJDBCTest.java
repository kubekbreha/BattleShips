import sk.tuke.gamestudio.service.CommentServiceJDBC;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Created by Kubo Brehuv with <3 (10.3.2018)
 */
public class CommentServiceJDBCTest extends CommentServiceTest {

    public static final String DELETE = "DELETE FROM comment";

    public static final String URL = "jdbc:mysql://localhost/gamestudio";
    public static final String USER = "root";
    public static final String PASSWORD = "Hackathon16";

    public CommentServiceJDBCTest() {
        super.commentService = new CommentServiceJDBC();
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
    public void testAddComment() throws Exception {
        super.testAddComment();
    }
}
