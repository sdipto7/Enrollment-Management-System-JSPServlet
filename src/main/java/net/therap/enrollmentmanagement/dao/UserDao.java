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

    private EntityManager em;

    public UserDao() {
        em = EntityManagerSingleton.getInstance().getEntityManager();
    }

    public User find(long id) {
        return em.find(User.class, id);
    }

    public List<User> findAll() {
        return em.createQuery("FROM User").getResultList();
    }

    public User findByName(String name) {
        return (User) em.createQuery("FROM User u WHERE u.name = :name")
                .setParameter("name", name)
                .getSingleResult();
    }

    public User saveOrUpdate(User user) {
        em.getTransaction().begin();

        if (user.isNew()) {
            em.persist(user);
        } else {
            em.merge(user);
        }
        em.flush();
        em.getTransaction().commit();

        return user;
    }

    public void delete(long id) {
        User user = em.getReference(User.class, id);

        em.getTransaction().begin();
        if (Objects.nonNull(user)) {
            em.remove(user);
        }
        em.flush();
        em.getTransaction().commit();
    }
}