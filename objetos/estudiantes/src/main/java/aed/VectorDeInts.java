package aed;

class VectorDeInts implements SecuenciaDeInts {
    private static final int CAPACIDAD_INICIAL = 1;

    private Integer[] _vectordeints;
    private Integer _capacidad;
    private Integer _longitud;

    public VectorDeInts() {
        _vectordeints = new Integer[CAPACIDAD_INICIAL];
        _longitud = 0;
        _capacidad = CAPACIDAD_INICIAL;
    }

    public VectorDeInts(VectorDeInts vector) {
        VectorDeInts vector2 = vector.copiar();
        _vectordeints = vector2._vectordeints;
        _longitud = vector2._longitud;
        _capacidad = vector2._capacidad;
    }

    public int longitud() {
        return _longitud;
    }

    public void agregarAtras(int i) {
        if (_longitud < _capacidad) {
            _vectordeints[_longitud] = i;
            _longitud = _longitud + 1;
        } else {
            _capacidad = 2 * _capacidad;
            Integer[] _vectordeintstemp;
            _vectordeintstemp = new Integer[_capacidad];
            for (int j = 0; j < _longitud; j++) {
                _vectordeintstemp[j] = _vectordeints[j];
            }
            _vectordeintstemp[_longitud] = i;
            _vectordeints = _vectordeintstemp;
            _longitud = _longitud + 1;
        }
        ;
    }

    public int obtener(int i) {
        Integer res; 
        if (i < _longitud) {
            res = _vectordeints[i];
        } else {
            throw new UnsupportedOperationException("No existe elemento el elemento en la secuencia");
        };
        return res;
    }

    public void quitarAtras() {
        if (_longitud > 0) {
            Integer[] _vectordeintstemp;
            _vectordeintstemp = new Integer[_capacidad];
            for (int j = 0; j < _longitud-1; j++) {
                _vectordeintstemp[j] = _vectordeints[j];
            }
            _vectordeints = _vectordeintstemp;
            _longitud = _longitud - 1;
        } else {
            throw new UnsupportedOperationException("No hay elementos en la secuencia");
        };
    }

    public void modificarPosicion(int indice, int valor) {
        if (indice < _capacidad) {
            _vectordeints[indice] = valor;
        } else {
            Integer _capacidad2;
            _capacidad2 = 2 * _capacidad;
            Integer[] _vectordeintstemp;
            _vectordeintstemp = new Integer[_capacidad2];
            for (int j = 0; j < _capacidad; j++) {
                _vectordeintstemp[j] = _vectordeints[j];
            }
            _vectordeintstemp[indice] = valor;
            _vectordeints = _vectordeintstemp;
            _longitud = indice + 1;
        };
    }

    public VectorDeInts copiar() {
        VectorDeInts _vectordeintscopia = new VectorDeInts();
        //_vectordeintscopia._capacidad = _capacidad;
            for (int i = 0; i < _longitud; i++) {
            _vectordeintscopia.agregarAtras(obtener(i));
            } 
        return _vectordeintscopia;
    }
}









