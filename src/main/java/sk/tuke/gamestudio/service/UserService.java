package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.User;

public interface UserService {
    User login(String username, String password);

    User register(String username, String password);
}
