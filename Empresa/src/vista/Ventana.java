package vista;

import controlador.DepartamentoDAO;
import controlador.MariaDBDepartamentoDAO;
import modelo.Departamento;
import modelo.DepartamentoImpl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ventana extends JFrame implements ActionListener {
    DepartamentoDAO metodos;
    TablaModelo tablaModelo;
    JPanel principal;
    JLabel titulo;
    JPanel central;
    JPanel controles;
    JPanel panId;
    JPanel panNombre;
    JPanel panNumEmpleados;
    JPanel panJefe;
    JPanel panServicios;
    JPanel panBotones;
    JLabel lblId;
    JLabel lblNombre;
    JLabel lblNumEmpleados;
    JLabel lblJefe;
    JLabel lblServicios;
    JTextField txtId;
    JTextField txtNombre;
    JSpinner spiNumEmpleados;
    JTextField txtJefe;
    JTextField txtServicios;
    JScrollPane panTabla;
    JButton btnInsertar;
    JButton btnModificar;
    JButton btnConsultar;
    JButton btnEliminar;
    JTable tabla;

    public Ventana() {
        metodos = new MariaDBDepartamentoDAO();
        tablaModelo = new TablaModelo(metodos);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Jose Manuel García Barrionuevo -  2º CFGS DAM");
        setSize(970,440);
        principal = new JPanel(new FlowLayout(FlowLayout.CENTER));
        principal.setBorder(new EmptyBorder(20,20,20,0));
        setContentPane(principal);
        titulo = new JLabel("CRUD DE DEPARTAMENTOS");
        titulo.setFont(new Font("Verdana",1,20));
        central = new JPanel(new GridBagLayout());
        central.setBorder(new EmptyBorder(20,0,0,0));
        controles = new JPanel(new GridLayout(6,2));
        controles.setBorder(new EmptyBorder(0,0,0,40));
        controles.setPreferredSize(new Dimension(400,300));

        panId = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        lblId = new JLabel("ID: ");
        panId.add(lblId);
        txtId = new JTextField(25);
        panId.add(txtId);

        panNombre = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        lblNombre = new JLabel("Nombre: ");
        panNombre.add(lblNombre);
        txtNombre = new JTextField(25);
        panNombre.add(txtNombre);

        panNumEmpleados = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lblNumEmpleados = new JLabel("Empleados: ");
        panNumEmpleados.add(lblNumEmpleados);
        spiNumEmpleados = new JSpinner(new SpinnerNumberModel(1,1,100,1));
        panNumEmpleados.add(spiNumEmpleados);

        panJefe = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        lblJefe = new JLabel("Jefe: ");
        panJefe.add(lblJefe);
        txtJefe = new JTextField(25);
        panJefe.add(txtJefe);

        panServicios = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        lblServicios = new JLabel("Servicios: ");
        panServicios.add(lblServicios);
        txtServicios = new JTextField(25);
        panServicios.add(txtServicios);

        btnInsertar = new JButton("Insertar");
        btnInsertar.addActionListener(this);
        btnModificar = new JButton("Modificar");
        btnModificar.addActionListener(this);
        btnConsultar = new JButton("Consultar");
        btnConsultar.addActionListener(this);
        btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(this);

        panBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panBotones.add(btnInsertar);
        panBotones.add(btnModificar);
        panBotones.add(btnConsultar);
        panBotones.add(btnEliminar);

        controles.add(panId);
        controles.add(panNombre);
        controles.add(panNumEmpleados);
        controles.add(panJefe);
        controles.add(panServicios);
        controles.add(panBotones);
        panTabla = new JScrollPane();
        panTabla.setPreferredSize(new Dimension(500,300));
        tabla = tablaModelo.construirTabla();
        panTabla.setViewportView(tabla);
        central.add(controles);
        central.add(panTabla);
        add(titulo);
        add(central);
        setVisible(true);
    }

    public int compruebaEntero(JTextField campo) {
        int id = 0;
        try {
            id = Integer.parseInt(campo.getText());
            if (id < 1) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            id = -1;
            JOptionPane.showMessageDialog(this, "Debes introducir un ID numérico válido.", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            return id;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int id;
        if (e.getSource() == btnInsertar) {
            if ((id = compruebaEntero(txtId)) > 0) {
                metodos.insertarDepartamento(new DepartamentoImpl(id,
                        txtNombre.getText(),
                        Integer.parseInt(String.valueOf(spiNumEmpleados.getValue())),
                        txtJefe.getText(),
                        txtServicios.getText()));
            }
        } else if (e.getSource() == btnModificar) {
            String[] botones = {"Sí","No"};
            int opcion = JOptionPane.showOptionDialog(this,"¿Estás seguro?","Confirmación",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,botones,null);
            if (opcion == 0) {
                if ((id = compruebaEntero(txtId)) > 0) {
                    int resultado = metodos.modificarDepartamento(new DepartamentoImpl(id,
                            txtNombre.getText(),
                            Integer.parseInt(String.valueOf(spiNumEmpleados.getValue())),
                            txtJefe.getText(),
                            txtServicios.getText()));
                    if (resultado > 0) {
                        JOptionPane.showMessageDialog(this, "Departamento modificado.", "Completado", JOptionPane.PLAIN_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "No se ha podido modificar ningún registro.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } else if (e.getSource() == btnConsultar || e.getSource() == btnEliminar) {
            if ((id = compruebaEntero(txtId)) > 0) {
                Departamento departamento = metodos.consultarDepartamento(id);
                if (departamento != null) {
                    txtId.setText(String.valueOf(departamento.getId()));
                    txtNombre.setText(departamento.getNombre());
                    spiNumEmpleados.setValue(departamento.getNumEmpleados());
                    txtJefe.setText(departamento.getJefe());
                    txtServicios.setText(departamento.getServicios());
                    if (e.getSource() == btnEliminar) {
                        String[] botones = {"Sí", "No"};
                        int opcion = JOptionPane.showOptionDialog(this, "¿Estás seguro?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, botones, null);
                        if (opcion == 0) {
                            int resultado = metodos.eliminarDepartamento(id);
                            if (resultado > 0) {
                                JOptionPane.showMessageDialog(this, "Departamento eliminado.", "Completado", JOptionPane.PLAIN_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(this, "No se ha podido eliminar ningún registro.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "No se ha encontrado ningún registro con ese ID.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        tabla = tablaModelo.construirTabla();
        panTabla.setViewportView(tabla);
    }
}
