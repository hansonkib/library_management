package model;
import Enum.Gender;
public class Librarian {
    private int ssNO;
    private String name;
    private Gender gender;
    private String password;

    public Librarian(int ssNO, String name, String gender,String password) {
        this.ssNO = ssNO;
        this.name = name;
        this.gender = Gender.valueOf(gender);
        this.password = password;
    }

    public Librarian() {

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSsNO() {
        return ssNO;
    }

    public void setSsNO(int ssNO) {
        this.ssNO = ssNO;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
