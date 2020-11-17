// WARNING: This program uses the Assertion class. When it is run,
// assertions must be turned on. For example, under Linux, use:
// java -ea Genfile

/**
 * Generate a data file. The size is a multiple of 8192 bytes.
 * Each record is one long and one double.
 */

import java.io.*;
import java.util.*;

/**
 * @author Pranav Chimote pchimote
 * @author Sunny Wadkar
 * @version 2020-11-12
 */
public class GenBinaryFile {

    static private final int NUMRECS = 512; // Because they are short ints

    /** Initialize the random variable */
    static private Random value = new Random(); // Hold the Random class object

    /**
     * 
     * @return long value
     */
    private static long randLong() {
        return value.nextLong();
    }


    /**
     * 
     * @return double value
     */
    private static double randDouble() {
        return value.nextDouble();
    }


    /**
     * Generate test binary file
     * 
     * @param filename
     *            file name
     * @param blocks
     *            number of blocks to be generated
     * @throws IOException
     */
    public static void genTestFile(String filename, int blocks)
        throws IOException {
        long val;
        double val2;
        int filesize = blocks; // Size of file in blocks
        DataOutputStream file = new DataOutputStream(new BufferedOutputStream(
            new FileOutputStream(filename)));

        for (int i = 0; i < filesize; i++) {
            for (int j = 0; j < NUMRECS; j++) {
                val = (long)(randLong());
                file.writeLong(val);
                val2 = (double)(randDouble());
                file.writeDouble(val2);
            }
        }
        file.flush();
        file.close();
    }

}
