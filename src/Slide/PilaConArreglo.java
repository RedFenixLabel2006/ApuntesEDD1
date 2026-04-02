package Slide;

public class PilaConArreglo {

    //LIFO (Last In First Out).

    private int tope;
    private int tamanho;
    private int[] elemento;

    public PilaConArreglo(int tam) {
        tamanho = tam;
        elemento = new int[tam];
        tope = -1;
    }

    void Push(int x) throws Overflow {
        if (isFull()) throw new Overflow("Pila Llena");
        else elemento[++tope] = x;
    }
    int Top() throws Underflow {
        if (isEmpty()) throw new Underflow("Pila Vacia");
        else return elemento[tope];
    }

    int Size() { return tope + 1; }

    int Pop() throws Underflow {
        if (isEmpty()) throw new Underflow("Pila Vacia");
        else return elemento[tope--];
    }

    private boolean isFull()  { return (tope + 1 >= tamanho); }
    private boolean isEmpty() { return (tope < 0); }
}
