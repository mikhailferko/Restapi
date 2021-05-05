package ferko.restapi.controller;

import ferko.restapi.service.office.OfficeService;
import ferko.restapi.dto.office.OfficeFilterInDto;
import ferko.restapi.dto.office.OfficeFilterOutDto;
import ferko.restapi.dto.office.OfficeSaveDto;
import ferko.restapi.dto.office.OfficeUpdateAndGetDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("office")
public class OfficeController {

    private final OfficeService officeService;

    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    @PostMapping("list")
    public List<OfficeFilterOutDto> filter(@RequestBody OfficeFilterInDto officeFilterInDTO){
        return officeService.filter(officeFilterInDTO);
    }

    @GetMapping("{id}")
    public OfficeUpdateAndGetDto getOffice(@PathVariable int id){
        return officeService.findById(id);
    }

    @PostMapping("save")
    public void saveOffice(@RequestBody OfficeSaveDto officeSaveDTO){
        officeService.save(officeSaveDTO);
    }

    @PostMapping("update")
    public void updateOffice(@RequestBody OfficeUpdateAndGetDto officeUpdateAndGetDTO){
        officeService.update(officeUpdateAndGetDTO);
    }
}
