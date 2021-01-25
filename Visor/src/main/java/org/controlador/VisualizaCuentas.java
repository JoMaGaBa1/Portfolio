package org.controlador;

import javafx.scene.control.TextField;
import org.modelo.Cuenta;

public class VisualizaCuentas {
    public void rellenaDatos(TextField ftxtNum,TextField ftxtTitular,TextField ftxtNacionalidad,TextField ftxtFecha,TextField ftxtSaldo,Cuenta fcuenta) {
        try {
            ftxtNum.setText(String.valueOf(fcuenta.getNumero()));
            ftxtTitular.setText(fcuenta.getTitular());
            ftxtNacionalidad.setText(fcuenta.getNacionalidad());
            ftxtFecha.setText(fcuenta.getFecha(1));
            ftxtSaldo.setText(String.valueOf(fcuenta.getSaldo()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
