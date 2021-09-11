package net.therap.enrollmentmanagement.dao;

import net.therap.enrollmentmanagement.domain.User;
import net.therap.enrollmentmanagement.util.EntityManagerSingleton;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Objects;

/**
 * @author rumi.dipto
 * @since 8/25/21
 */
public class UserDao {

    private EntityManager entityManager;

    public UserDao() {
        entityManager = EntityManagerSingleton.getInstance().getEntityManager();
    }

    public List<User> findAll() {
        Query query = entityManager.createQuery("from User");

        return query.getResultList();
    }

    public User find(long id) {
        return entityManager.find(User.class, id);
    }

    public User findByName(String name) {
        Query query = entityManager.createQuery("from User u where u.name = :name");
        query.setParameter("name", name);
        List<User> userList = query.getResultList();

        return userList.isEmpty() ? null : userList.get(0);
    }

    public void saveOrUpdate(User user) {
        entityManager.getTransaction().begin();

        if (user.isNew()) {
            entityManager.persist(user);
        } else {
            entityManager.merge(user);
        }
        entityManager.flush();
        entityManager.getTransaction().commit();
    }

    public void delete(long id) {
        User user = entityManager.getReference(User.class, id);

        entityManager.getTransaction().begin();
        if (Objects.nonNull(user)) {
            entityManager.remove(user);
        }
        entityManager.flush();
        entityManager.getTransaction().commit();
    }
}