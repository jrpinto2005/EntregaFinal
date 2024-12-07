package interfaz;

import javax.swing.*;

import constructores.ConstructorLearningPath;
import exceptions.ActivdadNoEcontradaException;
import learningPaths.LearningPath;
import usuario.Sistema;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaClonarActividad extends JFrame {

	private JFrame frame;
	private JTextField txtLPOrigen;
	private JTextField txtLPDestino;
	private JTextField txtActividad;
	private Sistema sistema;

	public  VentanaClonarActividad(MenuProfesor menu) {
		this.sistema = Sistema.getInstancia();

		frame = new JFrame("Clonar Actividad");
		frame.setBounds(100, 100, 400, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
		frame.getContentPane().add(panel, BorderLayout.CENTER);

		JLabel lblLPOrigen = new JLabel("Título del LP Origen:");
		txtLPOrigen = new JTextField();
		panel.add(lblLPOrigen);
		panel.add(txtLPOrigen);

		JLabel lblLPDestino = new JLabel("Título del LP Destino:");
		txtLPDestino = new JTextField();
		panel.add(lblLPDestino);
		panel.add(txtLPDestino);

		JLabel lblActividad = new JLabel("ID de la Actividad:");
		txtActividad = new JTextField();
		panel.add(lblActividad);
		panel.add(txtActividad);

		JButton btnClonar = new JButton("Clonar Actividad");
		panel.add(new JLabel());  
		panel.add(btnClonar);

		btnClonar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clonarActividad();
				menu.setVisible(true);
				frame.dispose();
				
			}
		});

		frame.setVisible(true);
	}



	private void clonarActividad() {
		String idLPOrigen = txtLPOrigen.getText();
		String idLPDestino = txtLPDestino.getText();
		String idActividad = txtActividad.getText();

		try {
			LearningPath lpOrigen = sistema.encontrarLP(idLPOrigen);
			LearningPath lpDestino = sistema.encontrarLP(idLPDestino);
			ConstructorLearningPath constructor = new ConstructorLearningPath();

			constructor.clonarActividad(lpOrigen, lpDestino, idActividad);

			JOptionPane.showMessageDialog(frame, "La actividad ha sido clonada con éxito a " + lpDestino.getTitulo(),
					"Éxito", JOptionPane.INFORMATION_MESSAGE);
		} catch (ActivdadNoEcontradaException ex) {
			JOptionPane.showMessageDialog(frame, "La actividad con ese ID no fue encontrada.", "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(frame, "Ocurrió un error inesperado: " + ex.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
