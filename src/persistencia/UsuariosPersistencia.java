package persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import constructores.ControladorEnvios;
import envios.CalificadorEnvioExamen;
import envios.Envio;
import envios.EnvioEncuesta;
import envios.EnvioExamen;
import envios.EnvioQuiz;
import envios.RespuestaAbierta;
import envios.RespuestaEncuesta;
import envios.RespuestaMultiple;
import exceptions.ActivdadNoEcontradaException;
import learningPaths.LearningPath;
import learningPaths.Quiz;
import usuario.ControladorUsuarios;
import usuario.Estudiante;
import usuario.Profesor;
import usuario.Sistema;

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
		ControladorEnvios env = new ControladorEnvios();
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
			} 
			else if (partes[0].equals("estudiante")) {
				Estudiante estu = new Estudiante(partes[2], partes[1], partes[3], partes[4], "estudiante");
				controlador.agregarEstudiante(estu);
				// agrega el perfil del estudiante al controlador de estudiantes
			}
			else 
			{
				Estudiante estudiante = controlador.encontrarEstudiante(partes[0]);
				String[] lps = partes[1].split(":");
				String [] envios = partes[2].split(";");
				for (String idlp: lps)
				{
					if (!idlp.equals(""))
					{
					LearningPath lp = Sistema.getInstancia().encontrarLP(idlp);
					estudiante.inscribirseEnLearningPath(lp);
					}
				}
				for (String envio: envios)
				{
					if (!envio.equals(""))
					{
						String[] campos = envio.split("<>");
						if (campos[0].equals("Recurso"))
						{
							try {
								env.hacerRecurso(estudiante,campos[1]);
							} catch (ActivdadNoEcontradaException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						else if (campos[0].equals("Tarea"))
						{
							try {
								env.hacerTarea(estudiante, campos[1]);
							} catch (ActivdadNoEcontradaException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						else if (campos[0].equals("Encuesta"))
						{
							String[] respuestas = campos[2].split("><");
							List<Integer> listaRespuestas = new ArrayList<Integer>();
							for (String respuesta: respuestas)
							{
								listaRespuestas.add(Integer.parseInt(respuesta));
							}
							try {
								env.hacerEncuesta(estudiante, campos[1], listaRespuestas);
							} catch (ActivdadNoEcontradaException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
						else if (campos[0].equals("Quiz"))
						{
							String[] respuestas = campos[2].split("><");
							List<Integer> listaRespuestas = new ArrayList<Integer>();
							for (String respuesta: respuestas)
							{
								listaRespuestas.add(Integer.parseInt(respuesta));
							}
							try {
								env.hacerQuiz(estudiante, campos[1], listaRespuestas);
							} catch (ActivdadNoEcontradaException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						else if (campos[0].equals("Examen"))
						{
							CalificadorEnvioExamen calificador = new CalificadorEnvioExamen();
							String[] respuestas = campos[2].split("><");
							List<String> listaRespuestas = new ArrayList<String>();
							for (String respuesta: respuestas)
							{
								listaRespuestas.add(respuesta);
							}
							try {
								EnvioExamen envioExamen = env.hacerExamen(estudiante, campos[1], listaRespuestas);
								
								if (Boolean.parseBoolean(campos[3]))
								{
									calificador.calificarExamen(Double.parseDouble(campos[4]), envioExamen);
								}
							} catch (ActivdadNoEcontradaException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
					}
				}
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
				bw.write(temp.getId()+",");
				bw.write(":");
				for (LearningPath lp : temp.getLearningPaths())
				{
					bw.write(lp.getTitulo()+":");
					
				}
				bw.write(",");
				bw.write(";");
				for (Envio envio: temp.getEnvios())
				{
					if (envio.getActividad().getTipoActividad().equals("Recurso"))
					{
						bw.write("Recurso"+"<>");
						bw.write(envio.getActividad().getId()+";");
					}
					else if (envio.getActividad().getTipoActividad().equals("Tarea"))
					{
						bw.write("Tarea"+"<>");
						bw.write(envio.getActividad().getId()+";");
					}
					else if (envio.getActividad().getTipoActividad().equals("Encuesta"))
					{
						EnvioEncuesta envioEncuesta = (EnvioEncuesta) envio;
						bw.write("Encuesta"+"<>");
						bw.write(envio.getActividad().getId()+"<>");
						for (RespuestaEncuesta respuesta: envioEncuesta.getRespuestas())
						{

							bw.write(String.valueOf(respuesta.getValor())+"><");
						}
						bw.write(";");
						
					}
					else if (envio.getActividad().getTipoActividad().equals("Quiz"))
					{
						EnvioQuiz envioQuiz = (EnvioQuiz) envio;
						bw.write("Quiz"+"<>");
						bw.write(envio.getActividad().getId()+"<>");
						for (RespuestaMultiple respuesta: envioQuiz.getRespuestas())
						{
							bw.write(String.valueOf(respuesta.getIndice())+"><");
						}
						bw.write(";");
						
					}
					else if (envio.getActividad().getTipoActividad().equals("Examen"))
					{
						
							EnvioExamen envioExamen = (EnvioExamen) envio;
							bw.write("Examen"+"<>");
							bw.write(envio.getActividad().getId()+"<>");
							for (RespuestaAbierta respuesta: envioExamen.getRespuestas())
							{
								bw.write(String.valueOf(respuesta.getContenido())+"><");
							}
							bw.write("<>");
							bw.write(String.valueOf(envioExamen.isCompletado())+"<>");
							bw.write(String.valueOf(envioExamen.getPuntaje()));
							bw.write(";");
							
						
					
						
					}
				}
				bw.newLine();
				
				
				
			}
			bw.close();

		}
	}
}
