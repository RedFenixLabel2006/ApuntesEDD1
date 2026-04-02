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
    void insertarOrdenado(int o) {

        if (EstaVacia() || o >= laCabeza.valor) {
            laCabeza = new Nodo(o, laCabeza);
            return;
        }

        Nodo actual = laCabeza;

        while (actual.next != null && actual.next.valor > o) {
            actual = actual.next;
        }

        actual.next = new Nodo(o, actual.next);

    }

    public boolean EstaVacia() {
        return laCabeza == null;
    }

    public boolean buscar(int datoBuscar){
        if (laCabeza == null){
            return false;
        } else {
            Nodo aux1 = laCabeza;
            while (aux1 !=null){
                if (aux1.valor == datoBuscar) {
                    return true;
                } else {
                    aux1 = aux1.next;
                }
            }
        }
        return false;

    }

    public boolean eliminar(int datoBuscar){
        if (laCabeza == null){
            return false;
        } else {
            Nodo aux1 = laCabeza;
            //nunca dara null dado que el anterior siempre existe amenos de ser cabeza
            //pero ese caso ya esta aparte
            Nodo auxant = null;
            //primero cabeza
            if (aux1.valor == datoBuscar){
                this.laCabeza = aux1.next;
                return true;
            }
            while (aux1 !=null){
                //ultimo
                if (aux1.next ==null && aux1.valor == datoBuscar){
                    auxant.next = null;
                    return true;

                }
                //ni ultimo ni primero
                if (aux1.valor == datoBuscar) {
                    auxant.next = aux1.next;
                    return true;
                } else {
                    auxant = aux1;
                    aux1 = aux1.next;
                }
            }
        }
        return false;
    }
}

