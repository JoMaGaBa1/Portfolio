package vista;

import controlador.DepartamentoDAO;
import modelo.Departamento;

import javax.swing.*;
import java.util.List;

public class TablaModelo {
    DepartamentoDAO metodos;

    public TablaModelo(DepartamentoDAO metodos) {
        this.metodos = metodos;
    }

    public JTable construirTabla() {
        String titulos[] = {"ID","Nombre","Núm. de empleados","Jefe","Servicios"};
        String informacion[][] = obtenerDatos();
        return new JTable(informacion,titulos);
    }

    private String[][] obtenerDatos() {
        List<Departamento> miLista = metodos.listarDepartamentos();
        String Datos[][]=new String[miLista.size()][5];
        for (int i = 0; i < miLista.size(); i++) {
            Datos[i][0]=miLista.get(i).getId()+"";
            Datos[i][1]=miLista.get(i).getNombre()+"";
            Datos[i][2]=miLista.get(i).getNumEmpleados()+"";
            Datos[i][3]=miLista.get(i).getJefe()+"";
            Datos[i][4]=miLista.get(i).getServicios()+"";
        }
        return Datos;
    }
}
