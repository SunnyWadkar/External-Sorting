public class MinHeap<T extends Comparable<T>> {
    private T Heap[];
    private int maxHeapSize = 0;
    private int currSize = 0;

    public MinHeap(T[] heapArray, int max, int size) {
        Heap = heapArray;
        maxHeapSize = max;
        currSize = size;
        buildheap();
    }


    public int getHeapSize() {
        return currSize;
    }


    public void setHeapSize(int size) {
        currSize = size;
    }


    public int getMaxHeapSize() {
        return maxHeapSize;
    }


    public String minHeaptoString() {
        String out = "";
        int i = 0;
        if (currSize > 0) {
            for (i = 0; i < currSize - 1; i++) {
                out = out + Heap[i].toString() + " ";
            }
            out = out + Heap[i].toString();
        }
        return out;
    }


    private boolean isLeaf(int pos) {
        return (pos >= currSize / 2) && (pos < currSize);
    }


    private int leftchild(int pos) {
        if (pos >= currSize / 2) {
            return -1;
        }
        return 2 * pos + 1;
    }


    private int rightchild(int pos) {
        if (pos >= (currSize - 1) / 2) {
            return -1;
        }
        return 2 * pos + 2;
    }


    private int parent(int pos) {
        if (pos <= 0) {
            return -1;
        }
        return (pos - 1) / 2;
    }


    private void heapSwap(int i, int j) {
        T temp = Heap[i];
        Heap[i] = Heap[j];
        Heap[j] = temp;
    }


    private void siftdown(int pos) {
        while (!isLeaf(pos)) {
            int j = leftchild(pos);
            int k = rightchild(pos);
            if ((j < (currSize - 1)) && (Heap[j].compareTo(Heap[k]) > 0)) {
                j = k;
            }
            if (Heap[pos].compareTo(Heap[j]) <= 0) {
                return;
            }
            heapSwap(pos, j);
            pos = j;
        }
    }


    public void buildheap() {
        for (int i = currSize / 2 - 1; i >= 0; i--) {
            siftdown(i);
        }
    }


    public int insert(T item) {
        if (currSize >= maxHeapSize) {
            return -1;
        }
        int curr = currSize++;
        Heap[curr] = item;
        while ((curr != 0) && (Heap[curr].compareTo(Heap[parent(curr)]) < 0)) {
            heapSwap(curr, parent(curr));
            curr = parent(curr);
        }
        return 0;
    }


    public T removeMin() {
        if (currSize == 0) {
            return null;
        }
        heapSwap(0, --currSize);
        if (currSize > 0) {
            siftdown(0);
        }
        return Heap[currSize];
    }


    public boolean isEmpty() {
        return (currSize == 0);
    }


    public void insertAtRoot(T item) {
        if (isEmpty()) {
            return;
        }
        Heap[0] = item;
        siftdown(0);
    }


    public T getMin() {
        if (isEmpty()) {
            return null;
        }
        else {
            return Heap[0];
        }
    }


    public void replaceRootForRS(T item) {
        if (isEmpty()) {
            return;
        }
        heapSwap(0, --currSize);
        Heap[currSize] = item;
        if (currSize > 0) {
            siftdown(0);
        }
    }

}
