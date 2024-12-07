package interfaz;

import javax.swing.*;

import constructores.ConstructorEncuesta;
import constructores.ConstructorExamen;
import constructores.ConstructorQuiz;
import constructores.ConstructorRecursoEducativo;
import constructores.ConstructorTarea;
import learningPaths.Encuesta;
import learningPaths.Examen;
import learningPaths.Quiz;
import learningPaths.RecursoEducativo;
import learningPaths.Tarea;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VentanaCrearActividad extends JFrame {
	private JTextField txtDescripcionGeneral;
	private JTextField txtNombre;
	private JTextField txtObjetivo;
	private JTextField txtFechaInicio;
	private JTextField txtFechaFin;
	private JSpinner spnDuracion;
	private JSpinner spnDificultad;
	private JCheckBox chkObligatoria;
	private JTextField txtIdLearningPath;
	private JComboBox<String> cmbTipoActividad;
	private JButton btnCrear;
	private JButton btnSalir;

	public VentanaCrearActividad(MenuProfesor menu) {
		setTitle("Crear Actividad");
		setSize(400, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new GridLayout(11, 2, 10, 10));

		add(new JLabel("Descripción de la actividad:"));
		txtDescripcionGeneral = new JTextField();
		add(txtDescripcionGeneral);

		add(new JLabel("Nombre de la actividad:"));
		txtNombre = new JTextField();
		add(txtNombre);

		add(new JLabel("Objetivo:"));
		txtObjetivo = new JTextField();
		add(txtObjetivo);

		add(new JLabel("Fecha de inicio (dd-MM-yyyy):"));
		txtFechaInicio = new JTextField();
		add(txtFechaInicio);

		add(new JLabel("Fecha de fin (dd-MM-yyyy):"));
		txtFechaFin = new JTextField();
		add(txtFechaFin);

		add(new JLabel("Duración (minutos):"));
		spnDuracion = new JSpinner(new SpinnerNumberModel(30, 1, 1000, 10));
		add(spnDuracion);

		add(new JLabel("Dificultad (1-10):"));
		spnDificultad = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
		add(spnDificultad);

		add(new JLabel("¿Es obligatoria?"));
		chkObligatoria = new JCheckBox();
		add(chkObligatoria);

		add(new JLabel("ID del Learning Path:"));
		txtIdLearningPath = new JTextField();
		add(txtIdLearningPath);

		add(new JLabel("Tipo de actividad:"));
		cmbTipoActividad = new JComboBox<>(new String[] { "Quiz", "Examen", "Tarea", "Recurso Educativo", "Encuesta" });
		add(cmbTipoActividad);

		btnCrear = new JButton("Crear Actividad");
		add(btnCrear);

		btnSalir = new JButton("Salir");
		add(btnSalir);

		btnCrear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String descripcionGeneral = txtDescripcionGeneral.getText();
					String nombre = txtNombre.getText();
					String objetivo = txtObjetivo.getText();
					Date fechaInicio = parseFecha(txtFechaInicio.getText());
					Date fechaFin = parseFecha(txtFechaFin.getText());
					int duracion = (int) spnDuracion.getValue();
					int dificultad = (int) spnDificultad.getValue();
					boolean isObligatoria = chkObligatoria.isSelected();
					String idLearningPath = txtIdLearningPath.getText();
					String tipoDeActividad = (String) cmbTipoActividad.getSelectedItem();

					if (tipoDeActividad.equals("Quiz")) {
						int puntajeMaximo=pedirEnteroAlUsuario("Cuanta preguntas tendrá el quiz ");
						ConstructorQuiz constructor = new ConstructorQuiz();
						Quiz quiz = constructor.crearQuiz(descripcionGeneral, objetivo, nombre, fechaInicio, fechaFin,
								duracion, dificultad, 0, tipoDeActividad, isObligatoria, idLearningPath, puntajeMaximo);
						VentanaCrearQuiz ventanaQuiz = new VentanaCrearQuiz(quiz, puntajeMaximo);
						ventanaQuiz.setVisible(true);
					} else if (tipoDeActividad.equals("Examen")) {
						int puntajeMaximo = pedirEnteroAlUsuario(
								"¿Cuál es el puntaje máximo que se puede obtener? Tenga en cuenta que este valor debe ser la suma de los valores que pondrá en cada pregunta");
						ConstructorExamen constructor = new ConstructorExamen();
						Examen examen = constructor.crearExamen(descripcionGeneral, objetivo, nombre, fechaInicio,
								fechaFin, duracion, dificultad, 0, tipoDeActividad, isObligatoria, idLearningPath,
								puntajeMaximo);
						VentanaCrearExamen ventanaExamen= new  VentanaCrearExamen(examen);
						ventanaExamen.setVisible(true);
					} else if (tipoDeActividad.equals("Tarea")) {
						ConstructorTarea constructor = new ConstructorTarea();
						Tarea tarea = constructor.crearTarea(descripcionGeneral, objetivo, nombre, fechaInicio,
								fechaFin, duracion, dificultad, 0, tipoDeActividad, isObligatoria, idLearningPath);
						VentanaCrearTarea ventanaTarea= new  VentanaCrearTarea(menu, tarea);
						ventanaTarea.setVisible(true);
					} else if (tipoDeActividad.equals("Recurso Educativo")) {
						ConstructorRecursoEducativo constructor = new ConstructorRecursoEducativo();
						RecursoEducativo recurso = constructor.crearRecursoEducativo(descripcionGeneral, objetivo,
								nombre, fechaInicio, fechaFin, duracion, dificultad, 0, tipoDeActividad, isObligatoria,
								idLearningPath);
						VentanaRecursoEducativo ventanaRecurso= new  VentanaRecursoEducativo(menu, recurso);
						ventanaRecurso.setVisible(true);
					} else if (tipoDeActividad.equals("Encuesta")) {
						int puntajeMaximo = pedirEnteroAlUsuario("¿Cuántas preguntas tendrá la encuesta?") * 5;
						ConstructorEncuesta constructor = new ConstructorEncuesta();
						Encuesta encuesta = constructor.crearEncuesta(descripcionGeneral, objetivo, nombre, fechaInicio,
								fechaFin, duracion, dificultad, 0, tipoDeActividad, isObligatoria, idLearningPath,
								puntajeMaximo);
						VentanaCrearEncuesta ventanaEncuesta= new  VentanaCrearEncuesta(encuesta, puntajeMaximo);
						ventanaEncuesta.setVisible(true);
					}

					JOptionPane.showMessageDialog(VentanaCrearActividad.this, "Actividad creada exitosamente");
				} catch (ParseException ex) {
					JOptionPane.showMessageDialog(VentanaCrearActividad.this, "Error en el formato de las fechas",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnSalir.addActionListener(e -> {
			this.dispose();
			menu.setVisible(true);
		});
	}

	private Date parseFecha(String fecha) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return sdf.parse(fecha);
	}

	private int pedirEnteroAlUsuario(String mensaje) {
		String input = JOptionPane.showInputDialog(this, mensaje);
		try {
			return Integer.parseInt(input);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Entrada no válida. Por favor, introduzca un número entero.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return pedirEnteroAlUsuario(mensaje);
		}
	}

}
