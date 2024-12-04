package consola;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import constructores.ControladorEnvios;
import envios.PreguntaAbierta;
import envios.PreguntaEncuesta;
import envios.PreguntaOpcionMultiple;
import exceptions.ActivdadNoEcontradaException;
import learningPaths.Actividad;
import learningPaths.Encuesta;
import learningPaths.Examen;
import learningPaths.LearningPath;
import learningPaths.Quiz;
import learningPaths.RecursoEducativo;
import learningPaths.Tarea;
import usuario.Estudiante;
import usuario.Sistema;

public class ConsolaEstudiantes extends ConsolaPrincipal
{
	Sistema sistema=Sistema.getInstancia();
	public void consultarProgrespLP(Estudiante e)
	{
		System.out.println("Consultando progreso de " + e.getNombre());
		double progreso=e.verProgreso();
		System.out.println("De los "+ e.getEnvios().size() + " que ha hecho el estudiante, han sido completados el "
				+ progreso + "porciento");
	}
	
	public void actividadesSugeridasLP(Actividad a)
	{
		System.out.println("Consultando actividades sugeridas ");
		List <Actividad> actividades= a.getActividadesRecomendadas();
		for (Actividad actividad: actividades)
		{
			System.out.println("Id actividad: " + actividad.getId() + " con el objetivo de:" + actividad.getObjetivo());
		}
	}
	
	public void inscribirLP(Estudiante e, LearningPath lp)
	{
		System.out.println("Revisando que el estudiante no este inscrito en el LP " + lp.getTitulo());
		if (e.inscribirseEnLearningPath(lp)==false)
		{
			System.out.println("El estudiante ya está inscrito al LearningPath"+ lp.getTitulo());
		}
		else
		{
			System.out.println("Ha sido inscrito con exito al LearningPath "+ lp.getTitulo());
		}
	}
	
	public void hacerActividad(String idActividad, Estudiante e) throws ActivdadNoEcontradaException
	{
		Actividad act=sistema.encontrarActividad(idActividad);
		String tipo=act.getTipoActividad();
		if (tipo.equals("Quiz"))
		{
			hacerQuiz(act,e);
		}
		else if (tipo.equals("Examen"))
		{
			hacerExamen(act,e);
		}
		else if (tipo.equals("Encuesta"))
		{
			hacerEncuesta(act,e);
		}
		else if (tipo.equals("Tarea"))
		{
			hacerTarea(act,e);
		}
		else if (tipo.equals("RecursoEducativo"))
		{
			hacerRecurso(act,e);
		}
		
	}
	
	public void hacerQuiz(Actividad act, Estudiante e) throws ActivdadNoEcontradaException
	{
		
		ControladorEnvios env= new ControladorEnvios();
		Quiz quiz=(Quiz) act;
		boolean recomendado= env.esBuenaIdeaHacerActividad(e,quiz);
        if(recomendado==false) 
        {
        	System.out.println("Te recomendamos hacer las actividades recomendadas antes de hacer este quiz");	
        }
		Collection<PreguntaOpcionMultiple> preguntas= quiz.getPreguntas();
		List<Integer> respuestas=new ArrayList<Integer>();
		for (PreguntaOpcionMultiple pregunta: preguntas)
		{
			pregunta.display();
			pregunta.displayOpciones();
			int respuesta =pedirEnteroAlUsuario("Tu respuesta");
            
            
            //pedir respuesta
            respuestas.add(respuesta);
		}
		
		env.hacerQuiz(e, act.getId(), respuestas);
		System.out.println("Envío realizado con exito");
		//Hacer que se califique?
		
	}
	
	public void hacerExamen(Actividad act, Estudiante e) throws ActivdadNoEcontradaException
	{
		ControladorEnvios env= new ControladorEnvios();
		Examen examen=(Examen) act;
		boolean recomendado= env.esBuenaIdeaHacerActividad(e,examen);
        if(recomendado==false) 
        {
        	System.out.println("Te recomendamos hacer las actividades recomendadas antes de hacer este examen");	
        }
		Collection<PreguntaAbierta> preguntas= examen.getPreguntas();
		List<String> respuestas=new ArrayList<String>();
		for (PreguntaAbierta pregunta: preguntas)
		{
			pregunta.display();
            String respuesta = pedirCadenaAlUsuario("Tu respuesta");
            respuestas.add(respuesta);
		}
		
		env.hacerExamen(e, act.getId(), respuestas);
		System.out.println("Envío realizado con exito");
		//Hacer que se califique?
		
	}
	
	public void hacerEncuesta(Actividad act, Estudiante e) throws ActivdadNoEcontradaException
	{
		ControladorEnvios env= new ControladorEnvios();
		Encuesta encuesta=(Encuesta) act;
		boolean recomendado= env.esBuenaIdeaHacerActividad(e,encuesta);
        if(recomendado==false) 
        {
        	System.out.println("Te recomendamos hacer las actividades recomendadas antes de hacer esta encuesta");	
        }
		Collection<PreguntaEncuesta> preguntas= encuesta.getPreguntas();
		List<Integer> respuestas=new ArrayList<Integer>();
		for (PreguntaEncuesta pregunta: preguntas)
		{
			pregunta.display();
            int respuesta = pedirEnteroAlUsuario("Ingresa un entero del 1 al 5");
            respuestas.add(respuesta);
		}
		
		env.hacerEncuesta(e, act.getId(), respuestas);
		System.out.println("Envío realizado con exito");
		
	}
	
	public void hacerTarea(Actividad act, Estudiante e) throws ActivdadNoEcontradaException
	{
		ControladorEnvios env= new ControladorEnvios();
		Tarea tarea=(Tarea) act;
		boolean recomendado= env.esBuenaIdeaHacerActividad(e,tarea);
        if(recomendado==false) 
        {
        	System.out.println("Te recomendamos hacer las actividades recomendadas antes de hacer esta tarea");	
        }
		env.hacerTarea(e, act.getId());
		System.out.println("Envío realizado con exito");
		
		
	}
	
	public void hacerRecurso(Actividad act, Estudiante e) throws ActivdadNoEcontradaException
	{
		ControladorEnvios env= new ControladorEnvios();
		RecursoEducativo recurso=(RecursoEducativo) act;
		boolean recomendado= env.esBuenaIdeaHacerActividad(e,recurso);
        if(recomendado==false) 
        {
        	System.out.println("Te recomendamos hacer las actividades recomendadas antes de hacer esta tarea");	
        }
		env.hacerRecurso(e, act.getId());
		System.out.println("Envío realizado con exito");
		
		
	}
}