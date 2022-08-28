package ru.atom.hibernatestarter;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;
import ru.atom.hibernatestarter.dao.entity.Birthday;
import ru.atom.hibernatestarter.dao.entity.Role;
import ru.atom.hibernatestarter.dao.entity.User;
import ru.atom.hibernatestarter.util.HibernateUtil;

import java.time.LocalDate;

public class HibernateRunner {
    public static void main(String[] args) {
        User user = User.builder()
                .username("lol")
                .lastname("kek")
                .firstname("heh")
                .build();
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
