package ducttape.observers;

import ducttape.entities.WebOrder;
import org.jboss.seam.jms.TopicBuilder;

import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.Topic;

/**
 * @Author Paul Bakker - paul.bakker@luminis.eu
 */
@Stateless
public class SMSNotifier {

    @Resource(mappedName = "topic/orders")
    Topic topic;

    @Resource(mappedName = "jms/ConnectionFactory")
    ConnectionFactory connectionFactory;

    @Inject
    TopicBuilder topicBuilder;

    @Asynchronous
    public void processOrder(@Observes @OrderPlaced WebOrder webOrder) {

        topicBuilder.destination(topic).connectionFactory(connectionFactory).sendString("Order for customer: " + webOrder.getCustomer().getName());

        System.out.println("SMS to " + webOrder.getCustomer().getName());
    }
}
