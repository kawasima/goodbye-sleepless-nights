package example;

import org.jberet.se.Main;
import org.junit.Test;

/**
 * @author kawasima
 */
public class SleepfulBatchletTest {
    @Test
    public void test() {
        Main.main(new String[]{"sleepfulJob.xml"});
    }
}
