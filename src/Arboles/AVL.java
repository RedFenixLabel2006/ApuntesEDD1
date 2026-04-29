package Arboles;

public class AVL {
    private NodoAVL laRaiz;

    AVL() {
        laRaiz = null;
    }

    class NodoAVL {
        NodoAVL lchild;   // hijo izquierdo
        int elemento; // valor almacenado
        short balan;    // factor de balance ∈ {-1, 0, 1}
        NodoAVL rchild;   // hijo derecho

        NodoAVL(int elemento, NodoAVL lchild, NodoAVL rchild) {
            // constructor (balan se inicializa en 0 implícitamente)
        }
    }


    boolean masAlto = false; // bandera global: ¿creció la altura?

    public void Insertar(int elemento) {
        laRaiz   = InsertaenAVL(laRaiz, elemento);
        masAlto  = false; // resetear bandera
    }

    private NodoAVL InsertaenAVL(NodoAVL a, int elemento) {

        // CASO BASE: posición vacía → crear hoja
        if (a == null) {
            masAlto = true;
            return new NodoAVL(elemento, null, null);
        }

        // Insertar en subárbol IZQUIERDO
        else if (elemento < a.elemento) {
            a.lchild = InsertaenAVL(a.lchild, elemento);
            if (masAlto)
                switch (a.balan) {
                    case  1: masAlto = false; a = balanceaIzq(a); break; // balan era +1 → ahora +2 → rotar
                    case  0: a.balan = 1;                         break; // balan era 0  → ahora +1 → ok
                    case -1: masAlto = false; a.balan = 0;        break; // balan era -1 → ahora 0  → ok
                }
        }

        // Insertar en subárbol DERECHO
        else {
            a.rchild = InsertaenAVL(a.rchild, elemento);
            if (masAlto)
                switch (a.balan) {
                    case  1: masAlto = false; a.balan = 0;        break; // balan era +1 → ahora 0  → ok
                    case  0: a.balan = -1;                        break; // balan era 0  → ahora -1 → ok
                    case -1: masAlto = false; a = balanceaDer(a); break; // balan era -1 → ahora -2 → rotar
                }
        }
        return a;
    }

    private NodoAVL balanceaIzq(NodoAVL a) {

        if (a.lchild.balan == 1) {
            // ── CASO LL: rotación simple a la derecha ──────────────────
            a.balan = a.lchild.balan = 0; // ambos nodos quedan balanceados
            a = roteDer(a);

        } else {
            // ── CASO LR: rotación doble izquierda-derecha ──────────────
            // El nieto (lchild.rchild) sube como nueva raíz.
            // Sus hijos se redistribuyen → hay que recalcular balan
            // de los dos nodos que quedan abajo según el balan del nieto.

            switch (a.lchild.rchild.balan) {
                case  1: a.balan = -1; a.lchild.balan =  0; break; // nieto pesaba a izq
                case  0: a.balan =  0; a.lchild.balan =  0; break; // nieto equilibrado
                case -1: a.balan =  0; a.lchild.balan =  1; break; // nieto pesaba a der
            }
            a.lchild.rchild.balan = 0; // el nieto queda como nueva raíz → balan = 0
            a = roteIzqDer(a);
        }
        return a;
    }

    boolean masBajo = false; // bandera: ¿bajó la altura?

    public void Eliminar(int elemento) {
        laRaiz  = EliminaenAVL(laRaiz, elemento);
        masBajo = false; // resetear bandera
    }

