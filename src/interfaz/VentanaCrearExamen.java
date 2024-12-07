package interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import constructores.ConstructorExamen;
import envios.PreguntaAbierta;
import learningPaths.Examen;

public class VentanaCrearExamen extends JFrame {
    private JTextField txtCantidadPreguntas;
    private JTextArea txtDescripcion;
    private JTextField txtValorPregunta;
    private JButton btnAgregarPregunta;
    private JButton btnFinalizarExamen;

    private int preguntaActual = 0;
    private int cantidadPreguntas;
    private Examen examen;

    public VentanaCrearExamen(Examen examen) {
        this.examen = examen;

        setTitle("Crear Examen");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 1, 10, 10));

        add(new JLabel("Cantidad de preguntas:"));
        txtCantidadPreguntas = new JTextField();
        add(txtCantidadPreguntas);
        
        add(new JLabel("Descripción de la pregunta:"));
        txtDescripcion = new JTextArea();
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        add(new JScrollPane(txtDescripcion));

        add(new JLabel("Valor de la pregunta:"));
        txtValorPregunta = new JTextField();
        add(txtValorPregunta);

        btnAgregarPregunta = new JButton("Agregar Pregunta");
        add(btnAgregarPregunta);

        btnFinalizarExamen = new JButton("Finalizar Examen");
        add(btnFinalizarExamen);
        btnFinalizarExamen.setEnabled(false);

        btnAgregarPregunta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (preguntaActual == 0) {
                        cantidadPreguntas = Integer.parseInt(txtCantidadPreguntas.getText());
                        if (cantidadPreguntas <= 0) {
                            throw new NumberFormatException();
                        }
                        txtCantidadPreguntas.setEditable(false);
                    }

                    if (preguntaActual < cantidadPreguntas) {
                        String descripcion = txtDescripcion.getText().trim();
                        if (descripcion.isEmpty()) {
                            JOptionPane.showMessageDialog(VentanaCrearExamen.this, "La descripción no puede estar vacía.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        int valorPregunta = Integer.parseInt(txtValorPregunta.getText().trim());
                        if (valorPregunta <= 0) {
                            throw new NumberFormatException();
                        }

                        PreguntaAbierta pregunta = new PreguntaAbierta(descripcion, preguntaActual + 1, valorPregunta);
                        examen.addPregunta(pregunta);

                        preguntaActual++;
                        txtDescripcion.setText("");
                        txtValorPregunta.setText("");

                        if (preguntaActual == cantidadPreguntas) {
                            btnAgregarPregunta.setEnabled(false);
                            btnFinalizarExamen.setEnabled(true);
                            JOptionPane.showMessageDialog(VentanaCrearExamen.this, "Se han agregado todas las preguntas.", "Información", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(VentanaCrearExamen.this, "Por favor, ingresa valores válidos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnFinalizarExamen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(VentanaCrearExamen.this, "Examen creado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        });
    }
}
