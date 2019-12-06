import main.java.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseTest
{
    
    @Before
    public void setUp()
    {
        
    }
    
    /*
     * Test Case: { < 197,198,199,200,201,202,203,204,205,206,210,211,213,215,217,219,220,210,227 > }
     */
    @Test
    public void noCurveOneF()
    {
        Course course;
        HashMap<String, Integer> courseExpected = new HashMap<String, Integer>();
        
        course = new Course("SER316");
        course.set_points("F", 35);
        
        courseExpected.put("A", 0);
        courseExpected.put("B", 0);
        courseExpected.put("C", 0);
        courseExpected.put("D", 0);
        courseExpected.put("F", 1);
        
        Map<String, Integer> ans = course.countOccurencesLetterGrades(false);
        assertTrue(ans.equals(courseExpected));
    }

    /*
     * Test Case: { < 197,198,199,200,201,202,203,204,205,206,210,211,213,215,217,218,210,227 > }
     */
    @Test
    public void noCurveOneD()
    {
        Course course;
        Map<String, Integer> courseExpected = new HashMap<String, Integer>();
        
        course = new Course("SER316");
        course.set_points("D", 36);
        
        courseExpected.put("A", 0);
        courseExpected.put("B", 0);
        courseExpected.put("C", 0);
        courseExpected.put("D", 1);
        courseExpected.put("F", 0);
        
        Map<String, Integer> ans = course.countOccurencesLetterGrades(false);
        assertTrue(ans.equals(courseExpected));
    }
    
    /*
     * Test Case: { < 197,198,199,200,201,202,203,204,205,206,210,211,213,215,216,210,227 > }
     */
    @Test
    public void noCurveOneC()
    {
        Course course;
        HashMap<String, Integer> courseExpected = new HashMap<String, Integer>();
        
        course = new Course("SER316");
        course.set_points("C", 60);
        
        courseExpected.put("A", 0);
        courseExpected.put("B", 0);
        courseExpected.put("C", 1);
        courseExpected.put("D", 0);
        courseExpected.put("F", 0);
        
        Map<String, Integer> ans = course.countOccurencesLetterGrades(false);
        assertTrue(ans.equals(courseExpected));
    }
    
    /*
     * Test Case: { < 197,198,199,200,201,202,203,204,205,206,210,211,213,214,210,227 > }
     */
    @Test
    public void noCurveOneB()
    {
        Course course;
        Map<String, Integer> courseExpected = new HashMap<String, Integer>();
        
        course = new Course("SER316");
        course.set_points("B", 85);
        
        courseExpected.put("A", 0);
        courseExpected.put("B", 1);
        courseExpected.put("C", 0);
        courseExpected.put("D", 0);
        courseExpected.put("F", 0);
        
        Map<String, Integer> ans = course.countOccurencesLetterGrades(false);
        assertTrue(ans.equals(courseExpected));
    }
    
    /*
     * Test Case: { < 197,198,199,200,201,202,203,204,205,206,210,211,212,210,227 > }
     */
    @Test
    public void noCurveOneA()
    {
        Course course;
        Map<String, Integer> courseExpected = new HashMap<String, Integer>();
        
        course = new Course("SER316");
        course.set_points("A", 100);
        
        courseExpected.put("A", 1);
        courseExpected.put("B", 0);
        courseExpected.put("C", 0);
        courseExpected.put("D", 0);
        courseExpected.put("F", 0);
        
        Map<String, Integer> ans = course.countOccurencesLetterGrades(false);
        assertTrue(ans.equals(courseExpected));
    }
    
    /*
     * Test Case: { < 197,198,199,200,201,202,203,204,205,206,207 > }
     */
    @Test
    public void nullPointerTest()
    {
        Course course;
        
        course = new Course("SER316");
        
        try
        {
            Map<String, Integer> ans = course.countOccurencesLetterGrades(false);
            
            assertTrue(false);
        }
        catch (NullPointerException e)
        {
            assertNotNull(e);
        }
    }
    
    /*
     * Test Case: { < 197,198,199,200,201,202,203,204,223,224,225,224,227 > }
     */
    @Test
    public void curveOneA()
    {
        Course course;
        HashMap<String, Integer> courseExpected = new HashMap<String, Integer>();
        
        course = new Course("SER316");
        course.set_points("A", 100);
        
        courseExpected.put("A", 1);
        courseExpected.put("B", 0);
        courseExpected.put("C", 0);
        courseExpected.put("D", 0);
        courseExpected.put("F", 0);
        
        Map<String, Integer> ans = course.countOccurencesLetterGrades(true);
        assertTrue(ans.equals(courseExpected));
    }
    
    @Test
    public void dropStudentTest()
    {
        Course course = new Course("SER316");
        
        String student = "Barney";
        
        course.set_points(student, 100);
        
        assertNotNull(course.getStudent_Points(student));
        
        course.dropStudent(student);

        try
        {
            int points = course.getStudent_Points(student);
            
            assertTrue(false);
        }
        catch (NullPointerException e)
        {
            assertNotNull(e);
        }
        
        course.dropStudent(null);
        
        course.set_points(student, 100);
        List<Student> students = course.getStudents();
        
        String newStudent = "Barney Goette";
        students.get(0).setAsurite(newStudent);
        
        course.dropStudent(newStudent);
    }
    
    @Test
    public void addStudentTest()
    {
        Course course = new Course("SER316");
        
        String student = "Barney";

        try
        {
            int points = course.getStudent_Points(student);
            
            assertTrue(false);
        }
        catch (NullPointerException e)
        {
            assertNotNull(e);
        }
        
        course.addStudent(new Student(student, null));
        
        assertNotNull(course.getStudent_Points(student));
        
        course.addStudent(new Student(student, null));
        
        assertNotNull(course.getStudent_Points(student));
    }
}
