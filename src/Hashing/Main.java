// ============================================================
// Demo: reproduce el ejemplo del slide 129
// U = [0,49], K = {10,12,32,33,22,20,8,45}, h(k) = k mod 10
//
// Colisiones esperadas:
//   slot 0 → 20, 10        (h(20)=0, h(10)=0)
//   slot 2 → 22, 32, 12    (h(22)=2, h(32)=2, h(12)=2)
//   slot 3 → 33
//   slot 5 → 45
//   slot 8 → 8
// ============================================================
package Hashing;

public class Main {

    public static void main(String[] args) {

        System.out.println("============================================================");
        System.out.println(" Hashing con Encadenamiento - Demo slides Gutiérrez UBB");
        System.out.println("============================================================\n");

        // Tabla de tamaño 10, h(k) = k mod 10
        HashEncadenamiento T = new HashEncadenamiento(10);

        // Insertar los elementos del ejemplo (slide 129)
        int[] claves = {10, 12, 32, 33, 22, 20, 8, 45};
        System.out.println("Insertando K = {10, 12, 32, 33, 22, 20, 8, 45}:");
        for (int k : claves) {
            T.Insertar(k, "Data" + k);
            System.out.println("  Insertar(" + k + ") → slot h(" + k + ") = " + (k % 10));
        }
        System.out.println();
        T.printTabla();

        // --- Buscar ---
        System.out.println("\n--- Buscar ---");
        int[] buscar = {32, 20, 99};
        for (int k : buscar) {
            String res = T.Buscar(k);
            if (res != null) System.out.println("Buscar(" + k + ") → encontrado: " + res);
            else             System.out.println("Buscar(" + k + ") → NO encontrado");
        }

        // --- Eliminar ---
        System.out.println("\n--- Eliminar ---");
        int[] eliminar = {32, 10};
        for (int k : eliminar) {
            boolean ok = T.Eliminar(k);
            System.out.println("Eliminar(" + k + ") → " + (ok ? "eliminado" : "no estaba"));
        }
        System.out.println();
        T.printTabla();

        // --- Factor de carga ---
        System.out.println("\nFactor de carga α = n/m = "
                + T.factorCarga() + "  → búsqueda promedio O(α) = O(1)");
    }
}
