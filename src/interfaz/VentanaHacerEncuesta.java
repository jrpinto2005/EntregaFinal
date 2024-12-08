package interfaz;

import javax.swing.*;

import constructores.ControladorEnvios;
import envios.Opcion;
import envios.PreguntaEncuesta;
import envios.PreguntaOpcionMultiple;
import exceptions.ActivdadNoEcontradaException;
import learningPaths.Encuesta;
import learningPaths.Quiz;
import usuario.Estudiante;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class VentanaHacerEncuesta extends JFrame {
    private int indicePreguntaActual; // Índice de la pregunta actual
    private Encuesta encuesta;
    private Estudiante estudiante;
    private VentanaSeleccionActividad ventanaSeleccionActividad;
    private List<Integer> respuestas;

    private JLabel lblPregunta;
    private JRadioButton[] opciones;
    private ButtonGroup grupoOpciones;
    private JButton btnSiguiente;
    private JButton btnVolver;

    public VentanaHacerEncuesta(Encuesta encuesta, Estudiante estudiante, VentanaSeleccionActividad ventanaSeleccionActividad)
    {
        this.encuesta = encuesta;
        this.estudiante = estudiante;
        this.ventanaSeleccionActividad = ventanaSeleccionActividad;
        this.indicePreguntaActual = 0;
        this.respuestas= new ArrayList<Integer>();

        // Configuración inicial de la ventana
        setTitle("Hacer Encuesta");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Panel para pregunta y opciones
        JPanel panelContenido = new JPanel(new BorderLayout(10, 10));
        lblPregunta = new JLabel("", SwingConstants.CENTER);
        lblPregunta.setFont(new Font("Arial", Font.BOLD, 16));
        panelContenido.add(lblPregunta, BorderLayout.NORTH);

        opciones = new JRadioButton[5]; // Máximo 5 opciones
        grupoOpciones = new ButtonGroup();
        JPanel panelOpciones = new JPanel(new GridLayout(4, 1, 5, 5));

        for (int i = 0; i < opciones.length; i++) {
            opciones[i] = new JRadioButton();
            opciones[i].setVisible(false); // Solo se muestran según el número de opciones de la pregunta
            grupoOpciones.add(opciones[i]);
            panelOpciones.add(opciones[i]);
        }

        panelContenido.add(panelOpciones, BorderLayout.CENTER);
        add(panelContenido, BorderLayout.CENTER);

        // Panel inferior con botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnVolver = new JButton("Volver");
        btnSiguiente = new JButton("Siguiente");

        panelBotones.add(btnVolver);
        panelBotones.add(btnSiguiente);
        add(panelBotones, BorderLayout.SOUTH);

        // Acción del botón "Volver"
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                ventanaSeleccionActividad.setVisible(true);
                dispose();
            }
        });

        // Acción del botón "Siguiente"
        btnSiguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	for (int i = 0; i < opciones.length; i++) {
                    if (opciones[i].isSelected()) { // Verifica si este botón está seleccionado
                        respuestas.add(i);
                        opciones[i].setSelected(false);
                       
                        
                    }
                    opciones[i].setVisible(false);
            	}
                if (indicePreguntaActual < encuesta.getPreguntas().size() - 1) {
                    indicePreguntaActual++;
                    mostrarPregunta();
                } else {
                    finalizarQuiz();
                }
            }
        });

        // Mostrar la primera pregunta
        mostrarPregunta();
    }

    /**
     * Muestra la pregunta actual y sus opciones.
     */
    private void mostrarPregunta()
    {
        PreguntaEncuesta preguntaActual = encuesta.getMapaPreguntas().get(indicePreguntaActual+1);
        lblPregunta.setText("<html>" + (indicePreguntaActual + 1) + ". " + preguntaActual.getTextoPregunta() + "</html>");

        // Configurar las opciones
        List<String> opcionesString = new ArrayList<String>();
        
        for (int i =1 ; i<6; i++)
        {
        	
        	opcionesString.add(String.valueOf(i));
        }
        for (int i = 0; i < opcionesString.size(); i++) {
            if (i < opcionesString.size()) {
                opciones[i].setText(opcionesString.get(i));
                opciones[i].setVisible(true);
            } else {
                opciones[i].setVisible(false);
            }
            opciones[i].setSelected(false); // Deseleccionar todas
        }
    }

    /**
     * Guarda la respuesta seleccionada para la pregunta actual.
     */


    /**
     * Finaliza el quiz, mostrando un mensaje de confirmación y regresando a la ventana de selección.
     */
    private void finalizarQuiz() {
    	 ControladorEnvios env= new ControladorEnvios();
         try {
 			env.hacerEncuesta(estudiante, encuesta.getId() , respuestas);
 		} catch (ActivdadNoEcontradaException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
         JOptionPane.showMessageDialog(
                 this,
                 "Encuesta completada. Gracias por responder.",
                 "Encuesta Finalizado",
                 JOptionPane.INFORMATION_MESSAGE
         );
         ventanaSeleccionActividad.setVisible(true);
         dispose();
}}