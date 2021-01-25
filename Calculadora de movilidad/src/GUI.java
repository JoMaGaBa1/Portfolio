import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    double[] coeficientes = {1d,0.5d,0.25d,0.4d,0.2d};
    MetodosImpl met = new Metodos(2.75d,33d,127d,coeficientes);
    public GUI() {
        JFrame marco = new JFrame("Aplicación Movilidad");
        JPanel principal = new JPanel();
        principal.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        principal.setLayout(new GridLayout(2,2,5,10));

        JPanel arrizq = new JPanel();
        arrizq.setBorder(new TitledBorder("Estancia"));
        arrizq.setLayout(new BorderLayout(5,30));
        JPanel arrder = new JPanel();
        arrder.setBorder(new TitledBorder("Colectivo"));
        arrder.setLayout(new GridLayout(5,1,0,10));
        JPanel abaizq = new JPanel();
        abaizq.setBorder(new TitledBorder("Propuesta"));
        abaizq.setLayout(new BorderLayout());
        JPanel abader = new JPanel();
        abader.setBorder(new TitledBorder("Su Billete"));
        abader.setLayout(new GridBagLayout());

        marco.setContentPane(principal);
        marco.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        marco.setSize(500,500);
        marco.setResizable(false);

        JLabel diasEti = new JLabel("Días");
        SpinnerModel model = new SpinnerNumberModel(1,1,999,1);
        JSpinner cuentaDias = new JSpinner(model);
        JComponent editor = new JSpinner.NumberEditor(cuentaDias,"###");
        cuentaDias.setEditor(editor);

        JPanel diasPan = new JPanel();
        diasPan.add(diasEti);
        diasPan.add(cuentaDias);
        diasPan.setBorder(BorderFactory.createEmptyBorder(15,0,0,0));

        JSlider cuentaViajes = new JSlider(0,100,1);
        cuentaViajes.setPaintTicks(true);
        cuentaViajes.setMajorTickSpacing(20);
        cuentaViajes.setMinorTickSpacing(5);
        cuentaViajes.setPaintLabels(true);

        JLabel viajesEti = new JLabel("Viajes: ");
        JLabel viajesEtiNum = new JLabel("1");

        JPanel viajesPan = new JPanel();
        viajesPan.add(viajesEti);
        viajesPan.add(viajesEtiNum);
        viajesPan.setBorder(BorderFactory.createEmptyBorder(0,0,15,0));

        arrizq.add(diasPan,BorderLayout.NORTH);
        arrizq.add(cuentaViajes,BorderLayout.CENTER);
        arrizq.add(viajesPan,BorderLayout.SOUTH);

        ButtonGroup grupoColectivo = new ButtonGroup();
        JRadioButton sinDesc = new JRadioButton("Sin Descuento");
        sinDesc.setActionCommand("1");
        JRadioButton jubilado = new JRadioButton("Jubilado");
        jubilado.setActionCommand("2");
        JRadioButton discapacitado = new JRadioButton("Discapacitado");
        discapacitado.setActionCommand("3");
        JRadioButton parado = new JRadioButton("Parado");
        parado.setActionCommand("4");
        JRadioButton estudiante = new JRadioButton("Estudiante");
        estudiante.setActionCommand("5");
        sinDesc.setSelected(true);

        grupoColectivo.add(sinDesc);
        grupoColectivo.add(jubilado);
        grupoColectivo.add(discapacitado);
        grupoColectivo.add(parado);
        grupoColectivo.add(estudiante);

        arrder.add(sinDesc);
        arrder.add(jubilado);
        arrder.add(discapacitado);
        arrder.add(parado);
        arrder.add(estudiante);

        JTextArea propuesta = new JTextArea(3,30);
        propuesta.setLineWrap(true);
        propuesta.setWrapStyleWord(true);
        propuesta.setBorder(BorderFactory.createLoweredBevelBorder());
        propuesta.setEditable(false);
        propuesta.setOpaque(false);
        propuesta.setFont(estudiante.getFont());

        JPanel botones = new JPanel();
        Icon aceptarIco = new ImageIcon("resources/images/aceptar.png");
        JButton aceptar = new JButton(aceptarIco);
        Icon rechazarIco = new ImageIcon("resources/images/rechazar.png");
        JButton rechazar = new JButton(rechazarIco);

        botones.add(aceptar);
        botones.add(rechazar);

        abaizq.add(propuesta,BorderLayout.NORTH);
        abaizq.add(botones,BorderLayout.SOUTH);

        JLabel billete = new JLabel();

        abader.add(billete);

        principal.add(arrizq);
        principal.add(arrder);
        principal.add(abaizq);
        principal.add(abader);
        marco.setVisible(true);

        cuentaViajes.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                viajesEtiNum.setText(String.valueOf(cuentaViajes.getValue()));
            }
        });
        aceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int i;
                int j;
                int grupoEnt;
                int diasViajeEnt;
                int numViajesEnt;
                String mejorOpcion;
                String mejorOpcionImprime;
                Icon[][] billetes = new ImageIcon[3][5];
                for(i=0;i<3;i++) {
                    for(j=0;j<5;j++) {
                        billetes[i][j] = new ImageIcon("resources/images/" + i + j + ".png");
                    }
                }
                grupoEnt = Integer.parseInt(grupoColectivo.getSelection().getActionCommand());
                diasViajeEnt = Integer.parseInt(String.valueOf(cuentaDias.getValue()));
                numViajesEnt = cuentaViajes.getValue();
                if(numViajesEnt == 0) {
                    propuesta.setText("El número de viajes debe ser mayor que 0.");
                    billete.setIcon(null);
                } else {
                    mejorOpcion = met.grupoPasajero(grupoEnt) + met.mejorOpcion(met.calculaPreciosViaje(met.precioSuelto(diasViajeEnt,numViajesEnt),met.precioIlimitado7d(diasViajeEnt,numViajesEnt),met.precioIlimitado30d(diasViajeEnt,numViajesEnt)),grupoEnt-1);
                    mejorOpcionImprime = mejorOpcion.substring(0,mejorOpcion.length()-2);
                    propuesta.setText(mejorOpcionImprime);
                    billete.setIcon(billetes[Character.getNumericValue(mejorOpcion.charAt(mejorOpcion.length()-2))][Character.getNumericValue(mejorOpcion.charAt(mejorOpcion.length()-1))]);
                }
            }
        });
        rechazar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cuentaDias.setValue(1);
                cuentaViajes.setValue(1);
                sinDesc.setSelected(true);
                propuesta.setText("");
                billete.setIcon(null);
            }
        });
    }
}
