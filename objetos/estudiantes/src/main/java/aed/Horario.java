package aed;

public class Horario {

    private int _hora;
    private int _minuto;
    private int[] _horario;

    public Horario(int hora, int minutos) {
        _horario = new int[2];
        _hora = hora;
        _minuto = minutos;
        _horario[0] = _hora;
        _horario[1] = _minuto;
    }

    public Horario(Horario horario) {
        _horario = new int[2];
        _hora = horario.hora();
        _minuto = horario.minutos();
        _horario[0] = _hora;
        _horario[1] = _minuto;
    }

    public int hora() {
        return _hora;
    }

    public int minutos() {
        return _minuto;
    }

    @Override
    public String toString() {
        String _horasString = "0";
        String _minutosString = "0";
        _horasString = Integer.toString(_hora);
        _minutosString = Integer.toString(_minuto);
        String _horarioString = _horasString + ":" + _minutosString;
        return _horarioString;
    }

    @Override
    public boolean equals(Object otro) {
        Horario f = new Horario(_hora, _minuto);
        boolean res = false;
        if (Horario.class.isInstance(otro)) {
            Horario miotra = (Horario) otro;
            if ((f.hora() == miotra.hora()) & (f.minutos() == miotra.minutos())) {
                res = true;
            }
        }
        return res;
    }

}
