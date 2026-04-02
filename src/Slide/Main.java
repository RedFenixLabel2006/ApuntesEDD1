package Slide;

public class Main {
        public static void main(String[] args) {
            Ejercicio4 cola = new Ejercicio4(5);

            cola.Insertar(10);
            cola.Insertar(20);
            cola.Insertar(30);

            System.out.println("Size: " + cola.size());   // 3
            System.out.println("Front: " + cola.Front()); // 10
            System.out.println("Borrar: " + cola.Borrar()); // 10
            System.out.println("Borrar: " + cola.Borrar()); // 20
            System.out.println("Front: " + cola.Front()); // 30
            System.out.println("Size: " + cola.size());   // 1
        }

}
