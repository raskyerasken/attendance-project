/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jacob
 */
public class CalculateAttendenceProcentTest {
    
    public CalculateAttendenceProcentTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of schoolDaysBetween method, of class CalculateAttendenceProcent.
     */
    @Test
    public void testSchoolDaysBetween() throws ParseException {
        
          DateFormat formatter = new SimpleDateFormat("yy-MM-dd");
        String start = "2018-02-12";
        java.sql.Date startsDate = new java.sql.Date(formatter.parse(start).getTime());
        String end = "2018-04-12";
        java.sql.Date endsDate = new java.sql.Date(formatter.parse(end).getTime());
       Model model = new Model();
        Date d1 = null;
        Date d2 = null;
        CalculateAttendenceProcent instance = new  CalculateAttendenceProcent(model); 
        double expResult = 44;
        double result = instance.schoolDaysBetween(startsDate, endsDate);
        
        assertEquals(expResult, result);
    }

    /**
     * Test of setAttendenceProcent method, of class CalculateAttendenceProcent.
     */
    @Test
    public void testSetAttendenceProcent() {
        System.out.println("setAttendenceProcent");
        Date d1 = null;
        Date d2 = null;
        CalculateAttendenceProcent instance = null;
        instance.setAttendenceProcent(d1, d2);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of returnMostSkippedDay method, of class CalculateAttendenceProcent.
     */
    @Test
    public void testReturnMostSkippedDay() {
        System.out.println("returnMostSkippedDay");
        Date d1 = null;
        Date D2 = null;
        CalculateAttendenceProcent instance = null;
        String expResult = "";
        String result = instance.returnMostSkippedDay(d1, D2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
