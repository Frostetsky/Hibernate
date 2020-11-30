package Hibernate.session;

import Hibernate.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        final SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        Employee employee = new Employee("Andrey", "Borchenko", "IT", 150_000);

        Main.save(employee, session);
    }

    private static void save(Employee employee, Session session) {
        session.beginTransaction();
        session.save(employee);
        session.getTransaction().commit();
    }
}
