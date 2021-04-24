package ferko.restapi.model;

import javax.persistence.*;

@Entity
@Table(name = "country")
public class Country {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "country_name", length = 50)
    private String countryName;

    @Column(name = "country_code", length = 10)
    private long countryCode;

    public Country(String countryName, long countryCode) {
        this.countryName = countryName;
        this.countryCode = countryCode;
    }

    public Country() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public long getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(long countryCode) {
        this.countryCode = countryCode;
    }
}
