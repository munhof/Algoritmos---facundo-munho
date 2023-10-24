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
        return buscar(_Raiz,elem);
    }

    private boolean buscar(Nodo raiz, T elem) {
        while (raiz != null) {
        if (raiz.valor.compareTo(elem) < 0)
                raiz = raiz.der;
            // pass left subtree as new tree
            else if (raiz.valor.compareTo(elem) > 0)
                raiz = raiz.izq;
            else
                return true; // if the key is found return 1
        }
        return false;
    }

    public void eliminar(T elem){
        eliminarRecurivo(_Raiz, elem);
        _Cardinal = _Cardinal - 1;
    }

    private Nodo eliminarRecurivo(Nodo raiz, T elem) {
        if (raiz == null){
            return raiz;
        }
            
        if (raiz.valor.compareTo(elem) > 0){
            raiz.izq = eliminarRecurivo(raiz.izq, elem);
            return raiz;
        } else if (raiz.valor.compareTo(elem) < 0){
            _Raiz.der = eliminarRecurivo(raiz.der, elem);
            return raiz;
        }

        if (raiz.izq == null){
            Nodo aux = raiz.der;
            return aux;
        } else if (raiz.der == null){
            Nodo aux = raiz.izq;
            return aux;
        }
        else{
            Nodo sucesorPadre = raiz;
            Nodo sucesor = raiz.der;
            while (sucesor.izq != null){
                sucesorPadre = sucesor;
                sucesor = sucesor.izq;
            }
            if (sucesor != raiz) {
                sucesorPadre.izq = sucesor.der;
            } else {
                sucesorPadre.der = sucesor.der;
            }

            raiz.valor = sucesor.valor;

            return raiz;
        }
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
