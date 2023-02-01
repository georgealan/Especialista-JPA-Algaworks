package com.algaworks.ecommerce.basicmapping;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Client;
import com.algaworks.ecommerce.model.ClientGender;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class EnumerationMappingTest extends EntityManagerTest {
    @Test
    public void testEnum() {
        Client client = new Client();
        client.setName("George Alan");
        client.setGender(ClientGender.MASCULINE);

        entityManager.getTransaction().begin();
        entityManager.persist(client);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Client verifyClient = entityManager.find(Client.class, client.getId());
        assertNotNull(verifyClient);
    }
}
