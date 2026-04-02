package Slide;

public class PilaConNodo {

    //LIFO (Last In First Out).

    private Nodo tope;

    PilaConNodo() { tope = null; }

    void Push(String c) { tope = new Nodo(c, tope); }

    int Pop() throws Underflow {
        if (isEmpty()) throw new Underflow("Pila Vacia");
        else {
            Nodo o = tope;
            tope = tope.next;
            return Integer.parseInt(o.valor);
        }
    }

    int Top() throws Underflow {
        if (isEmpty()) throw new Underflow("Pila Vacia");
        return Integer.parseInt(tope.valor);
    }

    int Size() {
        int count = 0;
        Nodo actual = tope;
        while (actual != null) {
            count++;
            actual = actual.next;
        }
        return count;
    }

    boolean isEmpty() { return tope == null; }

    private class Nodo {
        public String valor;
        public Nodo next;
        public Nodo(String c, Nodo next) {
            this.valor = c;
            this.next = next;
        }
    }
}
