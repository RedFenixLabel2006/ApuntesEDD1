package Heaps;

public class MinHeap {
    private int[] heap;
    private int size;

    public MinHeap(int capacity) {
        heap = new int[capacity];
        size = 0;
    }

    // -------------------------------------------------------
    // Helpers
    // -------------------------------------------------------

    private int parent(int i)     { return (i - 1) / 2; }
    private int leftChild(int i)  { return 2 * i + 1; }
    private int rightChild(int i) { return 2 * i + 2; }

    private void swap(int i, int j) {
        int tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    // -------------------------------------------------------
    // MIN-HEAPIFY
    // Mantiene la propiedad de min-heap desde el nodo i hacia abajo.
    // O(log n)
    // -------------------------------------------------------
    public void minHeapify(int i) {
        int left     = leftChild(i);
        int right    = rightChild(i);
        int smallest = i;

        if (left < size && heap[left] < heap[smallest])
            smallest = left;

        if (right < size && heap[right] < heap[smallest])
            smallest = right;

        if (smallest != i) {
            swap(i, smallest);
            minHeapify(smallest);
        }
    }

    // -------------------------------------------------------
    // BUILD-MIN-HEAP
    // Construye un min-heap a partir de un arreglo arbitrario.
    // O(n)
    // -------------------------------------------------------
    public void buildMinHeap(int[] array) {
        heap = array.clone();
        size = array.length;

        for (int i = size / 2 - 1; i >= 0; i--)
            minHeapify(i);
    }

    // -------------------------------------------------------
    // HEAPSORT
    // Ordena el arreglo interno de mayor a menor.
    // O(n log n)
    // -------------------------------------------------------
    public int[] heapSort(int[] array) {
        buildMinHeap(array);

        for (int i = size - 1; i >= 1; i--) {
            swap(0, i);
            size--;
            minHeapify(0);
        }

        int[] sorted = heap.clone();
        size = heap.length;
        return sorted;
    }

    // -------------------------------------------------------
    // HEAP-MINIMUM
    // Retorna el mínimo sin extraerlo.
    // O(1)
    // -------------------------------------------------------
    public int heapMinimum() {
        if (size == 0)
            throw new IllegalStateException("El heap está vacío");
        return heap[0];
    }

    // -------------------------------------------------------
    // HEAP-EXTRACT-MIN
    // Extrae y retorna el mínimo.
    // O(log n)
    // -------------------------------------------------------
    public int heapExtractMin() {
        if (size == 0)
            throw new IllegalStateException("El heap está vacío");

        int min  = heap[0];
        heap[0]  = heap[size - 1];
        size--;
        minHeapify(0);
        return min;
    }

    // -------------------------------------------------------
    // HEAP-DECREASE-KEY
    // Disminuye el valor del nodo i a key (key <= heap[i]).
    // O(log n)
    // -------------------------------------------------------
    public void heapDecreaseKey(int i, int key) {
        if (key > heap[i])
            throw new IllegalArgumentException("El nuevo key es mayor que el actual");

        heap[i] = key;

        while (i > 0 && heap[parent(i)] > heap[i]) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    // -------------------------------------------------------
    // MIN-HEAP-INSERT
    // Inserta un nuevo valor en el heap.
    // O(log n)
    // -------------------------------------------------------
    public void minHeapInsert(int key) {
        if (size == heap.length)
            throw new IllegalStateException("El heap está lleno");

        heap[size] = Integer.MAX_VALUE;
        size++;
        heapDecreaseKey(size - 1, key);
    }

    // -------------------------------------------------------
    // Helpers de uso
    // -------------------------------------------------------

    public int size()       { return size; }
    public boolean isEmpty(){ return size == 0; }

    public void print() {
        System.out.print("Heap: [");
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i]);
            if (i < size - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    // -------------------------------------------------------
    // Main de prueba
    // -------------------------------------------------------
    public static void main(String[] args) {
        MinHeap h = new MinHeap(20);

        h.buildMinHeap(new int[]{4, 1, 3, 2, 16, 9, 10, 14, 8, 7});
        System.out.print("Después de buildMinHeap: ");
        h.print();

        System.out.println("Mínimo: " + h.heapMinimum());

        h.minHeapInsert(0);
        System.out.print("Después de insertar 0: ");
        h.print();

        h.heapDecreaseKey(4, -1);
        System.out.print("Después de disminuir índice 4 a -1: ");
        h.print();

        System.out.println("ExtractMin: " + h.heapExtractMin());
        System.out.print("Después de extractMin: ");
        h.print();

        int[] arr = {4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
        MinHeap sorter = new MinHeap(arr.length);
        int[] sorted = sorter.heapSort(arr);
        System.out.print("HeapSort: [");
        for (int i = 0; i < sorted.length; i++) {
            System.out.print(sorted[i]);
            if (i < sorted.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}
