import main.java.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.HashMap;

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
        Course test1;
        HashMap<String, Integer> test1Expected = new HashMap<String, Integer>();
        
        test1 = new Course("SER316");
        test1.set_points("=35", 35);
        
        test1Expected.put("A", 0);
        test1Expected.put("B", 0);
        test1Expected.put("C", 0);
        test1Expected.put("D", 0);
        test1Expected.put("F", 1);
        
        HashMap<String, Integer> ans = test1.countOccurencesLetterGrades(false);
        assertTrue(ans.equals(test1Expected));
    }
}
