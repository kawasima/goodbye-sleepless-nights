package example.util;

import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author kawasima
 */
public class TrashUtilsTest {
    @Test
    public void throwAway() throws IOException {
        TrashUtils.clean();
        File aaa = new File("target/aaa");
        try (FileWriter writer = new FileWriter(aaa)) {
            writer.write("hello");
        }

        TrashUtils.throwAway(aaa, 10);
    }
}
