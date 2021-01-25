package org.modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class CuentaImpl implements Cuenta {
    private int numero;
    private String titular;
    private String nacionalidad;
    private LocalDate fecha;
    private float saldo;

    public CuentaImpl(int numero, String titular, String nacionalidad, String fecha, float saldo) {
        this.numero = numero;
        this.titular = titular;
        this.nacionalidad = nacionalidad;
        this.fecha = LocalDate.from(DateTimeFormatter.ofPattern("dd/MM/yyyy").parse(fecha));
        this.saldo  = saldo;
    }

    @Override
    public int getNumero() {
        return numero;
    }

    @Override
    public String getTitular() {
        return titular;
    }

    @Override
    public String getNacionalidad() {
        return nacionalidad;
    }

    @Override
    public String getFecha(int modo) {
        if(modo == 0) {
            return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(fecha);
        } else if(modo == 1) {
            return DateTimeFormatter.ofPattern("dd/MM/yyyy").format(fecha);
        }
        return null;
    }

    @Override
    public float getSaldo() {
        return saldo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CuentaImpl cuenta = (CuentaImpl) o;
        return getNumero() == cuenta.getNumero() &&
                Float.compare(cuenta.getSaldo(), getSaldo()) == 0 &&
                getTitular().equals(cuenta.getTitular()) &&
                getNacionalidad().equals(cuenta.getNacionalidad()) &&
                getFecha(0).equals(cuenta.getFecha(0));
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumero(), getTitular(), getNacionalidad(), getFecha(0), getSaldo());
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "numero='" + getNumero() + '\'' +
                ", titular='" + getTitular() + '\'' +
                ", nacionalidad='" + getNacionalidad() + '\'' +
                ", fecha=" + getFecha(0) +
                ", saldo=" + getSaldo() +
                '}';
    }
}
