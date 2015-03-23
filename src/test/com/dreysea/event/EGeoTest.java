package com.dreysea.event;

// http://blog.ingo-reschke.de/?p=642
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;


// need this? 
import com.dreysea.event.*;



public class EGeoTest {
    private Geo g1, g2;
    private EGeo eg;   

    // define two geos
    @Before
    public void setUp() {
       //beechwood blvd
       g1 = new Geo(40.4400952,-79.9160969);
       g2 = new Geo(40.4400960,-79.9160980);
    }
    
    // equal works
    @Test
    public void testEqual(){
       Assert.assertEquals(g1,g1);
       Assert.assertFalse(g1==g2);
    }

    @Test
    public void testInit(){
       EGeo eg = new EGeo(g1);
       Assert.assertEquals(eg.start,g1);
       Assert.assertEquals(eg.start,eg.finish);
    }

    @Test
    public void testUpdate(){
       EGeo eg = new EGeo(g1);
       Assert.assertEquals(eg.start,eg.finish);
       eg.update(g2);
       Assert.assertFalse(eg.start==eg.finish);
       Assert.assertEquals(eg.start,g1);
       Assert.assertEquals(eg.finish,g2);
    }

    
}

