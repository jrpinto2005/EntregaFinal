package persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import envios.Opcion;
import envios.Pregunta;
import envios.PreguntaAbierta;
import envios.PreguntaEncuesta;
import envios.PreguntaOpcionMultiple;
import exceptions.ActivdadNoEcontradaException;
import learningPaths.Actividad;
import learningPaths.Encuesta;
import learningPaths.Examen;
import learningPaths.LearningPath;
import learningPaths.Lectura;
import learningPaths.Quiz;
import learningPaths.SitioWeb;
import learningPaths.Video;
import usuario.Sistema;

public class CentralPersistencia {
	private static Sistema sistema;

	public CentralPersistencia() {
		super();
		sistema = Sistema.getInstancia();
		// creamos el sistema
	}
	private static void cargarSystem()
	{
		sistema = Sistema.getInstancia();
	}

	public static void cargarSistema(File archivo)
			throws FileNotFoundException, IOException, NumberFormatException, ParseException, ActivdadNoEcontradaException 
	{
		cargarSystem();
		BufferedReader br = new BufferedReader(new FileReader(archivo));
		String line = br.readLine();
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		// se prepara todo para empezar a leer el archivo
		while (line != null)
		// recorre el archivo hasta la ultima linea
		{
			String[] partes = line.split(",");
			// separa las filas por comas y cada palabra entre comas la guarda en una pos de
			// lista

			if (partes[0].equals("lp"))
			// si la primera palabra de la fila eslp sabemos que esto es un learning path

			{
				LearningPath lptemp = new LearningPath(partes[1], partes[2], Integer.parseInt(partes[3]),
						Integer.parseInt(partes[4]), Integer.parseInt(partes[5]), formato.parse(partes[6]),
						formato.parse(partes[7]), Integer.parseInt(partes[8]), partes[9], partes[10],
						Double.parseDouble(partes[11]));
				sistema.addLP(lptemp);
				// creamos un learning path y lo agregamos al sistema
			} else if (partes[0].equals("quiz")) {
				// si la primera palbra es quiz sabemos que es quiz
				Quiz quiztemp = new Quiz(partes[1], partes[2], partes[3], formato.parse(partes[4]),
						formato.parse(partes[5]), Integer.parseInt(partes[6]), Integer.parseInt(partes[7]),
						Double.parseDouble(partes[8]), "Quiz", Boolean.parseBoolean(partes[10]), partes[11],
						Integer.parseInt(partes[12]));
				sistema.addActividad(quiztemp);
				sistema.getLearningPaths().get(partes[11]).agregarActividad(quiztemp);
				
				// creamos un quisz y lo agregamos al sistema
				// lo mismo se hara con todas las actividades invocando a su constructor
			} else if (partes[0].equals("examen")) {
				Examen examentemp = new Examen(partes[1], partes[2], partes[3], formato.parse(partes[4]),
						formato.parse(partes[5]), Integer.parseInt(partes[6]), Integer.parseInt(partes[7]),
						Double.parseDouble(partes[8]), "Examen", Boolean.parseBoolean(partes[10]), partes[11],
						Integer.parseInt(partes[12]));
				sistema.addActividad(examentemp);
				sistema.getLearningPaths().get(partes[11]).agregarActividad(examentemp);
			} else if (partes[0].equals("encuesta")) {
				Encuesta encuestatemp = new Encuesta(partes[1], partes[2], partes[3], formato.parse(partes[4]),
						formato.parse(partes[5]), Integer.parseInt(partes[6]), Integer.parseInt(partes[7]),
						Double.parseDouble(partes[8]), "Encuesta", Boolean.parseBoolean(partes[10]), partes[11],
						Integer.parseInt(partes[12]));
				sistema.addActividad(encuestatemp);
				sistema.getLearningPaths().get(partes[11]).agregarActividad(encuestatemp);

			} else if (partes[0].equals("web")) {
				SitioWeb webtemp = new SitioWeb(partes[1], partes[2], partes[3], formato.parse(partes[4]),
						formato.parse(partes[5]), Integer.parseInt(partes[6]), Integer.parseInt(partes[7]),
						Double.parseDouble(partes[8]), "SitioWeb", Boolean.parseBoolean(partes[10]), partes[11],
						partes[12]);
				sistema.addActividad(webtemp);
				sistema.getLearningPaths().get(partes[11]).agregarActividad(webtemp);

			} else if (partes[0].equals("video")) {
				Video vidtemp = new Video(partes[1], partes[2], partes[3], formato.parse(partes[4]),
						formato.parse(partes[5]), Integer.parseInt(partes[6]), Integer.parseInt(partes[7]),
						Double.parseDouble(partes[8]), "Video", Boolean.parseBoolean(partes[10]), partes[11],
						Integer.parseInt(partes[12]));
				sistema.addActividad(vidtemp);
				sistema.getLearningPaths().get(partes[11]).agregarActividad(vidtemp);

			} else if (partes[0].equals("lectura")) {
				Lectura lecttemp = new Lectura(partes[1], partes[2], partes[3], formato.parse(partes[4]),
						formato.parse(partes[5]), Integer.parseInt(partes[6]), Integer.parseInt(partes[7]),
						Double.parseDouble(partes[8]), "Lectura", Boolean.parseBoolean(partes[10]), partes[11]);
				sistema.addActividad(lecttemp);
				sistema.getLearningPaths().get(partes[11]).agregarActividad(lecttemp);

			}
			else if (partes[0].equals("preguntam"))
			{
				PreguntaOpcionMultiple pregunta = new PreguntaOpcionMultiple(partes[1],Integer.parseInt(partes[2]));
				Quiz quiz  =(Quiz) sistema.encontrarActividad(partes[3]);
				quiz.addPregunta(pregunta);
			}
			else if (partes[0].equals("preguntaa"))
			{
				PreguntaAbierta preguntaa = new PreguntaAbierta(partes[1],Integer.parseInt(partes[2]),Double.parseDouble(partes[3]));
				Examen examen = (Examen) sistema.encontrarActividad(partes[4]);
				examen.addPregunta(preguntaa);
			}
			else if (partes[0].equals("preguntae"))
			{
				PreguntaEncuesta preguntae = new PreguntaEncuesta(partes[1],Integer.parseInt(partes[2]));
				Encuesta encuesta  =(Encuesta) sistema.encontrarActividad(partes[3]);
				encuesta.addPregunta(preguntae);
			}
			else if (partes[0].equals("opcionm"))
			{
				Opcion opcion = new Opcion(partes[1],Boolean.parseBoolean(partes[2]),Integer.parseInt(partes[3]));
				Quiz quiz  =(Quiz) sistema.encontrarActividad(partes[4]);
				List <PreguntaOpcionMultiple> lista = new ArrayList<PreguntaOpcionMultiple>(quiz.getPreguntas());
				PreguntaOpcionMultiple pregunta = lista.get(Integer.parseInt(partes[5]));
				pregunta.agregarOpcion(opcion);

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
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {

			// Escribir LearningPaths
			Map<String, LearningPath> lps = sistema.getLearningPaths();
			for (String lp : lps.keySet()) {
				LearningPath temp = lps.get(lp);
				bw.write("lp,");
				bw.write(lp+",");
				bw.write(temp.getDescripcionGeneral() + ",");
				bw.write(String.valueOf(temp.getNivelDificutad()) + ",");
				bw.write(String.valueOf(temp.getDuracion()) + ",");
				bw.write(String.valueOf(temp.getRating()) + ",");
				bw.write(formato.format(temp.getFechaDuracion()) + ",");
				bw.write(formato.format(temp.getFechaModificacion()) + ",");
				bw.write(String.valueOf(temp.getVersion()) + ",");
				bw.write(temp.getIdCreador() + ",");
				bw.write(temp.getObjetivos() + ",");
				bw.write(String.valueOf(temp.getPromedioActividadesCompletadas()));
				bw.newLine();
			}

			// Escribir otras actividades (quiz, examen, encuesta, web, video, lectura)
			Map<String, Actividad> actividades = sistema.getActividades();
			for (String act : actividades.keySet()) {
				Actividad acttemp = actividades.get(act);
				if (acttemp instanceof Quiz) {
					bw.write("quiz,");
					bw.write(acttemp.getDescripcion() + ",");
					bw.write(acttemp.getObjetivo() + ",");
					bw.write(act.split("\\.")[1] + ",");
					bw.write(formato.format(acttemp.getFechaInicio()) + ",");
					bw.write(formato.format(acttemp.getFechaFin()) + ",");
					bw.write(String.valueOf(acttemp.getDuracion()) + ",");
					bw.write(String.valueOf(acttemp.getDificultad()) + ",");
					bw.write(String.valueOf(acttemp.getRating()) + ",");
					bw.write( "Quiz,");
					bw.write(String.valueOf(acttemp.isObligatoria()) + ",");
					bw.write(acttemp.getLearningPath().getTitulo() + ",");
					bw.write(String.valueOf(((Quiz) acttemp).getPuntajeMaximo()));
					bw.newLine();
					Collection<PreguntaOpcionMultiple> preguntas = ((Quiz) acttemp).getPreguntas();
					for (PreguntaOpcionMultiple pregunta: preguntas)
					{ 
						bw.write("preguntam,");
						bw.write(pregunta.getTextoPregunta()+",");
						bw.write(String.valueOf(pregunta.getIdPregunta())+",");
						bw.write(act);
						bw.newLine();
						Collection<Opcion> opciones = pregunta.getOpciones();
						for (Opcion opcion: opciones)
						{
							bw.write("opcionm,");
							bw.write(opcion.getDescripcion()+",");
							bw.write(String.valueOf(opcion.esCorrecto())+",");
							bw.write(String.valueOf(opcion.getIndice())+",");
							bw.write(act+",");
							bw.write(String.valueOf(pregunta.getIdPregunta()-1));
							bw.newLine();
						}
					}
				} else if (acttemp instanceof Examen) {
					bw.write("examen,");
					bw.write(acttemp.getDescripcion() + ",");
					bw.write(acttemp.getObjetivo() + ",");
					bw.write(act.split("\\.")[1] + ",");
					bw.write(formato.format(acttemp.getFechaInicio()) + ",");
					bw.write(formato.format(acttemp.getFechaFin()) + ",");
					bw.write(String.valueOf(acttemp.getDuracion()) + ",");
					bw.write(String.valueOf(acttemp.getDificultad()) + ",");
					bw.write(String.valueOf(acttemp.getRating()) + ",");
					bw.write("Examen,");
					bw.write(String.valueOf(acttemp.isObligatoria()) + ",");
					bw.write(acttemp.getLearningPath().getTitulo() + ",");
					bw.write(String.valueOf(((Examen) acttemp).getPuntajeMaximo()));
					bw.newLine();
					Collection<PreguntaAbierta> preguntasa = ((Examen) acttemp).getPreguntas();
					for (PreguntaAbierta preguntaa: preguntasa)
					{
						bw.write("preguntaa,");
						bw.write(preguntaa.getTextoPregunta()+",");
						bw.write(String.valueOf(preguntaa.getIdPregunta())+",");
						bw.write(Double.valueOf(preguntaa.getValorPregunta())+",");
						bw.write(act);
						bw.newLine();
					}
				} else if (acttemp instanceof Encuesta) {
					bw.write("encuesta,");
					bw.write(acttemp.getDescripcion() + ",");
					bw.write(acttemp.getObjetivo() + ",");
					bw.write(act.split("\\.")[1] + ",");
					bw.write(formato.format(acttemp.getFechaInicio()) + ",");
					bw.write(formato.format(acttemp.getFechaFin()) + ",");
					bw.write(String.valueOf(acttemp.getDuracion()) + ",");
					bw.write(String.valueOf(acttemp.getDificultad()) + ",");
					bw.write(String.valueOf(acttemp.getRating()) + ",");
					bw.write("Encuesta,");
					bw.write(String.valueOf(acttemp.isObligatoria()) + ",");
					bw.write(acttemp.getLearningPath().getTitulo() + ",");
					bw.newLine();
					Collection<PreguntaEncuesta> preguntase = ((Encuesta) acttemp).getPreguntas();
					for (PreguntaEncuesta preguntae: preguntase)
					{
						bw.write("preguntae,");
						bw.write(preguntae.getTextoPregunta()+",");
						bw.write(String.valueOf(preguntae.getIdPregunta())+",");
						bw.write(act);
						bw.newLine();
						}
				} else if (acttemp instanceof SitioWeb) {
					bw.write("web,");
					bw.write(acttemp.getDescripcion() + ",");
					bw.write(acttemp.getObjetivo() + ",");
					bw.write(act.split("\\.")[1] + ",");
					bw.write(formato.format(acttemp.getFechaInicio()) + ",");
					bw.write(formato.format(acttemp.getFechaFin()) + ",");
					bw.write(String.valueOf(acttemp.getDuracion()) + ",");
					bw.write(String.valueOf(acttemp.getDificultad()) + ",");
					bw.write(String.valueOf(acttemp.getRating()) + ",");
					bw.write("Web,");
					bw.write(String.valueOf(acttemp.isObligatoria()) + ",");
					bw.write(acttemp.getLearningPath().getTitulo() + ",");
					bw.write(((SitioWeb) acttemp).getUrl());
					bw.newLine();
				} else if (acttemp instanceof Video) {
					bw.write("video,");
					bw.write(acttemp.getDescripcion() + ",");
					bw.write(acttemp.getObjetivo() + ",");
					bw.write(act.split("\\.")[1] + ",");
					bw.write(formato.format(acttemp.getFechaInicio()) + ",");
					bw.write(formato.format(acttemp.getFechaFin()) + ",");
					bw.write(String.valueOf(acttemp.getDuracion()) + ",");
					bw.write(String.valueOf(acttemp.getDificultad()) + ",");
					bw.write(String.valueOf(acttemp.getRating()) + ",");
					bw.write("Video,");
					bw.write(String.valueOf(acttemp.isObligatoria()) + ",");
					bw.write(acttemp.getLearningPath().getTitulo() + ",");
					bw.write(String.valueOf(((Video) acttemp).getDuracionV()));
					bw.newLine();
				} else if (acttemp instanceof Lectura) {
					bw.write("lectura,");
					bw.write(acttemp.getDescripcion() + ",");
					bw.write(acttemp.getObjetivo() + ",");
					bw.write(act.split("\\.")[1] + ",");
					bw.write(formato.format(acttemp.getFechaInicio()) + ",");
					bw.write(formato.format(acttemp.getFechaFin()) + ",");
					bw.write(String.valueOf(acttemp.getDuracion()) + ",");
					bw.write(String.valueOf(acttemp.getDificultad()) + ",");
					bw.write(String.valueOf(acttemp.getRating()) + ",");
					bw.write("Lectura,");
					bw.write(String.valueOf(acttemp.isObligatoria()) + ",");
					bw.write(acttemp.getLearningPath().getTitulo() + ",");
					bw.newLine();
				}
				bw.close();
			}
		}

	}
}
