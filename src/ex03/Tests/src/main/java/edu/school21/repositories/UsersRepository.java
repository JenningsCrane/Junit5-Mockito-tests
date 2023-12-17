package edu.school21.repositories;

import edu.school21.models.User;

import java.sql.SQLException;

public interface UsersRepository {
    User findByLogin(String login) throws SQLException;
    void update(User user);
}
