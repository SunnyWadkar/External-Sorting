/**
 * MinHeap Class
 * 
 * @author Pranav Chimote pchimote
 * @author Sunny Wadkar sunnywadkar
 * @version 2020-11-12
 * @param <T>
 *            generic type; extends Comparable
 */
public class MinHeap<T extends Comparable<T>> {
    private T[] heap;
    private int maxHeapSize = 0;
    private int currSize = 0;

    /**
     * Min Heap constructor
     * 
     * @param heapArray
     *            input array for heap construction
     * @param max
     *            Max Size of heap
     * @param size
     *            current heap size
     */
    public MinHeap(T[] heapArray, int max, int size) {
        heap = heapArray;
        maxHeapSize = max;
        currSize = size;
        buildheap();
    }


    /**
     * Get current heap size
     * 
     * @return current heap size
     */
    public int getHeapSize() {
        return currSize;
    }


    /**
     * Set current heap size
     * 
     * @param size
     *            new heap size
     */
    public void setHeapSize(int size) {
        currSize = size;
    }


    /**
     * Get Max heap size
     * 
     * @return max heap size
     */
    public int getMaxHeapSize() {
        return maxHeapSize;
    }


    /**
     * Print method for Heap
     * 
     * @return String representation of heap
     */
    public String minHeaptoString() {
        String out = "";
        int i = 0;
        if (currSize > 0) {
            for (i = 0; i < currSize - 1; i++) {
                out = out + heap[i].toString() + " ";
            }
            out = out + heap[i].toString();
        }
        return out;
    }


    /**
     * check if node is leaf node
     * 
     * @param pos
     *            position of node
     * @return decision whether node is leaf node
     */
    private boolean isLeaf(int pos) {
        return (pos >= currSize / 2) && (pos < currSize);
    }


    /**
     * get left child of node
     * 
     * @param pos
     *            position of node
     * @return position of left child
     */
    private int leftchild(int pos) {
        if (pos >= currSize / 2) {
            return -1;
        }
        return 2 * pos + 1;
    }


    /**
     * get right child of node
     * 
     * @param pos
     *            position of node
     * @return position of right child
     */
    private int rightchild(int pos) {
        if (pos >= (currSize - 1) / 2) {
            return -1;
        }
        return 2 * pos + 2;
    }


    /**
     * get parent node
     * 
     * @param pos
     *            position of node
     * @return position of parent node
     */
    private int parent(int pos) {
        if (pos <= 0) {
            return -1;
        }
        return (pos - 1) / 2;
    }


    /**
     * Swap nodes in heap
     * 
     * @param i
     *            first node
     * @param j
     *            second node
     */
    private void heapSwap(int i, int j) {
        T temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }


    /**
     * restore min heap property
     * 
     * @param pos
     *            position of node
     */
    private void siftdown(int pos) {
        while (!isLeaf(pos)) {
            int j = leftchild(pos);
            int k = rightchild(pos);
            if ((j < (currSize - 1)) && (heap[j].compareTo(heap[k]) > 0)) {
                j = k;
            }
            if (heap[pos].compareTo(heap[j]) <= 0) {
                return;
            }
            heapSwap(pos, j);
            pos = j;
        }
    }


    /**
     * Build min heap for the input array
     */
    public void buildheap() {
        for (int i = currSize / 2 - 1; i >= 0; i--) {
            siftdown(i);
        }
    }


    /**
     * Insert item into heap
     * 
     * @param item
     *            item to be inserted
     * @return status of insert operation
     */
    public int insert(T item) {
        if (currSize >= maxHeapSize) {
            return -1;
        }
        int curr = currSize++;
        heap[curr] = item;
        while ((curr != 0) && (heap[curr].compareTo(heap[parent(curr)]) < 0)) {
            heapSwap(curr, parent(curr));
            curr = parent(curr);
        }
        return 0;
    }


    /**
     * remove minimum element
     * 
     * @return minimum element
     */
    public T removeMin() {
        if (currSize == 0) {
            return null;
        }
        heapSwap(0, --currSize);
        if (currSize > 0) {
            siftdown(0);
        }
        return heap[currSize];
    }


    /**
     * Check if heap is empty
     * 
     * @return is heap empty
     */
    public boolean isEmpty() {
        return (currSize == 0);
    }


    /**
     * Insert the item at root
     * 
     * @param item
     *            item to be inserted
     */
    public void insertAtRoot(T item) {
        if (isEmpty()) {
            return;
        }
        heap[0] = item;
        siftdown(0);
    }


    /**
     * get the minimum element in the heap
     * 
     * @return minimum element
     */
    public T getMin() {
        if (isEmpty()) {
            return null;
        }
        else {
            return heap[0];
        }
    }


    /**
     * Replace root for replacement selection
     * 
     * @param item
     *            item for replacement
     */
    public void replaceRootForRS(T item) {
        if (isEmpty()) {
            return;
        }
        heapSwap(0, --currSize);
        heap[currSize] = item;
        if (currSize > 0) {
            siftdown(0);
        }
    }
}
