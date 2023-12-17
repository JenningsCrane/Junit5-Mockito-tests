package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ProductsRepositoryJdbcImplTest {
    private static final List<Product> EXPECTED_FIND_ALL_PRODUCTS = Arrays.asList(
            new Product(1L, "Product1", 50L),
            new Product(2L, "Product2", 100L),
            new Product(3L, "Product3", 150L)
    );
    private static final Product EXPECTED_FIND_BY_ID_PRODUCT =  new Product(1L, "Product1", 50L);
    private static final Product EXPECTED_UPDATED_PRODUCT = new Product(3L, "Product3", 555L);
    private static final Product EXPECTED_SAVE_PRODUCT = new Product(4L, "Product4", 400L);
    private EmbeddedDatabase dataSource;
    private ProductRepositoryJdbcImpl productsRepositoryJdbc;

    @BeforeEach
    public void init() throws SQLException {
        dataSource = new EmbeddedDatabaseBuilder()
                .generateUniqueName(true)
                .setScriptEncoding("UTF-8")
                .addScripts("classpath:/schema.sql", "classpath:/data.sql")
                .build();
        productsRepositoryJdbc = new ProductRepositoryJdbcImpl(dataSource);
    }

    @Test
    public void testForAll() {
        List<Product> actual = productsRepositoryJdbc.findAll();
        assertEquals(EXPECTED_FIND_ALL_PRODUCTS, actual);
    }

    @Test
    public void testForId() {
        assertEquals(productsRepositoryJdbc.findById(1L).get(), EXPECTED_FIND_BY_ID_PRODUCT);
    }

    @Test
    public void updateTest() {
        productsRepositoryJdbc.update(new Product(3L, "Product3", 555L));
        assertEquals(EXPECTED_UPDATED_PRODUCT, productsRepositoryJdbc.findById(3L).get());
    }
    @Test
    public void saveTest() {
        productsRepositoryJdbc.save(new Product(4L, "Product4", 400L));
        assertEquals(EXPECTED_SAVE_PRODUCT, productsRepositoryJdbc.findById(4L).get());
    }
    @Test
    public void deleteTest() {
        productsRepositoryJdbc.delete(1L);
        assertFalse(productsRepositoryJdbc.findById(1L).isPresent());
    }

    @AfterEach
    void close() {
        dataSource.shutdown();
    }
}
