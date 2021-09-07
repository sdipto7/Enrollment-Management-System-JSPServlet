package net.therap.enrollmentmanagement.dao;

import net.therap.enrollmentmanagement.domain.Course;
import net.therap.enrollmentmanagement.util.EntityManagerSingleton;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Objects;

/**
 * @author rumi.dipto
 * @since 8/25/21
 */
public class CourseDao {

    private EntityManager entityManager;

    public CourseDao() {
        entityManager = EntityManagerSingleton.getInstance().getEntityManager();
    }

    public List<Course> findAll() {
        Query query = entityManager.createQuery("from Course");

        return query.getResultList();
    }

    public Course find(long id) {
        return entityManager.find(Course.class, id);
    }

    public void saveOrUpdate(Course course) {
        entityManager.getTransaction().begin();

        if (course.isNew()) {
            entityManager.persist(course);
        } else {
            entityManager.merge(course);
        }
        entityManager.flush();
        entityManager.getTransaction().commit();
    }

    public void delete(long id) {
        Course course = entityManager.getReference(Course.class, id);

        entityManager.getTransaction().begin();
        if (Objects.nonNull(course)) {
            entityManager.remove(course);
        }
        entityManager.flush();
        entityManager.getTransaction().commit();
    }
}