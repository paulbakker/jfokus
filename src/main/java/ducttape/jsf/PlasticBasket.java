package ducttape.jsf;

import ducttape.entities.Customer;
import ducttape.entities.Product;
import ducttape.entities.WebOrder;
import ducttape.observers.OrderPlaced;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author Paul Bakker - paul.bakker@luminis.eu
 */
@Named
@ConversationScoped
public class PlasticBasket implements Serializable {
    private List<Product> productsInBasket = new ArrayList<Product>();

    private Customer customer = new Customer();

    public Customer getCustomer() {
        return customer;
    }

    @Inject
    Conversation conversation;

    public List<Product> getProductsInBasket() {
        return productsInBasket;
    }

    public void addProduct(Product product) {
        if(conversation.isTransient()) {
            conversation.begin();
        }

        productsInBasket.add(product);
    }

    public double getTotal() {
        double total = 0;
        for (Product product : productsInBasket) {
            total += product.getPrice();
        }

        return total;
    }

    @Inject @OrderPlaced
    Event<WebOrder> webOrderEvent;

    public String checkout() {
        if(!conversation.isTransient()) {
            conversation.end();
        }

        webOrderEvent.fire(new WebOrder(new Date(), productsInBasket, customer));

        return "index.xhtml?faces-redirect=true";
    }
}
