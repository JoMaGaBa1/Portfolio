package org.controlador;

import javafx.scene.control.TextField;
import org.modelo.Cuenta;
import org.modelo.CuentaImpl;

public class ModificaCuentas {
    public Cuenta anadeCuenta(TextField ftxtNum,TextField ftxtTitular,TextField ftxtNacionalidad,TextField ftxtFecha,TextField ftxtSaldo) {
        Cuenta nueva = new CuentaImpl(Integer.parseInt(ftxtNum.getText()),ftxtTitular.getText(),ftxtNacionalidad.getText(),ftxtFecha.getText(),Float.parseFloat(ftxtSaldo.getText()));
        return nueva;
    }
}
