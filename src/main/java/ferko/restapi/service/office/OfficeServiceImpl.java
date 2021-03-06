package ferko.restapi.service.office;

import ferko.restapi.dao.office.OfficeDao;
import ferko.restapi.dao.organization.OrganizationDao;
import ferko.restapi.dto.office.OfficeFilterInDto;
import ferko.restapi.dto.office.OfficeFilterOutDto;
import ferko.restapi.dto.office.OfficeSaveDto;
import ferko.restapi.dto.office.OfficeUpdateAndGetDto;
import ferko.restapi.exception.NotFoundException;
import ferko.restapi.mapper.MapperFacade;
import ferko.restapi.model.Office;
import ferko.restapi.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OfficeServiceImpl implements OfficeService{

    private final OfficeDao officeDao;
    private final MapperFacade mapperFacade;
    private final OrganizationDao organizationDao;

    @Autowired
    public OfficeServiceImpl(OfficeDao officeDao, MapperFacade mapperFacade, OrganizationDao organizationDao) {
        this.officeDao = officeDao;
        this.mapperFacade = mapperFacade;
        this.organizationDao = organizationDao;
    }

    @Override
    public OfficeUpdateAndGetDto findById(int id) {
        Office office = officeDao.findById(id);
        return mapperFacade.map(office, OfficeUpdateAndGetDto.class);
    }

    @Transactional
    @Override
    public void save(OfficeSaveDto officeSaveDTO) {
        Office office = mapperFacade.map(officeSaveDTO, Office.class);
        Organization organization = organizationDao.findById(officeSaveDTO.getOrgId());
        if (organization != null) {
            office.setOrganization(organization);
        }
        else {
            throw new NotFoundException("Организации с id = " + officeSaveDTO.getOrgId() + " не существует");
        }
        officeDao.save(office);
    }

    @Transactional
    @Override
    public void update(OfficeUpdateAndGetDto officeUpdateAndGetDTO) {
        Office office = mapperFacade.map(officeUpdateAndGetDTO, Office.class);
        officeDao.update(office, officeUpdateAndGetDTO.getId());
    }

    @Transactional
    @Override
    public List<OfficeFilterOutDto> filter(OfficeFilterInDto officeFilterInDTO) {
        List<Office> list = officeDao.filter(officeFilterInDTO);
        return mapperFacade.mapAsList(list, OfficeFilterOutDto.class);
    }
}
