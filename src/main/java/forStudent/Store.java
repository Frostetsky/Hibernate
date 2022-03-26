package forStudent;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.List;

public class Store {

    private Session session;
    private final SessionFactory sessionFactory;

    public Store() {
        sessionFactory = new Configuration()
                .configure("application.cfg.xml")
                .addAnnotatedClass(Animal.class)
                .buildSessionFactory();
    }


    public void save(Animal animal) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(animal);
        session.getTransaction().commit();
    }

    public Animal getById(int id) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Animal rsl = session.get(Animal.class, id);
        session.getTransaction().commit();
        return rsl;
    }

    public List<Animal> findAll() {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Animal> rsl = session.createQuery("from Animal", Animal.class).getResultList();
        session.getTransaction().commit();
        return rsl;
    }

    public List<Animal> findByName(String name, int age) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from Animal where name = :name and age = :age");
        query.setParameter("name", name);
        query.setParameter("age", age);
        List<Animal> rsl = query.getResultList();
        session.getTransaction().commit();
        return rsl;
    }
}
