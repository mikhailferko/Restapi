package ferko.restapi.dao.office;

import ferko.restapi.dto.office.OfficeFilterInDto;
import ferko.restapi.model.Office;
import java.util.List;

public interface OfficeDao {

    List<Office> filter(OfficeFilterInDto office);

    Office findById(int id);

    void save(Office office);

    void update(Office office, int id);
}
