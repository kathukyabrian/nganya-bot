package tech.kitucode.telegram.domain;

public class Nganya {
    private Long id;
    private String name;
    private String tagLine;
    private String registrationNumber;

    public Nganya() {
    }

    public Nganya(Long id, String name, String tagLine, String registrationNumber) {
        this.id = id;
        this.name = name;
        this.tagLine = tagLine;
        this.registrationNumber = registrationNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTagLine() {
        return tagLine;
    }

    public void setTagLine(String tagLine) {
        this.tagLine = tagLine;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }
}
