package ferko.restapi.dao.user;

import ferko.restapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserDaoImpl implements UserDao{

    private final EntityManager em;

    @Autowired
    public UserDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<User> list() {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
        return query.getResultList();
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

    }
}
