package ferko.restapi.service.organization;

import ferko.restapi.dto.organization.OrganizationFilterInDto;
import ferko.restapi.dto.organization.OrganizationFilterOutDto;
import ferko.restapi.dto.organization.OrganizationSaveDto;
import ferko.restapi.dto.organization.OrganizationUpdateAndGetDto;

import javax.validation.Valid;
import java.util.List;

public interface OrganizationService {

    OrganizationUpdateAndGetDto findById(int id);

    void save(OrganizationSaveDto organizationSaveDTO);

    void update(OrganizationUpdateAndGetDto organizationUpdateDTO);

    List<OrganizationFilterOutDto> filter(OrganizationFilterInDto organizationFilterDTO);
}
