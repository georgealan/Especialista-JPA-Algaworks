package com.algaworks.ecommerce.knowingentitymanager;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Product;
import org.junit.jupiter.api.Test;

public class FirstLevelCacheTest extends EntityManagerTest {
    @Test
    public void verifyCache() {
        Product product = entityManager.find(Product.class, 1);
        System.out.println(product.getName());
        System.out.println("---------------------------");

        Product redeemedProduct = entityManager.find(Product.class, product.getId());
        System.out.println(redeemedProduct.getName());
    }

    /*
    O JPA guarda o objeto na memória, no exemplo acima, o JPA busca um objeto, para isso
    no primeiro instante ele utiliza uma query para realizar a busca, logo em seguida
    abaixo realizamos outra busca pelo mesmo objeto, com ID igual, só que dessa vez
    o JPA não realiza a busca com utilizando uma query, ele reutiliza o objeto guardado na
    memória, evitando assim realizar uma nova busca no banco de dados.

    Quando precisamos limpar o cache do JPA utilizamos o entityManager.clean() ou o
    entityManager.close().
     */
}
