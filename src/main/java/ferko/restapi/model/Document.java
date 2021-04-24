package ferko.restapi.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "document")
public class Document {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "document_name", length = 50)
    private String documentName;

    @Column(name = "document_number", length = 30)
    private long documentNumber;

    @Column(name = "document_date")
    private Date documentDate;

    public Document(String documentName, long documentNumber, Date documentDate) {
        this.documentName = documentName;
        this.documentNumber = documentNumber;
        this.documentDate = documentDate;
    }

    public Document() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public long getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(long documentNumber) {
        this.documentNumber = documentNumber;
    }

    public Date getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(Date documentDate) {
        this.documentDate = documentDate;
    }
}
