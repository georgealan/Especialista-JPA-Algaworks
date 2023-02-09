package com.algaworks.ecommerce.knowingentitymanager;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Category;
import org.junit.jupiter.api.Test;

public class LifeCicleStatesTest extends EntityManagerTest {
    @Test
    public void analyzeStates() {
        // Instanciação (Intransient)
        Category category = new Category();

        // Managed
        Category categoryMerge = entityManager.merge(category);

        // Managed
        Category managedCategory = entityManager.find(Category.class, 1);

        // Removed
        entityManager.remove(managedCategory);

        // Managed
        entityManager.persist(managedCategory);

        // Detached
        entityManager.detach(managedCategory);
    }
}
