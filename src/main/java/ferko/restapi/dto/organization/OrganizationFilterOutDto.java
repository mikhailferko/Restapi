package ferko.restapi.dto.organization;

public class OrganizationFilterOutDto {

    private int id;

    private String name;

    private boolean isActive;

    public OrganizationFilterOutDto(int id, String name, boolean isActive) {
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
