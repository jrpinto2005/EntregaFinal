package learningPaths;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import exceptions.ActivdadNoEcontradaException;
import learningPaths.Actividad;
import usuario.Estudiante;
import usuario.Profesor;
import usuario.Sistema;
import envios.EnvioExamen;

public class LearningPath {
	private String titulo;
	private String descripcionGeneral;
	private int nivelDificultad;
	private int duracion;
	private int rating;
	private Date fechaDuracion;
	private Date fechaModificacion;
	private  int version;
	private String idCreador;
	private String objetivos;
	private double promedioActividadesCompletadas;
	private ArrayList<Actividad> actividadesOrdenadas;
	private Sistema sistema;
	

	public LearningPath(String titulo, String descripcionGeneral, int nivelDificultad, int duracion, int rating,
			Date fechaDuracion, Date fechaModificacion, int version, String idCreador, String objetivos,
			double promedioActividadesCompletadas) {
		super();
		this.titulo = titulo;
		this.descripcionGeneral = descripcionGeneral;
		this.nivelDificultad = nivelDificultad;
		this.duracion = duracion;
		this.rating = rating;
		this.fechaDuracion = fechaDuracion;
		this.fechaModificacion = fechaModificacion;
		this.version =version;
		this.idCreador = idCreador;
		this.objetivos = objetivos;
		this.promedioActividadesCompletadas = promedioActividadesCompletadas;
		this.actividadesOrdenadas = new ArrayList<Actividad>();
		this.sistema = Sistema.getInstancia();
	}

	public String getTitulo() {
		return titulo;
	}
	

	public int getNivelDificultad() {
		return nivelDificultad;
	}

	public void setNivelDificultad(int nivelDificultad) {
		this.nivelDificultad = nivelDificultad;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcionGeneral() {
		return descripcionGeneral;
	}

	public void setDescripcionGeneral(String descripcionGeneral) {
		this.descripcionGeneral = descripcionGeneral;
	}

	public int getNivelDificutad() {
		return nivelDificultad;
	}

	public void setNivelDificutad(int nivelDificutad) {
		this.nivelDificultad = nivelDificutad;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		int suma=0;
		for (Actividad act: this.getActividadesOrdenadas())
		{
			suma+=act.getDuracion();
		}
		this.duracion=suma;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Date getFechaDuracion() {
		return fechaDuracion;
	}

	public void setFechaDuracion(Date fechaDuracion) {
		this.fechaDuracion = fechaDuracion;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getIdCreador() {
		return idCreador;
	}

	public void setIdCreador(String idCreador) {
		this.idCreador = idCreador;
	}

	public String getObjetivos() {
		return objetivos;
	}

	public void setObjetivos(String objetivos) {
		this.objetivos = objetivos;
	}

	public double getPromedioActividadesCompletadas() {
		return promedioActividadesCompletadas;
	}

	public void setPromedioActividadesCompletadas(double promedioActividadesCompletadas) {
		this.promedioActividadesCompletadas = promedioActividadesCompletadas;
	}

	public ArrayList<Actividad> getActividadesOrdenadas() {
		return actividadesOrdenadas;
	}

	public void setActividadesOrdenadas(ArrayList<Actividad> actividadesOrdenadas) {
		this.actividadesOrdenadas = actividadesOrdenadas;
	}

	public boolean esElDueño(String idProfesor) {
		if (idProfesor.equals(this.idCreador)) {
			return true;
		}
		return false;

	}

	public void agregarActividad(Actividad actividad) {

		this.actividadesOrdenadas.add(actividad);
		setDuracion(0);
		version+=1;

	}


}