    private NodoAVL EliminaenAVL(NodoAVL a, int elemento) {

        if (elemento == a.elemento) {
            // CASO 1: hoja
            if (a.lchild == null && a.rchild == null) {
                masBajo = true;
                return null;
            }
            // CASO 2a: sin hijo izquierdo
            else if (a.lchild == null) {
                masBajo = true;
                return a.rchild;
            }
            // CASO 3: tiene hijo izquierdo → usar mayor del subárbol izquierdo
            else {
                a.elemento = BuscaMayorIzq(a.lchild); // predecesor inorden
                a.lchild   = EliminaenAVL(a.lchild, a.elemento);
                // if (masBajo) a = balanDer(a); // rebalancear si bajó altura
            }
        }
        // Bajar a buscar el elemento
        else if (a.elemento > elemento) {
            a.lchild = EliminaenAVL(a.lchild, elemento);
            // if (masBajo) a = balanDer(a);
        }
        else {
            a.rchild = EliminaenAVL(a.rchild, elemento);
            if (masBajo) a = balanIzq(a); // si la derecha bajó altura, rebalancear por izquierda
        }
        return a;
    }

    private int BuscaMayorIzq(NodoAVL n) {
        while (n.rchild != null)
            n = n.rchild;      // bajar siempre a la derecha
        return n.elemento;     // el nodo sin hijo derecho es el mayor
    }

    private NodoAVL balanceaDer(NodoAVL a) {
        if (a.rchild.balan == -1) {
            // CASO RR → rotación simple a la izquierda
            a.balan = a.rchild.balan = 0;
            a = roteIzq(a);
        } else {
            // CASO RL → rotación doble derecha-izquierda
            // Actualizar balan según el nieto
            switch (a.rchild.lchild.balan) {
                case  1: a.balan = 0;  a.rchild.balan = -1; break;
                case  0: a.balan = 0;  a.rchild.balan =  0; break;
                case -1: a.balan = 1;  a.rchild.balan =  0; break;
            }
            a.rchild.lchild.balan = 0;
            a = roteDerIzq(a);
        }
        return a;
    }
    private NodoAVL balanIzq(NodoAVL a) {
        switch (a.balan) {
            case 1:  // el izquierdo es más alto → puede necesitar rotación
                if (a.lchild.balan != -1) {
                    // CASO LL → rotación simple a la derecha
                    a = roteDer(a);
                    if (a.balan == 0) {
                        a.balan = -1;
                        a.rchild.balan = 1;
                        masBajo = false; // la altura no bajó
                    } else {
                        a.balan = a.rchild.balan = 0;
                    }
                } else {
                    // CASO LR → rotación doble
                    a = roteIzqDer(a);
                    a.rchild.balan = (a.balan ==  1) ? (short) -1 : (short) 0;
                    a.lchild.balan = (a.balan == -1) ? (short)  1 : (short) 0;
                    a.balan = 0;
                }
                break;

            case 0:  // árbol balanceado → solo actualizar balan, altura no baja
                a.balan = 1;
                masBajo = false;
                break;

            case -1: // el derecho era más alto → ahora equilibrado, altura SÍ baja
                a.balan = 0;
                break;
        }
        return a;
    }



    private NodoAVL roteDer(NodoAVL a) {
        NodoAVL temp = a.lchild;   // temp = hijo izquierdo (el que sube)
        a.lchild = temp.rchild;    // el subárbol derecho de temp pasa a ser hijo izq de a
        temp.rchild = a;           // a baja: queda como hijo derecho de temp
        return temp;               // temp es la nueva raíz de este subárbol
    }
    private NodoAVL roteIzq(NodoAVL a) {
        NodoAVL temp = a.rchild;   // temp = hijo derecho (el que sube)
        a.rchild = temp.lchild;    // el subárbol izquierdo de temp pasa a ser hijo der de a
        temp.lchild = a;           // a baja: queda como hijo izquierdo de temp
        return temp;               // temp es la nueva raíz
    }
    private NodoAVL roteIzqDer(NodoAVL a) {
        a.lchild = roteIzq(a.lchild); // paso 1: rotar hijo izquierdo a la izquierda
        return roteDer(a);             // paso 2: rotar a hacia la derecha
    }
    private NodoAVL roteDerIzq(NodoAVL a) {
        a.rchild = roteDer(a.rchild); // paso 1: rotar hijo derecho a la derecha
        return roteIzq(a);             // paso 2: rotar a hacia la izquierda
    }
}
