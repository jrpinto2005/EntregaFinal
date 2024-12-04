package consola;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import constructores.ConstructorEncuesta;
import constructores.ConstructorExamen;
import constructores.ConstructorLearningPath;
import constructores.ConstructorQuiz;
import constructores.ConstructorRecursoEducativo;
import constructores.ConstructorTarea;
import envios.CalificadorEnvioExamen;
import envios.Envio;
import envios.EnvioExamen;
import envios.Opcion;
import envios.PreguntaAbierta;
import envios.PreguntaEncuesta;
import envios.PreguntaOpcionMultiple;
import envios.RespuestaAbierta;
import exceptions.ActivdadNoEcontradaException;
import exceptions.ProfesorNoCreadorException;
import learningPaths.Actividad;
import learningPaths.Encuesta;
import learningPaths.Examen;
import learningPaths.LearningPath;
import learningPaths.Quiz;
import learningPaths.RecursoEducativo;
import learningPaths.Tarea;
import usuario.Profesor;
import usuario.Sistema;

public class ConsolaProfesor extends ConsolaPrincipal
{
	Sistema sistema=Sistema.getInstancia();
	
	public void crearLP(String idProfesor)
	{
		System.out.println("Está ingresando al menú para crear un LearningPath ");
		String titulo= pedirCadenaAlUsuario("Ingrese el título del LearningPath:");
		String descripcionGeneral= pedirCadenaAlUsuario("Ingrese la descripción del LearningPath:");
		int nivelDificultad= pedirEnteroAlUsuario("Ingrese el nivel de dificultad ");
		int duracion= pedirEnteroAlUsuario("Ingrese la duración en minutos aproximada de acabarlo "); //método con variable estatica sumando duracion
		int rating=0; //debería ser double 
		Date fechaDuracion=pedirFechaAlUsuario("Ingrese la fecha límite para terminarlo ");
		Date fechaModificación=pedirFechaAlUsuario("Ingrese la fecha de hoy "); 
		int version=1; //hacer método estatico
		String idCreador=idProfesor;
		String objetivo= pedirCadenaAlUsuario("Ingrese el objetivo del LearningPath:");
		double promedioActividadesCompletadas= 0; //método estático pero muy jodido de hacer 
		
		ConstructorLearningPath constructor= new ConstructorLearningPath();
		constructor.crearLP(titulo, descripcionGeneral, nivelDificultad, duracion, rating, fechaDuracion, fechaModificación, version, idCreador, objetivo, promedioActividadesCompletadas);
		
		
	}
	
