package envios;

public abstract class Pregunta {

	protected String textoPregunta;
	protected int idPregunta;

	public Pregunta(String textoPregunta, int idPregunta) {
		super();
		this.textoPregunta = textoPregunta;
		this.idPregunta = idPregunta;
	}

	public void display()

	{
		System.out.println(textoPregunta);
	}

	public String getTextoPregunta() {
		return textoPregunta;
	}

	public int getIdPregunta() {
		return idPregunta;
	}

	public void setTextoPregunta(String textoPregunta) {
		this.textoPregunta = textoPregunta;
	}

}
