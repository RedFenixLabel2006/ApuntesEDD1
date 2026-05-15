
package Cola_Binomial;

public class Main {

    public static void main(String[] args) {

        System.out.println("============================================");
        System.out.println(" Cola Binomial - Demo slides Gutiérrez UBB");
        System.out.println("============================================\n");

        ColaBinomial H = new ColaBinomial();

        // Insertar los 13 elementos del ejemplo (slide 105)
        // n = 13  →  representación binaria 1101  →  B3, B2, B0
        int[] valores = {10, 1, 12, 25, 18, 6, 8, 14, 29, 11, 17, 38, 27};
        System.out.println("Insertando: ");
        for (int v : valores) {
            System.out.print(v + " ");
            H.Insert(v);
        }
        System.out.println("\n");

        H.printHeap();

        System.out.println("\nMínimo: " + H.BinomialHeapMinimum());

        System.out.println("\n--- EXTRACT-MIN ---");
        NodoCB min = H.ExtractMin();
        System.out.println("Extraído: " + min.key);
        H.printHeap();
        System.out.println("Nuevo mínimo: " + H.BinomialHeapMinimum());

        System.out.println("\n--- INSERT(2) ---");
        H.Insert(2);
        H.printHeap();
        System.out.println("Mínimo: " + H.BinomialHeapMinimum());

        System.out.println("\n--- EXTRACT-MIN ---");
        min = H.ExtractMin();
        System.out.println("Extraído: " + min.key);
        H.printRoots();
    }
}

