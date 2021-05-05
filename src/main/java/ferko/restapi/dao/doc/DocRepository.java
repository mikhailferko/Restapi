package ferko.restapi.dao.doc;

import ferko.restapi.model.Doc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocRepository extends JpaRepository<Doc, Integer> {

    Optional<Doc> findById(Integer id);

    Optional<Doc> findByDocCode(int docCode);

    Optional<Doc> findByDocName(String name);
}
