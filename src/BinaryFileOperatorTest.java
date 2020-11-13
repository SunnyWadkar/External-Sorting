import java.io.IOException;

import student.TestCase;

public class BinaryFileOperatorTest extends TestCase {

    public void testBinaryFileOperator() {
        try {
            Genfile_proj3.genTestFile("sampleInput8.bin", 8);
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


    public void testBinaryFileOperator1() {
        try {
            Genfile_proj3.genTestFile("sampleInput8.bin", 8);
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
