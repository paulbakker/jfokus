package ducttape.jsf;

import ducttape.ejb.ProductDao;
import ducttape.entities.Product;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * @Author Paul Bakker - paul.bakker@luminis.eu
 */
@Named
@RequestScoped
public class ProductBean {
    private List<Product> products;
    private boolean asc;
    private String filter;

    @Inject
    private ProductDao productDao;

    public List<Product> getProducts() {
       return productDao.listProducts(filter, asc);
    }

    public boolean isAsc() {
        return asc;
    }

    public void setAsc(boolean asc) {
        this.asc = asc;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }
}
