package envios;

import java.util.Date;

public class Reseña {
	private String idReseña;
	private String comentario;
	private Integer rating; // Puntuación del 1 al 5
	private Date fecha;
	private String idActividad;

	public Reseña(String idReseña, String comentario, int rating, Date fecha, String idActividad) {
		this.idReseña = idReseña;
		this.comentario = comentario;
		this.rating = rating;
		this.fecha = fecha;
	}

	public String getIdReseña() {
		return idReseña;
	}

	public void setIdReseña(String idReseña) {
		this.idReseña = idReseña;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getIdActividad() {
		return idActividad;
	}

	public void setIdActividad(String idActidad) {
		this.idActividad = idActidad;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String display() {
		return "Reseña: " + comentario + ", Rating: " + rating + ", Fecha: " + fecha;
	}
}
