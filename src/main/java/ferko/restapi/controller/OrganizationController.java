package ferko.restapi.controller;

import ferko.restapi.Service.Organization.OrganizationService;
import ferko.restapi.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("organization")
public class OrganizationController {

    OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @GetMapping("{id}")
    public Organization getOrg(@PathVariable int id){
        return organizationService.findById(id);
    }


    @PostMapping
    public String saveOrg(@RequestBody Organization organization){
        organizationService.save(organization);
        return "success";
    }

    @PostMapping("list")
    public List<Organization> filter(@RequestBody String name){
        return organizationService.filter(name);
    }


}
