package learningPaths;

import java.util.Date;

public class Video extends RecursoEducativo {
	private int duracion;

	public Video(String descripcion, String objetivo, String nombre, Date fechaInicio, Date fechaFin, int duracion,
			int dificultad, double rating, String tipoActividad, boolean obligatoria, String learningPath,
			int duracionV) {
		super(descripcion, objetivo, nombre, fechaInicio, fechaFin, duracion, dificultad, rating, tipoActividad,
				obligatoria, learningPath);
		this.duracion = duracionV;
		// TODO Auto-generated constructor stub
	}

	public int getDuracionV() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

}
