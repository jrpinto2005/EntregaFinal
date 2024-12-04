package learningPaths;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import envios.PreguntaAbierta;
import envios.PreguntaOpcionMultiple;

public class Quiz extends Actividad {
	private int puntajeMaximo;
	private Map<Integer, PreguntaOpcionMultiple> preguntas;

	public Quiz(String descripcion, String objetivo, String id, Date fechaInicio, Date fechaFin, int duracion,
			int dificultad, double rating, String tipoActividad, boolean obligatoria, String idlearningPath,
			int puntajeMaximo) {
		super(descripcion, objetivo, id, fechaInicio, fechaFin, duracion, dificultad, rating, tipoActividad,
				obligatoria, idlearningPath);
		this.puntajeMaximo = puntajeMaximo;
		this.preguntas = new HashMap<Integer, PreguntaOpcionMultiple>();
		// TODO Auto-generated constructor stub
	}

	public void setPuntajeMaximo(Integer puntajeMaximo) {
		this.puntajeMaximo = puntajeMaximo;
	}

	public Integer getPuntajeMaximo() {
		return puntajeMaximo;
	}

	public Collection<PreguntaOpcionMultiple> getPreguntas() {
		return preguntas.values();
	}

	public void addPregunta(PreguntaOpcionMultiple p) {

		preguntas.put(p.getIdPregunta(), p);
	}

	public void removePregunta(PreguntaOpcionMultiple p) {

		preguntas.remove(p.getIdPregunta(), p);
	}

}
