package sk.tuke.gamestudio.game.battleships.service;


import sk.tuke.gamestudio.game.battleships.entity.GamePlay;

public interface GamePlayService {
    void store(GamePlay gamePlay);

    GamePlay load(int ident);

    GamePlay loadLast();
}
