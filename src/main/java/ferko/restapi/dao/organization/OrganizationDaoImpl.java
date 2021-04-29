package ferko.restapi.dao.organization;


import ferko.restapi.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class OrganizationDaoImpl implements OrganizationDao {

    private final EntityManager em;

    @Autowired
    OrganizationDaoImpl(EntityManager em){
        this.em = em;
    }

    @Override
    public Organization findById(int id) {
        return em.find(Organization.class, id);
    }


    @Override
    public void save(Organization organization) {
        em.persist(organization);
    }

    @Override
    public void update(Organization organization){
        Organization organizationfromDB = em.find(Organization.class, organization.getId());
        organizationfromDB.setName(organization.getName());
        organizationfromDB.setFullName(organization.getFullName());
        organizationfromDB.setActive(organization.isActive());
        organizationfromDB.setAddress(organization.getAddress());
        organizationfromDB.setInn(organization.getInn());
        organizationfromDB.setPhone(organization.getPhone());
        organizationfromDB.setKpp(organization.getKpp());
        em.flush();
    }


    @Override
    public List<Organization> filter(String name) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Organization> orgCriteria = cb.createQuery(Organization.class);
        Root<Organization> orgRoot = orgCriteria.from(Organization.class);
        //orgCriteria.select(orgRoot);
        orgCriteria.where(cb.equal(orgRoot.get("name"), name));
        return em.createQuery(orgCriteria).getResultList();
    }
}
