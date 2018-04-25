package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
public class UserServiceJPA implements UserService {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User login(String username, String passwd) {
        try {
            return (User) entityManager.createNamedQuery("User.login")
                    .setParameter("username", username)
                    .setParameter("password", passwd)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public User register(String username, String passwd) {
        User user = new User(username, passwd);

        try {
            User temp = (User) entityManager.createNamedQuery("User.find")
                    .setParameter("username", username)
                    .getSingleResult();
            if (temp != null) return null; //user is already in the database
        } catch (NoResultException e) {
            entityManager.persist(user);
        }

        return user;
    }
}
