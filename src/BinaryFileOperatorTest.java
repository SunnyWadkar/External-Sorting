import java.io.IOException;
import student.TestCase;

/**
 * Tests for Binary File Operator class
 * 
 * @author Pranav Chimote pchimote
 * @author Sunny Wadkar sunnywadkar
 * @version 2020-11-12
 */
public class BinaryFileOperatorTest extends TestCase {

    /**
     * Test 1
     */
    public void testBinaryFileOperator() {
        try {
            GenBinaryFile.genTestFile("sampleInput8.bin", 8);
            BinaryFileOperator bf = new BinaryFileOperator("sampleInput8.bin",
                16, 512);
            assertNotNull(bf);
            long[] stat = bf.getFileStats();
            assertEquals(8, stat[2]);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Test 2
     */
    public void testBinaryFileOperator1() {
        try {
            GenBinaryFile.genTestFile("sampleInput8.bin", 8);
            BinaryFileOperator bf = new BinaryFileOperator("sampleInput8.bin",
                16, 512);
            assertNotNull(bf);
            long eo = bf.getEndOffset();
            byte[] b = new byte[16];
            int ret = bf.getRecord(b, eo + 16);
            assertEquals(-1, ret);
            byte[][] b1 = new byte[10][16];
            ret = bf.getBlockFrom(b1, eo + 16, 10);
            assertEquals(0, ret);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
