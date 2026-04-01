package Slide;

public class Lista {
    private Nodo laCabeza;


    public Lista() {
        laCabeza = null;
    }

    void Insertar(int o) {
        if (EstaVacia()) {
            laCabeza = new Nodo(o, null);
        } else {
            laCabeza = new Nodo(o, laCabeza);
        }
    }

    public boolean EstaVacia() {
        return laCabeza == null;
    }

}
