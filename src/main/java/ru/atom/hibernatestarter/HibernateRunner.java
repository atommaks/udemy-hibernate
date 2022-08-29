package ru.atom.hibernatestarter;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.atom.hibernatestarter.dao.entity.User;
import ru.atom.hibernatestarter.util.HibernateUtil;

@Slf4j
public class HibernateRunner {

    public static void main(String[] args) {
        User user = User.builder()
                .username("lol1")
                .lastname("kek")
                .firstname("heh")
                .build();
        log.info("User {} is int transient state", user.getUsername());

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            try (Session session1 = sessionFactory.openSession()) {
                session1.beginTransaction();

                session1.saveOrUpdate(user);

                session1.getTransaction().commit();
            }

            try (Session session2 = sessionFactory.openSession()) {
                session2.beginTransaction();

                session2.delete(user);

                session2.getTransaction().commit();
            }
        }
    }
}
