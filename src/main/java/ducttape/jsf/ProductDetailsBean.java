package ducttape.jsf;

import ducttape.ejb.ProductDao;
import ducttape.entities.Product;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @Author Paul Bakker - paul.bakker@luminis.eu
 */
@Named
@RequestScoped
public class ProductDetailsBean {
    private Product product;
    private long pid;

    @Inject
    ProductDao productDao;

    public void loadProduct() {
        product = productDao.getProductById(pid);
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public Product getProduct() {
        return product;
    }
}
