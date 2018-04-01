package sk.tuke.gamestudio.server.service;

import sk.tuke.gamestudio.server.entity.Rating;
import sk.tuke.gamestudio.server.entity.Score;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class RatingServiceJPA implements RatingService {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void setRating(Rating rating) throws RatingException {
        entityManager.persist(rating);
    }

    //todo: refactor and implemet this
    @Override
    public int getAverageRating(String game) throws RatingException {
        return 0;
    }

    @Override
    public List<Rating> getRatings(String gameName) throws RatingException {
        return entityManager.createQuery(
                "SELECT r FROM Rating r WHERE r.game = :gameName ORDER BY r.ratedOn DESC")
                .setParameter("gameName", gameName).setMaxResults(10).getResultList();
    }


    @Override
    public int getRating(String game, String player) throws RatingException {
        return 0;
    }

}