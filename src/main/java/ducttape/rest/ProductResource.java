package ducttape.rest;

import ducttape.ejb.ProductDao;
import ducttape.entities.Product;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import java.util.List;

/**
 * @Author Paul Bakker - paul.bakker@luminis.eu
 */
@Stateless
@Path("products")
public class ProductResource {

    @Inject
    ProductDao productDao;

    @PersistenceContext
    EntityManager em;

    @GET
    @Produces({"application/xml", "application/json"})
    public List<Product> listProducts() {
        return productDao.listProducts();
    }

    @PUT
    @Consumes({"application/xml", "application/json"})
    public void put(Product product) {
        em.persist(product);
    }
}
