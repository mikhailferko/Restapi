package ferko.restapi.Service.Organization;

import ferko.restapi.model.Organization;
import java.util.List;

public interface OrganizationService {

    Organization findById(int id);

    void save(Organization organization);

    void update(Organization organization);

    List<Organization> filter(String name);
}
