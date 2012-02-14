package ducttape.observers;

import ducttape.entities.WebOrder;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.enterprise.event.Event;
import javax.inject.Inject;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(Arquillian.class)
public class ShippingProcessorTest {
    @Inject
    private ShippingProcessor shippingprocessor;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class, "test.jar")
                .addClass(ShippingProcessor.class)
                .addPackage(WebOrder.class.getPackage())
                .addClass(OrderPlaced.class)
                .addClass(OrderShipped.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void testIsDeployed() {
        Assert.assertNotNull(shippingprocessor);
    }

    @Inject @OrderPlaced
    Event<WebOrder> webOrderEvent;

    @Test
    public void testWebOrderEvent() {
        shippingprocessor.getWebOrderList().clear();

        webOrderEvent.fire(new WebOrder());

        assertThat(shippingprocessor.getWebOrderList().size(), is(0));
    }
}
