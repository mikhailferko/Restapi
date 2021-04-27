package ferko.restapi.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "document")
public class Document {
    @Id
    @Column(name = "id")
    private Long id;

    @Version
    private Integer version;

    @Column(name = "document_name", length = 50)
    private String documentName;

    @Column(name = "document_number", length = 30)
    private long documentNumber;

    @Column(name = "document_date")
    private Date documentDate;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    public Document(String documentName, long documentNumber, Date documentDate, User user) {
        this.documentName = documentName;
        this.documentNumber = documentNumber;
        this.documentDate = documentDate;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
