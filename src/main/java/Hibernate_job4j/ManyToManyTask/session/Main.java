package Hibernate_job4j.ManyToManyTask.session;

import Hibernate_job4j.ManyToManyTask.entity.Author;
import Hibernate_job4j.ManyToManyTask.entity.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Author.class)
                .addAnnotatedClass(Book.class)
                .buildSessionFactory();

        Session session = null;

        try {
            session = factory.getCurrentSession();

            Author author1 = new Author("author1");
            Author author2 = new Author("author2");

            Book book1 = new Book("book1");
            Book book2 = new Book("book2");

            author1.getBooks().add(book1);

            author2.getBooks().add(book1);
            author2.getBooks().add(book2);

            session.beginTransaction();

            //Book deleted = session.get(Book.class, 2);
            //session.delete(deleted);

            session.getTransaction().commit();

        } finally {
            session.close();
            factory.close();
        }
    }
}
