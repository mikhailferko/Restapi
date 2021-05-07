package ferko.restapi.dao.user;

import ferko.restapi.model.Organization;
import ferko.restapi.model.User;
import java.util.List;

public interface UserDao {

    User findById(int id);

    void save(User user);

    void update(User user, int id);

    List<User> filter(User user, int docCode);
}
