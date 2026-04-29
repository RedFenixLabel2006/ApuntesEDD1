package Heaps;

public class MaxHeap {
    private int[] heap;
    private int size;

    public MaxHeap(int capacity) {
        heap = new int[capacity];
        size = 0;
    }

    // -------------------------------------------------------
    // Helpers
    // -------------------------------------------------------

    private int parent(int i)    { return (i - 1) / 2; }
    private int leftChild(int i) { return 2 * i + 1; }
    private int rightChild(int i){ return 2 * i + 2; }

    private void swap(int i, int j) {
        int tmp  = heap[i];
        heap[i]  = heap[j];
        heap[j]  = tmp;
    }

    // -------------------------------------------------------
    // MAX-HEAPIFY
    // Mantiene la propiedad de max-heap desde el nodo i hacia abajo.
    // O(log n)
    // -------------------------------------------------------
    public void maxHeapify(int i) {
        int left    = leftChild(i);
        int right   = rightChild(i);
        int largest = i;

        if (left < size && heap[left] > heap[largest])
            largest = left;

        if (right < size && heap[right] > heap[largest])
            largest = right;

        if (largest != i) {
            swap(i, largest);
            maxHeapify(largest);
        }
    }

    // -------------------------------------------------------
    // BUILD-MAX-HEAP
    // Construye un max-heap a partir de un arreglo arbitrario.
    // O(n)
    // -------------------------------------------------------
    public void buildMaxHeap(int[] array) {
        heap = array.clone();
        size = array.length;

        for (int i = size / 2 - 1; i >= 0; i--)
            maxHeapify(i);
    }

    // -------------------------------------------------------
    // HEAPSORT
    // Ordena el arreglo interno de menor a mayor.
    // O(n log n)
    // -------------------------------------------------------
    public int[] heapSort(int[] array) {
        buildMaxHeap(array);

        for (int i = size - 1; i >= 1; i--) {
            swap(0, i);
            size--;
            maxHeapify(0);
        }

        int[] sorted = heap.clone();
        size = heap.length;
        return sorted;
    }

    // -------------------------------------------------------
    // HEAP-MAXIMUM
    // Retorna el máximo sin extraerlo.
    // O(1)
    // -------------------------------------------------------
    public int heapMaximum() {
        if (size == 0)
            throw new IllegalStateException("El heap está vacío");
        return heap[0];
    }

    // -------------------------------------------------------
    // HEAP-EXTRACT-MAX
    // Extrae y retorna el máximo.
    // O(log n)
    // -------------------------------------------------------
    public int heapExtractMax() {
        if (size == 0)
            throw new IllegalStateException("El heap está vacío");

        int max  = heap[0];
        heap[0]  = heap[size - 1];
        size--;
        maxHeapify(0);
        return max;
    }

    // -------------------------------------------------------
    // HEAP-INCREASE-KEY
    // Aumenta el valor del nodo i a key (key >= heap[i]).
    // O(log n)
    // -------------------------------------------------------
    public void heapIncreaseKey(int i, int key) {
        if (key < heap[i])
            throw new IllegalArgumentException("El nuevo key es menor que el actual");

        heap[i] = key;

        while (i > 0 && heap[parent(i)] < heap[i]) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    // -------------------------------------------------------
    // MAX-HEAP-INSERT
    // Inserta un nuevo valor en el heap.
    // O(log n)
    // -------------------------------------------------------
    public void maxHeapInsert(int key) {
        if (size == heap.length)
            throw new IllegalStateException("El heap está lleno");

        heap[size] = Integer.MIN_VALUE;
        size++;
        heapIncreaseKey(size - 1, key);
    }

    // -------------------------------------------------------
    // Helpers de uso
    // -------------------------------------------------------

    public int size()      { return size; }
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
        MaxHeap h = new MaxHeap(20);

        h.buildMaxHeap(new int[]{4, 1, 3, 2, 16, 9, 10, 14, 8, 7});
        System.out.print("Después de buildMaxHeap: ");
        h.print();

        System.out.println("Máximo: " + h.heapMaximum());

        h.maxHeapInsert(15);
        System.out.print("Después de insertar 15: ");
        h.print();

        h.heapIncreaseKey(4, 18);
        System.out.print("Después de aumentar índice 4 a 18: ");
        h.print();

        System.out.println("ExtractMax: " + h.heapExtractMax());
        System.out.print("Después de extractMax: ");
        h.print();

        int[] arr = {4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
        MaxHeap sorter = new MaxHeap(arr.length);
        int[] sorted = sorter.heapSort(arr);
        System.out.print("HeapSort: [");
        for (int i = 0; i < sorted.length; i++) {
            System.out.print(sorted[i]);
            if (i < sorted.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}
