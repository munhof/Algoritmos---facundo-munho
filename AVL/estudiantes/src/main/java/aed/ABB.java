package aed;

import java.util.*;

// Todos los tipos de datos "Comparables" tienen el método compareTo()
// elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
public class ABB<T extends Comparable<T>> implements Conjunto<T> {
    // Agregar atributos privados del Conjunto
    private Nodo _Raiz;
    private int _Cardinal;

    private class Nodo {
        T valor;
        Nodo izq;
        Nodo der;
        Nodo padre;

        Nodo(T v) {
            valor = v;
            izq = null;
            der = null;
            padre = null;
        }

    }

    public ABB() {
        _Raiz = null;
        _Cardinal = 0;
    }

    public int cardinal() {
        return _Cardinal;
    }

    public T minimo() {
            throw new UnsupportedOperationException("No implementada aun");
    }

    public T maximo() {
            throw new UnsupportedOperationException("No implementada aun");
    }

    public void insertar(T elem) {
        Nodo actual = _Raiz;
        Nodo nuevo = new Nodo(elem);
        if (_Raiz == null) {
            _Raiz = nuevo;
            _Cardinal = 1;
            return;
        } 
        
        if (pertenece(elem)) {
            return;
        }

        Nodo hijoMayor = actual.der;
        Nodo hijoMenor = actual.izq;
        Nodo padre = null;
        while (hijoMenor != null || hijoMayor != null) {              
            padre = actual;
            hijoMenor = actual.izq;
            hijoMayor = actual.der;
            if (actual.valor.compareTo(elem) > 0) {                    
                actual = hijoMenor;
            } else {
                actual = hijoMayor;
            }
        }
        if (actual.valor.compareTo(elem) > 0) {                    
                actual.izq = nuevo;
            } else {
                actual.der = nuevo;
            }
        _Cardinal ++;
    }

    public boolean pertenece(T elem) {
        Nodo actual = _Raiz;
        while (actual != null) {
            if (actual.valor.compareTo(elem) == 0) {
                return true;
            } else {
                if (actual.valor.compareTo(elem) < 0) {
                    actual = actual.der;
                } else {
                    actual = actual.izq;
                }
            }
        }
        return false;
    }

    public void eliminar(T elem) {
        throw new UnsupportedOperationException("No implementada aun");
    }

    public String toString() {
        throw new UnsupportedOperationException("No implementada aun");
    }

    private class ABB_Iterador implements Iterador<T> {
        private Nodo _actual;

        public boolean haySiguiente() {
            throw new UnsupportedOperationException("No implementada aun");
        }

        public T siguiente() {
            throw new UnsupportedOperationException("No implementada aun");
        }
    }

    public Iterador<T> iterador() {
        return new ABB_Iterador();
    }

}
