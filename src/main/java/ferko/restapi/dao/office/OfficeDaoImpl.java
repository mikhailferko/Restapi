package ferko.restapi.dao.office;

import ferko.restapi.dto.office.OfficeFilterInDto;
import ferko.restapi.model.Office;
import ferko.restapi.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class OfficeDaoImpl implements OfficeDao{

    private final EntityManager em;

    @Autowired
    public OfficeDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Office> filter(OfficeFilterInDto office) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Office> orgCriteria = cb.createQuery(Office.class);
        Root<Office> orgRoot = orgCriteria.from(Office.class);
        orgCriteria.select(orgRoot);
        if (office.getName() != null && office.getPhone() != null && office.isActive() != null) {
            orgCriteria.where(cb.and(cb.equal(orgRoot.get("organization").get("id"), office.getOrgId()),
                    cb.equal(orgRoot.get("name"), office.getName()),
                    cb.equal(orgRoot.get("phone"), office.getPhone()),
                    cb.equal(orgRoot.get("isActive"), office.isActive())));
        }
        else if (office.getName() != null && office.getPhone() != null) {
            orgCriteria.where(cb.and(cb.equal(orgRoot.get("organization").get("id"), office.getOrgId()),
                    cb.equal(orgRoot.get("name"), office.getName()),
                    cb.equal(orgRoot.get("phone"), office.getPhone())));
        }
        else if (office.getName() != null && office.isActive() != null) {
            orgCriteria.where(cb.and(cb.equal(orgRoot.get("organization").get("id"), office.getOrgId()),
                    cb.equal(orgRoot.get("name"), office.getName()),
                    cb.equal(orgRoot.get("isActive"), office.isActive())));
        }
        else if (office.getPhone() != null && office.isActive() != null) {
            orgCriteria.where(cb.and(cb.equal(orgRoot.get("organization").get("id"), office.getOrgId()),
                    cb.equal(orgRoot.get("phone"), office.getPhone()),
                    cb.equal(orgRoot.get("isActive"), office.isActive())));
        }
        else if (office.getName() != null) {
            orgCriteria.where(cb.and(cb.equal(orgRoot.get("organization").get("id"), office.getOrgId()),
                    cb.equal(orgRoot.get("name"), office.getName())));
        }
        else if (office.getPhone() != null) {
            orgCriteria.where(cb.and(cb.equal(orgRoot.get("organization").get("id"), office.getOrgId()),
                    cb.equal(orgRoot.get("phone"), office.getPhone())));
        }
        else if (office.isActive() != null) {
            orgCriteria.where(cb.and(cb.equal(orgRoot.get("organization").get("id"), office.getOrgId()),
                    cb.equal(orgRoot.get("isActive"), office.isActive())));
        }
        else {
            orgCriteria.where(cb.and(cb.equal(orgRoot.get("organization").get("id"), office.getOrgId())));
        }
        return em.createQuery(orgCriteria).getResultList();
    }

    @Override
    public Office findById(int id) {
        return em.find(Office.class, id);
    }

    @Override
    public void save(Office office) {
        em.persist(office);
    }

    @Override
    public void update(Office office, int id) {
        Office officefromDB = em.find(Office.class, id);
        officefromDB.setActive(office.isActive());
        officefromDB.setAddress(office.getAddress());
        officefromDB.setName(office.getName());
        officefromDB.setPhone(office.getPhone());
    }
}
