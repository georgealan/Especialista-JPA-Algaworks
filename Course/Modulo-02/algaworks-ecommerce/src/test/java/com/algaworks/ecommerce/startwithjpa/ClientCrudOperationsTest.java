package com.algaworks.ecommerce.startwithjpa;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Client;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClientCrudOperationsTest extends EntityManagerTest {
    @Test
    public void insertNewClient() {
        Client client = new Client();
        client.setId(3);
        client.setName("Veralucia da Silva");

        entityManager.getTransaction().begin();
        entityManager.persist(client);
        entityManager.getTransaction().commit();
    }
    @Test
    public void findClient() {
        Client client = entityManager.find(Client.class, 1);
        assertEquals("Ayrton Senna", client.getName());
    }
    @Test
    public void updateClient() {
        Client client = entityManager.find(Client.class, 2);
        client.setName("Goku");

        entityManager.getTransaction().begin();
        entityManager.merge(client);
        entityManager.getTransaction().commit();

        Client verifyClient = entityManager.find(Client.class, client.getId());
        assertEquals("Goku", verifyClient.getName());
    }
    @Test
    public void deleteClient() {
        Client client = entityManager.find(Client.class, 2);

        entityManager.getTransaction().begin();
        entityManager.remove(client);
        entityManager.getTransaction().commit();

        Client verifyClient = entityManager.find(Client.class, 2);
        assertNull(verifyClient);
    }
}