	public void crearActividad()
	{
		
		String descripcionGeneral= pedirCadenaAlUsuario("Ingrese la descripción de la actividad: ");
		String objetivo= pedirCadenaAlUsuario("Ingrese el objetivo de la actividad: ");
		String nombre= pedirCadenaAlUsuario("Ingrese el nombre de la actividad:");
		Date fechaInicio=pedirFechaAlUsuario("Ingrese la fecha de hoy "); 
		Date fechaFin=pedirFechaAlUsuario("Ingrese la fecha límite para completar la actividad: ");
		int duracion= pedirEnteroAlUsuario("Ingrese la duración en minutos aproximada de acabar la actividad: ");
		int dificultad= pedirEnteroAlUsuario("Ingrese el nivel de dificultad: ");
		int rating=0; //debería ser double 
		int obligatoria= pedirEnteroAlUsuario("Ingrese 1 si quiere que la actividad sea obligatoria y 0 si no ");
		boolean isObligatoria=false;
		if (obligatoria==1)
		{
			isObligatoria=true;
		}
		String idLearningPath=pedirCadenaAlUsuario("Ingrese el nombre del lp:");
		String tipoDeActividad=pedirCadenaAlUsuario("Que tipo de actividad quiere realizar: Quiz, Examen, Tarea, Recurso Educativo, Encuesta ");
		
		if (tipoDeActividad.equals("Quiz"))
		{
			int puntajeMaximo=pedirEnteroAlUsuario("Cuanta preguntas tendrá el quiz ");
			ConstructorQuiz constructor= new ConstructorQuiz();
			Quiz quiz=constructor.crearQuiz(descripcionGeneral, objetivo, nombre, fechaInicio, fechaFin, duracion, dificultad, rating, tipoDeActividad, isObligatoria, idLearningPath, puntajeMaximo);
			crearQuiz(quiz, puntajeMaximo);
		}
		else if (tipoDeActividad.equals("Examen"))
		{
			int puntajeMaximo=pedirEnteroAlUsuario("Cual es el puntaje máximo que se puede obtener? Tenga en cuenta que este valor deber ser la suma de los valores que pondrá en cada pregunta ");
			ConstructorExamen constructor= new ConstructorExamen();
			Examen examen=constructor.crearExamen(descripcionGeneral, objetivo, nombre, fechaInicio, fechaFin, duracion, dificultad, rating, tipoDeActividad, isObligatoria, idLearningPath, puntajeMaximo);
			crearExamen(examen, puntajeMaximo);
		}
		else if (tipoDeActividad.equals("Tarea"))
		{
			ConstructorTarea constructor= new ConstructorTarea();
			Tarea tarea=constructor.crearTarea(descripcionGeneral, objetivo, nombre, fechaInicio, fechaFin, duracion, dificultad, rating, tipoDeActividad, isObligatoria, idLearningPath);
			crearTarea(tarea);
		}
		else if (tipoDeActividad.equals("Recurso Educativo"))
		{
			ConstructorRecursoEducativo constructor= new ConstructorRecursoEducativo();
			RecursoEducativo recurso =constructor.crearRecursoEducativo(descripcionGeneral, objetivo, nombre, fechaInicio, fechaFin, duracion, dificultad, rating, tipoDeActividad, isObligatoria, idLearningPath);
			crearRecurso(recurso);
		}
		else if (tipoDeActividad.equals("Encuesta"))
		{
			int puntajeMaximo=pedirEnteroAlUsuario("Cuanta preguntas tendrá la encuesta ");
			puntajeMaximo=puntajeMaximo*5;
			ConstructorEncuesta constructor= new ConstructorEncuesta();
			Encuesta encuesta=constructor.crearEncuesta(descripcionGeneral, objetivo, nombre, fechaInicio, fechaFin, duracion, dificultad, rating, tipoDeActividad, isObligatoria, idLearningPath, puntajeMaximo);
			crearEncuesta(encuesta, puntajeMaximo);
		}
		else
		{
			System.out.println("Opción seleccinada no es valida");
		}
		
		int crearact=pedirEnteroAlUsuario("Desea crear otra actividad (escriba 1 si si o 0 si no)");
		if (crearact==1)
		{
			crearActividad();
			
		}
		
	}
	
	public void crearQuiz(Quiz quiz, int cantidadPreguntas)
	{
		System.out.println("Has decidido hacer un quiz ");
		
		for (int i=0; i<cantidadPreguntas; i++)
		{
			int p = i+1;
			String descripcion=pedirCadenaAlUsuario("Ingrese la descripción o texto de la pregunta " + p+  " : " );
			PreguntaOpcionMultiple pregunta=new PreguntaOpcionMultiple(descripcion, i+1);  
			int tipo= pedirEnteroAlUsuario("Cuantas opciones desea tener? ");
			for (int j=0; j<tipo; j++)
			{
				int q = j+1;
				String texto=pedirCadenaAlUsuario("Ingrese el texto de la opción " +  q + " : ");
				int correcto= pedirEnteroAlUsuario("Ingrese 1 si esta opción es correcta y 0 si no: ");
				boolean esCorrecto=false;
				if (correcto==1)
				{
					esCorrecto=true;
				}
				Opcion opcion= new Opcion(texto, esCorrecto, j);
				pregunta.agregarOpcion(opcion);
			}
			
			quiz.addPregunta(pregunta);
			
		}
	}
	
	public void crearExamen(Examen examen, int cantidadPreguntas)
	{
		System.out.println("Has decidido hacer un Examen ");
		for (int i=0; i<cantidadPreguntas; i++)
		{
			String descripcion=pedirCadenaAlUsuario("Ingrese la descripción o texto de la pregunta: ");
			int valorPregunta=pedirEnteroAlUsuario("Ingresa el valor que tiene la pregunta ");
			PreguntaAbierta pregunta=new PreguntaAbierta(descripcion, i+1, valorPregunta);  
			examen.addPregunta(pregunta);
		}
	}
	
	public void crearEncuesta(Encuesta encuesta, int cantidadPreguntas)
	{
		System.out.println("Has decidido hacer una encuesta ");
		for (int i=0; i<cantidadPreguntas; i++)
		{
			String descripcion=pedirCadenaAlUsuario("Ingrese la descripción o texto de la pregunta: ");
			PreguntaEncuesta pregunta=new PreguntaEncuesta(descripcion, i+1);  
			encuesta.addPregunta(pregunta);
		}
	}
	
	public void crearTarea(Tarea tarea)
	{
		System.out.println("Has decidido hacer una Tarea ");
		String contenido= pedirCadenaAlUsuario("Ingresa el contenido de la tarea, esto es lo que verá el estudiante"); //toca añadir este atributo medio la cagada
		tarea.setContenido(contenido);
	}
	
