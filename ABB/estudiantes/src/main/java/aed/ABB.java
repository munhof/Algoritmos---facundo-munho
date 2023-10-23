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
        Nodo actual = _Raiz;
        T min = null;
        while(actual.izq != null){
            actual = actual.izq;
        }
        min = actual.valor; 
        return min;
        }
       


    public T maximo() {
        Nodo actual = _Raiz;
        T min = null;
        while(actual.der != null){
            actual = actual.der;
        }
        min = actual.valor; 
        return min;
        }

    public void insertar(T elem) {
        Nodo actual = _Raiz;
        Nodo nuevo = new Nodo(elem);
        if (_Raiz == null) {
            _Raiz = nuevo;
            _Cardinal = 1;
            return;
        } 
        Nodo anterior = null;
        while (actual != null) {
            anterior = actual;
            if (elem.compareTo(actual.valor) < 0) {
                actual = actual.izq;
            } else if (elem.compareTo(actual.valor) > 0) {
                actual = actual.der;
            } else {
                // Elemento ya existe en el árbol, no hacemos nada
                return;
            }
        }
        if (elem.compareTo(anterior.valor) < 0) {
            anterior.izq = nuevo;
        } else {
            anterior.der = nuevo;
        }
        nuevo.padre = anterior;
        _Cardinal++;
    }
    

    public boolean pertenece(T elem) {
        return buscarElemento(_Raiz, elem);
    }
    
    private boolean buscarElemento(Nodo nodo, T elem) {
        if (nodo == null) {
            return false; // El elemento no se encontró en el árbol
        }
    
        int comparacion = elem.compareTo(nodo.valor);
        if (comparacion < 0) {
            return buscarElemento(nodo.izq, elem);
        } else if (comparacion > 0) {
            return buscarElemento(nodo.der, elem);
        } else {
            return true; // Elemento encontrado en el árbol
        }
    }

    private Nodo eliminarRecursivo(Nodo raiz, T elem) {
        if (raiz == null) {
            return raiz; // El elemento no se encontró en el árbol
        }
    
        int comparacion = elem.compareTo(raiz.valor);
        if (comparacion < 0) {
            raiz.izq = eliminarRecursivo(raiz.izq, elem);
        } else if (comparacion > 0) {
            raiz.der = eliminarRecursivo(raiz.der, elem);
        } else {
            // Este es el nodo que queremos eliminar
            if (raiz.izq == null) {
                return raiz.der; // Caso 1: Nodo con un solo hijo o ningún hijo
            } else if (raiz.der == null) {
                return raiz.izq; // Caso 1: Nodo con un solo hijo o ningún hijo
            } else {
                // Caso 2: Nodo con dos hijos, obtenemos el sucesor inorden (nodo más pequeño en el subárbol derecho)
                raiz.valor = minimoValor(raiz.der);
                raiz.der = eliminarRecursivo(raiz.der, raiz.valor);
            }
        }
        return raiz;
    }
    
    private T minimoValor(Nodo nodo) {
        T minValor = nodo.valor;
        while (nodo.izq != null) {
            minValor = nodo.izq.valor;
            nodo = nodo.izq;
        }
        return minValor;
    }
    

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("{");
        imprimirInOrden(_Raiz, result);
        if (result.length() > 1) {
            result.deleteCharAt(result.length() - 1); // Eliminar la última coma si hay elementos
        }
        result.append("}");
        return result.toString();
    }
    
    private void imprimirInOrden(Nodo nodo, StringBuilder result) {
        if (nodo == null) {
            return;
        }
        imprimirInOrden(nodo.izq, result);
        result.append(nodo.valor);
        result.append(",");
        imprimirInOrden(nodo.der, result);
    }
    

    private class ABB_Iterador implements Iterador<T> {
        private Nodo _actual;
        private Stack<Nodo> pila; // Usaremos una pila para realizar el recorrido en inorden
    
        public ABB_Iterador() {
            _actual = _Raiz;
            pila = new Stack<>();
            while (_actual != null) {
                pila.push(_actual);
                _actual = _actual.izq;
            }
        }
    
        public boolean haySiguiente() {
            return !pila.isEmpty();
        }
    
        public T siguiente() {
            if (!haySiguiente()) {
                throw new NoSuchElementException("No hay más elementos para recorrer");
            }
    
            _actual = pila.pop();
            T valor = _actual.valor;
    
            // Avanzamos al siguiente nodo en inorden (subárbol derecho si existe)
            _actual = _actual.der;
            while (_actual != null) {
                pila.push(_actual);
                _actual = _actual.izq;
            }
    
            return valor;
        }
    }
    
    public Iterador<T> iterador() {
        return new ABB_Iterador();
    }
    

}
