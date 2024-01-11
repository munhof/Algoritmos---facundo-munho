package aed;

public class Recordatorio {

    private Horario _horario;
    private Fecha _fecha;
    private String _mensaje;
    private Object[] _recordatorio;

    public Recordatorio(String mensaje, Fecha fecha, Horario horario) {
        _horario = new Horario(horario);
        _fecha = new Fecha(fecha);
        _mensaje = new String(mensaje);
        _recordatorio = new Object[3];
        _recordatorio[0] = _mensaje;
        _recordatorio[1] = _fecha;
        _recordatorio[2] = _horario;
    }

    public Horario horario() {
        return _horario;
    }

    public Fecha fecha() {
        return _fecha;
    }

    public String mensaje() {
       return _mensaje;
    }

    @Override
    public String toString() {
        return _mensaje + " @ " + _fecha.toString() + " " + _horario.toString() ;
    }

}
