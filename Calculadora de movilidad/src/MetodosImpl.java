public interface MetodosImpl {
    double[] precioSuelto(int numDias, int numViajes);
    double[] precioIlimitado7d(int numDias, int numViajes);
    double[] precioIlimitado30d(int numDias, int numViajes);
    double[][] calculaPreciosViaje(double[] preciosSuelto, double[] preciosSiete, double[] preciosTreinta);
    String mejorOpcion(double[][] tarifa, int fgrupo);
    String grupoPasajero(int fgrupo);
}