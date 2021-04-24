package ferko.restapi.Service.Organization;

import ferko.restapi.dao.organization.OrganizationDao;
import ferko.restapi.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService{

    OrganizationDao organizationDao;

    @Autowired
    public OrganizationServiceImpl(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }


    @Override
    public Organization findById(int id) {
        return organizationDao.findById(id);
    }

    @Override
    public void save(Organization organization) {
        organizationDao.save(organization);
    }

    @Override
    public void update(Organization organization) {
        organizationDao.update(organization);
    }

    @Override
    public List<Organization> filter(String name) {
        return organizationDao.filter(name);
    }
}
