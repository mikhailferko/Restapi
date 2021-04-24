package ferko.restapi.dao.user;

import ferko.restapi.model.User;
import java.util.List;

public interface UserDao {

    List<User> list();

    User findById(int id);

    void save(User user);

    void update(User user, int id);
}
