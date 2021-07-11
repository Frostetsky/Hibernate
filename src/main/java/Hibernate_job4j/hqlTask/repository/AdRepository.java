package Hibernate_job4j.hqlTask.repository;

import Hibernate_job4j.hqlTask.entity.Ad;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.LocalDate;
import java.util.List;

public class AdRepository {

    private final Session session;

    private static final LocalDate CURRENT_DATE = LocalDate.now();

    public AdRepository(SessionFactory sessionFactory) {
        session = sessionFactory.getCurrentSession();
    }

    public void InitAndSaveAds(List<Ad> ads) {
        session.beginTransaction();
        for (Ad ad : ads) {
            session.save(ad);
        }
        session.getTransaction().commit();
    }

    @SuppressWarnings("unchecked")
    public List<Ad> findByAdsForTheLastDay() {
        session.beginTransaction();
        List<Ad> rsl = session
                .createQuery("FROM Ad WHERE date = :current_date")
                .setParameter("current_date", CURRENT_DATE)
                .getResultList();
        session.getTransaction().commit();
        return rsl;
    }

    @SuppressWarnings("unchecked")
    public List<Ad> findAdsWithPhoto() {
        session.beginTransaction();
        List<Ad> rsl = session.createQuery("FROM Ad WHERE url is not null").getResultList();
        session.getTransaction().commit();
        return rsl;
    }

    @SuppressWarnings("unchecked")
    public List<Ad> findAdsWithCriteriaMark(String brand) {
        session.beginTransaction();
        List<Ad> rsl = session.createQuery("FROM Ad WHERE brand = :brand")
                .setParameter("brand", brand)
                .getResultList();
        session.getTransaction().commit();
        return rsl;
    }

}
