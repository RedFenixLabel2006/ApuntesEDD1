// ============================
// Nodo de la lista encadenada
// Almacena un par <key, Data>
// ============================
package Hashing;
class NodoHash {
    int      key;
    String   data;
    NodoHash siguiente;

    NodoHash(int key, String data) {
        this.key       = key;
        this.data      = data;
        this.siguiente = null;
    }
}
