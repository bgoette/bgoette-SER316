package main.java;

/**
 * class for managing course statistics
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.IntFunction;


public class Course {

    // Maps student names (asurite) to their points
    public Map<String, Integer> points = new HashMap<>();
    
    // Course name
    private String name;
    private int maxPoints;

    public Course(String name) {
        this(name, 100);
    }

    public Course(String name, int maxPoints) {
        this.setName(name);
        this.maxPoints = maxPoints;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void printCourseStats() {

        // SER316 TASK 2 SPOTBUGS FIX
        System.out.print("Average Grades without max and without min: ");
        System.out.println(this.calculateAverageWithoutMinWithoutMax());
    }


    public double calculateAverageWithoutMinWithoutMax() throws NullPointerException {
        ArrayList<Integer> collection = new ArrayList<Integer>(points.values());

        int counter = 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
        if (collection.size() == 1) {
            return collection.get(0);            
        } else if (collection.size() == 2) {
            return (double)(collection.get(0) + collection.get(1)) / 2;
        } else {
            int allPoints = 0;
            for (int point: collection) {
                if (point >= 0) {
                    
                    // SER316 TASK 2 SPOTBUGS FIX
                    counter++;
                    
                    if (point < min) {
                        min = point;
                    }
                    
                    if (point > max) {
                        max = point;
                    }
                    
                    allPoints = allPoints + point;
                }
            }

            int totalPoints = allPoints - max - min;
            return totalPoints / (double)(counter - 1);
        }
    }

    // REACH at least 95% Code Coverage (assign 3)
    // drop a student from course.
    public boolean dropStudent(String asurite) {
        boolean removeFromPoints = points.remove(asurite) != null;
        boolean removeFromStudents = students.remove(new Student(asurite, null));
        return removeFromPoints == removeFromStudents;
    }

    // REACH at least 95% Code coverage  (assign 3)
    // Students should only be added when they are not yet 
    // in the course (names (asurite member) needs to be unique)
    ArrayList<Student> students  = new ArrayList<Student>();
    
    public boolean addStudent(Student s) {
        if (students != null && points.putIfAbsent(s.getAsurite(), -1) == null) {
            /*
             * Calls students.add() twice
             */
            // SER316-Start
            return students.add(s);
            // SER316-End
        }
        
        return false;
    }

    public void set_points(String name, int points) {
        if (!this.points.containsKey(name)) {
            addStudent(new Student(name, null));            
        }
        
        this.points.put(name, points);
    }

    public Map<String, Integer> getPoints() {
        return points;
    }

    /*
     * If key doesn't exist in points, this method will try
     * and return null, but it needs to return an int
     */
    // SER316-Start
    public int getStudent_Points(String student) throws NullPointerException {
        return points.get(student);
    }

    public int getStudent_Points(Student student) throws NullPointerException {
        return points.get(student.getAsurite());
    }
    // SER316-End

    public int getMaxPoints() {
        return maxPoints;
    }

    public void setMaxPoints(int maxPoints) {
        this.maxPoints = maxPoints;
    }


    public List<Student> getStudents() {
        return students;
    }

    public List<Double> calculatePercentiles(List<Integer> collection) 
            throws NullPointerException {
        if (collection == null) {
            throw new NullPointerException();
        }

        int maxMarks = calculateMax();
        System.out.println("Test: " + maxMarks);
        double eachPercentile = 0.0;
        List<Double> percentileList = new ArrayList<Double>();

        for (int element : collection) {
            if (element > 0) {
                eachPercentile = (double)(element / maxMarks  * 100);
                percentileList.add(eachPercentile);
            }
        }
        
        System.out.println(percentileList);
        return percentileList;
    }

    public int calculateMax() throws NullPointerException {
        List<Integer> collection = new ArrayList<Integer>(points.values());

        if (collection.size() == 1) {
            return -1;
        }

        int max = Integer.MIN_VALUE;

        for (int point: collection) {
            if (point >= 0) {
                if (point > max) {
                    max = point;
                }
            }
        }

        return max;
    }

    private Map<String, Integer> increaseGradeCount(List<Integer> collection){
        final Map<String, Integer> occur = new HashMap<String, Integer>();
        occur.put("A", 0);
        occur.put("B", 0);
        occur.put("C", 0);
        occur.put("D", 0);
        occur.put("F", 0);
        
        collection.forEach((value) -> {

            if ((double)value / maxPoints * 100 > 89.0) {
                occur.put("A", occur.get("A") + 1);
            } else if ((double)value / maxPoints * 100 > 80.0 && value / maxPoints <= 89.0) {
                occur.put("B", occur.get("B") + 1);
            } else if ((double)value / maxPoints * 100 > 50.0 && value / maxPoints <= 65) {
                occur.put("C", occur.get("C") + 1);
            } else if ((double)value / maxPoints * 100 > 35.0 && value / maxPoints <= 50.0) {
                occur.put("D", occur.get("D") + 1);
            } else {
                occur.put("F", occur.get("F") + 1);
            }
        });
        
        return occur;
    }

    /**
     * This is where you create your node flow graph and write your White Box test. 
     * Calculates final grades either with curve or without  (assign 3)
     * 
     * <p>Calculation is based on points member and maxPoints from Course.
     * Will call curve if input is true. 
     *  * Grading Scale:
     * >  89% -> A
     * >  79% -> B
     * >  59% -> C
     * >  35% -> D
     * <= 35% -> F
     * 
     * @input curved if true curving is done if not curving is ommitted
     * 
     * @return hashmap with final letter grades for students based on curving `points`.
     * @throws NullPointerException Is thrown if "points" is empty
     */
    public Map<String, Integer> countOccurencesLetterGrades(boolean curved) 
            throws NullPointerException {
        Map<String, Integer> occur;

        if (!curved) {
            ArrayList<Integer> collection = new ArrayList<Integer>(points.values());
            if (collection.isEmpty()) {
                throw new NullPointerException();
            }
            
            occur = increaseGradeCount(collection);
        } else {
            occur = new HashMap<>();
            for (String grade : curveLetterGrades().values()) {
                occur.put("A", 0);
                occur.put("B", 0);
                occur.put("C", 0);
                occur.put("D", 0);
                occur.put("F", 0);
                
                /*
                 * Changed because the key being passed to 
                 * occur.get is a HashMap, needs to be a string
                 */
                // SER316 - Start
                occur.put(grade, occur.get(grade) + 1);
                // SER 316 - End
            }
        }
        return occur;
    }

    /** This will be needed for assignment 3 (do not change in assignment 2)
     * Calculates final grades including a curve and returns final letter grade
     * of each student.
     * 
     * <p>Calculation is based on points member inherited from Course.
     * Curve is calculated by adding the positive difference between the student
     * with the highest non-negative points and maxPoints to all scores.
     * Grading Scale:
     * >  89% -> A
     * >  79% -> B
     * >  59% -> C
     * >  35% -> D
     * <= 35% -> F
     * 
     * <p>eg.let points = [Alice:15, Bill:30, Cathy:45, Joe:70, Jane:80] and maxPoints = 100,
     * curve would be 100 - 80 = 20.
     * Adjusted points would be = [Alice:35, Bill:50, Cathy:65, Joe:90, Jane:100].
     * Adjusted percentages would be = [35%, 50%, 65%, 90%, 100%].
     * Returned HashMap points would be = [Alice:F, Bill:D, Cathy:C, Joe:A, Jane:A].
     *
     * @return hashmap with final letter grades for students based on curving `points`.
     * @throws NullPointerException Returns null
     */
    public Map<String, String> curveLetterGrades() throws NullPointerException { 
        if (students == null || students.size() < 1) {
            throw new NullPointerException("Must have students in course!");
        }
        
        Map<String, String> adjustedLetterGrades = new HashMap<String, String>();
        
        int maxStudentPoints = calculateMax();
        int scoreToAdd = 0;
        
        if (maxStudentPoints == -1 && points.size() == 1) {
            for (String key : points.keySet()) {
                adjustedLetterGrades.put(key, "A");
            }
            
            return adjustedLetterGrades;
        } else if (maxStudentPoints <= this.maxPoints) {
            scoreToAdd = this.maxPoints - maxStudentPoints;
        }

        for (Entry<String, Integer> key: points.entrySet()) {
            double newScore = key.getValue() + scoreToAdd;
            double percent = newScore / this.maxPoints * 100;
            String letterGrade = "A";
            
            if (percent <= 35) {
                letterGrade = "F";
            } else if (percent <= 59) {
                letterGrade = "D";
            } else if (percent <= 79) {
                letterGrade = "C";
            } else if (percent <= 89) {
                letterGrade = "B";
            }
            
            adjustedLetterGrades.put(key.getKey(), letterGrade);
        }
        
        return adjustedLetterGrades;
    }
}