package ferko.restapi.dto.organization;

public class OrganizationFilterOutDto {

    private int id;

    private String name;

    private Boolean isActive;

    public OrganizationFilterOutDto(int id, String name, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
    }

    public OrganizationFilterOutDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
