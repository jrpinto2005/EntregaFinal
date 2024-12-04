package envios;

import java.util.ArrayList;
import java.util.List;

public class PreguntaOpcionMultiple extends Pregunta {

	public List<Opcion> opciones;

	public PreguntaOpcionMultiple(String textoPregunta, int idPregunta) {
		super(textoPregunta, idPregunta);
		opciones = new ArrayList<Opcion>();
		// TODO Auto-generated constructor stub
	}

	public void displayOpciones() {
		for (Opcion elemento : opciones) {
			System.out.println("Opcion" + elemento.getIndice() + "----- " + elemento.getDescripcion());
		}
	}

	public List<Opcion> getOpciones() {
		return opciones;
	}

	public void agregarOpcion(Opcion opcion) {

		this.opciones.add(opcion);
	}

}
