import main.java.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import main.java.Course;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class GivenBlackBox
{
    private Class<Course> classUnderTest;

    @SuppressWarnings("unchecked")
    public GivenBlackBox(Object classUnderTest)
    {
        this.classUnderTest = (Class<Course>) classUnderTest;
    }

    @Parameters
    public static Collection<Object[]> courseGradesUnderTest()
    {
        Object[][] classes = { 
                { CourseGrades0.class }, 
                { CourseGrades1.class }, 
                { CourseGrades2.class },
                { CourseGrades3.class }, 
                { CourseGrades4.class }, 
                { CourseGrades5.class }

        };
        
        return Arrays.asList(classes);
    }

    private Course createCourse(String name) throws Exception
    {
        Constructor<Course> constructor = classUnderTest.getConstructor(String.class);
        return constructor.newInstance(name);
    }

    Course oneStudent;
    HashMap<String, String> oneStudentExpected = new HashMap<String, String>();

    Course happyDayGradeBoundary;
    HashMap<String, String> happyDayGradeBoundaryExpected = new HashMap<String, String>();

    Course lowerPartition;
    HashMap<String, String> lowerPartitionExpected = new HashMap<String, String>();

    Course upperPartition;
    HashMap<String, String> upperPartitionExpected = new HashMap<String, String>();
    
    Course nullPointerException;

    @Before
    public void setUp() throws Exception
    {
        // One Student

        // all courses should be created like this

        // Course created with two Students having
        oneStudent = createCourse("SER316");

        // this would be the expected result after the method
        // countOccurencesLetterGrades is called
        oneStudent.set_points("Hanna", 50);
        oneStudentExpected.put("Hanna", "A");

        // Happy Day Case Grade Boundaries
        // Four Students mix of grades
        happyDayGradeBoundary = createCourse("SER315");
        happyDayGradeBoundary.set_points("100", 100); // A
        happyDayGradeBoundary.set_points(">89", 90); // A
        happyDayGradeBoundary.set_points(">79", 89); // B
        happyDayGradeBoundary.set_points(">59", 79); // C
        happyDayGradeBoundary.set_points(">35", 59); // D
        happyDayGradeBoundary.set_points("<=35", 35); // F

        happyDayGradeBoundaryExpected.put("100", "A");
        happyDayGradeBoundaryExpected.put(">89", "A");
        happyDayGradeBoundaryExpected.put(">79", "B");
        happyDayGradeBoundaryExpected.put(">59", "C");
        happyDayGradeBoundaryExpected.put(">35", "D");
        happyDayGradeBoundaryExpected.put("<=35", "F");
        
        lowerPartition = createCourse("Lower");
        
        lowerPartition.set_points("<0", -1);
        lowerPartition.set_points(">=0", 100);
        
        lowerPartitionExpected.put("<0", "F");
        lowerPartitionExpected.put(">=0", "A");
        
        upperPartition = createCourse("Upper");
        
        upperPartition.set_points(">100", 110);
        upperPartition.set_points("=35", 35);
        
        upperPartitionExpected.put(">100", "A");
        upperPartitionExpected.put("=35", "F");
        
        nullPointerException = createCourse("Null Pointer");
    }

    @After
    public void tearDown() throws Exception
    {
    }

    // sample test
    @Test
    public void oneStudent()
    {
        Map<String, String> ans = oneStudent.curveLetterGrades();
        for (Map.Entry<String, String> e : ans.entrySet())
            System.out.println(e.getKey() + " " + e.getValue());
        assertTrue(ans.equals(oneStudentExpected));
    }

    // sample test2
    @Test
    public void happyDayGradeBoundaries()
    {
        Map<String, String> ans = happyDayGradeBoundary.curveLetterGrades();
        for (Map.Entry<String, String> e : ans.entrySet())
            System.out.println(e.getKey() + " " + e.getValue());
        assertTrue(ans.equals(happyDayGradeBoundaryExpected));
    }

    @Test
    public void lowerPartitionBoundary()
    {
        Map<String, String> ans = lowerPartition.curveLetterGrades();
        for (Map.Entry<String, String> e : ans.entrySet())
            System.out.println("Lower: " + e.getKey() + " " + e.getValue());
        assertTrue(ans.equals(lowerPartitionExpected));
    }

    @Test
    public void upperPartitionBoundary()
    {
        Map<String, String> ans = upperPartition.curveLetterGrades();
        for (Map.Entry<String, String> e : ans.entrySet())
            System.out.println("Upper: " + e.getKey() + " " + e.getValue());
        assertTrue(ans.equals(upperPartitionExpected));
    }
    
    @Test
    public void nullPointerTest()
    {
        try
        {
            Map<String, String> ans = nullPointerException.curveLetterGrades();
            
            assertTrue(false);
        }
        catch (NullPointerException e)
        {
            assertNotNull(e);
        }
    }
}
