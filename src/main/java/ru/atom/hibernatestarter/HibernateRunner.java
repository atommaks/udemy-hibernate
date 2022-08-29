package ru.atom.hibernatestarter;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.atom.hibernatestarter.dao.entity.Artist;
import ru.atom.hibernatestarter.dao.entity.ArtistInfo;
import ru.atom.hibernatestarter.dao.entity.PersonalInfo;
import ru.atom.hibernatestarter.dao.entity.User;
import ru.atom.hibernatestarter.util.HibernateUtil;

import java.util.Optional;

@Slf4j
public class HibernateRunner {

    public static void main(String[] args) {
        User user = User.builder()
                .username("lol12")
                .personalInfo(PersonalInfo.builder()
                        .lastname("kek")
                        .firstname("heh")
                        .build())
                .build();
        log.info("User {} is int transient state", user.getUsername());

        final var artistKey = ArtistInfo.builder()
                .nickname("21 Savage")
                .firstname("Joseph")
                .lastname("Hard")
                .build();
        final Artist artist = Artist.builder()
                .artistInfo(artistKey)
                .chartPlace(1)
                .build();

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            try (Session session1 = sessionFactory.openSession()) {
                session1.beginTransaction();

                session1.saveOrUpdate(user);
                session1.saveOrUpdate(artist);

                session1.getTransaction().commit();
            }

            try (Session session2 = sessionFactory.openSession()) {
                session2.beginTransaction();

                session2.delete(user);

                session2.getTransaction().commit();
            }

            try (Session session3 = sessionFactory.openSession()) {
                session3.beginTransaction();

                final var entity = session3.get(Artist.class, artistKey);
                Optional.ofNullable(entity)
                                .ifPresentOrElse(
                                        (a) -> log.info("Found our saved artist {}", a),
                                        () -> log.warn("Didn't find our saved artist ("));
                session3.delete(entity);

                session3.getTransaction().commit();
            }
        }
    }
}
