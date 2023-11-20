package aed;


public class ListaEnlazada<T> {
    private Nodo _primero;
    private Nodo _ultimo;
    private int _longitud;    

    private class Nodo {
        T valor;
        Nodo siguiente;
        Nodo anterior;

        Nodo(T v) {valor = v; };
    }

    public ListaEnlazada() {
        _primero = null;
        _ultimo = null;
        _longitud = 0;
    }

    public int longitud() {
        return _longitud;
    }

    public void agregarAdelante(T elem) {
        Nodo nuevo = new Nodo(elem);
        Nodo segundo = _primero;
        if (_primero == null) {
            _primero = nuevo;
            _ultimo = nuevo;
            _longitud ++;
        } else {
            _primero = nuevo;
            _primero.anterior = null;
            _primero.siguiente = segundo;
            segundo.anterior = _primero;
            _longitud ++ ;
        }

    }

    public void agregarAtras(T elem) {
        Nodo nuevo = new Nodo(elem);
        if (_primero == null) {
            _primero = nuevo;
            _ultimo = nuevo;
            _longitud ++;
        } else {
            nuevo.anterior = _ultimo;
            _ultimo.siguiente = nuevo;
            _ultimo = nuevo;
            _longitud ++ ;  
        }

    }

    public T obtener(int i) {
        Nodo actual = _primero;
        T res = actual.valor;
        for (int j = 0; j < i; j++) {
            actual = actual.siguiente;
        }
        res = actual.valor;
        return res;
    }

    public void eliminar(int i) {
        Nodo primero = _primero;
        Nodo actual = primero.siguiente;
        Nodo sucesor;
        Nodo antesesor;

        if (_longitud > 1) {
            if (i == 0) {
            _primero = actual;
            _primero.anterior = null;
        } else {
            if (i == _longitud - 1) {
                _ultimo = _ultimo.anterior;
                _ultimo.siguiente = null;
            } else {
                for (int j = 1; j < i; j++) {
                    actual = actual.siguiente;
                }
                sucesor = actual.siguiente;
                antesesor = actual.anterior;
                sucesor.anterior = antesesor;
                antesesor.siguiente = sucesor;
            }  
        }
        } else {
            _primero = null;
            _ultimo = null;
        }


        _longitud --;
    }

    public void modificarPosicion(int indice, T elem) {
        Nodo actual = _primero;
        if (indice < _longitud) {
            for (int j = 0; j < indice; j++) {
                actual = actual.siguiente;
            } 
        }
        actual.valor = elem;
    }

    public ListaEnlazada<T> copiar() {
        ListaEnlazada copia = new ListaEnlazada();
        copia.agregarAtras(obtener(0));
        for (int i = 1; i < _longitud; i++) {
            copia.agregarAtras(obtener(i));
        }
        return copia;
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        ListaEnlazada copia = lista.copiar();
        _primero = copia._primero;
        _ultimo = copia._ultimo;
        _longitud = copia._longitud;
    }
    
    @Override
    public String toString() {
        String out = "[";

        for (int i = 0; i < _longitud - 1; i++) {
            out = out + obtener(i).toString() + ", ";
        }

        out = out + obtener(_longitud-1).toString() + "]";
        return out;
    }


}
