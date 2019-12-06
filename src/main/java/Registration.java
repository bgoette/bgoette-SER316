package main.java;

public class Registration {

    private String asurite;
    private Course course;
    private int points;
    
    public Registration(String id, Course course) {
        this.asurite = id;
        this.course = course;
    }
    
    public Registration (String id, Course course, int points) {
        this(id, course);
        
        this.points = points;
    }
    
    public int getPoints() {
        return this.points;
    }

    public String getAsurite() {
        return this.asurite;
    }
    
    public Course getCourse() {
        return this.course;
    }
    
    public void setPoints(int points) {
        this.points = Math.max(0, points);
    }
}
