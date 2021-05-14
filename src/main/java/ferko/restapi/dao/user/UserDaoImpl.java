package ferko.restapi.dao.user;

import ferko.restapi.model.Doc;
import ferko.restapi.model.Document;
import ferko.restapi.model.Organization;
import ferko.restapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    private final EntityManager em;

    @Autowired
    public UserDaoImpl(EntityManager em) {
        this.em = em;
    }


    @Override
    public User findById(int id) {
        return em.find(User.class, id);
    }

    @Override
    public void save(User user) {
        em.persist(user);
    }

    @Override
    public void update(User user, int id) {
        User userFromDB = em.find(User.class, id);
        userFromDB.setOffice(user.getOffice());
        userFromDB.setFirstName(user.getFirstName());
        userFromDB.setSecondName(user.getSecondName());
        userFromDB.setMiddleName(user.getMiddleName());
        userFromDB.setPosition(user.getPosition());
        userFromDB.setPhone(user.getPhone());
        userFromDB.setIdentified(user.isIdentified());
    }

    @Override
    public List<User> filter(ferko.restapi.dto.user.UserFilterInDto user) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> orgCriteria = cb.createQuery(User.class);
        Root<User> orgRoot = orgCriteria.from(User.class);
        orgCriteria.select(orgRoot);

        if (user.getFirstName() != null && user.getSecondName() != null && user.getMiddleName() != null
                && user.getPosition() != null && user.getDocCode() != 0 && user.getCitizenshipCode() != 0) {
            orgCriteria.where(cb.and(cb.equal(orgRoot.get("office").get("id"), user.getOfficeId()),
                    cb.equal(orgRoot.get("firstName"), user.getFirstName()),
                    cb.equal(orgRoot.get("secondName"), user.getSecondName()),
                    cb.equal(orgRoot.get("middleName"), user.getMiddleName()),
                    cb.equal(orgRoot.get("position"), user.getPosition()),
                    cb.equal(orgRoot.get("document").get("doc").get("docCode"), user.getDocCode()),
                    cb.equal(orgRoot.get("country").get("countryCode"), user.getCitizenshipCode())));
        }
        else if (user.getFirstName() != null && user.getSecondName() != null && user.getMiddleName() != null
                && user.getPosition() != null && user.getDocCode() != 0) {
            orgCriteria.where(cb.and(cb.equal(orgRoot.get("office").get("id"), user.getOfficeId()),
                    cb.equal(orgRoot.get("firstName"), user.getFirstName()),
                    cb.equal(orgRoot.get("secondName"), user.getSecondName()),
                    cb.equal(orgRoot.get("middleName"), user.getMiddleName()),
                    cb.equal(orgRoot.get("position"), user.getPosition()),
                    cb.equal(orgRoot.get("document").get("doc").get("docCode"), user.getDocCode())));
        }
        else if (user.getFirstName() != null && user.getSecondName() != null && user.getMiddleName() != null
                && user.getPosition() != null && user.getCitizenshipCode() != 0) {
            orgCriteria.where(cb.and(cb.equal(orgRoot.get("office").get("id"), user.getOfficeId()),
                    cb.equal(orgRoot.get("firstName"), user.getFirstName()),
                    cb.equal(orgRoot.get("secondName"), user.getSecondName()),
                    cb.equal(orgRoot.get("middleName"), user.getMiddleName()),
                    cb.equal(orgRoot.get("position"), user.getPosition()),
                    cb.equal(orgRoot.get("country").get("countryCode"), user.getCitizenshipCode())));
        }
        else if (user.getFirstName() == null && user.getSecondName() == null && user.getMiddleName() == null
                && user.getPosition() == null && user.getDocCode() == 0 && user.getCitizenshipCode() == 0) {
            orgCriteria.where(cb.equal(orgRoot.get("office").get("id"), user.getOfficeId()));}

        return em.createQuery(orgCriteria).getResultList();
    }


}
