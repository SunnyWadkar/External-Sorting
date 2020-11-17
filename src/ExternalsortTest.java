import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import student.TestCase;

/**
 * Tests for ExternalSort Class
 * 
 * @author Pranav Chimote pchimote
 * @author Sunny Wadkar sunnywadkar
 * @version 2020-11-12
 */
public class ExternalsortTest extends TestCase {

    private final ByteArrayOutputStream progOut = new ByteArrayOutputStream();

    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        System.setOut(new PrintStream(progOut));
    }


    /**
     * Test 1
     */
    public void testExternalsortInit() {
        Externalsort sorter = new Externalsort();
        assertNotNull(sorter);
        try {
            GenBinaryFile.genTestFile("sampleInput26.bin", 26);
            String[] args = { "sampleInput26.bin" };
            Externalsort.main(args);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Test 2
     */
    public void testExternalsortInit2() {
        Externalsort sorter = new Externalsort();
        assertNotNull(sorter);
        try {
            GenBinaryFile.genTestFile("sampleInput32.bin", 32);
            String[] args = { "sampleInput32.bin" };
            Externalsort.main(args);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Test 3
     */
    public void testExternalsortInit3() {
        Externalsort sorter = new Externalsort();
        assertNotNull(sorter);
        String[] args = {};
        Externalsort.main(args);
        assertEquals(progOut.toString().trim(),
            "Usage: java Externalsort <record file name>");
    }


    /**
     * Test Genfile_proj3 class constructor
     */
    public void testGenFileClass() {
        GenBinaryFile gp = new GenBinaryFile();
        assertNotNull(gp);
    }
}
