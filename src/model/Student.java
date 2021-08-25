package model;

import Enum.Gender;
public class Student {
    private int regNo;
    private String name;
    private Gender gender;
    private String faculty;

    public Student(int regNo, String name, String gender, String faculty) {
        this.regNo = regNo;
        this.name = name;
        this.gender = Gender.valueOf(gender);
        this.faculty = faculty;
    }

    public Student() {
    }

    public int getRegNo() {
        return regNo;
    }

    public void setRegNo(int regNo) {
        this.regNo = regNo;
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

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    @Override
    public String toString() {
        return "" +this.getRegNo()+
                ", " +this.getName()+
                ", " +this.getGender()+
                ", " +this.getFaculty();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
