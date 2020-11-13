import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import student.TestCase;

public class ExternalsortTest extends TestCase {

    private final ByteArrayOutputStream progOut = new ByteArrayOutputStream();

    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        System.setOut(new PrintStream(progOut));
    }


    public void testExternalsortInit() {
        Externalsort sorter = new Externalsort();
        assertNotNull(sorter);
        try {
            Genfile_proj3.genTestFile("sampleInput26.bin", 26);
            String[] args = { "sampleInput26.bin" };
            Externalsort.main(args);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void testExternalsortInit2() {
        Externalsort sorter = new Externalsort();
        assertNotNull(sorter);
        try {
            Genfile_proj3.genTestFile("sampleInput32.bin", 32);
            String[] args = { "sampleInput32.bin" };
            Externalsort.main(args);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void testRInit2() {
        Externalsort sorter = new Externalsort();
        assertNotNull(sorter);
        String[] args = {};
        Externalsort.main(args);
        assertEquals(progOut.toString().trim(),
            "Usage: java Externalsort <record file name>");
    }


    public void testGenFileClass() {
        Genfile_proj3 gp = new Genfile_proj3();
        assertNotNull(gp);
    }
}
