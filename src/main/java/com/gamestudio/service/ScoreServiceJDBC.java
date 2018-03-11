package com.gamestudio.service;

import com.gamestudio.entity.Score;

import java.sql.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/*
    CREATE TABLE score (
        player VARCHAR(64) NOT NULL,
        game VARCHAR(64) NOT NULL,
        points INTEGER NOT NULL,
        playedon TIMESTAMP NOT NULL
    );
     */

//INSERT INTO score (player, game, points, playedon) VALUES ('jaro', 'mines', 200, '2017-03-02 14:30')

//SELECT player, game, points, playedon FROM score WHERE game = 'mines' ORDER BY points DESC LIMIT 10;

public class ScoreServiceJDBC implements ScoreService {

    public static final String URL = "jdbc:mysql://localhost/gamestudio";
    public static final String USER = "root";
    public static final String PASSWORD = "Hackathon16";

    public static final String INSERT_SCORE =
    "INSERT INTO score (player, game, points, playedon) VALUES (?, ?, ?, ?)";

    public static final String SELECT_SCORE =
        "SELECT player, game, points, playedon FROM score WHERE game = ? ORDER BY points DESC LIMIT 10;";


    @Override
    public void addScore(Score score) throws ScoreException {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try(PreparedStatement ps = connection.prepareStatement(INSERT_SCORE)){
                ps.setString(1, score.getGame());
                ps.setString(2, score.getPlayer());
                ps.setInt(3, score.getPoints());
                ps.setDate(4, new Date(score.getPlayedOn().getTime()));

                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new ScoreException("Error saving score", e);
        }
    }


    @Override
    public List<Score> getBestScores(String game) throws ScoreException {
        List<Score> scores = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try(PreparedStatement ps = connection.prepareStatement(SELECT_SCORE)){
                ps.setString(1, game);
                try(ResultSet rs = ps.executeQuery()) {
                    while(rs.next()) {
                        Score score = new Score(
                                rs.getString(1),
                                rs.getString(2),
                                rs.getInt(3),
                                rs.getTimestamp(4)
                        );
                        scores.add(score);
                    }
                }
            }
        } catch (SQLException e) {
            throw new ScoreException("Error loading score", e);
        }
        return scores;
    }
}
