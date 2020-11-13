// WARNING: This program uses the Assertion class. When it is run,
// assertions must be turned on. For example, under Linux, use:
// java -ea Genfile

/**
 * Generate a data file. The size is a multiple of 8192 bytes.
 * Each record is one long and one double.
 */

import java.io.*;
import java.util.*;

public class Genfile_proj3 {

    static private final int NumRecs = 512; // Because they are short ints

    /** Initialize the random variable */
    static private Random value = new Random(); // Hold the Random class object

    private static long randLong() {
        return value.nextLong();
    }


    private static double randDouble() {
        return value.nextDouble();
    }


    public static void genTestFile(String filename, int blocks)
        throws IOException {
        long val;
        double val2;
        int filesize = blocks; // Size of file in blocks
        DataOutputStream file = new DataOutputStream(new BufferedOutputStream(
            new FileOutputStream(filename)));

        for (int i = 0; i < filesize; i++) {
            for (int j = 0; j < NumRecs; j++) {
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
