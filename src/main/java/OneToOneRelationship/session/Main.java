package OneToOneRelationship.session;

import OneToOneRelationship.entity.Director;
import OneToOneRelationship.entity.School;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Director.class)
                .addAnnotatedClass(School.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();
    }

    private static void add(Session session, Director director) {
        session.beginTransaction();
        session.save(director);
        session.getTransaction().commit();
    }

    private static Director getDirector(Session session, int id) {
        session.beginTransaction();
        Director director = session.get(Director.class, id);
        session.getTransaction().commit();
        return director;
    }

    private static School getSchool(Session session, int id) {
        session.beginTransaction();
        School school = session.get(School.class, id);
        session.getTransaction().commit();
        return school;
    }

    private static void delete(Session session, int id) {
        session.beginTransaction();
        Director director = session.get(Director.class, id);
        session.delete(director);
        session.getTransaction().commit();
    }
}
