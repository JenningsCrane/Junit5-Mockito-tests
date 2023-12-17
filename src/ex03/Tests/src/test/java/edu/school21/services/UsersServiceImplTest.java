package edu.school21.services;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.exceptions.EntityNotFoundException;
import edu.school21.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UsersServiceImplTest {

    private EmbeddedDatabase dataSource;

    private UsersServiceImpl usersServiceImpl;

    @BeforeEach
    void init() throws SQLException {
        dataSource = new EmbeddedDatabaseBuilder()
                .generateUniqueName(true)
                .setScriptEncoding("UTF-8")
                .addScripts("classpath:/schema.sql", "classpath:/data.sql")
                .build();
        usersServiceImpl = new UsersServiceImpl(dataSource);
    }

    @Test
    void authenticate() {
        User user = new User(1L, "jenningc", "1221", false);
        assertTrue(usersServiceImpl.authenticate(user.getLogin(), user.getPassword()));

        assertThrows(AlreadyAuthenticatedException.class, () ->
            usersServiceImpl.authenticate(user.getLogin(), user.getPassword()));

        assertThrows(EntityNotFoundException.class, () ->
            usersServiceImpl.authenticate("markus", user.getPassword()));

        assertFalse(usersServiceImpl.authenticate("jenningc", "1234"));
    }
    
}
