package org.controlador;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import org.modelo.Cuenta;
import org.modelo.CuentaDAO;
import org.modelo.MariaDBCuentaDAO;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

public class VisorController implements Initializable {
    private int indice = 0;
    private boolean numValido = true;
    private boolean titularValido = false;
    private boolean nacionalidadValida = false;
    private boolean fechaValida = false;
    private boolean saldoValido = false;
    public Label lblTitulo;
    public TextField txtNum;
    public TextField txtTitular;
    public TextField txtNacionalidad;
    public TextField txtFecha;
    public TextField txtSaldo;
    public Button btnNext;
    public Button btnPrev;
    public Button btnCancelar;
    public Button btnAceptar;
    public Button btnNueva;
    public Button btnPdf;
    public Button btnHtml;

    CuentaDAO metodos = new MariaDBCuentaDAO();
    VisualizaCuentas visualizador;
    ModificaCuentas modificador;
    List<Cuenta> lista = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colocaInfo();
    }

    public VisorController() {
        visualizador = new VisualizaCuentas();
        modificador = new ModificaCuentas();
        recargaDatos();
    }

    public void recargaDatos() {
        lista = metodos.listarCuentas();
    }

    public void revisaBotones() {
        if(indice == lista.size() - 1) {
            btnPrev.setVisible(true);
            btnNext.setVisible(false);
            btnNueva.setVisible(true);
        } else if(indice == 0) {
            btnPrev.setVisible(false);
            btnNext.setVisible(true);
            btnNueva.setVisible(false);
        } else {
            btnPrev.setVisible(true);
            btnNext.setVisible(true);
            btnNueva.setVisible(false);
        }
    }

    public void revisaAceptable() {
        if(numValido && titularValido && nacionalidadValida && fechaValida && saldoValido) {
            btnAceptar.setDisable(false);
        } else {
            btnAceptar.setDisable(true);
        }
    }

    public void colocaInfo() {
        recargaDatos();
        visualizador.rellenaDatos(txtNum,txtTitular,txtNacionalidad,txtFecha,txtSaldo,lista.get(indice));
        revisaBotones();
    }

    public void pasaSiguiente(ActionEvent actionEvent) {
        indice++;
        colocaInfo();
    }

    public void pasaAnterior(ActionEvent actionEvent) {
        indice--;
        colocaInfo();
    }

    public void cancelaNueva(ActionEvent actionEvent) {
        lblTitulo.setText("VISOR DE LAS CUENTAS EXISTENTES");
        colocaInfo();
        btnCancelar.setVisible(false);
        btnAceptar.setVisible(false);
        txtNum.setEditable(false);
        txtTitular.setEditable(false);
        txtNacionalidad.setEditable(false);
        txtFecha.setEditable(false);
        txtSaldo.setEditable(false);
        txtNum.setStyle(null);
        txtTitular.setStyle(null);
        txtNacionalidad.setStyle(null);
        txtFecha.setStyle(null);
        txtSaldo.setStyle(null);
        numValido = true;
        titularValido = false;
        nacionalidadValida = false;
        fechaValida = false;
        saldoValido = false;
        revisaAceptable();
    }

    public void aceptaNueva(ActionEvent actionEvent) {
        lblTitulo.setText("VISOR DE LAS CUENTAS EXISTENTES");
        Cuenta cuenta = modificador.anadeCuenta(txtNum,txtTitular,txtNacionalidad,txtFecha,txtSaldo);
        if(metodos.insertarCuenta(cuenta) > 0) {
            indice++;
            colocaInfo();
            btnCancelar.setVisible(false);
            btnAceptar.setVisible(false);
            numValido = true;
            titularValido = false;
            nacionalidadValida = false;
            fechaValida = false;
            saldoValido = false;
        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText("Error con la base de datos");
            alerta.setContentText("Algo ha fallado, revisa los datos que quieres introducir y vuelve a intentarlo.");
            alerta.show();
        }
        revisaAceptable();
    }

    public void abreNueva(ActionEvent actionEvent) {
        lblTitulo.setText("VISOR DE LAS CUENTAS NUEVAS");
        btnPrev.setVisible(false);
        btnNueva.setVisible(false);
        btnCancelar.setVisible(true);
        btnAceptar.setVisible(true);
        txtNum.setEditable(true);
        txtTitular.setEditable(true);
        txtNacionalidad.setEditable(true);
        txtFecha.setEditable(true);
        txtSaldo.setEditable(true);
        txtNum.setText(String.valueOf(Integer.parseInt(txtNum.getText()) + 1));
        txtTitular.setText("");
        txtNacionalidad.setText("");
        txtFecha.setText("");
        txtSaldo.setText("");
    }

    public void revisaNum(KeyEvent keyEvent) {
        String numero = txtNum.getText();
        try {
            Integer.parseInt(numero);
            txtNum.setStyle(null);
            numValido = true;
        } catch (NumberFormatException e) {
            txtNum.setStyle("-fx-background-color: #FF5959");
            numValido = false;
        }
        revisaAceptable();
    }

    public void revisaTitular(KeyEvent keyEvent) {
        String titular = txtTitular.getText();
        if(titular.length() != 0) {
            titularValido = true;
            txtTitular.setStyle(null);
        } else {
            titularValido = false;
            txtTitular.setStyle("-fx-background-color: #FF5959");
        }
        revisaAceptable();
    }

    public void revisaNacionalidad(KeyEvent keyEvent) {
        String nacionalidad = txtNacionalidad.getText();
        if(nacionalidad.length() != 0) {
            nacionalidadValida = true;
            txtNacionalidad.setStyle(null);
        } else {
            nacionalidadValida = false;
            txtNacionalidad.setStyle("-fx-background-color: #FF5959");
        }
        revisaAceptable();
    }

    public void revisaFecha(KeyEvent keyEvent) {
        String strFecha = txtFecha.getText();
        try {
            LocalDate elementoFecha = LocalDate.from(DateTimeFormatter.ofPattern("dd/MM/yyyy").parse(strFecha));
            LocalDate elementoActual = LocalDate.now();
            if (elementoFecha.isBefore(elementoActual) || elementoFecha.isEqual(elementoActual)) {
                txtFecha.setStyle(null);
                fechaValida = true;
            } else {
                txtFecha.setStyle("-fx-background-color: #FF5959");
                fechaValida = false;
            }
        } catch (DateTimeException e) {
            txtFecha.setStyle("-fx-background-color: #FF5959");
            fechaValida = false;
        }
        revisaAceptable();
    }

    public void revisaSaldo(KeyEvent keyEvent) {
        String saldo = txtSaldo.getText();
        try {
            Float.parseFloat(saldo);
            txtSaldo.setStyle(null);
            saldoValido = true;
        } catch (NumberFormatException e) {
            txtSaldo.setStyle("-fx-background-color: #FF5959");
            saldoValido = false;
        }
        revisaAceptable();
    }

    public JasperPrint generaReporte(String jasper) throws JRException {
        InputStream archivo = getClass().getResourceAsStream(jasper);
        Map<String,Object> mapa = new HashMap<>();
        mapa.put("logo","logo.png");
        JasperPrint print = JasperFillManager.fillReport(archivo,mapa,metodos.getConexion());
        return print;
    }

    public void generaPdf(ActionEvent actionEvent) {
        try {
            JasperPrint print = generaReporte("Apartado4B_JMGB.jasper");
            JasperExportManager.exportReportToPdfFile(print,"reporteVisor.pdf");
            Desktop desktop = Desktop.getDesktop();
            desktop.open(new File("reporteVisor.pdf"));
        } catch (JRException | IOException e) {
            e.printStackTrace();
        }
    }

    public void generaHtml(ActionEvent actionEvent) {
        try {
            JasperPrint print = generaReporte("Apartado4E_JMGB.jasper");
            JasperExportManager.exportReportToHtmlFile(print,"reporteVisor.html");
            Desktop desktop = Desktop.getDesktop();
            desktop.open(new File("reporteVisor.html"));
        } catch (JRException | IOException e) {
            e.printStackTrace();
        }
    }
}
