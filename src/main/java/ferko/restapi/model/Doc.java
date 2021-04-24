package ferko.restapi.model;

import javax.persistence.*;

@Entity
@Table(name = "doc")
public class Doc {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "doc_name", length = 50)
    private String docName;

    @Column(name = "doc_code", length = 10)
    private long docCode;

    public Doc(String docName, long docCode) {
        this.docName = docName;
        this.docCode = docCode;
    }

    public Doc() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public long getDocCode() {
        return docCode;
    }

    public void setDocCode(long docCode) {
        this.docCode = docCode;
    }
}
