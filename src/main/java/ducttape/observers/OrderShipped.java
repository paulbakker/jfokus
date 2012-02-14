package ducttape.observers;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @Author Paul Bakker - paul.bakker@luminis.eu
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface OrderShipped {
}
