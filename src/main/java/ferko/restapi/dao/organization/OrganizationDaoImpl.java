package ferko.restapi.dao.organization;


import ferko.restapi.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
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
    public void update(Organization organization, int id){
        Organization organizationFromDB = em.find(Organization.class, id);
        organizationFromDB.setName(organization.getName());
        organizationFromDB.setFullName(organization.getFullName());
        organizationFromDB.setActive(organization.isActive());
        organizationFromDB.setAddress(organization.getAddress());
        organizationFromDB.setInn(organization.getInn());
        organizationFromDB.setPhone(organization.getPhone());
        organizationFromDB.setKpp(organization.getKpp());
    }


    @Override
    public List<Organization> filter(Organization organization) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Organization> orgCriteria = cb.createQuery(Organization.class);
        Root<Organization> orgRoot = orgCriteria.from(Organization.class);
        orgCriteria.select(orgRoot);
        if (organization.getInn() != null && organization.isActive() != null) {
            orgCriteria.where(cb.and(cb.equal(orgRoot.get("name"), organization.getName()),
                    cb.equal(orgRoot.get("inn"), organization.getInn()),
                    cb.equal(orgRoot.get("isActive"), organization.isActive())));
        }
        else if (organization.getInn() != null) {
            orgCriteria.where(cb.and(cb.equal(orgRoot.get("name"), organization.getName()),
                    cb.equal(orgRoot.get("inn"), organization.getInn())));
        }
        else if (organization.isActive() != null) {
            orgCriteria.where(cb.and(cb.equal(orgRoot.get("name"), organization.getName()),
                    cb.equal(orgRoot.get("isActive"), organization.isActive())));
        }
        else {
            orgCriteria.where(cb.and(cb.equal(orgRoot.get("name"), organization.getName())));
        }
        return em.createQuery(orgCriteria).getResultList();
    }
}
