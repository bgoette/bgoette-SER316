package main.java;

import java.util.ArrayList;

public class Student {
    private String asurite;
    private Major major;
    private double overallGrade;
    private ArrayList<Course> courses = new ArrayList<Course>();

    public Student(String asurite, Major major) {
        this.setAsurite(asurite);
        this.setMajor(major);
        setOverall_grade(0);
    }

    public String getAsurite() {
        return asurite;
    }

    public void setAsurite(String asurite) {
        this.asurite = asurite;
    }

    public Major getMajor() {
        return major;
    }

    public boolean register_forCourse(Course course) {
        course.addStudent(this);
        return courses.add(course);
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public double getOverall_grade() {
        return overallGrade;
    }

    public void setOverall_grade(double overallGrade) {
        this.overallGrade = overallGrade;
    }

    public boolean equals(Object other) {
        if (other == null) {
            return false;
        } else if (other == this) {
            return true;
        } else if (other.getClass() != this.getClass()) {
            return false;
        }
        
        Student s = (Student) other;
        
        /*
         * Checking this.getMajor().equals... will fail if major is null
         */
        // SER316-Start
        return this.getAsurite().equals(s.getAsurite()) 
                && this.getMajor() == s.getMajor() 
                && this.getOverall_grade() == s.getOverall_grade();
        // SER316-End
    }
    
    // SER316 TASK 2 SPOTBUGS FIX
    public int hashCode() {
        return this.asurite.hashCode() + this.major.hashCode();
    }
}
