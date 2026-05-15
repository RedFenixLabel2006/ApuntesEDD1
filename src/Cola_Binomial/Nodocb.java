// ============================
// Nodo árbol/cola binomial
// ============================

package Cola_Binomial;
public class NodoCB {
    NodoCB p;        // El padre
    NodoCB sibling;  // el hermano
    NodoCB child;    // el hijo
    Integer key;     // la clave
    Short degree;    // el grado

    // ============================
    // Constructor
    // ============================
    public NodoCB(NodoCB p, NodoCB sibling, NodoCB child, Integer key, Short degree) {
        this.p       = p;
        this.sibling = sibling;
        this.child   = child;
        this.key     = key;
        this.degree  = degree;
    }

    // ===============================
    // Une dos árboles binomiales B_k
    // this = y (árbol con raíz mayor), z = árbol con raíz menor
    // Resultado: this queda como hijo de z → se forma B_{k+1}
    // ===============================
    public void BinomialLink(NodoCB z) {
        p        = z;
        sibling  = z.child;
        z.child  = this;
        z.degree++;
    }
}
