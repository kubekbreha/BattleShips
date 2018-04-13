package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Rating;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
public class RatingServiceJPA implements RatingService {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void setRating(Rating rating) throws RatingException {
        Rating oldRating;
        try {
            oldRating = (Rating) entityManager.createQuery("SELECT r FROM Rating r WHERE r.player = :player AND r.game = :game")
                    .setParameter("player", rating.getPlayer()).setParameter("game", rating.getGame())
                    .getSingleResult();

            oldRating.setRating(rating.getRating());
        } catch (NoResultException e) {
            entityManager.persist(rating);
        }

    }

    @Override
    public int getAverageRating(String game) throws RatingException {
        Double avgrating = (Double) entityManager
                .createQuery("SELECT AVG(r.rating) from Rating r WHERE r.game =  :game").setParameter("game", game)
                .getSingleResult();
        if (avgrating == null) {
            return -1;
        }

        return avgrating.intValue();

    }




    @Override
    public int getRating(String game, String player) throws RatingException {
        Integer rating = (Integer) entityManager
                .createQuery("Select r.rating from Rating r WHERE r.game = :game AND r.player = :player")
                .setParameter("game", game).setParameter("player", player).getSingleResult();
        return rating;

    }

}