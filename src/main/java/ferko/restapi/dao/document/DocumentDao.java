package ferko.restapi.dao.document;

import ferko.restapi.model.Document;

public interface DocumentDao {

    Document findById(int id);

    void save(Document document);

    void update(Document document);

}
