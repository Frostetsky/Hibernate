package Hibernate.session;

import Hibernate.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        final SessionFactory factory = new Configuration()
                        .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();
        deleteBySalary(session, 300_000);
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

    private static void updateSalary(Session session, int id, int salary) {
        session.beginTransaction();
        Employee whoUpdated = session.get(Employee.class, id);
        whoUpdated.setSalary(salary);
        session.getTransaction().commit();
    }

    private static void updateDepartmentByID(Session session, String newDepartment, int id) {
        session.beginTransaction();
        Query query = session.createQuery("update Employee set department = :param1 where id = :param2");
                query.setParameter("param1", newDepartment);
                query.setParameter("param2", id);
                query.executeUpdate();
        session.getTransaction().commit();
    }

    private static void deleteByID(Session session, int id) {
        session.beginTransaction();
        Employee employee = session.get(Employee.class, id);
        session.delete(employee);
        session.getTransaction().commit();
    }

    private static void deleteBySalary(Session session, int salary) {
        session.beginTransaction();
        Query query = session.createQuery("delete Employee where salary > :param1");
        query.setParameter("param1", salary);
        query.executeUpdate();
        session.getTransaction().commit();
    }
}
