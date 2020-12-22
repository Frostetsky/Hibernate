package OneToManyRelationship.session;

import OneToManyRelationship.entity.Country;
import OneToManyRelationship.entity.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        SessionFactory sf = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Country.class)
                .addAnnotatedClass(Person.class)
                .buildSessionFactory();

        /*
        Person nikita = new Person("Nikita", "Nesterenko", 21);
        Person julia = new Person("Julia", "Holland", 21);
        Person evgeniy = new Person("Evgeniy", "Morozov", 18);
        Country russia = new Country("Russia");
        Country german = new Country("German");

        russia.addPersonToCountry(nikita);
        russia.addPersonToCountry(julia);
        german.addPersonToCountry(evgeniy);
        */

        Session session = sf.getCurrentSession();
        deletePerson(session, 3);
    }

    private static void save(Session session, Country country) {
        session.beginTransaction();
        session.save(country);
        session.getTransaction().commit();
    }

    private static Person getPerson(Session session, int id) {
        session.beginTransaction();
        Person person = session.get(Person.class, id);
        session.getTransaction().commit();
        return person;
    }

    private static void deletePerson(Session session, int id) {
        session.beginTransaction();
        Person person = session.get(Person.class, id);
        session.delete(person);
        session.getTransaction().commit();
    }
}
