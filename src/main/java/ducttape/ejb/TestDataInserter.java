package ducttape.ejb;

import ducttape.entities.Product;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @Author Paul Bakker - paul.bakker@luminis.eu
 */
@Singleton
@Startup
public class TestDataInserter {
    @PersistenceContext
    EntityManager em;

    @PostConstruct
    public void insertTestData() {
        for(int i = 1; i < 10; i++) {
            em.persist(new Product(i + " Product", i * 10));
        }
    }
}
