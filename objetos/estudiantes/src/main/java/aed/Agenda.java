package aed;

public class Agenda {

    private Fecha _diaActual;
    private Object[] _agenda;
    private Recordatorio[] _recordatorios;
    private int _cantrecordatorios;
    private int _capacidadrecordatorios = 2;
 

    public Agenda(Fecha fechaActual) {
        _agenda = new Object[2];
        _recordatorios = new Recordatorio[_capacidadrecordatorios];
        _diaActual = fechaActual;
        _agenda[0] = _diaActual;
        _agenda[1] = _recordatorios;
        _cantrecordatorios = 0;
    }

    public void agregarRecordatorio(Recordatorio recordatorio) {
        if (_cantrecordatorios < _capacidadrecordatorios) {
            _recordatorios[_cantrecordatorios] = recordatorio;
            _cantrecordatorios = _cantrecordatorios + 1;
        } else {
            _capacidadrecordatorios = 2 * _capacidadrecordatorios;
            Recordatorio[] _recordatoriostemp;
            _recordatoriostemp = new Recordatorio[_capacidadrecordatorios];
            for (int j = 0; j < _cantrecordatorios; j++) {
                _recordatoriostemp[j] = _recordatorios[j];
            }
            _recordatoriostemp[_cantrecordatorios] = recordatorio;
            _recordatorios = _recordatoriostemp;
            _cantrecordatorios = _cantrecordatorios + 1;
        }
        ;
        _agenda[1] = _recordatorios;
    }

    @Override
    public String toString() {
        String res = _diaActual.toString()+"\n"
                .concat("=====\n");
        for (int i = 0; i < _cantrecordatorios; i++) {
        if (_diaActual.equals(_recordatorios[i].fecha())){
            res = res.concat(_recordatorios[i].toString() + "\n");
            }
        }
        return res;
    }

   
    public void incrementarDia() {
    Fecha _fechaAux = new Fecha(_diaActual);
    _fechaAux.incrementarDia();
    _diaActual = _fechaAux;
    _agenda[0] = _fechaAux;
    }

    public Fecha fechaActual() {
        return _diaActual;
    }

}
