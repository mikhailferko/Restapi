package ferko.restapi.service.office;

import ferko.restapi.dao.office.OfficeDao;
import ferko.restapi.dao.organization.OrganizationDao;
import ferko.restapi.dto.office.OfficeFilterInDto;
import ferko.restapi.dto.office.OfficeFilterOutDto;
import ferko.restapi.dto.office.OfficeSaveDto;
import ferko.restapi.dto.office.OfficeUpdateAndGetDto;
import ferko.restapi.mapper.MapperFacade;
import ferko.restapi.model.Office;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public void save(OfficeSaveDto officeSaveDTO) {
        Office office = mapperFacade.map(officeSaveDTO, Office.class);
        office.setOrganization(organizationDao.findById(officeSaveDTO.getOrgId()));
        officeDao.save(office);
    }

    @Override
    public void update(OfficeUpdateAndGetDto officeUpdateAndGetDTO) {
        Office office = mapperFacade.map(officeUpdateAndGetDTO, Office.class);
        officeDao.update(office);
    }

    @Override
    public List<OfficeFilterOutDto> filter(OfficeFilterInDto officeFilterInDTO) {
        return null;
    }
}