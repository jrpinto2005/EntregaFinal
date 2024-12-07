package interfaz;

import javax.swing.*;

import constructores.ControladorEnvios;
import exceptions.ActivdadNoEcontradaException;
import learningPaths.RecursoEducativo;
import usuario.Estudiante;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class VentanaHacerRecursoEducativo extends JFrame {
    private JLabel lblContenido;
    private JButton btnVolver;
    private JButton btnMarcarCompletado;

    public VentanaHacerRecursoEducativo(RecursoEducativo recurso, Estudiante estudiante, VentanaSeleccionActividad ventanaSeleccionActividad) {
        // Configuración de la ventana
        setTitle("Hacer Recurso Educativo");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Mostrar el contenido de la tarea
        lblContenido = new JLabel("<html>" + recurso.getContenido() + "</html>", SwingConstants.CENTER);
        lblContenido.setFont(new Font("Arial", Font.PLAIN, 14));
        add(lblContenido, BorderLayout.CENTER);

        // Crear botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnVolver = new JButton("Volver");
        btnMarcarCompletado = new JButton("Marcar como Completado");
        ControladorEnvios env= new ControladorEnvios();
		boolean recomendado= env.esBuenaIdeaHacerActividad(estudiante,recurso);
        if(recomendado==false) 
        {
        	int opcion = JOptionPane.showConfirmDialog(
        	        this,
        	        "No se recomienda hacer esta actividad. ¿Desea proceder?",
        	        "Advertencia",
        	        JOptionPane.YES_NO_OPTION,
        	        JOptionPane.WARNING_MESSAGE
        	);

        	// Si el usuario selecciona "No", regresar a VentanaSeleccionActividad
        	if (opcion == JOptionPane.NO_OPTION) {
        	    ventanaSeleccionActividad.setVisible(true); // Mostrar la ventana anterior
        	    dispose(); // Cerrar la ventana actual
        	    return;
        }
        }


        // Acción del botón "Volver"
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Volver a la ventana de selección de actividades
                setVisible(false);
                ventanaSeleccionActividad.setVisible(true);
                dispose();
            }
        });

        // Acción del botón "Marcar como Completado"
        btnMarcarCompletado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear un envío para la tarea
        		try {
					env.hacerRecurso(estudiante, recurso.getId());
				} catch (ActivdadNoEcontradaException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

                // Mostrar un mensaje de confirmación
                JOptionPane.showMessageDialog(
                		VentanaHacerRecursoEducativo.this,
                        "Tarea marcada como completada.",
                        "Confirmación",
                        JOptionPane.INFORMATION_MESSAGE
                );

                // Volver a la ventana de selección de actividades
                setVisible(false);
                ventanaSeleccionActividad.setVisible(true);
                dispose();
            }
        });

        // Agregar los botones al panel
        panelBotones.add(btnVolver);
        panelBotones.add(btnMarcarCompletado);
        add(panelBotones, BorderLayout.SOUTH);
    }
}

