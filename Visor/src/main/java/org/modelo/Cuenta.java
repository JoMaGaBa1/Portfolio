package org.modelo;

public interface Cuenta {
    public int getNumero();
    public String getTitular();
    public String getNacionalidad();
    public String getFecha(int modo);
    public float getSaldo();
    public String toString();
}
