public class Metodos implements MetodosImpl {
    final double simple;
    final double sieteDias;
    final double treintaDias;
    final double[] coeficientes;
    public Metodos(double fsimple,double fsieteDias,double ftreintaDias,double[] fcoeficientes) {
        simple = fsimple;
        sieteDias = fsieteDias;
        treintaDias =  ftreintaDias;
        coeficientes = fcoeficientes;
    }
    public double[] precioSuelto(int numDias,int numViajes) {
        double[] precios = new double[5];
        for(int i = 0;i<5;i++) {
            precios[i] = simple*coeficientes[i];
        }
        return precios;
    }
    public double[] precioIlimitado7d(int numDias,int numViajes) {
        double[] precios = new double[5];
        double precXViaje = (sieteDias*Math.ceil(numDias/7d))/numViajes;
        for(int i = 0;i<5;i++) {
            precios[i] = precXViaje*coeficientes[i];
        }
        return precios;
    }
    public double[] precioIlimitado30d(int numDias,int numViajes) {
        double[] precios = new double[5];
        double precXViaje = (treintaDias*Math.ceil(numDias/30d))/numViajes;
        for(int i = 0;i<5;i++) {
            precios[i] = precXViaje*coeficientes[i];
        }
        return precios;
    }
    public double[][] calculaPreciosViaje(double[] preciosSuelto,double[] preciosSiete,double[] preciosTreinta) {
        int i = 0;
        int j;
        double[][] precios = new double[3][5];
        while(i<2) {
            for(j=0;j<5;j++) {
                precios[i][j] = preciosSuelto[j];
            }
            i++;
            for(j=0;j<5;j++) {
                precios[i][j] = preciosSiete[j];
            }
            i++;
            for(j=0;j<5;j++) {
                precios[i][j] = preciosTreinta[j];
            }
        }
        return precios;
    }
    public String mejorOpcion(double[][] tarifa,int fgrupo) {
        double[] preciosInteresa = new double[3];
        for(int i=0;i<3;i++) {
            preciosInteresa[i] = tarifa[i][fgrupo];
        }
        if(preciosInteresa[0]<=preciosInteresa[1] && preciosInteresa[0]<=preciosInteresa[2]) {
            return "Billete suelto (" + String.format("%4.2f",preciosInteresa[0]) + "€/viaje)" + "0" + fgrupo;
        } else if (preciosInteresa[1]<preciosInteresa[0] && preciosInteresa[1]<=preciosInteresa[2]) {
            return "Bono para 7 días (" + String.format("%4.2f",preciosInteresa[1]) + "€/viaje)" + "1" + fgrupo;
        } else if (preciosInteresa[2]<preciosInteresa[0] && preciosInteresa[2]<preciosInteresa[1]) {
            return "Bono para 30 días (" + String.format("%4.2f",preciosInteresa[2]) + "€/viaje)" + "2" + fgrupo;
        } else {
            return null;
        }
    }
    public String grupoPasajero(int fgrupo) {
        String cadena = " Debería coger la opción de ";
        switch(fgrupo) {
            case 1:
                return "(Sin descuento)" + cadena;
            case 2:
                return "(Jubilado)" + cadena;
            case 3:
                return "(Discapacitado)" + cadena;
            case 4:
                return "(Parado)" + cadena;
            case 5:
                return "(Estudiante)" + cadena;
        }
        return null;
    }
}
