package edu.school21.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.sql.DataSource;
import java.sql.Connection;

public class EmbeddedDataSourceTest {

    private DataSource dataSource;

    @BeforeEach
    public void init() {
        dataSource = new EmbeddedDatabaseBuilder()
                .generateUniqueName(true)
                .setScriptEncoding("UTF-8")
                .addScripts("classpath:/schema.sql", "classpath:/data.sql")
                .build();
    }

    @Test
    public void testDataSourceConnection() throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            Assertions.assertNotNull(connection);
        }
    }
}
