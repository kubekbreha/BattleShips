package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
public class UserServiceJPA implements UserService {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User login(String username, String passwd) {
        return (User)entityManager.createNamedQuery("User.login")
                .setParameter("username", username).setParameter("passwd", passwd).getSingleResult();
    }

    @Override
    public User register(String username, String passwd) {
        User user = new User(username, passwd);
        entityManager.persist(user);
        return user;
    }
}
