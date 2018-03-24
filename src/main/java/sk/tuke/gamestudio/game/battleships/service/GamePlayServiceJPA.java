package sk.tuke.gamestudio.game.battleships.service;

import sk.tuke.gamestudio.game.battleships.entity.GamePlay;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
public class GamePlayServiceJPA implements GamePlayService {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void store(GamePlay gamePlay) {
        entityManager.persist(gamePlay);
    }

    @Override
    public GamePlay load(int ident) {
        return entityManager.find(GamePlay.class, ident);
    }

    @Override
    public GamePlay loadLast() {
        try {
            return (GamePlay) entityManager.createQuery(
                    "SELECT g FROM GamePlay g WHERE g.ident = (SELECT MAX(g2.ident) FROM GamePlay g2)").getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
