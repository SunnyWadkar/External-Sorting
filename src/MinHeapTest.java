import student.TestCase;

/**
 * Tests for Min Heap
 * 
 * @author Pranav Chimote pchimote
 * @author Sunny Wadkar sunnywadkar
 * @version 2020-11-12
 */
public class MinHeapTest extends TestCase {

    /**
     * Get code coverage of the class declaration.
     */
    public void testMinHeap() {
        Integer[] a = { 45, 32, 90, 5, 78, 2, 54, 55, 10 };
        MinHeap<Integer> h = new MinHeap<Integer>(a, 9, 9);
        String ex = "2 5 45 10 78 90 54 55 32";
        assertEquals(ex, h.minHeaptoString());
        assertEquals(9, h.getHeapSize());
        assertEquals(9, h.getMaxHeapSize());
    }


    /**
     * Test Min Heap Insert
     */
    public void testMinHeapInsert() {
        Integer[] a = new Integer[10];
        a[0] = 45;
        a[1] = 32;
        a[2] = 90;
        a[3] = 5;
        a[4] = 78;
        a[5] = 2;
        a[6] = 54;
        a[7] = 55;
        a[8] = 10;
        MinHeap<Integer> h = new MinHeap<Integer>(a, 10, 9);
        assertEquals(9, h.getHeapSize());
        h.insert(1);
        String ex = "1 2 45 10 5 90 54 55 32 78";
        assertEquals(ex, h.minHeaptoString());
        assertEquals(10, h.getHeapSize());
    }


    /**
     * Test Min Heap Remove
     */
    public void testMinremove() {
        Integer[] a = { 45, 32, 90, 5, 78, 2, 54, 55, 10 };
        MinHeap<Integer> h = new MinHeap<Integer>(a, 9, 9);
        int min = h.removeMin();
        assertEquals(2, min);
        assertEquals(8, h.getHeapSize());
        min = h.removeMin();
        assertEquals(5, min);
        assertEquals(7, h.getHeapSize());
        min = h.removeMin();
        assertEquals(10, min);
        assertEquals(6, h.getHeapSize());
        min = h.removeMin();
        assertEquals(32, min);
        assertEquals(5, h.getHeapSize());
        min = h.removeMin();
        assertEquals(45, min);
        assertEquals(4, h.getHeapSize());
    }


    /**
     * Test Replacement selection methods
     */
    public void testRSMethods() {
        Integer[] a = { 45, 32, 90, 5, 78, 2, 54, 55, 10 };
        MinHeap<Integer> h = new MinHeap<Integer>(a, 9, 9);
        int min = h.getMin();
        assertEquals(2, min);
        assertEquals(9, h.getHeapSize());
        h.insertAtRoot(7);
        min = h.getMin();
        assertEquals(5, min);
        assertEquals(9, h.getHeapSize());
        h.replaceRootForRS(4);
        min = h.getMin();
        assertEquals(7, min);
        assertEquals(8, h.getHeapSize());
        assertEquals(4, (int)a[8]);
        h.replaceRootForRS(3);
        min = h.getMin();
        assertEquals(10, min);
        assertEquals(7, h.getHeapSize());
        assertEquals(3, (int)a[7]);
    }


    /**
     * Test other methods
     */
    public void testOther() {
        Integer[] a = { 50 };
        MinHeap<Integer> h = new MinHeap<Integer>(a, 1, 1);
        h.removeMin();
        assertNull(h.removeMin());
        h.replaceRootForRS(5);
        assertNull(h.getMin());
        h.insertAtRoot(10);
        h.insert(7);
        assertEquals(-1, h.insert(7));
    }
}
