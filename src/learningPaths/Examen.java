package learningPaths;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import envios.Pregunta;
import envios.PreguntaAbierta;

public class Examen extends Actividad {
	private int puntajeMaximo;
	private Map<Integer, PreguntaAbierta> preguntas;

	public Examen(String descripcion, String objetivo, String nombre, Date fechaInicio, Date fechaFin, int duracion,
			int dificultad, double rating, String tipoActividad, boolean obligatoria, String learningPath,
			int puntajeMaximo) {
		super(descripcion, objetivo, nombre, fechaInicio, fechaFin, duracion, dificultad, rating, tipoActividad,
				obligatoria, learningPath);
		this.puntajeMaximo = puntajeMaximo;
		this.preguntas = new HashMap<Integer, PreguntaAbierta>();
	}

	public void setPuntajeMaximo(Integer puntajeMaximo) {
		this.puntajeMaximo = puntajeMaximo;
	}

	public Integer getPuntajeMaximo() {
		return puntajeMaximo;
	}

	public Collection<PreguntaAbierta> getPreguntas() {
		return preguntas.values();
	}

	public void addPregunta(PreguntaAbierta p) {

		preguntas.put(p.getIdPregunta(), p);
	}

	public void removePregunta(PreguntaAbierta p) {

		preguntas.remove(p.getIdPregunta(), p);
	}

}
