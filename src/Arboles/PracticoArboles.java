package Arboles;

import java.util.LinkedList;
import java.util.Queue;

class ArbolG {
    private class NodoArbol {
        String nombre;
        NodoArbol hijo;
        NodoArbol hermano;
        NodoArbol(String elNombre, NodoArbol elHijo, NodoArbol elHermano) {
            nombre   = elNombre;
            hijo     = elHijo;
            hermano  = elHermano;
        }
    }

    private NodoArbol root;
    public ArbolG() { root = null; }

    // Post-orden: primero todos los hijos, luego el nodo actual
    public void postOrden(NodoArbol nodo) {
        if (nodo == null) return;

        // 1. Recorrer todos los hijos en post-orden
        NodoArbol hijo = nodo.hijo;
        while (hijo != null) {
            postOrden(hijo);
            hijo = hijo.hermano;
        }

        // 2. Visitar el nodo actual (después de sus hijos)
        System.out.print(nodo.nombre + " ");
    }

    public void postOrden() {
        postOrden(root);
    }

    // ── 2. Size ───────────────────────────────────────────────
    int Size() { return size(root); }

    private int size(NodoArbol nodo) {
        if (nodo == null) return 0;
        return 1 + size(nodo.hijo) + size(nodo.hermano);
    }

    // ── 3. newNodo ────────────────────────────────────────────
    private void newNodo(NodoArbol padre, String s) {
        if (padre == null) return;
        padre.hijo = new NodoArbol(s, null, padre.hijo);
    }

    // ── 4. move ───────────────────────────────────────────────
    private void move(NodoArbol origen, NodoArbol destino) {
        if (origen == null || destino == null) return;
        origen.hermano = null;
        if (destino.hijo == null) {
            destino.hijo = origen;
        } else {
            NodoArbol actual = destino.hijo;
            while (actual.hermano != null) actual = actual.hermano;
            actual.hermano = origen;
        }
    }

    // ── 5. RecorridoPorNivel ──────────────────────────────────
    void recorridoPornivel() {
        if (root == null) return;
        Queue<NodoArbol> cola = new LinkedList<>();
        cola.add(root);
        while (!cola.isEmpty()) {
            NodoArbol actual = cola.poll();
            while (actual != null) {
                System.out.print(actual.nombre + " ");
                if (actual.hijo != null) cola.add(actual.hijo);
                actual = actual.hermano;
            }
        }
        System.out.println();
    }

    // ── Método auxiliar para construir el árbol del ejemplo ───
    public static void main(String[] args) {
        ArbolG t = new ArbolG();
        // Construir el árbol manualmente para probar
        t.root = t.new NodoArbol("a", null, null);
        NodoArbol b = t.new NodoArbol("b", null, null);
        NodoArbol c = t.new NodoArbol("c", null, null);
        NodoArbol d = t.new NodoArbol("d", null, null);
        t.root.hijo = b; b.hermano = c; c.hermano = d;

        NodoArbol e = t.new NodoArbol("e", null, null);
        NodoArbol f = t.new NodoArbol("f", null, null);
        b.hijo = e; e.hermano = f;

        t.newNodo(c, "i"); t.newNodo(c, "j"); // j queda como primero
        NodoArbol g = t.new NodoArbol("g", null, null);
        c.hijo.hermano.hermano = g; // g es tercer hijo de c

        System.out.print("Postorder: ");    t.postOrden();
        System.out.println("Size: "         + t.Size());
        System.out.print("Por nivel: ");    t.recorridoPornivel();
    }
}
