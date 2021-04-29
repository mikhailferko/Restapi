package ferko.restapi.dao.document;

import ferko.restapi.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class DocumentDaoImpl implements DocumentDao{

    private final EntityManager em;

    @Autowired
    public DocumentDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Document findById(int id) {
        return em.find(Document.class, id);
    }

    @Override
    public void save(Document document) {
        em.persist(document);
    }

    @Override
    public void update(Document document) {
        Document documentfromDB = em.find(Document.class, document.getId());
        documentfromDB.setDocumentName(document.getDocumentName());
        documentfromDB.setDocumentNumber(document.getDocumentNumber());
        documentfromDB.setDocumentDate(document.getDocumentDate());
        em.persist(documentfromDB);
    }
}
