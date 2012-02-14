package ducttape.ejb;

import ducttape.entities.Product;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(Arquillian.class)
public class ProductDaoTest {
    @Inject
    private ProductDao productdao;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class, "test.jar")
                .addClass(ProductDao.class)
                .addClass(TestDataInserter.class)
                .addPackage(Product.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void testIsDeployed() {
        Assert.assertNotNull(productdao);
    }

    @Test
    public void testListProducts() {
        List<Product> products = productdao.listProducts();
        assertThat(products.size(), is(9));
        assertThat(products.get(0).getPrice(), is(10d));
    }
    
    @Test
    public void testListProductsOrderDesc() {
        List<Product> products = productdao.listProducts(null, false);
        assertThat(products.get(0).getPrice(), is(90d));
    }
    
    @Test
    public void testListProductsFilter() {
        List<Product> products = productdao.listProducts("1 ", true);
        assertThat(products.size(), is(1));
        assertThat(products.get(0).getPrice(), is(10d));
    }
}
