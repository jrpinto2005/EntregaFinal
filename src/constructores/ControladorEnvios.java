package constructores;

import envios.*;
import exceptions.ActivdadNoEcontradaException;
import learningPaths.*;
import usuario.ControladorUsuarios;
import usuario.Estudiante;
import usuario.Profesor;
import usuario.Sistema;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class ControladorEnvios {

    private Sistema sistema;
    

    public ControladorEnvios() {
        this.sistema = Sistema.getInstancia();
    }
    
    public boolean esBuenaIdeaHacerActividad(Estudiante estudiante, Actividad actividad) {
		List<Envio> envios = estudiante.getEnvios();
		int contador = actividad.getActividadesRecomendadas().size();
		int cantidad = 0;
		for (Actividad actividades : actividad.getActividadesRecomendadas()) {
			for (Envio envio : envios) {
				Actividad act = envio.getActividad();
				if (actividades.getId().equals(act.getId()) && envio.isCompletado()) {
					cantidad += 1;
				}
			}
		}
		if (cantidad != contador) {
			return false;
		} else {
			return true;
		}

	}

    public EnvioExamen hacerExamen(Estudiante estudiante, String idExamen, List<String> respuestasUsuario) throws ActivdadNoEcontradaException {
        Examen examen = (Examen) sistema.encontrarActividad(idExamen);
        
        

        Collection<PreguntaAbierta> preguntas = examen.getPreguntas();
        List<RespuestaAbierta> respuestas = new ArrayList<>();
        
        Iterator<PreguntaAbierta> iterador = preguntas.iterator();
        for (int i = 0; i < preguntas.size(); i++) {
        	PreguntaAbierta pregunta = iterador.next();
            String respuestaTexto = respuestasUsuario.get(i);
            RespuestaAbierta respuesta = new RespuestaAbierta(0, respuestaTexto, pregunta.getValorPregunta(), pregunta);
            respuestas.add(respuesta);
        }
        
        EnvioExamen envio = new EnvioExamen(examen, estudiante.getId(), examen.getLearningPath().getTitulo(), 
                                            false, 0, examen.getPuntajeMaximo(), 0, respuestas);
        ControladorUsuarios cu= ControladorUsuarios.getInstancia();
        Profesor p=cu.encontrarProfesor(examen.getLearningPath().getIdCreador());
        p.agregarEnvio(envio);
        estudiante.getEnvios().add(envio);
        
        return envio;
    }


    public EnvioQuiz hacerQuiz(Estudiante estudiante, String idQuiz, List<Integer> respuestasIngresadas) throws ActivdadNoEcontradaException {
        Quiz quiz = (Quiz) sistema.encontrarActividad(idQuiz);
        Collection<PreguntaOpcionMultiple> preguntas = quiz.getPreguntas();
        List<RespuestaMultiple> respuestas = new ArrayList<>();
        Iterator<PreguntaOpcionMultiple> iterador = preguntas.iterator();
        for (int i = 0; i < preguntas.size(); i++) {
        	PreguntaOpcionMultiple pregunta = iterador.next();
            int respuestaSeleccionada = respuestasIngresadas.get(i);  
            RespuestaMultiple respuesta = new RespuestaMultiple(0, respuestaSeleccionada, pregunta);
            respuestas.add(respuesta);
        }
        EnvioQuiz envio = new EnvioQuiz(quiz, estudiante.getId(), quiz.getLearningPath().getTitulo(), false, 0, quiz.getPuntajeMaximo(), 0, respuestas);
        estudiante.getEnvios().add(envio);
        return envio;
    }


    public EnvioEncuesta hacerEncuesta(Estudiante estudiante, String idEncuesta, List<Integer> respuestasIngresadas) throws ActivdadNoEcontradaException {
        Encuesta encuesta = (Encuesta) sistema.encontrarActividad(idEncuesta);
        Collection<PreguntaEncuesta> preguntas = encuesta.getPreguntas();
        List<RespuestaEncuesta> respuestas = new ArrayList<>();
        Iterator<PreguntaEncuesta> iterador = preguntas.iterator();
        for (int i = 0; i < preguntas.size(); i++) {
        	PreguntaEncuesta pregunta = iterador.next();
            int respuestaSeleccionada = respuestasIngresadas.get(i); 
            RespuestaEncuesta respuesta = new RespuestaEncuesta(respuestaSeleccionada, pregunta);
            respuestas.add(respuesta);
        }
        EnvioEncuesta envio = new EnvioEncuesta(encuesta, estudiante.getId(), encuesta.getLearningPath().getTitulo(), true, 0, encuesta.getPuntajeMaximo(), 0, respuestas);
        estudiante.getEnvios().add(envio);
        return envio;
    }

    public EnvioTarea hacerTarea(Estudiante estudiante, String idTarea) throws ActivdadNoEcontradaException {
        Tarea tarea = (Tarea) sistema.encontrarActividad(idTarea);
        EnvioTarea envio = new EnvioTarea(tarea, estudiante.getId(), tarea.getLearningPath().getTitulo(), true);
        estudiante.getEnvios().add(envio);
        return envio;
    }

    public EnvioRecurso hacerRecurso(Estudiante estudiante, String idRecurso) throws ActivdadNoEcontradaException {
        RecursoEducativo recurso = (RecursoEducativo) sistema.encontrarActividad(idRecurso);
        EnvioRecurso envio = new EnvioRecurso(recurso, estudiante.getId(), recurso.getLearningPath().getTitulo(), true);
        estudiante.getEnvios().add(envio);
        return envio;
    }
}
