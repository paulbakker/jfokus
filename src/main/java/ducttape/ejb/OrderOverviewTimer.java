package ducttape.ejb;

import ducttape.entities.WebOrder;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @Author Paul Bakker - paul.bakker@luminis.eu
 */
@Stateless
public class OrderOverviewTimer {
    @PersistenceContext
    EntityManager em;

    @Schedule(hour = "*", minute = "*", second = "*/10")
    public void printOverview() {
        List<WebOrder> list = em.createQuery("select w from WebOrder w", WebOrder.class).getResultList();
        for (WebOrder webOrder : list) {
            System.out.println(webOrder.getOrderDate() + " " + webOrder.getCustomer().getName());
        }
    }
}
