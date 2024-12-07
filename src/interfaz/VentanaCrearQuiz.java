package interfaz;

import javax.swing.*;

import envios.Opcion;
import envios.PreguntaOpcionMultiple;
import learningPaths.Quiz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaCrearQuiz extends JFrame {
    private JTextField txtDescripcionPregunta;
    private JComboBox<String> cmbCantidadOpciones;
    private JTextField[] txtOpciones;
    private JCheckBox[] chkEsCorrecto;
    private JButton btnSiguiente;
    private JButton btnFinalizar;

    private int cantidadPreguntas;
    private int preguntaActual;
    
    private Quiz quiz;

    public VentanaCrearQuiz(Quiz quiz, int cantidadPreguntas) {
        this.cantidadPreguntas = cantidadPreguntas;
        this.preguntaActual = 0;
        this.quiz=quiz;
        setTitle("Crear Quiz");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        inicializarFormulario();
    }

    private void inicializarFormulario() {
        JPanel panelCentral = new JPanel(new GridLayout(0, 1));
        panelCentral.setBorder(BorderFactory.createTitledBorder("Pregunta " + (preguntaActual + 1)));

        txtDescripcionPregunta = new JTextField();
        panelCentral.add(new JLabel("Descripción de la pregunta:"));
        panelCentral.add(txtDescripcionPregunta);

        cmbCantidadOpciones = new JSpinner(new SpinnerNumberModel(2, 2, 4, 1));
        panelCentral.add(new JLabel("Cantidad de opciones:"));
        panelCentral.add(spnCantidadOpciones);

        btnSiguiente = new JButton("Siguiente");
        btnFinalizar = new JButton("Finalizar");
        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.add(btnSiguiente);
        panelBotones.add(btnFinalizar);

        btnSiguiente.addActionListener(e -> capturarPregunta());
        btnFinalizar.addActionListener(e -> finalizarQuiz());

        add(panelCentral, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        actualizarOpciones(2);
        spnCantidadOpciones.addChangeListener(e -> actualizarOpciones((int) spnCantidadOpciones.getValue()));
    }

    private void actualizarOpciones(int cantidad) {
        if (txtOpciones != null) {
            for (JTextField campo : txtOpciones) {
                remove(campo);
            }
            for (JCheckBox chk : chkEsCorrecto) {
                remove(chk);
            }
        }

        txtOpciones = new JTextField[cantidad];
        chkEsCorrecto = new JCheckBox[cantidad];
        JPanel panelOpciones = new JPanel(new GridLayout(cantidad, 2));
        panelOpciones.setBorder(BorderFactory.createTitledBorder("Opciones"));

        for (int i = 0; i < cantidad; i++) {
            txtOpciones[i] = new JTextField();
            chkEsCorrecto[i] = new JCheckBox("Correcta");

            panelOpciones.add(new JLabel("Opción " + (i + 1) + ":"));
            panelOpciones.add(txtOpciones[i]);
            panelOpciones.add(new JLabel(""));
            panelOpciones.add(chkEsCorrecto[i]);
        }

        add(panelOpciones, BorderLayout.EAST);
        revalidate();
        repaint();
    }

    private void capturarPregunta() {
        String descripcion = txtDescripcionPregunta.getText();
        PreguntaOpcionMultiple pregunta = new PreguntaOpcionMultiple(descripcion, preguntaActual + 1);

        for (int i = 0; i < txtOpciones.length; i++) {
            String texto = txtOpciones[i].getText();
            boolean esCorrecta = chkEsCorrecto[i].isSelected();
            Opcion opcion = new Opcion(texto, esCorrecta, i);
            pregunta.agregarOpcion(opcion);
        }

        quiz.addPregunta(pregunta);
        preguntaActual++;

        if (preguntaActual < cantidadPreguntas) {
            txtDescripcionPregunta.setText("");
            spnCantidadOpciones.setValue(2);
            actualizarOpciones(2);
        } else {
            JOptionPane.showMessageDialog(this, "Todas las preguntas han sido creadas.");
            btnSiguiente.setEnabled(false);
        }
    }

    private void finalizarQuiz() {
        JOptionPane.showMessageDialog(this, "Quiz creado con éxito.");
        dispose();
    }
}
