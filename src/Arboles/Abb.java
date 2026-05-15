package Arboles;

import java.util.LinkedList;
import java.util.Queue;

public class Abb {
    private NodoAbb laRaiz;
    Abb(){ laRaiz = null; } // árbol vacío al inicio

    class NodoAbb {
        NodoAbb lchild;  // puntero hijo izquierdo
        int elemento;    // valor almacenado
        NodoAbb rchild;  // puntero hijo derecho

        NodoAbb(int elemento, NodoAbb lchild, NodoAbb rchild){
            this.elemento = elemento;
            this.lchild   = lchild;
            this.rchild   = rchild;
        }
    }
    public boolean Buscar(int elemento) {
        return BuscaenAbb(laRaiz, elemento);
    }

    private boolean BuscaenAbb(NodoAbb n, int x) {
        if (n == null)            return false;     // base: vacío
        if (x == n.elemento)      return true;      // base: encontrado
        if (x < n.elemento)       return BuscaenAbb(n.lchild, x); // ir izq
        else                      return BuscaenAbb(n.rchild, x); // ir der
    }
    public void Insert(int elemento) {
        if (laRaiz == null)
            laRaiz = new NodoAbb(elemento, null, null); // árbol vacío
        else
            insert(laRaiz, elemento);
    }

    // Supone que el elemento NO existe ya en el árbol
    private void insert(NodoAbb n, int x) {
        if (x < n.elemento) {                  // va a la izquierda
            if (n.lchild == null)
                n.lchild = new NodoAbb(x, null, null); // insertar hoja
            else
                insert(n.lchild, x);           // seguir bajando
        } else if (x > n.elemento) {            // va a la derecha
            if (n.rchild == null)
                n.rchild = new NodoAbb(x, null, null);
            else
                insert(n.rchild, x);
        }
        // si x == n.elemento: duplicado, no se inserta
    }
    public void Delete(int elemento) {
        laRaiz = EliminaenAbb(laRaiz, elemento);
    }

    // Supone que el elemento existe en el árbol
    private NodoAbb EliminaenAbb(NodoAbb nodo, int x) {
        if (nodo.elemento == x) {
            // CASO 1: nodo hoja — simplemente eliminar
            if (nodo.lchild == null && nodo.rchild == null)
                return null;

                // CASO 2a: sin hijo izquierdo — subir el derecho
            else if (nodo.lchild == null)
                return nodo.rchild;

                // CASO 2b: sin hijo derecho — subir el izquierdo
            else if (nodo.rchild == null)
                return nodo.lchild;

                // CASO 3: dos hijos — reemplazar con el MAYOR del subárbol izquierdo
            else {
                nodo.elemento = MayorElemento(nodo.lchild); // predecesor inorden
                nodo.lchild   = EliminaenAbb(nodo.lchild, nodo.elemento); // eliminarlo
            }
        }
        // Bajar recursivamente a buscar el nodo
        else if (nodo.elemento > x)
            nodo.lchild = EliminaenAbb(nodo.lchild, x);
        else
            nodo.rchild = EliminaenAbb(nodo.rchild, x);

        return nodo;
    }
    public int Max() {
        if (laRaiz == null)return 0;
        return MayorElemento(laRaiz);
    }

    // Método privado — usado también por Delete (caso 3)
    private int MayorElemento(NodoAbb n) {
        while (n.rchild != null)
            n = n.rchild;  // bajar siempre a la derecha
        return n.elemento; // el nodo sin hijo derecho es el máximo
    }



    // mismo codigo para avl
    public void recorridoPorNivel() {
        if (laRaiz == null) return;

        Queue<NodoAbb> cola = new LinkedList<>();
        cola.add(laRaiz);

        while (!cola.isEmpty()) {
            NodoAbb actual = cola.poll();
            System.out.print(actual.elemento + " ");

            if (actual.lchild != null) cola.add(actual.lchild);
            if (actual.rchild != null) cola.add(actual.rchild);
        }
        System.out.println();
    }
}
