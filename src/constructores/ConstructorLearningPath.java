package constructores;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import envios.Opcion;
import envios.PreguntaAbierta;
import envios.PreguntaOpcionMultiple;
import exceptions.ActivdadNoEcontradaException;
import exceptions.ProfesorNoCreadorException;
import learningPaths.Actividad;
import learningPaths.Examen;
import learningPaths.LearningPath;
import learningPaths.Quiz;
import learningPaths.RecursoEducativo;
import learningPaths.Tarea;
import usuario.Sistema;

public class ConstructorLearningPath {
	private Sistema sistema;
	

	public ConstructorLearningPath() {
		super();
		this.sistema = Sistema.getInstancia();
	}

	public LearningPath crearLP(String titulo, String descripcionGeneral, int nivelDificultad, int duracion, int rating,
			Date fechaDuracion, Date fechaModificacion, int version, String idCreador, String objetivos,
			double promedioActividadesCompletadas) {
		LearningPath LP = new LearningPath(titulo, descripcionGeneral, nivelDificultad, duracion, rating, fechaDuracion,
				fechaModificacion, version, idCreador, objetivos, promedioActividadesCompletadas);
		sistema.addLP(LP);
		return LP;
	}

	public void editarLP(LearningPath lp, String atributo, Object valorNuevo, String idProfesor) throws ProfesorNoCreadorException 
	{
		if (lp.esElDueño(idProfesor))
		{
	    try {
	        String setter = "set" + atributo.substring(0, 1).toUpperCase() + atributo.substring(1);
	        Class<?> valorClase = valorNuevo.getClass();
	        Method metodoSetter = lp.getClass().getMethod(setter, valorClase);
	        sistema.getLearningPaths().remove(lp.getTitulo());
	        metodoSetter.invoke(lp, valorNuevo);
	        sistema.addLP(lp);
	        
	    } 
	    catch (NoSuchMethodException e) {
	        System.out.println("Error: El método no existe. Revisa el nombre del atributo.");
	        e.printStackTrace();
	    } catch (SecurityException e) {
	        System.out.println("Error de seguridad al acceder al método.");
	        e.printStackTrace();
	    } catch (IllegalAccessException e) {
	        System.out.println("Error: No se tiene acceso al método.");
	        e.printStackTrace();
	    } catch (InvocationTargetException e) {
	        System.out.println("Error: Fallo al invocar el método.");
	        e.printStackTrace();
	    }
		}
		else 
		{
			throw new ProfesorNoCreadorException();
		}
	}
	
	
	public Actividad clonar(Actividad act) {
		Actividad copia;
		String id = act.getId();
		String descripcion = act.getDescripcion();
		String objetivo = act.getObjetivo();
		String nombre = act.getNombre();
		Date fechaInicio = act.getFechaInicio();
		Date fechaFin = act.getFechaFin();
		Integer duracion = act.getDuracion();
		Integer dificultad = act.getDificultad();
		Double rating = 0.0;
		String tipoActividad = act.getTipoActividad();
		boolean obligatoria = act.isObligatoria();
		String idLearningPath = act.getLearningPath().getTitulo();

		if (tipoActividad.equals("Quiz")) {
			Quiz quiz = (Quiz) act;
			Collection<PreguntaOpcionMultiple> preguntas = quiz.getPreguntas();
			Quiz quizClonado = new Quiz(descripcion, objetivo, id, fechaInicio, fechaFin, duracion, dificultad, rating,
					tipoActividad, obligatoria, idLearningPath, quiz.getPuntajeMaximo());
			for (PreguntaOpcionMultiple pregunta : preguntas) {
				PreguntaOpcionMultiple p = clonarPregunta(pregunta);
				quizClonado.addPregunta(p);
			}
			return quizClonado;
		}
	    else if(tipoActividad.equals("Tarea")) {
	    	Tarea tarea=(Tarea)act;
	    	Tarea tareaClonado = new Tarea(descripcion, objetivo, id, fechaInicio, fechaFin, duracion, dificultad, rating,
					tipoActividad, obligatoria, idLearningPath);
	    	tareaClonado.setContenido(tarea.getContenido());
	    	return tareaClonado;
	    }
	    else if(tipoActividad.equals("Examen")) {
	    	Examen examen=(Examen)act;
	    	Collection<PreguntaAbierta> preguntas = examen.getPreguntas();
	    	Examen examenClonado = new Examen(descripcion, objetivo, id, fechaInicio, fechaFin, duracion, dificultad, rating,
					tipoActividad, obligatoria, idLearningPath, examen.getPuntajeMaximo());
	    	for (PreguntaAbierta pregunta : preguntas) {
				PreguntaAbierta p = clonarPregunta(pregunta);
				examenClonado.addPregunta(p);
			}
			return examenClonado;
	    	
	    	
	    }
		else if(tipoActividad.equals("RecursoEducativo")) {
			RecursoEducativo recurso=(RecursoEducativo)act;
	    	RecursoEducativo recursoClonado = new RecursoEducativo(descripcion, objetivo, id, fechaInicio, fechaFin, duracion, dificultad, rating,
					tipoActividad, obligatoria, idLearningPath);
	    	recursoClonado.setContenido(recurso.getContenido());
	    	return recursoClonado;
			    	
			    }
		
		return null;
	}
	
	public static PreguntaAbierta clonarPregunta(PreguntaAbierta pregunta) {
        if (pregunta == null) {
            throw new IllegalArgumentException("La pregunta no puede ser null.");
        }
        return new PreguntaAbierta(pregunta.getTextoPregunta(), pregunta.getIdPregunta(), pregunta.getValorPregunta());
    }
	
	public static PreguntaOpcionMultiple clonarPregunta(PreguntaOpcionMultiple pregunta) {
        if (pregunta == null) {
            throw new IllegalArgumentException("La pregunta no puede ser null.");
        }

         
        PreguntaOpcionMultiple clon = new PreguntaOpcionMultiple(
            pregunta.getTextoPregunta(),
            pregunta.getIdPregunta()
        );

        
        for (Opcion opcion : pregunta.getOpciones()) {
            clon.agregarOpcion(new Opcion(opcion.getDescripcion(), opcion.esCorrecto(),opcion.getIndice()));
        }

        return clon;
    }
	
	public void clonarActividad(LearningPath origen, LearningPath destino, String id) throws ActivdadNoEcontradaException {
	    Actividad actividadOriginal = null;

	    for (Actividad elemento : origen.getActividadesOrdenadas()) {
	        if (elemento.getId().equals(id)) {
	            actividadOriginal = elemento;
	            break;
	        }
	    }

	    if (actividadOriginal == null) {
	        throw new ActivdadNoEcontradaException(id);
	    } else {
	        Actividad actividadClonada = clonar(actividadOriginal);
	        actividadClonada.setLearningPath(destino);
	        actividadClonada.setId(destino.getTitulo() + "." + actividadOriginal.getId());
	        destino.agregarActividad(actividadClonada);
	        sistema.addActividad(actividadClonada);
	    }
	}

}
