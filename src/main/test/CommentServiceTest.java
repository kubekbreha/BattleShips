import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.service.CommentService;
import org.junit.Test;

import java.util.Date;

import static sk.tuke.gamestudio.game.battleships.core.board.Board.GAME_NAME;
import static junit.framework.TestCase.*;

/**
 * Created by Kubo Brehuv with <3 (10.3.2018)
 */
public class CommentServiceTest {
    protected CommentService commentService;

    @Test
    public void testDbInit() throws Exception {
        assertEquals(0, commentService.getComments(GAME_NAME).size());
    }

    @Test
    public void testAddComment() throws Exception {
        Comment comment = new Comment(GAME_NAME, "miska", "perfect game", new Date());
        commentService.addComment(comment);
        assertEquals(1, commentService.getComments(GAME_NAME).size());
    }
}
