package persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

import usuario.ControladorUsuarios;
import usuario.Estudiante;
import usuario.Profesor;

public class UsuariosPersistencia {
	private static ControladorUsuarios controlador;

	public UsuariosPersistencia() {
		super();
		controlador = ControladorUsuarios.getInstancia();
	}
	private static void cargarSystem()
	{
		controlador = ControladorUsuarios.getInstancia();
	}
	

	public static void cargarSistema(File archivo)
			throws FileNotFoundException, IOException, NumberFormatException, ParseException
	{
		cargarSystem();
		BufferedReader br = new BufferedReader(new FileReader(archivo));
		String line = br.readLine();
		// se prepara todo para empezar a leer el archivo
		while (line != null)
		// recorre el archivo hasta la ultima linea
		{
			String[] partes = line.split(",");
			// separa las filas por comas y cada palabra entre comas la guarda en una pos de
			// lista
			if (partes[0].equals("profesor")) {
				Profesor profe = new Profesor(partes[2], partes[1], partes[3], partes[4], "profesor");
				controlador.agregarProfesor(profe);
				// agrega al profesor al controlador de usuarios
			} else if (partes[0].equals("estudiante")) {
				Estudiante estu = new Estudiante(partes[2], partes[1], partes[3], partes[4], "estudiante");
				controlador.agregarEstudiante(estu);
				// agrega el perfil del estudiante al controlador de estudiantes
			}
			line = br.readLine();
			// siguiente linea
		}
		br.close();
		// se cierra
	}

	public static void guardarSistema(File archivo) throws IOException 
	{
		cargarSystem();
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {

			// Escribir LearningPaths
			Map<String, Profesor> profesores = controlador.getProfesores();
			for (String profe : profesores.keySet()) {
				Profesor temp = profesores.get(profe);
				bw.write("profesor,");
				bw.write(temp.getNombre() + ",");
				bw.write(temp.getId() + ",");
				bw.write(temp.getEmail() + ",");
				bw.write(temp.getContrasena());
				bw.newLine();
			}
			Map<String, Estudiante> estudiantes = controlador.getEstudiantes();
			for (String estu : estudiantes.keySet()) {
				Estudiante temp = estudiantes.get(estu);
				bw.write("estudiante,");
				bw.write(temp.getNombre() + ",");
				bw.write(temp.getId() + ",");
				bw.write(temp.getEmail() + ",");
				bw.write(temp.getContrasena());
				bw.newLine();
			}
			bw.close();

		}
	}
}
