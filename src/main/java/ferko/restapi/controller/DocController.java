package ferko.restapi.controller;

import ferko.restapi.service.doc.DocService;
import ferko.restapi.model.Doc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("docs")
public class DocController {

    private final DocService docService;

    @Autowired
    public DocController(DocService docService) {
        this.docService = docService;
    }

    @GetMapping
    public List<Doc> list(){
        return docService.getAll();
    }


}
