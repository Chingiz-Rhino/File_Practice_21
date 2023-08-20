package model;



public class JsonModel {
    private String name;
    private int age;
    private String email;
    private AddressModel address;

    private SkillsModel skills;

    public AddressModel getAddress() {
        return address;
    }

    public SkillsModel getSkills() {
        return skills;
    }

    public void setAddress(AddressModel address) {
        this.address = address;
    }

    public void setSkills(SkillsModel skills) {
        this.skills = skills;
    }


    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
