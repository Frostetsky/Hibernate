package IntegrationTesting.session;

import IntegrationTesting.entity.Order;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class OrdersStoreTest {
    private BasicDataSource pool = new BasicDataSource();

    @Before
    public void setUp() throws SQLException {
        pool.setDriverClassName("org.hsqldb.jdbcDriver");
        pool.setUrl("jdbc:hsqldb:mem:tests;sql.syntax_pgs=true");
        pool.setUsername("sa");
        pool.setPassword("");
        pool.setMaxActive(2);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream("src/main/java/IntegrationTesting/database/update.sql")))
        ) {
            br.lines().forEach(line -> builder.append(line).append(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pool.getConnection().prepareStatement(builder.toString()).executeUpdate();
    }

    @Test
    public void whenSaveOrderAndFindAllOneRowWithDescription() {
        OrdersStore store = new OrdersStore(pool);

        store.save(Order.of("name1", "description1"));

        List<Order> all = (List<Order>) store.findAll();

        assertThat(all.size(), is(1));
        assertThat(all.get(0).getDescription(), is("description1"));
        assertThat(all.get(0).getId(), is(1));
    }

    @Test
    public void whenFindOrderByName() {
        OrdersStore store = new OrdersStore(pool);

        store.save(Order.of("name1", "desc1"));
        store.save(Order.of("name1", "desc2"));

        List<Order> allByName = store.findByName("name1");

        assertThat(allByName.size(), is(2));
    }

    @Test
    public void whenFindOrderByID() {
        OrdersStore store = new OrdersStore(pool);

        store.save(Order.of("name1", "desc1"));

        Order result = store.findById(1);

        assertThat(result.getName(), is("name1"));
        assertThat(result.getDescription(), is("desc1"));
        assertThat(result.getId(), is(1));
    }

    @Test
    public void whenUpdateOrder() {
        OrdersStore store = new OrdersStore(pool);

        store.save(Order.of("name1", "desc2"));

        Order upd = Order.of("name2", "desc2");

        store.replace(1, upd);

        Order result = store.findById(1);

        assertThat(result.getName(), is("name2"));
        assertThat(result.getDescription(), is("desc2"));
        assertThat(result.getId(), is(1));
    }
}