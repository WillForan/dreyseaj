import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import com.dreysea.event.*;

/*
 * 
 * export CLASSPATH="build/classes/main/:build/classes/test:/usr/share/java/junit.jar"  
 * javac src/TestAll.java
 *
 */

@RunWith(Suite.class)
@SuiteClasses({ EGeoTest.class})
public class TestAll{
 /* empty class */
}
