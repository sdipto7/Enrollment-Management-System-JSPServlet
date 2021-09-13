package net.therap.enrollmentmanagement.dao;

import net.therap.enrollmentmanagement.domain.User;
import net.therap.enrollmentmanagement.util.EntityManagerSingleton;

import javax.persistence.EntityManager;
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

    public User find(long id) {
        return entityManager.find(User.class, id);
    }

    public List<User> findAll() {
        return entityManager.createQuery("FROM User").getResultList();
    }

    public User findByName(String name) {
        return (User) entityManager.createQuery("FROM User u WHERE u.name = :name")
                .setParameter("name", name)
                .getSingleResult();
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