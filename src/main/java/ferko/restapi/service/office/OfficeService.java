package ferko.restapi.service.office;

import ferko.restapi.dto.office.OfficeFilterInDto;
import ferko.restapi.dto.office.OfficeFilterOutDto;
import ferko.restapi.dto.office.OfficeSaveDto;
import ferko.restapi.dto.office.OfficeUpdateAndGetDto;

import javax.validation.Valid;
import java.util.List;

public interface OfficeService {

    OfficeUpdateAndGetDto findById(int id);

    void save(@Valid OfficeSaveDto officeSaveDTO);

    void update(@Valid OfficeUpdateAndGetDto officeUpdateAndGetDTO);

    List<OfficeFilterOutDto> filter(@Valid OfficeFilterInDto officeFilterInDTO);
}
