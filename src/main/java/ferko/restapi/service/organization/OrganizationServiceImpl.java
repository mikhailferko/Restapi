package ferko.restapi.service.organization;

import ferko.restapi.dao.organization.OrganizationDao;
import ferko.restapi.dto.organization.OrganizationFilterInDto;
import ferko.restapi.dto.organization.OrganizationFilterOutDto;
import ferko.restapi.dto.organization.OrganizationSaveDto;
import ferko.restapi.dto.organization.OrganizationUpdateAndGetDto;
import ferko.restapi.mapper.MapperFacade;
import ferko.restapi.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService{

    private final OrganizationDao organizationDao;
    private final MapperFacade mapperFacade;

    @Autowired
    public OrganizationServiceImpl(OrganizationDao organizationDao, MapperFacade mapperFacade) {
        this.organizationDao = organizationDao;
        this.mapperFacade = mapperFacade;
    }

    @Override
    public OrganizationUpdateAndGetDto findById(int id) {
        Organization organization = organizationDao.findById(id);
        OrganizationUpdateAndGetDto org = mapperFacade.map(organization, OrganizationUpdateAndGetDto.class);
        return org;
    }

    @Transactional
    @Override
    public void save(OrganizationSaveDto organizationSaveDTO) {
        Organization organization = mapperFacade.map(organizationSaveDTO, Organization.class);
        organizationDao.save(organization);
    }

    @Transactional
    @Override
    public void update(OrganizationUpdateAndGetDto organizationUpdateDTO) {
        Organization organization = mapperFacade.map(organizationUpdateDTO, Organization.class);
        organizationDao.update(organization, organizationUpdateDTO.getId());
    }


    @Transactional
    @Override
    public List<OrganizationFilterOutDto> filter(OrganizationFilterInDto organizationFilterDTO) {
        Organization organization = mapperFacade.map(organizationFilterDTO, Organization.class);
        List<Organization> list = organizationDao.filter(organization);
        return mapperFacade.mapAsList(list, OrganizationFilterOutDto.class);
    }
}
