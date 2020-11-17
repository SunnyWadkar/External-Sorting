import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Pranav Chimote pchimote
 * @author Sunny Wadkar sunnywadkar
 * @version 2020-11-12
 */
public class BinaryFileOperator {

    private long fileOffset = 0;
    private int recLen = 0;
    private long fileLength = 0;
    private long totalRecords = 0;
    private int blockLen = 0;
    private long totalBlocks = 0;
    private long currentBlock = 0;
    private RandomAccessFile raf;

    /**
     * Constructor for BinaryFileOperator class
     * 
     * @param inFile
     *            input file
     * @param recordLength
     *            length of each record
     * @param blockLength
     *            length of block of records
     */
    public BinaryFileOperator(
        String inFile,
        int recordLength,
        int blockLength) {
        try {
            raf = new RandomAccessFile(inFile, "rw");
            fileLength = raf.length();
            recLen = recordLength;
            blockLen = blockLength;
            totalRecords = fileLength / recLen;
            totalBlocks = fileLength / (recLen * blockLen);
            if (fileLength % (recLen * blockLen) != 0) {
                totalBlocks += 1;
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Method to retrieve a record from binary file
     * 
     * @param record
     *            byte array to hold the record
     * @param offset
     *            offset of the record to be retrieved
     * @return Status of the read operation
     */
    public int getRecord(byte[] record, long offset) {
        try {
            if (offset < fileLength) {
                raf.seek(offset);
                raf.read(record);
            }
            else {
                return -1;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * Method to retrieve the next block from the binary file
     * 
     * @param block
     *            byte array to hold the block
     * @return status of the read operation
     */
    public int getNextBlock(byte[][] block) {
        if ((currentBlock == totalBlocks) || (block.length != blockLen)) {
            closeFile();
            return -1;
        }
        try {
            for (int i = 0; i < blockLen; i++) {
                raf.seek(fileOffset);
                raf.read(block[i]);
                fileOffset = raf.getFilePointer();
            }
            currentBlock++;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * Method to retrieve the block from binary file
     * 
     * @param block
     *            byte array to hold the block
     * @param offset
     *            offset of the block
     * @param len
     *            length of the block
     * @return number of records read from the file
     */
    public int getBlockFrom(byte[][] block, long offset, int len) {
        int recordCnt = 0;
        fileOffset = offset;
        try {
            for (int i = 0; i < len; i++) {
                if (fileOffset > totalRecords * recLen) {
                    break;
                }
                raf.seek(fileOffset);
                raf.read(block[i]);
                fileOffset = raf.getFilePointer();
                recordCnt++;
            }
            if (len == blockLen) {
                currentBlock++;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return recordCnt;
    }


    /**
     * Get the current offset in binary file
     * 
     * @return current file offset
     */
    public long getFileOffset() {
        try {
            return raf.getFilePointer();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }


    /**
     * Get the end offset of binary file
     * 
     * @return end offset
     */
    public long getEndOffset() {
        return fileLength;
    }


    /**
     * Method to write records in binary file
     * 
     * @param data
     *            byte array that holds the data
     */
    public void writeRecord(byte[] data) {
        try {
            raf.write(data);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Method to write records at particular offset
     * 
     * @param data
     *            byte array that holds the data
     * @param offset
     *            write offset
     */
    public void writeRecord(byte[] data, long offset) {
        try {
            raf.seek(offset);
            raf.write(data);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Close the binary file that is open
     */
    public void closeFile() {
        try {
            raf.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Method to copy records between files
     * 
     * @param ip
     *            source binary file
     * @param offset
     *            copy offset
     * @param len
     *            number of record to copy
     */
    public void copyFromFile(BinaryFileOperator ip, long offset, int len) {
        try {
            byte[] b = new byte[len];
            if (ip.getRecord(b, offset) == 0) {
                raf.write(b);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Get file statistics
     * 
     * @return array containing total bytes, total records, total blocks
     */
    public long[] getFileStats() {
        long[] stats = { fileLength, totalRecords, totalBlocks };
        return stats;
    }


    /**
     * Delete the binary file
     * 
     * @param fileName
     *            File to be deleted
     */
    public static void deleteIfExists(String fileName) {
        try {
            Files.deleteIfExists(Paths.get(fileName));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
