package Arboles;


public class ejerciciosRecursividad {
    class Nodo {
        public int valor;
        public Nodo next;

        public Nodo(int valor, Nodo next) {
            this.valor = valor;
            this.next = next;
        }

    }
    // Retorna el último nodo de la lista
    static Nodo ultimo(Nodo cabeza) {
        if (cabeza == null)              return null;   // lista vacía
        if (cabeza.next == null)    return cabeza; // caso base: soy el último
        return ultimo(cabeza.next);               // caso recursivo
    }
    // Retorna la cantidad de nodos en la lista
    static int contar(Nodo cabeza) {
        if (cabeza == null) return 0;                  // caso base
        return 1 + contar(cabeza.next);           // este nodo + el resto
    }
    // Retorna la posición (base 1) del valor buscado, o -1 si no existe
    static int buscar(Nodo cabeza, int valor, int posicion) {
        if (cabeza == null)           return -1;                              // no encontrado
        if (cabeza.valor == valor)     return posicion;                        // encontrado
        return buscar(cabeza.next, valor, posicion + 1);                 // avanzar
    }

    // Retorna la nueva cabeza de la lista invertida
    static Nodo invertir(Nodo cabeza) {
        if (cabeza == null || cabeza.next == null)
            return cabeza;                                    // caso base

        Nodo nuevaCabeza = invertir(cabeza.next);        // invertir el resto

        cabeza.next.next = cabeza;                  // el siguiente apunta de vuelta
        cabeza.next = null;                              // cabeza pasa a ser el último

        return nuevaCabeza;
    }
}
