package ferko.restapi.dto.organization;

public class OrganizationFilterInDto {

    private String name;

    private Long inn;

    private boolean isActive;

    public OrganizationFilterInDto(String name, Long inn, boolean isActive) {
        this.name = name;
        this.inn = inn;
        this.isActive = isActive;
    }

    public OrganizationFilterInDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getInn() {
        return inn;
    }

    public void setInn(Long inn) {
        this.inn = inn;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
