/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog.ca1;

/**
 *
 * @author guozh
 */
public class Student {

    public String name;  //represents the name of a student 
    public String course; //represents the course that the student studies 
    public int mobile;  //represents the contact number of the student
    public char gender; //represents the gender of the student
    public String dateedit; //represents the time of the object that has been edited

    public Student() {
    }

    public Student(String inputname, String inputcourse, int inputmobile, char inputgender, String time_edited) {
        this.name = inputname;
        this.course = inputcourse;
        this.mobile = inputmobile;
        this.gender = inputgender;
        this.dateedit = time_edited;
    }

    public Student(String time_edited) {
        this.dateedit = time_edited;
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    public int getMobile() {
        return mobile;
    }

    public char getGender() {
        return gender;
    }

    public String getTime() {
        return dateedit;
    }
}
