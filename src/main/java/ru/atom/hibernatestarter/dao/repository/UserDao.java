package ru.atom.hibernatestarter.dao.repository;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import ru.atom.hibernatestarter.dao.entity.PersonalInfo_;
import ru.atom.hibernatestarter.dao.entity.User;
import ru.atom.hibernatestarter.dao.entity.User_;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDao {
    private static final UserDao INSTANCE = new UserDao();

    public List<User> findByName(Session session, String name) {
        var cb = session.getCriteriaBuilder();
        var criteria = cb.createQuery(User.class);
        var user = criteria.from(User.class);
        criteria.select(user).where(cb.equal(user.get(User_.personalInfo).get(PersonalInfo_.firstname), name));

        return session.createQuery(criteria).list();
    }
}
