public class Employee {
    private int id;
    private String name;
    private String birthday;
    private String sex;
    private String country;

    public Employee(int id, String name, String birthday, String sex, String country) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.sex = sex;
        this.country = country;
    }

    public Employee(String name, String birthday, String sex, String country) {
        this.name = name;
        this.birthday = birthday;
        this.sex = sex;
        this.country = country;
    }

    public Employee() {
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", name='" + name + '\'' +
                ", birthday='" + birthday + '\'' +
                ", sex='" + sex + '\'' +
                ", country='" + country + '\'';
    }
}
