package Hibernate_job4j.hqlTask.session;

import Hibernate_job4j.hqlTask.entity.Ad;
import Hibernate_job4j.hqlTask.repository.AdRepository;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class Main {

    private static final List<Ad> ads = List.of(
            new Ad("BMW", "M5E30", LocalDate.of(2020, Month.APRIL, 15)),
            new Ad("Audi", "A8", LocalDate.of(2021, Month.JULY, 9)),
            new Ad("BMW", "M5E30", "link1", LocalDate.now()),
            new Ad("Lada", "14", "link2", LocalDate.of(2021, Month.JANUARY, 19)),
            new Ad("Kio", "K5", LocalDate.now()),
            new Ad("BMW", "M5E30", "link3", LocalDate.now())
    );

    public static void main(String[] args) {
        final SessionFactory sessionFactory = new Configuration()
                .configure("job4j.cfg.xml")
                .addAnnotatedClass(Ad.class)
                .buildSessionFactory();

        final AdRepository repository = new AdRepository(sessionFactory);

        // repository.InitAndSaveAds(ads);

        // List<Ad> rsl = repository.findAdsWithCriteriaMark("M5E30");

        // List<Ad> rsl = repository.findAdsWithPhoto();

        // List<Ad> rsl = repository.findByAdsForTheLastDay();


        // rsl.forEach(System.out::println);
    }
}
