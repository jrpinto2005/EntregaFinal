package interfaz;

import javax.swing.*;

import constructores.ConstructorLearningPath;
import usuario.Profesor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class VentanaCrearLP extends JFrame {
    private JTextField txtTitulo;
    private JTextField txtDescripcionGeneral;
    private JSpinner spnNivelDificultad;
    private JSpinner spnDuracion;
    private JTextField txtFechaDuracion;
    private JTextField txtFechaModificacion;
    private JTextField txtObjetivo;
    private JButton btnCrear;
    private JButton btnSalir;
    
    public VentanaCrearLP(MenuProfesor menu,Profesor profesor) {
        setTitle("Crear Learning Path");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setLocationRelativeTo(null);

        
        setLayout(new GridLayout(9, 2, 10, 10));

        add(new JLabel("Título:"));
        txtTitulo = new JTextField();
        add(txtTitulo);

        add(new JLabel("Descripción General:"));
        txtDescripcionGeneral = new JTextField();
        add(txtDescripcionGeneral);

        add(new JLabel("Nivel de Dificultad:"));
        spnNivelDificultad = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        add(spnNivelDificultad);

        add(new JLabel("Duración (min):"));
        spnDuracion = new JSpinner(new SpinnerNumberModel(30, 1, 1000, 10));
        add(spnDuracion);

        add(new JLabel("Fecha Límite (dd-MM-yyyy):"));
        txtFechaDuracion = new JTextField();
        add(txtFechaDuracion);

        add(new JLabel("Fecha de Modificación (dd-MM-yyyy):"));
        txtFechaModificacion = new JTextField();
        add(txtFechaModificacion);

        add(new JLabel("Objetivo:"));
        txtObjetivo = new JTextField();
        add(txtObjetivo);

        btnCrear = new JButton("Crear Learning Path");
        add(btnCrear);

        btnSalir = new JButton("Salir");
        add(btnSalir);

        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titulo = txtTitulo.getText();
                String descripcionGeneral = txtDescripcionGeneral.getText();
                int nivelDificultad = (int) spnNivelDificultad.getValue();
                int duracion = (int) spnDuracion.getValue();
                String fechaDuracion = txtFechaDuracion.getText();
                String fechaModificacion = txtFechaModificacion.getText();
                String objetivo = txtObjetivo.getText();

                ConstructorLearningPath constructor = new ConstructorLearningPath();
                constructor.crearLP(
                    titulo,
                    descripcionGeneral,
                    nivelDificultad,
                    duracion,
                    0, // Rating inicial
                    new Date(), 
                    new Date(), 
                    1, 
                    profesor.getId(), 
                    objetivo,
                    0.0 
                );

                JOptionPane.showMessageDialog(VentanaCrearLP.this, "Learning Path creado exitosamente");
            }
        });
        btnSalir.addActionListener(e ->{
        	 this.dispose(); 
             menu.setVisible(true);
        });
    }
}