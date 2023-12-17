package edu.school21.services;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.exceptions.EntityNotFoundException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersServiceImpl  implements UsersRepository {
    private final Connection connection;

    public UsersServiceImpl(DataSource dataSource) throws SQLException {
        connection = dataSource.getConnection();
    }

    @Override
    public User findByLogin(String login) {
        try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Tests.users WHERE login=?")) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(!resultSet.next()) {
                throw new EntityNotFoundException("EntityNotFoundException");
            }
            return new User(
                    resultSet.getLong("id"),
                    resultSet.getString("login"),
                    resultSet.getString("password"),
                    resultSet.getBoolean("authentication")
            );
        } catch (SQLException e) {
            System.err.println(e.getMessage());;
        }
        return null;
    }

    @Override
    public void update(User user) {
        try(PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Tests.users SET login=?, password=?, authentication=? WHERE id=?")) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setBoolean(3, user.getAuthenticationSuccessStatus());
            preparedStatement.setLong(4, user.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public boolean authenticate(String login, String password) {
        User user = findByLogin(login);
        if(user.getPassword().equals(password)) {
            if(user.getAuthenticationSuccessStatus()) {
                throw new AlreadyAuthenticatedException("AlreadyAuthenticatedException");
            }
            user.setAuthenticationSuccessStatus(true);
            update(user);
            return user.getAuthenticationSuccessStatus();
        }
        return false;
    }
}
