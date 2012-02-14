package ducttape.observers;

import ducttape.entities.WebOrder;

import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @Author Paul Bakker - paul.bakker@luminis.eu
 */
@Stateless
public class OrderProcessor {
    @PersistenceContext
    EntityManager em;

    public void process(@Observes @OrderPlaced WebOrder webOrder) {
        em.persist(webOrder);
    }

}
