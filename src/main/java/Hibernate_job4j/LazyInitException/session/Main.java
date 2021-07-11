package Hibernate_job4j.LazyInitException.session;

import Hibernate_job4j.LazyInitException.entity.Brand;
import Hibernate_job4j.LazyInitException.entity.Model;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("job4j.cfg.xml")
                .addAnnotatedClass(Brand.class)
                .addAnnotatedClass(Model.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        // init(session);

        session.beginTransaction();
        List<Model> models = session.createQuery("SELECT DISTINCT m FROM Model m JOIN FETCH m.brands").list();
        session.getTransaction().commit();
        for (Model model : models) {
            for (Brand brand : model.getBrands()) {
                System.out.println(brand);
            }
        }

    }

    private static void init(Session session) {

        Model model = new Model("BMW");

        Brand brand1 = new Brand("M5");
        Brand brand2 = new Brand("3 series");
        Brand brand3 = new Brand("i8");

        model.getBrands().add(brand1);
        model.getBrands().add(brand2);
        model.getBrands().add(brand3);

        session.beginTransaction();
        session.save(model);
        session.getTransaction().commit();
    }
}
