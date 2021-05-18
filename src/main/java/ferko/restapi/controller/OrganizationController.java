package ferko.restapi.controller;

import ferko.restapi.exception.NotFoundException;
import ferko.restapi.service.organization.OrganizationService;
import ferko.restapi.dto.organization.OrganizationFilterInDto;
import ferko.restapi.dto.organization.OrganizationFilterOutDto;
import ferko.restapi.dto.organization.OrganizationSaveDto;
import ferko.restapi.dto.organization.OrganizationUpdateAndGetDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("organization")
public class OrganizationController {

    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @PostMapping("list")
    public List<OrganizationFilterOutDto> filter(@RequestBody @Valid OrganizationFilterInDto organizationFilterInDTO){
        return organizationService.filter(organizationFilterInDTO);
    }

    @GetMapping("{id}")
    public OrganizationUpdateAndGetDto getOrg(@PathVariable int id){
        if(organizationService.findById(id) != null){
        return organizationService.findById(id);
            }
        else throw new NotFoundException("Не найдена организация с id = " + id);
    }

    @PostMapping("save")
    public void saveOrg(@RequestBody @Valid OrganizationSaveDto organizationSaveDTO){
        organizationService.save(organizationSaveDTO);
    }

    @PostMapping("update")
    public void updateOrg(@RequestBody @Valid OrganizationUpdateAndGetDto organizationUpdateAndGetDTO){
        organizationService.update(organizationUpdateAndGetDTO);
    }


}
