package envios;

public class CalificadorRespuestaMultiple {

	public int esCorrecta(RespuestaMultiple respuesta) {
		for (Opcion opcion : respuesta.getPregunta().getOpciones()) {
			if (respuesta.getIndice() == opcion.getIndice()) {
				return 1;
			}
		}
		return 0;
	}
}
