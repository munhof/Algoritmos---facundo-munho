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

    /** 
    public void eliminar(T elem){
        if (pertenece(elem)) {
            eliminarRecurivo(_Raiz, elem);
            _Cardinal = _Cardinal - 1;
        }

    }

    private Nodo eliminarRecurivo(Nodo raiz, T elem) {

        // si la raiz es nulo devuelve nulo
        if (raiz == null){
            return raiz;
        }
            
        // reviso si el elemento es mayor o menor a la raiz actual
        // hago recursion en el caso correspondiente
        if (raiz.valor.compareTo(elem) > 0){
            raiz.izq = eliminarRecurivo(raiz.izq, elem);
            return raiz;
        } else if (raiz.valor.compareTo(elem) < 0){
            raiz.der = eliminarRecurivo(raiz.der, elem);
            return raiz;
        }

        // si es nulo termino
        if (raiz.izq == null){
            Nodo aux = raiz.der;
            return aux;
        } else if (raiz.der == null){
            Nodo aux = raiz.izq;
            return aux;
        }
        else{
            Nodo suceseorPadr = raiz;
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
*/
/**
    public void eliminar(T elem) {
        if (pertenece(elem)) {
            eliminarRecurivo(_Raiz, elem);
            _Cardinal = _Cardinal - 1;
        }
    }

    private Nodo eliminarRecurivo(Nodo raiz, T elem) {
        if (raiz == null) {
            return raiz;
        }

        // Realizar la eliminación recursiva en el subárbol izquierdo o derecho según sea necesario
        if (raiz.valor.compareTo(elem) > 0) {
            raiz.izq = eliminarRecurivo(raiz.izq, elem);
        } else if (raiz.valor.compareTo(elem) < 0) {
            raiz.der = eliminarRecurivo(raiz.der, elem);
        } else if (raiz.valor.compareTo(elem) == 0) {
            // Si se encontró el elemento a eliminar
            if (raiz.izq == null) {
                return raiz.der;
            } else if (raiz.der == null) {
                return raiz.izq;
            }else{
                // Caso de dos hijos: encontrar el sucesor en orden y reemplazar el valor
            Nodo sucesor = encontrarSucesor(raiz.der);
            raiz.valor = sucesor.valor;
            raiz.der = eliminarRecurivo(raiz.der, sucesor.valor);
            }

            
        }

        return raiz;
    }

    private Nodo encontrarSucesor(Nodo raiz) {
        Nodo actual = raiz;
        while (actual.izq != null) {
            actual = actual.izq;
        }
        return actual;
    }
    
    */

    public void eliminar(T elem){
        Nodo actual = _Raiz;
        Nodo _padre = null;

        while (actual!= null) {
            int comparacion = elem.compareTo(actual.valor);

            if (comparacion == 0) {
                eliminarNodo (actual,_padre);
                return;                
            }else{
                _padre = actual;

                if (comparacion < 0 ) {
                    actual=actual.izq;
                    
                }else{
                    actual=actual.der;
                }
            }
            
        }

    }

    private void eliminarNodo(Nodo nodoAEliminar, Nodo padre) {

        if (nodoAEliminar.izq == null && nodoAEliminar.der == null) {
            // Caso 1: Nodo hoja, simplemente lo eliminamos
            if (padre == null) {
                _Raiz = null;
                _Cardinal--;
            } else if (padre.izq == nodoAEliminar) {
                padre.izq = null;
                _Cardinal--;
            } else {
                padre.der = null;
                _Cardinal--;
            }

            } else if (nodoAEliminar.izq != null && nodoAEliminar.der != null) {
                // Caso 3: Nodo con dos hijos
                Nodo sucesor = encontrarSucesorInOrder(nodoAEliminar);

                T valorSucesor = sucesor.valor;
                eliminar(valorSucesor);
                nodoAEliminar.valor = valorSucesor;
                        

            } else {
                // Caso 2: Nodo con un hijo
                Nodo hijo = (nodoAEliminar.izq != null) ? nodoAEliminar.izq : nodoAEliminar.der;
                if (padre == null) {
                    _Raiz = hijo;
                    _Cardinal--;
                } else if (padre.izq == nodoAEliminar) {
                    padre.izq = hijo;
                    _Cardinal--;
                } else {
                    padre.der = hijo;
                    _Cardinal--;
                }
                
            }
    }

    private Nodo encontrarSucesorInOrder(Nodo nodo) {
        Nodo sucesor = nodo.der;
        while (sucesor.izq != null) {
            sucesor = sucesor.izq;
        }
        return sucesor;
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
        private Stack<Nodo> pila; // Usaremos una pila para realizar el recorrido en inorden
    
        public ABB_Iterador() {
            Nodo _actual = _Raiz;
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
                return null;
            }
    
            Nodo nodo = pila.pop();
            Nodo _actual = nodo;
    
            // Avanzamos al siguiente nodo en inorden (subárbol derecho si existe)
            _actual = _actual.der;
            while (_actual != null) {
                pila.push(_actual);
                _actual = _actual.izq;
            }
    
            return nodo.valor;
        }
    }
    
    public Iterador<T> iterador() {
        return new ABB_Iterador();
    }
    

}
