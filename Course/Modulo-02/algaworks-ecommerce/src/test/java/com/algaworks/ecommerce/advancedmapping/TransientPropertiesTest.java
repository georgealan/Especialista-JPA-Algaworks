package com.algaworks.ecommerce.advancedmapping;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Client;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TransientPropertiesTest extends EntityManagerTest {
    @Test
    public void hasFirstName() {
        Client client = entityManager.find(Client.class, 1);

        assertEquals("Ayrton", client.getFirstName());
    }
}
