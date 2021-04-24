package ferko.restapi.model;

import javax.persistence.*;

@Entity
@Table(name = "office")
public class Office {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name", length = 40, nullable = false)
    private String name;

    @Column(name = "address", length = 100, nullable = false)
    private String address;

    @Column(name = "phone", length = 11)
    private Long phone;

    @Column(name = "is_active")
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "org_id")
    private Organization organization;

    public Office(int id ,String name, String address, Long phone, boolean isActive, Organization organization) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.isActive = isActive;
        this.organization = organization;
    }

    public Office() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
