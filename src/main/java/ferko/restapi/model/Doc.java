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
    private int docCode;

    public Doc(String docName, int docCode) {
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

    public int getDocCode() {
        return docCode;
    }

    public void setDocCode(int docCode) {
        this.docCode = docCode;
    }
}
