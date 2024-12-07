package interfaz;

import javax.swing.*;

import envios.PreguntaEncuesta;
import learningPaths.Encuesta;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaCrearEncuesta extends JFrame {
    private JTextField txtCantidadPreguntas;
    private JTextField txtDescripcionPregunta;
    private JButton btnAgregarPregunta;
    private JButton btnFinalizar;
    private JTextArea areaPreguntasAgregadas;

    private Encuesta encuesta;
    private int preguntasRestantes;

    public VentanaCrearEncuesta(Encuesta encuesta, int cantidadPreguntas) {
        this.encuesta = encuesta;
        this.preguntasRestantes = cantidadPreguntas/5;

        setTitle("Crear Encuesta");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1, 10, 10));

       
        JPanel panelCantidadPreguntas = new JPanel(new FlowLayout());
        panelCantidadPreguntas.add(new JLabel("Preguntas restantes: "));
        txtCantidadPreguntas = new JTextField(String.valueOf(preguntasRestantes), 5);
        txtCantidadPreguntas.setEditable(false);
        panelCantidadPreguntas.add(txtCantidadPreguntas);
        add(panelCantidadPreguntas);

        
        JPanel panelDescripcionPregunta = new JPanel(new FlowLayout());
        panelDescripcionPregunta.add(new JLabel("Descripción de la pregunta: "));
        txtDescripcionPregunta = new JTextField(20);
        panelDescripcionPregunta.add(txtDescripcionPregunta);
        add(panelDescripcionPregunta);

       
        btnAgregarPregunta = new JButton("Agregar Pregunta");
        add(btnAgregarPregunta);

       
        areaPreguntasAgregadas = new JTextArea(5, 30);
        areaPreguntasAgregadas.setEditable(false);
        add(new JScrollPane(areaPreguntasAgregadas));

 
        btnFinalizar = new JButton("Finalizar Encuesta");
        btnFinalizar.setEnabled(false);
        add(btnFinalizar);


        btnAgregarPregunta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String descripcion = txtDescripcionPregunta.getText().trim();

                if (descripcion.isEmpty()) {
                    JOptionPane.showMessageDialog(VentanaCrearEncuesta.this, "La descripción no puede estar vacía", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                PreguntaEncuesta pregunta = new PreguntaEncuesta(descripcion, encuesta.getPreguntas().size() + 1);
                encuesta.addPregunta(pregunta);
                preguntasRestantes--;

                txtCantidadPreguntas.setText(String.valueOf(preguntasRestantes));
                areaPreguntasAgregadas.append("Pregunta " + pregunta.getIdPregunta() + ": " + descripcion + "\n");
                txtDescripcionPregunta.setText("");

                if (preguntasRestantes <= 0) {
                    btnAgregarPregunta.setEnabled(false);
                    btnFinalizar.setEnabled(true);
                }
            }
        });

        btnFinalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(VentanaCrearEncuesta.this, "Encuesta creada exitosamente con " + encuesta.getPreguntas().size() + " preguntas.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        });
    }
}