package ducttape.ejb;

import ducttape.entities.Product;
import ducttape.entities.Product_;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.List;

/**
 * @Author Paul Bakker - paul.bakker@luminis.eu
 */
@Stateless
public class ProductDao {
    @PersistenceContext
    EntityManager em;

    public Product getProductById(long id) {
        return em.find(Product.class, id);
    }
    
    public List<Product> listProducts() {
        return listProducts(null, true);
    }

    public List<Product> listProducts(String filter, boolean asc) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> query = cb.createQuery(Product.class);
        Root<Product> product = query.from(Product.class);

        query.select(product);

        Path<Double> path = product.get(Product_.price);
        Order order = asc ? cb.asc(path) : cb.desc(path);
        query.orderBy(order);
        
        if(filter != null) {
            query.where(cb.like(product.get(Product_.name), filter +"%"));
        }

        return em.createQuery(query).getResultList();
    }
}