	public void crearRecurso(RecursoEducativo recurso)
	{
		System.out.println("Has decidido hacer una Recurso educativo ");
		String contenido= pedirCadenaAlUsuario("Ingresa el contenido del recurso, esto es lo que verá el estudiante"); //toca añadir este atributo medio la cagada
		recurso.setContenido(contenido);
	}
	
	public void editarLP(LearningPath lp, String idProfesor) throws IOException
	{
		try
		{
		System.out.println("Has decidido editar un LearningPath ");
		System.out.println("Por favor para el siguiente campo escriba el campo que quiere editar de la forma exacta en la que sale");
		String atributo =pedirCadenaAlUsuario("Ingresa el atributo que quieres cambiar: (Titulo, DescripcionGeneral, Niveldificultad, FechaDuracion, FechaModificacion, Objetivos) ");
		Object valorNuevo=pedirObjetoAlUsuario("Ingresa el valor por el cual lo quieres reemplazar: "); 
		if (atributo.equals("Titulo") || atributo.equals("DescripcionGeneral") || atributo.equals("Objetivos"))
		{
			valorNuevo= (String) valorNuevo;
		}
		else if (atributo.equals("NivelDificultad"))
		{
			valorNuevo= (int) valorNuevo;
		}
		else if (atributo.equals("FechaDuracion") || atributo.equals("FechaModificacion"))
		{
			valorNuevo= (Date) valorNuevo;
		}
		ConstructorLearningPath  constructor=new ConstructorLearningPath();
		constructor.editarLP(lp, atributo, valorNuevo, idProfesor); 
		}
		catch (ProfesorNoCreadorException e)
		{
			System.out.println(e.getMessage());
		}
		
	}
		
	
	
	public void clonarActividad() throws ActivdadNoEcontradaException
	{
		System.out.println("Has decidido clonar una actividad ");
		String idLPOrigen=pedirCadenaAlUsuario("Ingresa el titulo del learningpath que contiene la actividad que deseas clonar");
		String idLPDestino=pedirCadenaAlUsuario("Ingresa el titulo del learning path al cual deseas agregar la actividad. Este debe haber sido creado por ti"); //hacer lo de revisar si es dueño
		String idActividad=pedirCadenaAlUsuario("Ingresa nombre de la actividad que deseas añadir");
		LearningPath lpOrigen=sistema.encontrarLP(idLPOrigen);
		LearningPath lpDestino=sistema.encontrarLP(idLPDestino);
		ConstructorLearningPath  constructor=new ConstructorLearningPath();
		try
		{
			constructor.clonarActividad(lpOrigen, lpDestino, idActividad);
			System.out.println("la actividad ha sido clonada con éxito a el learning path " + lpDestino.getTitulo());
		}
		catch (ActivdadNoEcontradaException e)
		{
			System.out.println("La actividad con ese id no fue encontrada ");
		}
	}
	public void calificarExamen(Profesor profesor)
	{
		System.out.println("Has decidido calificar un examen");
		List<EnvioExamen> envios=profesor.getEnviosPorCalificar();  
		int contador=0;
		for (EnvioExamen envio: envios)
		{
			contador+=1;
			System.out.println("Envío " + contador);
			System.out.println("Actividad con id: " + envio.getActividad().getId());
			System.out.println("Hecha por el estudiante: " + envio.getIdEstudiante());
			System.out.println("");
			System.out.println("");
		}
		int numeroEnvio=pedirEnteroAlUsuario("Ingresa el número de envío que quieres calificar: ");
		EnvioExamen envioElegido=envios.get(numeroEnvio-1);
		corregirExamen(envioElegido);
	}
	public void corregirExamen(EnvioExamen envioEx)
	{
		
		System.out.println("Has decidido corregir el envío del estudiante " + envioEx.getIdEstudiante() + "y de la actividad " + envioEx.getActividad().getId());
		List<RespuestaAbierta> respuestas=envioEx.getRespuestas(); 
		double valore=0;
		for (RespuestaAbierta respuesta: respuestas)
		{
			System.out.println("Pregunta: "  + respuesta.getPregunta().getTextoPregunta());  
			System.out.println("Respuesta: " + respuesta.getContenido());
			double valor=pedirEnteroAlUsuario("Ingrese el puntaje de esta respuesta. Recuerde que el valor de esta pregunta es de " + respuesta.getPregunta().getValorPregunta());
			valore+=valor;
		}
		CalificadorEnvioExamen c= new CalificadorEnvioExamen();
		
		c.calificarExamen(valore, envioEx);
	}
}
