package net.therap.enrollmentmanagement.dao;

import net.therap.enrollmentmanagement.domain.Credential;
import net.therap.enrollmentmanagement.util.EntityManagerSingleton;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * @author rumi.dipto
 * @since 9/9/21
 */
public class CredentialDao {

    private EntityManager em;

    public CredentialDao() {
        em = EntityManagerSingleton.getInstance().getEntityManager();
    }

    public Credential check(String name, String password) {
        Query query = em.createQuery("FROM Credential c WHERE c.name = :name AND c.password = :password");
        query.setParameter("name", name);
        query.setParameter("password", password);

        List<Credential> credentialList = query.getResultList();

        return credentialList.isEmpty() ? null : credentialList.get(0);
    }
}
