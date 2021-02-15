package Hibernate_job4j.OneToManyTask.session;

import Hibernate_job4j.OneToManyTask.entity.Brand;
import Hibernate_job4j.OneToManyTask.entity.Model;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        final SessionFactory factory = new Configuration()
                .configure("job4j.cfg.xml")
                .addAnnotatedClass(Model.class)
                .addAnnotatedClass(Brand.class)
                .buildSessionFactory();

        Brand BMWs = new Brand("BMW");
        Model fiveSeries = new Model("BMW 5 Series");
        Model threeSeries = new Model("BMW 3420");
        Model i8Series = new Model("BMW I8");
        Model m5Series = new Model("BMW M5");
        Model m4Series = new Model("BMW M4");

        BMWs.getModels().add(fiveSeries);
        BMWs.getModels().add(threeSeries);
        BMWs.getModels().add(i8Series);
        BMWs.getModels().add(m5Series);
        BMWs.getModels().add(m4Series);

        Session session = factory.getCurrentSession();

        save(session, BMWs);
    }


    private static void save(Session session, Brand brand) {
        session.beginTransaction();
        session.save(brand);
        session.getTransaction().commit();
    }
}
