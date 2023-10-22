package aed;

public class Fecha {

    private Integer _mes;
    private Integer _dia;
    private Integer[] _fecha;

    public Fecha(int dia, int mes) {
        _fecha = new Integer[2];
        _dia = dia;
        _mes = mes;
        _fecha[0] = _dia;
        _fecha[1] = _mes;
    }

    public Fecha(Fecha fecha) {
        _fecha = new Integer[2];
        _dia = fecha.dia();
        _mes = fecha.mes();
        _fecha[0] = _dia;
        _fecha[1] = _mes;
    }

    public Integer dia() {
        return _dia;
    }

    public Integer mes() {
        return _mes;
    }

    @Override
    public String toString() {
        String _stringFecha = Integer.toString(_dia) + "/" + Integer.toString(_mes);
        return _stringFecha;
    }

    @Override
    public boolean equals(Object otra) {
        Fecha f = new Fecha(_dia, _mes);
        boolean res = false;
        if (Fecha.class.isInstance(otra)) {
            Fecha miotra = (Fecha) otra;
            if ((f.dia() == miotra.dia()) & (f.mes() == miotra.mes())) {
                res = true;
            }
        }
        return res;
    }

    private Integer diasEnMes(Integer mes) {
        Integer dias[] = {
                // ene, feb, mar, abr, may, jun
                31, 28, 31, 30, 31, 30,
                // jul, ago, sep, oct, nov, dic
                31, 31, 30, 31, 30, 31
        };
        return dias[mes - 1];
    }

    public void incrementarDia() {
        Fecha f = new Fecha(_dia, _mes);
        Integer diaAux = f._dia;
        Integer mesAux = f._mes;
        final int limiteDias = diasEnMes(_mes);
        if (0 < diaAux && diaAux < limiteDias) {
            diaAux = diaAux + 1;
        } else {
            diaAux = 1;
        if (0 < mesAux && mesAux < 12) {
                mesAux = mesAux + 1;
        } else {
                mesAux = 1;
            }
        }
            ;
        _dia = diaAux;
        _mes = mesAux;
        _fecha[0] = _dia;
        _fecha[1] = _mes
        ;
    }

}
