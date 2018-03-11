package com.gamestudio.service;


import com.gamestudio.entity.Score;

import java.util.List;

public interface ScoreService {
    void addScore(Score score) throws ScoreException;
    List<Score> getBestScores(String game) throws ScoreException;
}
