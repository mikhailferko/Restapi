package ferko.restapi.dao.office;

import ferko.restapi.model.Office;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class OfficeDaoImpl implements OfficeDao{

    private final EntityManager em;

    @Autowired
    public OfficeDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Office> list() {
        TypedQuery<Office> query = em.createQuery("SELECT o FROM Office o", Office.class);
        return query.getResultList();
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
    public void update(Office office) {
        Office officefromDB = em.find(Office.class, office.getId());
        officefromDB.setActive(office.isActive());
        officefromDB.setAddress(office.getAddress());
        officefromDB.setName(office.getName());
        officefromDB.setPhone(office.getPhone());
        officefromDB.setOrganization(office.getOrganization());
        em.persist(officefromDB);
    }
}
