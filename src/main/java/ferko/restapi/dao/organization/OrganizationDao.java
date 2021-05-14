package ferko.restapi.dao.organization;

import ferko.restapi.dto.organization.OrganizationFilterInDto;
import ferko.restapi.model.Organization;

import java.util.List;

public interface OrganizationDao {

    Organization findById(int id);

    void save(Organization organization);

    void update(Organization organization, int id);

    List<Organization> filter(OrganizationFilterInDto organizationFilterInDto);
}
