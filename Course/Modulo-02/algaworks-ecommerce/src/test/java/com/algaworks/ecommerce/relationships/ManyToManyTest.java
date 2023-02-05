package com.algaworks.ecommerce.relationships;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Category;
import com.algaworks.ecommerce.model.Product;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class ManyToManyTest extends EntityManagerTest {
    @Test
    public void verifyRelationship() {
        Product product = entityManager.find(Product.class, 1);
        Category category = entityManager.find(Category.class, 1);

        entityManager.getTransaction().begin();
        product.setCategories(Arrays.asList(category));
        entityManager.getTransaction().commit();
        entityManager.clear();

        Category verifyCategory = entityManager.find(Category.class, category.getId());
        assertFalse(verifyCategory.getProducts().isEmpty());
    }
}
