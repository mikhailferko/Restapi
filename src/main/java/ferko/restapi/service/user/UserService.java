package ferko.restapi.service.user;

import ferko.restapi.dto.user.*;

import javax.validation.Valid;
import java.util.List;

public interface UserService {

    UserGetDto findById(int id);

    void save(UserSaveDto userSaveDTO);

    void update(UserUpdateDto userUpdateDTO);

    List<UserFilterOutDto> filter(UserFilterInDto userFilterInDTO);
}
