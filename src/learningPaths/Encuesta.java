package learningPaths;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import envios.PreguntaEncuesta;
import envios.PreguntaOpcionMultiple;

public class Encuesta extends Actividad {
	private int puntajeMaximo;
	private Map<Integer, PreguntaEncuesta> preguntas;

	public Encuesta(String descripcion, String objetivo, String nombre, Date fechaInicio, Date fechaFin, int duracion,
			int dificultad, double rating, String tipoActividad, boolean obligatoria, String learningPath,
			int puntajeMaximo) {
		super(descripcion, objetivo, nombre, fechaInicio, fechaFin, duracion, dificultad, rating, tipoActividad,
				obligatoria, learningPath);
		this.puntajeMaximo = puntajeMaximo;
		this.preguntas = new HashMap<Integer, PreguntaEncuesta>();
		 
	}

	public void setPuntajeMaximo(Integer puntajeMaximo) {
		this.puntajeMaximo = puntajeMaximo;
	}

	public Integer getPuntajeMaximo() {
		return puntajeMaximo;
	}

	public Collection<PreguntaEncuesta> getPreguntas() {
		return preguntas.values();
	}

	public void addPregunta(PreguntaEncuesta p) {

		preguntas.put(p.getIdPregunta(), p);
	}

	public void removePregunta(PreguntaEncuesta p) {

		preguntas.remove(p.getIdPregunta(), p);
	}

	public void setPreguntas(Map<Integer, PreguntaEncuesta> preguntas) {
		this.preguntas = preguntas;
	}
}
