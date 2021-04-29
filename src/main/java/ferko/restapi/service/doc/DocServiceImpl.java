package ferko.restapi.service.doc;

import ferko.restapi.dao.doc.DocRepository;
import ferko.restapi.model.Doc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocServiceImpl implements DocService{

    private final DocRepository docRepository;

    @Autowired
    public DocServiceImpl(DocRepository docRepository) {
        this.docRepository = docRepository;
    }

    @Override
    public List<Doc> getAll() {
        return docRepository.findAll();
    }
}
