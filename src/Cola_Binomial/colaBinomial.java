// ==============================
// Cola Binomial (Binomial Heap)
// Basada en slides Gilberto Gutiérrez - UBB Otoño 2026
// ==============================

package Cola_Binomial;
public class ColaBinomial {

    NodoCB head; // Puntero al primer árbol binomial de la cola

    // ==============================
    // Constructor
    // ==============================
    ColaBinomial() {
        head = null;
    }

    // ======================================================
    // MINIMUM()
    // Obtiene la menor clave almacenada en la cola binomial.
    // Recorre sólo las raíces (lista de hermanos en head).
    // Complejidad: O(log n)
    // ======================================================
    public Integer BinomialHeapMinimum() {
        NodoCB t   = head;
        int    min = t.key;
        while (t != null) {
            if (t.key < min) min = t.key;
            t = t.sibling;
        }
        return min;
    }

    // ======================================================
    // BINOMIAL-HEAP-MERGE
    // Mezcla las listas de raíces de dos colas en orden
    // no-decreciente de grado (análogo a merge de mergesort).
    // ======================================================
    private static ColaBinomial BinomialHeapMerge(ColaBinomial H1, ColaBinomial H2) {
        ColaBinomial H = new ColaBinomial();

        NodoCB x = H1.head;
        NodoCB y = H2.head;
        NodoCB tail = null; // último nodo de la nueva lista

        // Encabezado: elegir el de menor grado
        if (x == null) { H.head = y; return H; }
        if (y == null) { H.head = x; return H; }

        if (x.degree <= y.degree) { H.head = x; x = x.sibling; }
        else                      { H.head = y; y = y.sibling; }
        tail = H.head;

        while (x != null && y != null) {
            if (x.degree <= y.degree) { tail.sibling = x; x = x.sibling; }
            else                      { tail.sibling = y; y = y.sibling; }
            tail = tail.sibling;
        }
        tail.sibling = (x != null) ? x : y;
        return H;
    }

    // ======================================================
    // BINOMIAL-HEAP-UNION()
    // Fusión de dos colas binomiales.
    // Implementa los 4 casos del algoritmo CLRS / Gutiérrez.
    // Complejidad: O(log n)
    // ======================================================
    private NodoCB BinomialHeapUnion(ColaBinomial H2) {
        ColaBinomial H = BinomialHeapMerge(this, H2);
        if (H.head == null) return H.head;

        NodoCB prevx = null;
        NodoCB x     = H.head;
        NodoCB nextx = x.sibling;

        while (nextx != null) {
            // Caso 1: grados distintos  →  avanzar sin tocar
            // Caso 2: tres iguales      →  avanzar (se resuelve en siguiente iter)
            if ((x.degree != nextx.degree) ||
                (nextx.sibling != null && nextx.sibling.degree == x.degree)) {
                prevx = x;          // Cases 1 y 2
                x     = nextx;      // Cases 1 y 2
            }
            // Caso 3: key[x] <= key[next-x]  →  next-x se cuelga de x
            else if (x.key <= nextx.key) {
                x.sibling = nextx.sibling; // Case 3
                nextx.BinomialLink(x);     // Case 3
            }
            // Caso 4: key[x] > key[next-x]  →  x se cuelga de next-x
            else {
                if (prevx == null) H.head       = nextx; // Case 4
                else               prevx.sibling = nextx; // Case 4
                x.BinomialLink(nextx);                    // Case 4
                x = nextx;                                // Case 4
            }
            nextx = x.sibling;
        }
        return H.head;
    }

    // ======================================================
    // INSERT(H, x)
    // Crea una cola temporal de un nodo y hace UNION.
    // Complejidad: O(log n)
    // ======================================================
    void Insert(int a) {
        ColaBinomial H1 = new ColaBinomial();
        H1.head = new NodoCB(null, null, null, a, (short) 0);
        head = BinomialHeapUnion(H1);
    }

    // ======================================================
    // EXTRACT-MIN(H)
    // 1. Encuentra y remueve el AB con raíz mínima.
    // 2. Construye H' con los hijos de esa raíz en orden reverso.
    // 3. Fusiona H con H'.
    // Complejidad: O(log n)
    // ======================================================
    public NodoCB ExtractMin() {
        if (head == null) return null;

        // --- Paso 1: encontrar la raíz mínima y removerla de la lista ---
        NodoCB minPrev = null;
        NodoCB minNode = head;
        NodoCB prev    = null;
        NodoCB curr    = head;

        while (curr != null) {
            if (curr.key < minNode.key) {
                minNode = curr;
                minPrev = prev;
            }
            prev = curr;
            curr = curr.sibling;
        }

        // Remover minNode de la lista de raíces
        if (minPrev == null) head          = minNode.sibling;
        else                 minPrev.sibling = minNode.sibling;

        // --- Paso 2: revertir la lista de hijos de minNode ---
        // Los hijos están en orden B_{k-1}, B_{k-2}, ..., B_0
        // Para UNION necesitamos orden creciente de grado: B_0, B_1, ..., B_{k-1}
        NodoCB child   = minNode.child;
        NodoCB revHead = null;
        while (child != null) {
            NodoCB next  = child.sibling;
            child.sibling = revHead;
            child.p       = null;
            revHead       = child;
            child         = next;
        }

        // --- Paso 3: fusionar ---
        ColaBinomial H2 = new ColaBinomial();
        H2.head = revHead;
        head = BinomialHeapUnion(H2);

        return minNode;
    }

    // ======================================================
    // DECREASE-KEY(H, x, k)
    // Reduce la clave de x a k y sube "bubbleup" para
    // mantener la propiedad min-heap.
    // Complejidad: O(log n)
    // ======================================================
    public void DecreaseKey(NodoCB x, int k) {
        if (k > x.key) {
            System.out.println("Error: nueva clave mayor que la actual.");
            return;
        }
        x.key = k;
        NodoCB y      = x;
        NodoCB parent = y.p;
        while (parent != null && y.key < parent.key) {
            // Intercambiar claves (bubble-up)
            int tmp   = y.key;
            y.key     = parent.key;
            parent.key = tmp;
            y      = parent;
            parent = y.p;
        }
    }

    // ======================================================
    // DELETE(H, x)
    // Baja la clave a -∞ y luego hace ExtractMin.
    // Complejidad: O(log n)
    // ======================================================
    public void Delete(NodoCB x) {
        DecreaseKey(x, Integer.MIN_VALUE);
        ExtractMin();
    }

    // ======================================================
    // Utilidad: imprime la lista de raíces con su grado
    // ======================================================
    public void printRoots() {
        NodoCB t = head;
        System.out.print("Raíces [key(grado)]: ");
        while (t != null) {
            System.out.print(t.key + "(B" + t.degree + ") ");
            t = t.sibling;
        }
        System.out.println();
    }

    // ======================================================
    // Utilidad: imprime el árbol completo (preorden)
    // ======================================================
    public void printHeap() {
        System.out.println("=== Cola Binomial ===");
        NodoCB t = head;
        while (t != null) {
            System.out.println("-- Árbol B" + t.degree + " --");
            printTree(t, 0);
            t = t.sibling;
        }
    }

    private void printTree(NodoCB node, int level) {
        if (node == null) return;
        System.out.println("  ".repeat(level) + node.key + " (grado=" + node.degree + ")");
        // Recorrer hijos
        NodoCB child = node.child;
        while (child != null) {
            printTree(child, level + 1);
            child = child.sibling;
        }
    }
}
