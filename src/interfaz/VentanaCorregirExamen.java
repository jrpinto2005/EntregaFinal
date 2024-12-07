package interfaz;

import javax.swing.*;

import envios.CalificadorEnvioExamen;
import envios.EnvioExamen;
import envios.RespuestaAbierta;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaCorregirExamen extends JFrame {

    private JPanel panelPrincipal;
    private JButton btnSiguiente;
    private JButton btnFinalizar;

    private int indicePregunta = 0;
    private double puntajeTotal = 0.0;
    private List<RespuestaAbierta> respuestas;
    private EnvioExamen envioExamen;

    public VentanaCorregirExamen(EnvioExamen envioEx, MenuProfesor menu) {
        this.envioExamen = envioEx;
        this.respuestas = envioEx.getRespuestas();

        setTitle("Corregir Envío de Examen");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        panelPrincipal = new JPanel(new GridLayout(4, 1));
        mostrarPregunta(indicePregunta);
        
        add(panelPrincipal, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout());

        btnSiguiente = new JButton("Siguiente");
        btnSiguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                avanzarPregunta();
            }
        });
        panelBotones.add(btnSiguiente);

        btnFinalizar = new JButton("Finalizar");
        btnFinalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalizarCorreccion(menu);
            }
        });
        btnFinalizar.setEnabled(false);
        panelBotones.add(btnFinalizar);

        add(panelBotones, BorderLayout.SOUTH);
    }

    private void mostrarPregunta(int indice) {
        panelPrincipal.removeAll();

        RespuestaAbierta respuesta = respuestas.get(indice);

        JLabel lblEstudiante = new JLabel("Estudiante: " + envioExamen.getIdEstudiante());
        JLabel lblActividad = new JLabel("Actividad: " + envioExamen.getActividad().getId());
        JLabel lblPregunta = new JLabel("Pregunta: " + respuesta.getPregunta().getTextoPregunta());
        JLabel lblRespuesta = new JLabel("Respuesta: " + respuesta.getContenido());

        panelPrincipal.add(lblEstudiante);
        panelPrincipal.add(lblActividad);
        panelPrincipal.add(lblPregunta);
        panelPrincipal.add(lblRespuesta);

        panelPrincipal.revalidate();
        panelPrincipal.repaint();
    }

    private void avanzarPregunta() {
        RespuestaAbierta respuesta = respuestas.get(indicePregunta);

        // Pedir puntaje para la respuesta actual
        String puntajeStr = JOptionPane.showInputDialog(this,
                "Ingrese el puntaje para esta respuesta (Máximo: " + respuesta.getPregunta().getValorPregunta() + "):",
                "Puntaje", JOptionPane.QUESTION_MESSAGE);

        try {
            double puntaje = Double.parseDouble(puntajeStr);

            if (puntaje < 0 || puntaje > respuesta.getPregunta().getValorPregunta()) {
                JOptionPane.showMessageDialog(this, "El puntaje debe estar entre 0 y " + respuesta.getPregunta().getValorPregunta());
                return;
            }

            puntajeTotal += puntaje;
            indicePregunta++;

            if (indicePregunta < respuestas.size()) {
                mostrarPregunta(indicePregunta);
            } else {
                btnSiguiente.setEnabled(false);
                btnFinalizar.setEnabled(true);
                JOptionPane.showMessageDialog(this, "Has corregido todas las preguntas. Pulsa Finalizar.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un número válido.");
        }
    }

    private void finalizarCorreccion(MenuProfesor menu) {
        CalificadorEnvioExamen calificador = new CalificadorEnvioExamen();
        calificador.calificarExamen(puntajeTotal, envioExamen);
        JOptionPane.showMessageDialog(this, "La corrección del examen ha finalizado. Puntaje total: " + puntajeTotal);
        this.dispose();
        menu.setVisible(true);
    }
}
