package sk.tuke.gamestudio.service;

/**
 * Created by Kubo Brehuv with <3 (11.3.2018)
 */
import sk.tuke.gamestudio.entity.Score;

import java.util.List;

public interface ScoreService {
    void addScore(Score score) throws ScoreException;
    List<Score> getBestScores(String game) throws ScoreException;
}
