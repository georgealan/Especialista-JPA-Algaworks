package com.algaworks.ecommerce.relationships;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Category;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class AutoRelationshipTest extends EntityManagerTest {
    @Test
    public void verifyCategoryAutoRelationship(){
        Category headCategory = new Category();
        headCategory.setName("Animation");

        Category category = new Category();
        category.setName("The Lion King");
        category.setHeadCategory(headCategory);

        entityManager.getTransaction().begin();
        entityManager.persist(headCategory);
        entityManager.persist(category);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Category verifyCategory = entityManager.find(Category.class, category.getId());
        assertNotNull(verifyCategory.getHeadCategory());
    }
}
