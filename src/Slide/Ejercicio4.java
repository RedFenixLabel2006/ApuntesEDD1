package Slide;

public class Ejercicio4 {
    private PilaConArreglo pila1; // pila principal (encolar)
    private PilaConArreglo pila2; // pila auxiliar (desencolar)
    private int capacidad;
    private int cantidad;

    public Ejercicio4(int capacidad) {
        this.capacidad = capacidad;
        this.cantidad = 0;
        pila1 = new PilaConArreglo(capacidad);
        pila2 = new PilaConArreglo(capacidad);
    }

    // Encolar: simplemente apila en pila1
    public void Insertar(int valor) throws Overflow {
        if (isFull()) throw new Overflow("Cola llena");
        pila1.Push(valor);
        cantidad++;
    }

    // Desencolar: pasa todo de pila1 a pila2, saca el tope, devuelve todo
    public int Borrar() throws Underflow {
        if (isEmpty()) throw new Underflow("Cola vacía");
        pasarAPila2();
        int valor = pila2.Pop();
        pasarAPila1();
        cantidad--;
        return valor;
    }

    // Front: igual que Borrar pero sin eliminar
    public int Front() throws Underflow {
        if (isEmpty()) throw new Underflow("Cola vacía");
        pasarAPila2();
        int valor = pila2.Top();
        pasarAPila1();
        return valor;
    }

    public boolean isEmpty() { return cantidad == 0; }
    public boolean isFull()  { return cantidad >= capacidad; }
    public int size()        { return cantidad; }

    // Mueve todos los elementos de pila1 a pila2 (invierte el orden)
    private void pasarAPila2() throws Underflow {
        while (pila1.Size() > 0) {
            pila2.Push(pila1.Pop());
        }
    }

    // Devuelve todos los elementos de pila2 a pila1
    private void pasarAPila1() throws Underflow {
        while (pila2.Size() > 0) {
            pila1.Push(pila2.Pop());
        }

}


//El truco clave es que al pasar todos los elementos de `pila1` a `pila2`, el orden se **invierte**, y eso convierte el comportamiento LIFO en FIFO:

// Encolar 10, 20, 30:
//pila1 → [10, 20, 30]  (30 es el tope)
//
//Pasar a pila2:
//pila2 → [30, 20, 10]  (10 es el tope ahora)
//
//Desencolar → saca 10  ← que fue el PRIMERO en entrar ✓
}
