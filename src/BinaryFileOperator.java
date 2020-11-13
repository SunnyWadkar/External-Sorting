import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Pranav Chimote pchimote
 * @author Sunny Wadkar sunnywadkar
 * @version 2020-9-18
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


    public long getFileOffset() {
        try {
            return raf.getFilePointer();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }


    public long getEndOffset() {
        return fileLength;
    }


    public void writeRecord(byte[] data) {
        try {
            raf.write(data);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void writeRecord(byte[] data, long offset) {
        try {
            raf.seek(offset);
            raf.write(data);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void closeFile() {
        try {
            raf.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


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


    public long[] getFileStats() {
        long[] stats = { fileLength, totalRecords, totalBlocks };
        return stats;
    }


    public static void deleteIfExists(String fileName) {
        try {
            Files.deleteIfExists(Paths.get(fileName));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
