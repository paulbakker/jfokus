package ducttape.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @Author Paul Bakker - paul.bakker@luminis.eu
 */
@Entity
public class WebOrder {
    @Id @GeneratedValue long id;

    private Date orderDate;

    @ManyToMany
    private List<Product> products;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Customer customer;

    public WebOrder(Date orderDate, List<Product> products, Customer customer) {
        this.orderDate = orderDate;
        this.products = products;
        this.customer = customer;
    }

    public WebOrder() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
