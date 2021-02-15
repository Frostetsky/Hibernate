package HQL.session;

import HQL.entity.Candidate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.List;

// save +
// update +
// deleteByID +
// findAll +
// findByID +
// findByName +


public class Main {
    public static void main(String[] args) {
        SessionFactory sf = new Configuration()
                .configure("job4j.cfg.xml")
                .addAnnotatedClass(Candidate.class)
                .buildSessionFactory();

        Session session = sf.getCurrentSession();
    }


    private static void save(Session session, Candidate candidate) {
        session.beginTransaction();
        session.save(candidate);
        session.getTransaction().commit();
    }

    private static void update(Session session, Integer id, String name, String experience, Integer salary) {
        session.beginTransaction();
        Candidate result = session.get(Candidate.class, id);
        result.setName(name);
        result.setExperience(experience);
        result.setSalary(salary);
        session.getTransaction().commit();
    }

    private static void deleteByID(Session session, Integer id) {
        session.beginTransaction();
        Candidate result = session.get(Candidate.class, id);
        session.delete(result);
        session.getTransaction().commit();
    }

    private static List<Candidate> findAll(Session session) {
        session.beginTransaction();
        List<Candidate> result = session.createQuery("from Candidate").getResultList();
        return result;
    }

    private static Candidate findByID(Session session, Integer id) {
        session.beginTransaction();
        Candidate result = session.get(Candidate.class, id);
        session.getTransaction().commit();
        return result;
    }

    private static List<Candidate> findByName(Session session, String name) {
        session.beginTransaction();
        Query query = session.createQuery("from Candidate where name = :name");
        query.setParameter("name", name);
        List<Candidate> result = query.getResultList();
        return result;
    }
}
