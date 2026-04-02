package Slide;

public class FilaConNodo {

    //FIFO (First In First Out).


    Nodo rear;
    Nodo front;

    FilaConNodo() { front = rear = null; }

    void Insert(int e) {
        if (rear == null || front == null) {
            rear = front = new Nodo(e, null);
        } else {
            rear.next = new Nodo(e, null);
            rear = rear.next;
        }
    }

    int Delete() throws Underflow {
        if (isEmpty()) throw new Underflow("Fila Vacia");
        else {
            int val = front.valor;
            front = front.next;
            return val;
        }
    }

    private boolean isEmpty() { return front == null || rear == null; }

    private class Nodo {
        int valor;
        Nodo next;
        Nodo(int v, Nodo n) { valor = v; next = n; }
    }
}
