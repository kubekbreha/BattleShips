package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Score;

import java.io.*;
import java.util.*;

import static sk.tuke.gamestudio.game.battleships.core.board.Board.GAME_NAME;

/**
 * Created by Kubo Brehuv with <3 (23.3.2018)
 */
public class ScoreServiceFile implements ScoreService {

    /**
     * Get file name.
     *
     * @param game name.
     * @return filename.
     */
    private String getFileName(String game) {
        return System.getProperty("user.home") + "/" + game+"-score" + ".score";
    }

    /**
     * Add score object to file.
     *
     * @param score which will be added.
     * @throws ScoreException
     */
    @Override
    public void addScore(Score score) throws ScoreException {
        List<Score> scores = getBestScores(GAME_NAME);
        scores.add(score);
        Collections.sort(scores);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(getFileName(GAME_NAME)))) {
            oos.writeObject(scores);
        } catch (Exception e) {
            throw new ScoreException("Error saving score", e);
        }
    }

    /**
     * Get list of scores objects from file.
     *
     * @param game name
     * @return list of scores.
     * @throws ScoreException
     */
    @Override
    public List<Score> getBestScores(String game) throws ScoreException {
        List<Score> scores = new ArrayList<>();
        File file = new File(getFileName(game));
        if (file.exists()) {
            try (ObjectInputStream ois
                         = new ObjectInputStream(
                    new FileInputStream(file))) {
                scores = (List<Score>) ois.readObject();
            } catch (Exception e) {
                throw new ScoreException("Error loading score", e);
            }
        }
        return scores;
    }

    public static void main(String[] args) throws Exception {
        Score score = new Score("jaro", GAME_NAME,  100, new java.util.Date());
        ScoreService scoreService = new ScoreServiceFile();
        scoreService.addScore(score);
        System.out.println(scoreService.getBestScores(GAME_NAME));
    }
}
