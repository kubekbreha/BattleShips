package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.User;

public interface UserService {
    User login(String username, String passwd);

    User register(String username, String passwd);
}
