package ducttape.observers;

import ducttape.entities.WebOrder;

import javax.enterprise.event.Observes;
import javax.inject.Singleton;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author Paul Bakker - paul.bakker@luminis.eu
 */
@Singleton
public class ShippingProcessor {
    private List<WebOrder> webOrderList = new CopyOnWriteArrayList<WebOrder>();

    public List<WebOrder> getWebOrderList() {
        return webOrderList;
    }

    public void process(@Observes @OrderShipped WebOrder webOrder) {
        webOrderList.add(webOrder);
    }
}
