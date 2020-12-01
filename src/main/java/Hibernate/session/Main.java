package Hibernate.session;

import Hibernate.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        final SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();
        getAll(session).forEach(System.out::println);
    }

    private static void save(Employee employee, Session session) {
        session.beginTransaction();
        session.save(employee);
        session.getTransaction().commit();
    }

    private static Employee get(int id, Session session) {
        session.beginTransaction();
        Employee employee = session.get(Employee.class, id);
        session.getTransaction().commit();
        return employee;
    }

    private static List<Employee> getAll(Session session) {
        session.beginTransaction();
        List<Employee> result = session.createQuery("from Employee").getResultList();
        session.getTransaction().commit();
        return result;
    }
}
