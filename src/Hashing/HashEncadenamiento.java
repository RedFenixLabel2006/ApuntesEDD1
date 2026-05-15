// ============================================================
// Hashing con Encadenamiento
// Basado en slides Gilberto Gutiérrez - UBB Otoño 2026
// slides 121-131
//
// Cada slot T[i] apunta a la cabeza de una lista encadenada simple.
// Colisiones: los elementos con h(k1)==h(k2) se encadenan en T[h(k)].
// Insertar siempre al COMIENZO de la lista (slide 130).
//
// Función hash: h(k) = k mod m   (slide 125)
// Factor de carga: α = n/m       (slide 131)
// Rendimiento promedio: O(α) = O(1)
// Peor caso: O(n)
// ============================================================
package Hashing;
public class HashEncadenamiento {

    private NodoHash[] tabla; // arreglo de cabezas de listas
    private int        m;    // tamaño de la tabla
    private int        n;    // cantidad de elementos almacenados

    // ============================================================
    // Constructor - MAKE-TABLE(m)
    // Inicializa todos los slots en null
    // ============================================================
    HashEncadenamiento(int m) {
        this.m    = m;
        this.n    = 0;
        tabla     = new NodoHash[m];
        for (int i = 0; i < m; i++) tabla[i] = null;
    }

    // ============================================================
    // Función hash: h(k) = k mod m   (slide 124-125)
    // ============================================================
    private int h(int key) {
        return key % m;
    }

    // ============================================================
    // Insertar(T, o)
    // Inserta o al COMIENZO de la lista T[h(o.key)]  (slide 130)
    // Complejidad: O(1)
    // ============================================================
    void Insertar(int key, String data) {
        int slot = h(key);
        NodoHash nuevo = new NodoHash(key, data);
        // Insertar al frente
        nuevo.siguiente = tabla[slot];
        tabla[slot]     = nuevo;
        n++;
    }

    // ============================================================
    // Buscar(T, key)
    // Recorre la lista en T[h(key)] buscando key.
    // Retorna data si lo encuentra, null si no (slide 130).
    // Complejidad promedio: O(α) = O(1)
    // Peor caso: O(n)
    // ============================================================
    String Buscar(int key) {
        int      slot = h(key);
        NodoHash curr = tabla[slot];
        while (curr != null) {
            if (curr.key == key) return curr.data;
            curr = curr.siguiente;
        }
        return null; // no encontrado
    }

    // ============================================================
    // Eliminar(T, o)
    // Busca y elimina el nodo con clave key de T[h(key)] (slide 130).
    // Complejidad promedio: O(α) = O(1)
    // ============================================================
    boolean Eliminar(int key) {
        int      slot = h(key);
        NodoHash curr = tabla[slot];
        NodoHash prev = null;

        while (curr != null) {
            if (curr.key == key) {
                if (prev == null) tabla[slot]   = curr.siguiente; // era cabeza
                else              prev.siguiente = curr.siguiente;
                n--;
                return true;
            }
            prev = curr;
            curr = curr.siguiente;
        }
        return false; // no encontrado
    }

    // ============================================================
    // Factor de carga α = n/m  (slide 131)
    // ============================================================
    double factorCarga() {
        return (double) n / m;
    }

    // ============================================================
    // Imprime la tabla completa (reproduce figura slide 129)
    // ============================================================
    void printTabla() {
        System.out.println("=== Tabla Hash (m=" + m + ", n=" + n
                + ", α=" + String.format("%.2f", factorCarga()) + ") ===");
        for (int i = 0; i < m; i++) {
            System.out.print("T[" + i + "] -> ");
            NodoHash curr = tabla[i];
            if (curr == null) {
                System.out.println("null");
            } else {
                while (curr != null) {
                    System.out.print("[key=" + curr.key + ", " + curr.data + "] -> ");
                    curr = curr.siguiente;
                }
                System.out.println("null");
            }
        }
    }
}
